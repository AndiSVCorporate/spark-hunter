package com.sparkhunter.main;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.Facebook.DialogListener;

public class FacebookUtils{
	private static final String APP_ID = "263739193698958";
	
	private static FacebookUtils instance = new FacebookUtils();
	
	private static Facebook mFacebook;
	private static AsyncFacebookRunner mAsync;
	private Handler mHandler;

	
	private FacebookUtils(){
        mFacebook = new Facebook(APP_ID);
        mAsync = new AsyncFacebookRunner(mFacebook);
        mHandler = new Handler();
	}

	public static FacebookUtils get(){
		return instance;
	}
	
	public static void login(Activity a, DialogListener listen){
		if(!isSessionValid()){
			mFacebook.authorize(a,listen);
		}
	}
	
	public static void logout(Activity a, RequestListener listen){
		if(isSessionValid()){
			mAsync.logout(a.getBaseContext(),listen);
		}
	}
	
	public static boolean isSessionValid(){
		return mFacebook.isSessionValid();
	}
	
	public static void authorizeCallback(int requestCode, int resultCode, Intent data){
		mFacebook.authorizeCallback(requestCode, resultCode, data);
	}
	
	public static void getFriends(RequestListener listen){
		mAsync.request("me/friends",listen);
	}
	
}
