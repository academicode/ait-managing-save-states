package com.academicode.understandingsavestates;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This Activity is dedicated to the AcademiCode episode "Understanding Save States".  
 * 
 * Here, we will use a simple login variable to demonstrate how saving the instance 
 * state can simplify things for the user.
 * 
 * @author AcademiCode
 */
public class Main extends Activity implements View.OnClickListener{

	// We will change this variable to turn on and off the state saving functionality.
	private static final boolean ENABLE_STATE_SAVE = true;
	
	// This tag is used to identify the profile id inside the bundle.
	public static final String PROFILE_ID = "tag_profileid";
	
	// This EditText will hold the user's profile name.
	private EditText et;
	
	// Buttons used to control the interactions with the Activity.
	private Button login;
	private Button printID;
	
	// An internal variable used to track the user's profile id, potentially providing a custom experience.
	private int profileID;
	
	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		// Boilerplate method used to setup the views for the first time.
		initializeViews();
		
		// If there is data inside the saved instance state...
		if(savedInstanceState != null && ENABLE_STATE_SAVE){
			
			// Obtain the profile id from the bundle, and assign it to the local variable.
			profileID = savedInstanceState.getInt(PROFILE_ID);
		}
		else{
			
			// Otherwise, set it to -1.
			profileID = -1;
		}
	}

	/*
	 * This method is called whenever the instance state is going to be saved.  It is here
	 * that we will save our profile id to the outState Bundle.
	 * 
	 * (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		
		// This super method must be called from within this method to ensure that all view-related
		// variables persist.
		super.onSaveInstanceState(outState);
		
		// We place an integer into the state bundle.
		outState.putInt(PROFILE_ID, profileID);
		
		System.out.println("Activity instance state saved.");
	}
	
	/*
	 * This method is called when the Activity is destroyed.  Note that destruction can occur in a 
	 * variety of ways.  
	 * 
	 * In our example, since we're using !isFinishing() to modify execution, were
	 * only going to be working with this callback when the SYSTEM destroys the application (not the
	 * user).
	 * 
	 * (non-Javadoc)
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		
		// If the user did not intentionally destroy this activity...
		if(!isFinishing()){
			
			// Then, it must have been the Android OS, so let's log it.
			System.out.println("Activity temporarily destroyed to free resources.");
		}
	}

	@Override
	public void onClick(View v) {
		
		if(v.getId() == R.id.button1){
			
			String profileName = et.getText().toString();
			
			// Create a simple hash of the profile name as the profile id.  Note this is a bad practice.
			profileID = profileName.hashCode();
			
			Toast.makeText(Main.this, "You have been successfully logged in, " + profileName + ".\n\n( Your profile ID is " + profileID + ").", Toast.LENGTH_LONG).show();
		}
		else if(v.getId() == R.id.button2){
			
			Toast.makeText(Main.this, "Your profile id is:" + profileID + ".", Toast.LENGTH_SHORT).show();
		}
	}
	
	/**
	 * Helper method used to run the boilerplate code necessary to initialize the views.
	 */
	private void initializeViews() {
		
		et = (EditText) findViewById(R.id.editText1);
		
		login = (Button) findViewById(R.id.button1);
		printID = (Button) findViewById(R.id.button2);
		
		// Set the click listeners to this class.  As you can see it implemented the OnClickListener interface.
		login.setOnClickListener(this);
		printID.setOnClickListener(this);
	}
}
