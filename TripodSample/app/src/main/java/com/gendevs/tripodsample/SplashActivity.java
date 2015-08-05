package com.gendevs.tripodsample;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class SplashActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setUpScreen();
		new CountDownTimer(4000, 100) {
			@Override
			public void onFinish() {
				goToNextScreen();
			}
			@Override
			public void onTick(long millisUntilFinished) {
			}
		}.start();
	}


	/**

	/**
	 * TO setup layout and the screen settings 
	 */
	private void setUpScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash_screen);
	}

	/**
	 * TO redirect next screen
	 */
	public void goToNextScreen() {
		
		startActivity(new Intent(getApplicationContext(), MainActivity.class));
		finish();
	}


	/**
	 * It will close the app if the network is not available
	 */
	public void closeApp() {
		Toast.makeText(getApplicationContext(), "No internet connection.  Shutting down.",
				Toast.LENGTH_SHORT).show();
		new CountDownTimer(3000, 1000) {
			@Override
			public void onFinish() {
				finish();
			}

			@Override
			public void onTick(long millisUntilFinished) {
			}
		}.start();
	}
	

}
