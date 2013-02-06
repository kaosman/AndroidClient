package com.eventmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UserRegisterActivity extends Activity{


	
	private EditText emailEt;
	private EditText passwordEt;
	private EditText firstNameEt;
	private EditText lastNameEt;
	private EditText contactNoEt;
	private Button registerUserButton;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userregisterpage);
        
        emailEt = (EditText) findViewById(R.id.edittext_email);
        passwordEt = (EditText) findViewById(R.id.edittext_password);
        
        registerUserButton = (Button) findViewById(R.id.button_Register);
        
        this.registerUserButton.setOnClickListener(mregisterListener);
    }
    

	private OnClickListener mregisterListener = new OnClickListener() 
    {
		
    	//@Override
		public void onClick(View v) 
		{

			final String email = emailEt.getText().toString();
			final String password = passwordEt.getText().toString();
			final String firstname = firstNameEt.getText().toString();
			final String lastname = lastNameEt.getText().toString();
			final String contactno = contactNoEt.getText().toString();
			// sending group name and description to server for insertion. On successful insertion, ID should be generated and displayed as a toast
			
			//hardcoded group ID for testing
			//int groupID = 1000;
			
			//Context context = getApplicationContext();
			//CharSequence text = "THIS IS YOUR GROUP ID, PLEASE NOTE IT DOWN" + groupID;
			//int duration = Toast.LENGTH_LONG;

			//Toast toast = Toast.makeText(context, text, duration);
			//toast.show();
			//setContentView(R.layout.userhomepage);
		
		}
	};
	

}
