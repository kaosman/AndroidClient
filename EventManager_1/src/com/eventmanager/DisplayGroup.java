package com.eventmanager;

import java.io.InputStream;

import com.model.Group;
import com.support.Helper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class DisplayGroup extends Activity{

	private ServerConnector serverConnector;
	private Helper helper;
	private Spinner spinner;
	private Button logoutButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grouphomepage);
		Bundle bundle = this.getIntent().getExtras();
		
		String groupName = "";
		if(bundle != null){
			groupName = bundle.getString("selectedGroup");
		}
		
		this.serverConnector = new ServerConnector();
		InputStream inputStream = this.serverConnector.getGroupMembers(groupName);
		this.helper = new Helper();
		Group group = this.helper.parseGroup(inputStream);
		
		String[] groupMembers = (String[]) group.getMembers().toArray(); 
		ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, groupMembers);
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner = (Spinner) findViewById(R.id.spinner_memberlist);
		spinner.setAdapter(adapter1);
		
		this.logoutButton = (Button) findViewById(R.id.button_logout);
		this.logoutButton.setOnClickListener(mLogoutListener);
	}
	
	private OnClickListener mLogoutListener = new OnClickListener() {

		// @Override
		public void onClick(View v) {

			setContentView(R.layout.loginpage);

		}
	};
}
