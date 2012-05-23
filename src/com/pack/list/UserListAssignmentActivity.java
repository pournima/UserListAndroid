package com.pack.list;

import com.pack.db.DBAdapter;
import com.pack.db.DBHelper;
import com.pack.db.DummyDBAdapter;
import com.pack.db.DummyDBHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserListAssignmentActivity extends Activity {
   
	SQLiteDatabase sqldb;
	EditText userName;
	EditText userAge;
	Button btnList;
	DBHelper dbhelp;
	DBAdapter dbAdapt;
	Intent i;
	DummyDBAdapter dummyAdap;
	DummyDBHelper dummyHelp;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        dbAdapt=new DBAdapter(this);
        
        userName=(EditText) findViewById(R.id.userName);
        userAge=(EditText) findViewById(R.id.userAge);
        btnList=(Button) findViewById(R.id.buttonList);
        
        i=new Intent(UserListAssignmentActivity.this, UserListViewActivity.class);
        
        btnList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				dbhelp=new DBHelper(UserListAssignmentActivity.this);
				sqldb=dbhelp.getWritableDatabase();
				
				
				//dbAdapt.createList(userName.getText().toString(), userAge.getText().toString());

		        ContentValues initialValues = new ContentValues();
		        initialValues.put("name",userName.getText().toString());
		        initialValues.put("age", userAge.getText().toString());

		        sqldb.insert(dbhelp.DATABASE_TABLE, null, initialValues);
	        

//				dummyHelp=new DummyDBHelper(UserListAssignmentActivity.this);
//				sqldb=dummyHelp.getWritableDatabase();
				  
				ContentValues initialValues1 = new ContentValues();
				initialValues1.put("name", userName.getText().toString());
				initialValues1.put("age", userAge.getText().toString());
				initialValues1.put("gender", "Female");
				initialValues1.put("hobby", "Singing");

				sqldb.insert(dummyHelp.DATABASE_TABLE, null, initialValues1);
					   		
				
				System.out.println("inserted data");
				
				startActivity(i);
				
				
			}
		});
        
        
    }
    
    
}