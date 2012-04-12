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

package com.sparkhunter.activities;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.sparkhunter.main.R;
import com.sparkhunter.main.R.id;
import com.sparkhunter.main.R.layout;
import com.sparkhunter.network.PHPTask;
import com.sparkhunter.network.ServerInterface;
import com.sparkhunter.res.FacebookUtils;
import com.sparkhunter.res.Player;


public class FbfriendsActivity extends Activity {

    private HashMap<String, String> mFriends;
    private Activity mActivity;
    private Handler mHandler = new Handler();
    TextView mList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	mActivity = this;
    	//initialize screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);
        mList = (TextView) findViewById(R.id.friendlist);
        mFriends = new HashMap();
        
        (new AddUserTask()).execute(Player.getInstance().playerID);
        //get friend list
		FacebookUtils.getFriends(new FriendListener());
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
								//ListView list = (ListView) findViewById(R.id.friendlist);
								//ImageView img = (ImageView) findViewById(R.id.imageView1);
								mList.setMovementMethod(new ScrollingMovementMethod());
								String[] tokens = jsonFriends.getString(i).split("\"");
								//list.append(tokens[7]+"\n");
									mFriends.put(tokens[3], tokens[7]);
									(new FindUserTask()).execute(tokens[3]);
								//if(i==1)
								//	img.setImageBitmap(getBitmap("https://graph.facebook.com/"+ tokens[3] +"/picture"));
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FacebookUtils.authorizeCallback(requestCode, resultCode, data);
    }
	public class FindUserTask extends PHPTask {
		@Override
		protected String ServerCommand(String[] args) {
			return ServerInterface.findUser(args[0]);
		}

        @Override
        protected void PostExecute(){
        	if(mResponses.length>0){
        		String temp = mFriends.get(mResponses[0]);
        		if(temp!=null)
        			mList.append(temp);
        	}
        }
	}
    private class AddUserTask extends PHPTask {
    
		protected String ServerCommand(String[] args){
			return ServerInterface.addUser(args[0]);
		}
    	
    }

//copy pasted from Hackbook (Facebook's example app)
//not used yet
public Bitmap getBitmap(String url) {
	AndroidHttpClient httpclient = null;
    Bitmap bm = null;
    try {
        URL aURL = new URL(url);
        URLConnection conn = aURL.openConnection();
        conn.connect();
        InputStream is = conn.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        bm = BitmapFactory.decodeStream(new FlushedInputStream(is));
        bis.close();
        is.close();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (httpclient != null) {
            httpclient.close();
        }
    }
    return bm;
}

class FlushedInputStream extends FilterInputStream {
    public FlushedInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override
    public long skip(long n) throws IOException {
        long totalBytesSkipped = 0L;
        while (totalBytesSkipped < n) {
            long bytesSkipped = in.skip(n - totalBytesSkipped);
            if (bytesSkipped == 0L) {
                int b = read();
                if (b < 0) {
                    break; // we reached EOF
                } else {
                    bytesSkipped = 1; // we read one byte
                }
            }
            totalBytesSkipped += bytesSkipped;
        }
        return totalBytesSkipped;
    }
}
}






    