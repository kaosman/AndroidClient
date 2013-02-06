package com.eventmanager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class GroupCreateActivity extends Activity{


	
	private EditText groupNameEt;
	private EditText groupDescEt;
	private Button finalcreategroupButton;
	private Button logout_button;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.groupcreatepage);
        
        groupNameEt = (EditText) findViewById(R.id.edittext_groupname);
        groupDescEt = (EditText) findViewById(R.id.edittext_groupdesc);
        
        this.finalcreategroupButton = (Button) findViewById(R.id.button_createNewGroup);
        this.logout_button = (Button) findViewById(R.id.button_logout);
        this.logout_button.setOnClickListener(mLogoutListener);
        this.finalcreategroupButton.setOnClickListener(mCreateGroupListener);
    }
    

	private OnClickListener mCreateGroupListener = new OnClickListener() 
    {
		
    	//@Override
		public void onClick(View v) 
		{

			final String groupname = groupNameEt.getText().toString();
			final String groupdesc = groupDescEt.getText().toString();
			// sending group name and description to server for insertion. On successful insertion, ID should be generated and displayed as a toast
			
			//harcoded group ID for testing
			int groupID = 1000;
			
			Context context = getApplicationContext();
			CharSequence text = "THIS IS YOUR GROUP ID, PLEASE NOTE IT DOWN" + groupID;
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
			setContentView(R.layout.grouphomepage);
			setContentView(R.layout.grouphomepage);
		
		}
	};
	
	private OnClickListener mLogoutListener = new OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			setContentView(R.layout.loginpage);
		}
	};
	

}
