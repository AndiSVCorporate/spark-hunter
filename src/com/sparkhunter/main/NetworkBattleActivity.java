package com.sparkhunter.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.StringTokenizer;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.sparkhunter.main.FbfriendsActivity.FriendListener;
import com.sparkhunter.network.ServerInterface;
import com.sparkhunter.res.FacebookUtils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NetworkBattleActivity extends Activity {
	public Activity mActivity;
    public String mName = null;//TODO: Put this in an initialize method on login and store it
    public String mID = null;
    public ListView mBattleList;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battlelist);
		mActivity = this;
		mBattleList = (ListView) findViewById(R.id.battleListList);
        
        Button b = (Button)findViewById(R.id.battleListStartMatch);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				(new AddBattleTask()).execute(mID,mName);
				Dialog d = new Waiting(mActivity);
				d.show();
			}
		});
        b = (Button)findViewById(R.id.battleListGetMatch);
        b.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
		        (new GetListTask()).execute((Object)null);
			}
		});
        
        if (FacebookUtils.isSessionValid()) {
        	FacebookUtils.getMe(new FBListener());
        }
        
	}
	public class FBListener implements RequestListener {

		@Override
		public void onComplete(String response, Object state) {
			try {
				JSONObject json = Util.parseJson(response);
				mName=json.getString("name");
				mID=json.getString("id");
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (FacebookError e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onIOException(IOException e, Object state) {
		}

		@Override
		public void onFileNotFoundException(FileNotFoundException e,
				Object state) {
		}

		@Override
		public void onMalformedURLException(MalformedURLException e,
				Object state) {
		}

		@Override
		public void onFacebookError(FacebookError e, Object state) {
		}
		
	}
    @SuppressWarnings("unchecked")
    private class GetListTask extends AsyncTask {

            /**
             * Let's make the http request and return the result as a String.
             */
            protected String doInBackground(Object... args) {                       
                    try {
						return ServerInterface.getList();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    return null;
            }

            /**
             * Parse the String result, and create a new array adapter for the list
             * view.
             */
            protected void onPostExecute(Object objResult) {
                	if(objResult != null && objResult instanceof String) {
                		String result = (String) objResult;
                    	String[] responseList;
                    	StringTokenizer tk = new StringTokenizer(result, ",");
                    	responseList = new String[tk.countTokens()];
         
                    	// let's build the string array
                    	int i = 0;
                    	while(tk.hasMoreTokens()) {
                    		responseList[i++] = tk.nextToken();
                    	}
                      	ArrayAdapter<String> newAdapter = new ArrayAdapter<String>(mActivity,android.R.layout.simple_list_item_1, responseList);
                    	mBattleList.setAdapter(newAdapter);
                	}

            }
    }
    private class BattleClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int pos,
				long id) {
			//When clicked, try to join the battle
			
			
		}
    	
    }
    //Use to spawn thread
    @SuppressWarnings("unchecked")
    private class AddBattleTask extends AsyncTask {

            /**
             * Let's make the http request and return the result as a String.
             */
            protected String doInBackground(Object... args) {
                    if(args != null && args[0] instanceof String) {
                            String id = (String) args[0];
                            String desc = (String) args[1];
                            try {
								return ServerInterface.addBattle(id,desc);
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    }
					return null;
            }

            /**
             * Display the result as a Toast.
             */
            protected void onPostExecute(Object objResult) {
                    // check to make sure we're dealing with a string
                    if(objResult != null && objResult instanceof String) {                          
                            String result = (String) objResult;
                            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    }
            }

    }
}
