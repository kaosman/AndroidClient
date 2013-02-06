package com.eventmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class UserHomeActivity extends Activity{
	
	private Spinner groupListSpinner;
	Spinner sp;
	private Spinner eventListSpinner;
	private Button logoutButton;
	private Button creategroupButton;
	private Button createeventButton;
	private String[] group_list = new String[5];
	private String[] event_list = new String[5];
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhomepage);
        
        Bundle bundle = this.getIntent().getExtras();
        
        if(bundle !=null){
        	
        	ArrayList<String> groupList = bundle.getStringArrayList("groups");
        	
        	 int i=0;
             for(String groupname : groupList){
             	group_list[i++] = groupname;
             }
             
        	ArrayList<String> eventList = bundle.getStringArrayList("events");
        	
        	int j=0;
        	for(String eventname : eventList){
        		event_list[j++] = eventname;
        	}
        }
       
        //this.gotoButton = (Button) findViewById(R.id.button_gotogroup);
        this.creategroupButton = (Button) findViewById(R.id.button_creategrp);
        this.createeventButton = (Button) findViewById(R.id.button_createevent);
        this.logoutButton = (Button) findViewById(R.id.button_logout);
        
        this.creategroupButton.setOnClickListener(mCreateGroupListener);
        this.createeventButton.setOnClickListener(mCreateEventListener);
        this.logoutButton.setOnClickListener(mLogoutListener);
        
		ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, this.group_list);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		groupListSpinner = (Spinner) findViewById(R.id.spinner_grouplist);
		groupListSpinner.setAdapter(adapter1);
		
		
		ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, this.event_list);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		eventListSpinner = (Spinner) findViewById(R.id.spinner_eventlist);
		eventListSpinner.setAdapter(adapter2);
		
		groupListSpinner.setOnItemSelectedListener(mGroupItemListner);
		eventListSpinner.setOnItemSelectedListener(mEventItemListener);
    }
	

 
    private OnItemSelectedListener mGroupItemListner = new OnItemSelectedListener() {

    	public void onItemSelected(AdapterView<?> data, View arg1, int arg2,long arg3) {

			Intent groupIntent = new Intent(UserHomeActivity.this, DisplayGroup.class);
			Bundle bundle = new Bundle();
			bundle.putString("selectedGroup", data.getSelectedItem().toString());
			groupIntent.putExtras(bundle);
			
			setContentView(R.layout.grouphomepage);
			UserHomeActivity.this.startActivity(groupIntent);
			
			
			//setContentView(R.layout.userhomepage);
		}

		public void onNothingSelected(AdapterView<?> data) {
			
			Context context = getApplicationContext();
			CharSequence text = data.getSelectedItem().toString();
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}

		
	};
	
	private OnItemSelectedListener mEventItemListener = new OnItemSelectedListener() {

		public void onItemSelected(AdapterView<?> data, View arg1, int arg2,
				long arg3) {
			
			Intent eventIntent = new Intent(UserHomeActivity.this, DisplayEvent.class);
			Bundle bundle = new Bundle();
			bundle.putString("selectedEvent", data.getSelectedItem().toString());
			eventIntent.putExtras(bundle);
			UserHomeActivity.this.startActivity(eventIntent);
		}

		public void onNothingSelected(AdapterView<?> data) {
			
			Context context = getApplicationContext();
			CharSequence text = data.getSelectedItem().toString();
			int duration = Toast.LENGTH_LONG;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	};
    private OnClickListener mCreateGroupListener = new OnClickListener() 
    {

		// @Override
		public void onClick(View v) {

			setContentView(R.layout.groupcreatepage);

		}
	};

	private OnClickListener mCreateEventListener = new OnClickListener() {

		// @Override
		public void onClick(View v) {

			setContentView(R.layout.eventcreatepage);

		}
	};

	private OnClickListener mLogoutListener = new OnClickListener() {

		// @Override
		public void onClick(View v) {

			setContentView(R.layout.loginpage);

		}
	};

}
