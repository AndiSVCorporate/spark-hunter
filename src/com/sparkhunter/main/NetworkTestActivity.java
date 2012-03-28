package com.sparkhunter.main;

import java.io.UnsupportedEncodingException;

import com.sparkhunter.network.ServerInterface;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NetworkTestActivity extends Activity {
	Activity mActivity;
	TextView mOut;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debug);
		mActivity = this;
		mOut = (TextView) findViewById(R.id.debugOut);
		(new GetAnimalSoundTask()).execute("test");
        (new GetDataTask()).execute((Object)null);
	}
    @SuppressWarnings("unchecked")
    private class GetDataTask extends AsyncTask {

            /**
             * Let's make the http request and return the result as a String.
             */
            protected String doInBackground(Object... args) {                       
                    try {
						return ServerInterface.getAnimalList();
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
                    // check to make sure we're dealing with a string
                    if(objResult != null && objResult instanceof String) {                          
                            String result = (String) objResult;
                            mOut.append(result);
                    }
            }

    }
    /**
     * Used to spawn a thread to retrieve the animal sound.
     */
    @SuppressWarnings("unchecked")
    private class GetAnimalSoundTask extends AsyncTask {

            /**
             * Let's make the http request and return the result as a String.
             */
            protected String doInBackground(Object... args) {
                    if(args != null && args[0] instanceof String) {
                            String animal = (String) args[0];
                            try {
								return ServerInterface.getAnimalSound(animal);
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
