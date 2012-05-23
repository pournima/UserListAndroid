package com.pack.db;

	import java.util.ArrayList;
	import java.util.List;

	import com.pack.model.DummyUserClass;

	import android.content.ContentValues;
	import android.content.Context;
	import android.database.Cursor;
	import android.database.SQLException;
	import android.database.sqlite.SQLiteDatabase;

	public class DummyDBAdapter {

		public SQLiteDatabase sqldb;
		public static final String KEY_ROWID = "_id";
	    public static final String KEY_NAME = "name";
	    public static final String KEY_AGE = "age";
	    public static final String KEY_GENDER = "gender";
	    public static final String KEY_HOBBY = "hobby";
	    
	    public static String[] allColumns = {KEY_NAME,KEY_AGE,KEY_GENDER,KEY_HOBBY};
	    public DummyDBHelper ddbhelp;
	    
	  
		public DummyDBAdapter(Context context) {
			ddbhelp = new DummyDBHelper(context);
		}

		public void open() throws SQLException {
			sqldb = ddbhelp.getWritableDatabase();
		}
		
		public void close() {
			ddbhelp.close();
		}
		
		public long createList(String name, String age,String gender,String hobby) {
		        ContentValues initialValues = new ContentValues();
		        initialValues.put(KEY_NAME, name);
		        initialValues.put(KEY_AGE, age);
		        initialValues.put(KEY_GENDER, gender);
		        initialValues.put(KEY_HOBBY, hobby);
		        
		    return sqldb.insert(ddbhelp.DATABASE_TABLE, null, initialValues);
		    }

		  public boolean updateList(long rowId, String name, String age,String gender,String hobby) {
		        ContentValues args = new ContentValues();
		        args.put(KEY_NAME, name);
		        args.put(KEY_AGE, age);
		        args.put(KEY_GENDER,gender);
		        args.put(KEY_HOBBY,hobby);
		        
		        return sqldb.update(ddbhelp.DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
		       
		 }


			public Cursor fetch(long rowId) throws SQLException {
				Cursor mCursor = sqldb.query(true, ddbhelp.DATABASE_TABLE, allColumns, "fid=" + rowId,
						null, null, null, null, null);
	
				if (mCursor != null) {
					mCursor.moveToFirst();
				}
	
				return mCursor;
			}


			private DummyUserClass cursorToComment(Cursor cursor) {
				DummyUserClass comment = new DummyUserClass();
				comment.setName(cursor.getString(0));
				comment.setAge(cursor.getString(1));
				comment.setGender(cursor.getString(2));
				comment.setHobby(cursor.getString(3));
				return comment;
			}
			
			public List<DummyUserClass> getAllComments() {
				List<DummyUserClass> list = new ArrayList<DummyUserClass>();

				Cursor cursor = sqldb.query(ddbhelp.DATABASE_TABLE,
						allColumns, null, null, null, null, null);

				cursor.moveToFirst();
				while (!cursor.isAfterLast()) {
					DummyUserClass comment = cursorToComment(cursor);
					list.add(comment);
					cursor.moveToNext();
				}
				// Make sure to close the cursor
				cursor.close();
				return list;
			}
			
		  

}
