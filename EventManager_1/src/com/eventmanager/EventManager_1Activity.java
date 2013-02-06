package com.eventmanager;

import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.support.Helper;

public class EventManager_1Activity extends Activity 
{
	
	
	private EditText usernameEt;
	private EditText passwordEt;
	private Button loginButton;
	private Button registerButton;
	private String credentials;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        
        usernameEt = (EditText) findViewById(R.id.text_username);
        passwordEt = (EditText) findViewById(R.id.text_password);
        
        this.loginButton = (Button) findViewById(R.id.button_login);
        this.loginButton.setOnClickListener(mLoginListener);
        
        this.registerButton = (Button) findViewById(R.id.button_register);
        //this.registerButton.setOnClickListener(mRegisterListener);
    }
    
    private OnClickListener mLoginListener = new OnClickListener() 
    {
		
    	//@Override
		public void onClick(View v) 
		{

			final String username = usernameEt.getText().toString();
			final String password = passwordEt.getText().toString();
			
			String homepageXml = new ServerConnector().login(username,password);
			
			if(homepageXml.equals("invalid")){
				Context context = getApplicationContext();
				CharSequence text = "Incorrect Credentials. Enter correct username or password.";
				int duration = Toast.LENGTH_LONG;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				
			} else {

				Helper helper = new Helper();
				Map<String, ArrayList<String>> map = helper.parseHomePageXml(homepageXml);
				ArrayList<String> groupList = map.get("groups");
				ArrayList<String> eventList = map.get("events");

				Intent homepageIntent = new Intent(EventManager_1Activity.this,UserHomeActivity.class);

				Bundle bundle = new Bundle();
				bundle.putString("username", username);
				bundle.putStringArrayList("groups", groupList);
				bundle.putStringArrayList("events", eventList);
				homepageIntent.putExtras(bundle);

				setContentView(R.layout.userhomepage);
				EventManager_1Activity.this.startActivity(homepageIntent);
			}
			
			// set grp and event list in spinner
			//if(groupList != null){
				/*Context context = getApplicationContext();
				CharSequence text = "Logging into application, please wait";
				int duration = Toast.LENGTH_LONG;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				setContentView(R.layout.userhomepage);*/
			//}
			//else{
				/*Context context = getApplicationContext();
				CharSequence text = "Error username or password incorrect! Please recheck or register as a new user";
				int duration = Toast.LENGTH_LONG;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();*/
			//}
		}
	};
	
	/*private OnClickListener mRegisterListener = new OnClickListener() 
    {
		
    	//@Override
		public void onClick(View v) 
		{
			
			setContentView(R.layout.userregisterpage);
			
		}
	};*/
	
	
}
