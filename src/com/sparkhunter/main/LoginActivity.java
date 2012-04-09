package com.sparkhunter.main;

import com.facebook.android.DialogError;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;
import com.sparkhunter.res.FacebookUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends Activity{
	Activity mActivity;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		//FacebookUtils fBUtil;
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mActivity = this;
        Button loginB = (Button)findViewById(R.id.loginButton);
        loginB.setOnClickListener(mLoginButtonListener);
        loginB = (Button)findViewById(R.id.exit);
        loginB.setOnClickListener(mExitButtonListener);
        
	}
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FacebookUtils.authorizeCallback(requestCode, resultCode, data);
    }
	
    private OnClickListener mLoginButtonListener = new OnClickListener() {
    	@Override
    	public void onClick(View item) {
    		if(FacebookUtils.isSessionValid()) {
    			Intent i = new Intent(mActivity, SparkHunterTitleScreen.class);
    			startActivity(i);
    		}
    		else {
    			FacebookUtils.login(mActivity, new LoginListener());
    		}
    	}

    };
    private OnClickListener mExitButtonListener = new OnClickListener() {
    	@Override
    	public void onClick(View item){
    		mActivity.finish();
    	}

    };
    private class LoginListener implements DialogListener{

        @Override
        public void onComplete(Bundle values) {
        	mActivity.runOnUiThread(new Runnable(){
				@Override
				public void run() {
		        	Toast.makeText(mActivity, "login success", Toast.LENGTH_SHORT).show();
					Intent i = new Intent(mActivity, SparkHunterTitleScreen.class);
					startActivity(i);
				}
        		
        	});
        }

        @Override
        public void onFacebookError(FacebookError error) {
        	Toast.makeText(mActivity, "error", Toast.LENGTH_SHORT).show();
        }
        
        @Override
        public void onError(DialogError e) {
        	Toast.makeText(mActivity, "error", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
        	Toast.makeText(mActivity, "cancel", Toast.LENGTH_SHORT).show();
        }
    }
}
