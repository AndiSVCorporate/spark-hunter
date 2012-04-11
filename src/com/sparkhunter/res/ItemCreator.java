package com.sparkhunter.res;

public class ItemCreator implements EntityCreator {
	private static final String type = "ITEM";

	@Override
	public Entity createEntity() {
		return new Item();
	}

	@Override
	public String getType() {
		return type;
	}

}
