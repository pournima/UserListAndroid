	package com.pack.db;

	import android.content.Context;
	import android.database.sqlite.SQLiteDatabase;
	import android.database.sqlite.SQLiteOpenHelper;


	public class DummyDBHelper extends SQLiteOpenHelper {

	    private static final String CREATE_DATABASE =
	            "create table userdetails (_id integer primary key autoincrement, "
	            + "name text not null, age text not null, gender text not null, hobby text not null);";
	    
	    //"+"fid integer foreign key REFERENCES userlist(_id)
		
	    
	    private static final String DATABASE_NAME = "Database";
	    public String DATABASE_TABLE = "userdetails";
	    private static final int DATABASE_VERSION = 1;

	    
	    public DummyDBHelper(Context context)
	    {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	    }
	        
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
		
			
		db.execSQL(CREATE_DATABASE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		       db.execSQL("DROP TABLE IF EXISTS" +DATABASE_TABLE);
	           onCreate(db);
	           
		}

		  
}

