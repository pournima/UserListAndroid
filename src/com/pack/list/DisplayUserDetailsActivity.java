package com.pack.list;

import com.pack.db.DBHelper;
import com.pack.db.DummyDBAdapter;
import com.pack.model.DummyUserClass;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DisplayUserDetailsActivity extends Activity {

	TextView txtName;
	TextView txtAge;
	TextView txtGender;
	TextView txtHobby;
	SQLiteDatabase sqldb;
	DummyDBAdapter adapter;
	DBHelper dbhelp;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displayuserdetails);
		
		adapter=new DummyDBAdapter(this);
		
		txtName=(TextView)findViewById(R.id.displayUser);
		txtAge=(TextView)findViewById(R.id.displayAge);
		txtGender=(TextView)findViewById(R.id.displayGender);
		txtHobby=(TextView)findViewById(R.id.displayHobbies);
		
		String name=getIntent().getExtras().getString("name");
		txtName.setText(name);
		
		
	    Log.d("name-------","before cursor");

	    
		dbhelp=new DBHelper(DisplayUserDetailsActivity.this);
		sqldb=dbhelp.getWritableDatabase();
		
	    
		Cursor cursor=sqldb.query("userdetails", new String[]{"name","age","gender","hobby"},null, null,null, null, null);
		
//		Cursor cursor = sqldb.query(true,"userdetails", new String[]{"name","age","gender","hobby"}, "name=" + name,
//				null, null, null, null, null);
		
		//Cursor cursor=adapter.fetch(name);
	    Log.d("name-------","after cursor");
	    
        if(cursor.moveToFirst())
        {
                do
                {
                        DummyUserClass dummyclass=new DummyUserClass();
                        dummyclass.setName(cursor.getString(cursor.getColumnIndex("name")));
                        dummyclass.setAge(cursor.getString(cursor.getColumnIndex("age")));
                        dummyclass.setGender(cursor.getString(cursor.getColumnIndex("gender")));
                        dummyclass.setHobby(cursor.getString(cursor.getColumnIndex("hobby")));
                
                        Log.d("class",dummyclass.toString());
                        if(dummyclass.getName().equals(name))
                        {
                        	txtName.setText(dummyclass.getName());
                        	txtAge.setText(dummyclass.getAge());
                        	txtGender.setText(dummyclass.getGender());
                        	txtHobby.setText(dummyclass.getHobby());
                        }
                
                }while(cursor.moveToNext());

        }
        
    
	}
	
}
