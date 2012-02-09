/*
   Copyright 2012 Travis Porter

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.sparkhunter.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.sparkhunter.main.R;

public class FbfriendsActivity extends Activity {
	
	public static final String APP_ID = "263739193698958";
	
	
    public static final int LOGIN = R.id.login;
    public static final int OUTPUT = R.id.output;
    public static final int FRIEND = R.id.getfriends;
    public static final int FRIENDLIST = R.id.friendlist;
    
    public static TextView output;
    
    
    private Activity mActivity;
    private Handler mHandler = new Handler();
    private AsyncFacebookRunner mAsync;
    private Facebook facebook;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	mActivity = this;
    	
    	//initialize screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);
        output = (TextView) findViewById(OUTPUT);
        output.setText("You should login.");
        
        //initialize facebook
        facebook = new Facebook(APP_ID);
        mAsync = new AsyncFacebookRunner(facebook);
        
        //Setup button listeners
        Button button = (Button)findViewById(LOGIN);
        button.setOnClickListener(mLoginListener);
        button = (Button)findViewById(FRIEND);
        button.setOnClickListener(mFriendListener);
    }
    
    private class LoginListener implements DialogListener{

        @Override
        public void onComplete(Bundle values) {
        	output.setText("Great Success.");
        }

        @Override
        public void onFacebookError(FacebookError error) {
        	output.setText(error.getMessage());
        	}

        @Override
        public void onError(DialogError e) {
        	output.setText(e.getMessage());
        	}

        @Override
        public void onCancel() {
        	output.setText("Canceled");
        	}
    	
    }
    private class LogoutListener implements RequestListener{

		@Override
		public void onComplete(String response, Object state) {
			mHandler.post(new Runnable() {
				public void run(){
					output.setText("Logged out");
					TextView list = (TextView) findViewById(FRIENDLIST);
					list.setText("");
				}
			}
			);
			
		}

		@Override
		public void onIOException(IOException e, Object state) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onFileNotFoundException(FileNotFoundException e,
				Object state) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onMalformedURLException(MalformedURLException e,
				Object state) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onFacebookError(FacebookError e, Object state) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    public class FriendListener implements RequestListener {

		@Override
		public void onComplete(final String response, final Object state) {
			try {
				//parse the friend data
				JSONObject json = Util.parseJson(response);
				final JSONArray jsonFriends = json.getJSONArray("data");
				FbfriendsActivity.this.runOnUiThread(new Runnable(){
					public void run(){
						try {
							
							int i = 0;
							while(!jsonFriends.isNull(i))
							{
								//print out friend data
								TextView list = (TextView) findViewById(FRIENDLIST);
								list.setMovementMethod(new ScrollingMovementMethod());
								list.append(jsonFriends.getString(i)+"\n");
								i++;
							}
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FacebookError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		public void onIOException(IOException e, Object state) {
			output.setText(e.getMessage());
			
		}
		@Override
		public void onFileNotFoundException(FileNotFoundException e,
				Object state) {
			output.setText(e.getMessage());
			
		}
		@Override
		public void onMalformedURLException(MalformedURLException e,
				Object state) {
			output.setText(e.getMessage());
			
		}
		@Override
		public void onFacebookError(FacebookError e, Object state) {
			output.setText(e.getMessage());
			
		}
    	
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        facebook.authorizeCallback(requestCode, resultCode, data);
    }

    
    private OnClickListener mLoginListener = new OnClickListener() {
    	@Override
    	public void onClick(View item){
    		//toggle logout/login depending on current state
    		if (facebook.isSessionValid()){
    			output.setText("Logging out...");
    			AsyncFacebookRunner tempRunner = new AsyncFacebookRunner(facebook);
    			tempRunner.logout(getBaseContext(),new LogoutListener());
    		}
    		else{
    			output.setText("Logging in...");
    			facebook.authorize(mActivity,new LoginListener());
    		}
    	}

    };
    private OnClickListener mFriendListener = new OnClickListener() {
    	@Override
    	public void onClick(View item){
    		//request friend list from facebook
    		if (facebook.isSessionValid()){
    			 mAsync.request("me/friends",new FriendListener());
    			 output.setText("Fetching datas...");
    		}
    	}

    };
}
    