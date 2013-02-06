package com.eventmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EventCreateActivity extends Activity{

	private Button logout_button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventcreatepage);
		
		this.logout_button = (Button) findViewById(R.id.button_logout);
		this.logout_button.setOnClickListener(mLogoutListener);
	}
	
	
	private OnClickListener mLogoutListener = new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			setContentView(R.layout.loginpage);
		}
	};
}
