package com.pack.db;

	import java.util.ArrayList;
	import java.util.List;

	import com.pack.model.DummyUserClass;
import com.pack.model.UserClass;

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
	    public DBHelper dbhelp;
	    
	  
		public DummyDBAdapter(Context context) {
			dbhelp = new DBHelper(context);
		}

		public void open() throws SQLException {
			sqldb = dbhelp.getWritableDatabase();
		}
		
		public void close() {
			dbhelp.close();
		}
		
		public long createList(String name, String age,String gender,String hobby) {
		        ContentValues initialValues = new ContentValues();
		        initialValues.put(KEY_NAME, name);
		        initialValues.put(KEY_AGE, age);
		        initialValues.put(KEY_GENDER, gender);
		        initialValues.put(KEY_HOBBY, hobby);
		        
		    return sqldb.insert(dbhelp.DUMMY_DATABASE_TABLE, null, initialValues);
		    }

		  public boolean updateList(long rowId, String name, String age,String gender,String hobby) {
		        ContentValues args = new ContentValues();
		        args.put(KEY_NAME, name);
		        args.put(KEY_AGE, age);
		        args.put(KEY_GENDER,gender);
		        args.put(KEY_HOBBY,hobby);
		        
		        return sqldb.update(dbhelp.DUMMY_DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
		       
		 }


//			public Cursor fetch(long rowId) throws SQLException {
//				Cursor mCursor = sqldb.query(true, ddbhelp.DATABASE_TABLE, allColumns, "fid=" + rowId,
//						null, null, null, null, null);
//	
//				if (mCursor != null) {
//					mCursor.moveToFirst();
//				}
//	
//				return mCursor;
//			}


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

				Cursor cursor = sqldb.query(dbhelp.DUMMY_DATABASE_TABLE,
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
			

			public List<DummyUserClass> getNAME(String name) {
				List<DummyUserClass> comments = new ArrayList<DummyUserClass>();
				// Select All Query
				String selectQuery = "SELECT * FROM userdetails WHERE NAME= "+ name;
				
				Cursor cursor = sqldb.rawQuery(selectQuery, null);

				cursor.moveToFirst();
				while (!cursor.isAfterLast()) {
					DummyUserClass comment = cursorToComment(cursor);
					comments.add(comment);
					cursor.moveToNext();
				}
				// Make sure to close the cursor
				cursor.close();
				return comments;
			}		
			

			public Cursor fetch(String name) throws SQLException {
			Cursor mCursor = sqldb.query(true,dbhelp.DUMMY_DATABASE_TABLE, allColumns, "name=" + name,
					null, null, null, null, null);

			if (mCursor != null) {
				mCursor.moveToFirst();
			}

			return mCursor;
		}
			
}
