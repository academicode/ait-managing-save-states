package com.academicode.understandingsavestates;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity implements View.OnClickListener{

	private static final boolean ENABLE_STATE_SAVE = true;
	
	public static final String PROFILE_ID = "tag_profileid";
	
	private EditText et;
	
	private Button login;
	private Button printID;
	
	private int profileID;
	
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		initializeViews();
		
		if(savedInstanceState != null && ENABLE_STATE_SAVE){
			
			profileID = savedInstanceState.getInt(PROFILE_ID);
		}
		else{
			
			profileID = -1;
		}
	}

	private void initializeViews() {
		
		et = (EditText) findViewById(R.id.editText1);
		
		login = (Button) findViewById(R.id.button1);
		printID = (Button) findViewById(R.id.button2);
		
		login.setOnClickListener(this);
		printID.setOnClickListener(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
		super.onSaveInstanceState(outState);
		
		outState.putInt(PROFILE_ID, profileID);
		System.out.println("Activity instance state saved.");
	}
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		
		if(!isFinishing()){
			
			System.out.println("Activity temporarily destroyed to free resources.");
		}
	}

	@Override
	public void onClick(View v) {
		
		if(v.getId() == R.id.button1){
			
			String profileName = et.getText().toString();
			
			profileID = profileName.hashCode();
			
			Toast.makeText(Main.this, "You have been successfully logged in, " + profileName + ".\n\n( Your profile ID is " + profileID + ").", Toast.LENGTH_LONG).show();
		}
		else if(v.getId() == R.id.button2){
			
			Toast.makeText(Main.this, "Your profile id is:" + profileID + ".", Toast.LENGTH_SHORT).show();
		}
	}
}
