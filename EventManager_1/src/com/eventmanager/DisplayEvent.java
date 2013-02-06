package com.eventmanager;

import java.io.InputStream;

import com.model.Event;
import com.model.Group;
import com.support.Helper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class DisplayEvent extends Activity{

	private ServerConnector serverConnector;
	private Helper helper;
	private Button logoutButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventhomepage);
		Bundle bundle = this.getIntent().getExtras();
		
		String eventName = "";
		if(bundle != null){
			eventName = bundle.getString("selectedEvent");
		}
		
		this.serverConnector = new ServerConnector();
		InputStream inputStream = this.serverConnector.getEventDescription(eventName);
		this.helper = new Helper();
		
		Event event = this.helper.parseEvent(inputStream);
		
		TextView eventNameTextView = (TextView)findViewById(R.id.text_eventname);
		eventNameTextView.setText(event.getEventName());
		
		TextView eventDescriptionTextView = (TextView)findViewById(R.id.text_eventdesc);
		eventDescriptionTextView.setText(event.getEventDescription());
		
		TextView eventLocationTextView = (TextView)findViewById(R.id.text_eventlocation);
		eventLocationTextView.setText(event.getEventLocation());
		
		TextView eventDateTextView = (TextView)findViewById(R.id.text_eventdate);
		eventDateTextView.setText(event.getEventDate());
		
		TextView eventTimeTextView = (TextView)findViewById(R.id.text_eventtime);
		eventTimeTextView.setText(event.getEventTime());
		
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
