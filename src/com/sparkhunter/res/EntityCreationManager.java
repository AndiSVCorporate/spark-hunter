package com.sparkhunter.res;

import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.util.Log;

public class EntityCreationManager {
	//singleton, since only one ever needs to exist
	private static EntityCreationManager instance = new EntityCreationManager();
	private Map<String, EntityCreator> creators = new HashMap<String, EntityCreator>();
	
	//fields to parse from database
	//TODO find a better way to do this
	//TODO trim player_data down to reference most of this from game_data
	private static final String _ID = "_id";
	private static final String E_ID = "e_id";
	private static final String TYPE = "type";
	private static final String NAME = "name";
	private static final String LEVEL = "level";
	private static final String EXP = "exp";
	private static final String MAX_HP = "max_hp";
	private static final String CUR_HP = "cur_hp";
	private static final String SPEED = "speed";
	private static final String ATTACK = "attack";
	private static final String DEFENSE = "defense";
	private static final String HP_GAIN = "hp_gain";
	private static final String SPEED_GAIN = "speed_gain";
	private static final String ATTACK_GAIN = "attack_gain";
	private static final String DEFENSE_GAIN = "defense_gain";
	private static final String EFFECT = "effect";
	private static final String QUANTITY = "quantity";
	private static final String IMAGE_RES = "image_res";
	private static final String SOUND_RES = "sound_res";
	
	private EntityCreationManager(){
		this.addEntityType(new ItemCreator());
		this.addEntityType(new SparkCreator());
	}
	
	public static EntityCreationManager getInstance(){
		return instance;
	}
	
	public void addEntityType(EntityCreator e){
		creators.put(e.getType(), e);
	}
	
	public void deleteEntityType(EntityCreator e){
		creators.remove(e.getType());
	}
	
	//creates an Entity with no set parameters (other than type)
	public Vector<Entity> createEntity(String creatorType) throws TypeNotPresentException{
		EntityCreator creator = creators.get(creatorType);
		Entity newEntity;
		Vector<Entity> entities = new Vector<Entity>();
		
		//check to make sure the type was actually found
		//flip the fuck out if not
		if(creator == null)
			throw new TypeNotPresentException(creatorType, new Exception());
		
		newEntity = creator.createEntity();
		newEntity.setType(creatorType);
		
		entities.add(newEntity);
		
		return entities;
	}
	
	//heavy lifter, creates an entity from a query-derived Cursor
	//NOTE: Assumes Cursor is in the proper location!
	public Vector<Entity> createEntity(Cursor parameters, Context c){
		EntityCreator creator = null;
		Entity newEntity = null;
		String typeParam;
		Vector<Entity> entities = new Vector<Entity>();
		int quantity;
		
		try{
			//parse out type
			typeParam = parameters.getString(parameters.getColumnIndexOrThrow(TYPE));
			creator = creators.get(typeParam);
			
			if(creator == null)
				throw new TypeNotPresentException(typeParam, new Exception());
			
			newEntity = creator.createEntity();
			newEntity.setType(typeParam);
			
			//parse and populate other fields
			//TODO split this apart with a parseField method
			newEntity.setId(parameters.getInt(parameters.getColumnIndex(E_ID)));
			newEntity.setType(parameters.getString(parameters.getColumnIndex(TYPE)));
			newEntity.setName(parameters.getString(parameters.getColumnIndex(NAME)));
			newEntity.setLevel(parameters.getInt(parameters.getColumnIndex(LEVEL)));
			newEntity.setExperience(parameters.getInt(parameters.getColumnIndex(EXP)));
			newEntity.setMaxHp(parameters.getInt(parameters.getColumnIndex(MAX_HP)));
			newEntity.setCurHp(parameters.getInt(parameters.getColumnIndex(CUR_HP)));
			newEntity.setSpeed(parameters.getInt(parameters.getColumnIndex(SPEED)));
			newEntity.setAttack(parameters.getInt(parameters.getColumnIndex(ATTACK)));
			newEntity.setDefense(parameters.getInt(parameters.getColumnIndex(DEFENSE)));
			newEntity.setHpGain(parameters.getInt(parameters.getColumnIndex(HP_GAIN)));
			newEntity.setSpeedGain(parameters.getInt(parameters.getColumnIndex(SPEED_GAIN)));
			newEntity.setAttackGain(parameters.getInt(parameters.getColumnIndex(ATTACK_GAIN)));
			newEntity.setDefenseGain(parameters.getInt(parameters.getColumnIndex(DEFENSE_GAIN)));
			newEntity.setEffect(parameters.getString(parameters.getColumnIndex(EFFECT)));
			
			//set and resolve resources tied to this Entity
			try{
				newEntity.setImageResource(parameters.getString(parameters.getColumnIndex(IMAGE_RES)), c);
				newEntity.setSoundResource(parameters.getString(parameters.getColumnIndex(SOUND_RES)), c);
			}
			catch (Resources.NotFoundException e){
				Log.d("DEBUG", "ERROR: could not find resource!" + e);
			}
			
			//have this return an array of newEntity objects based on quantity field
			quantity = parameters.getInt(parameters.getColumnIndex(QUANTITY));
			
			for(int i = 0; i < quantity; i++){
				//something tells me this won't make actual copies...
				entities.add(newEntity);
			}
		}
		catch (IllegalArgumentException e){
			Log.d("DEBUG", "Error: Column not found in database.");
		}
		
		return entities;
	}
}
