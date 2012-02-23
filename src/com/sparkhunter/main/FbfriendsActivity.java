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

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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

import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.sparkhunter.main.R;


public class FbfriendsActivity extends Activity {

    
    private Activity mActivity;
    private Handler mHandler = new Handler();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	mActivity = this;
    	//initialize screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends);
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
								//print out friend data
								TextView list = (TextView) findViewById(R.id.friendlist);
								//ListView list = (ListView) findViewById(R.id.friendlist);
								//ImageView img = (ImageView) findViewById(R.id.imageView1);
								list.setMovementMethod(new ScrollingMovementMethod());
								String[] tokens = jsonFriends.getString(i).split("\"");
								list.append(tokens[7]+"\n");
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






    