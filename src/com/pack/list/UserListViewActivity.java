package com.pack.list;

import java.util.List;
import com.pack.db.DBAdapter;
import com.pack.model.UserClass;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import android.view.View;


public class UserListViewActivity extends Activity
{
	ListView listView;
	TextView userView;
	
	ArrayAdapter<UserClass> listAdapter;
	private DBAdapter dataAdapter;
	SQLiteDatabase database;
	List<UserClass> ListValues;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
			setContentView(R.layout.userlist);
			
			//listView=(ListView) findViewById(R.id.list);
			
			dataAdapter = new DBAdapter(this);
			dataAdapter.open();

			ListValues = dataAdapter.getAllComments();
			
			listAdapter = new ArrayAdapter<UserClass>(this, R.layout.listusertext, ListValues);


			listView = (ListView)findViewById(R.id.list);
			listView.setAdapter(listAdapter);

					listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
							// TODO Auto-generated method stub
						
							
						}
						

					});
			


	}

	
	

}
