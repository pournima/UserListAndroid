package com.pack.db;

import java.util.ArrayList;
import java.util.List;
import com.pack.model.UserClass;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {

	public SQLiteDatabase sqldb;
	public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_AGE = "age";
    public static String[] allColumns = {KEY_NAME,KEY_AGE};
    public DBHelper dbhelp;
    
  
	public DBAdapter(Context context) {
		dbhelp = new DBHelper(context);
	}

	public void open() throws SQLException {
		sqldb = dbhelp.getWritableDatabase();
	}
	
	public void close() {
		dbhelp.close();
	}
	
	public long createList(String name, String age) {
	        ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_NAME, name);
	        initialValues.put(KEY_AGE, age);

	    return sqldb.insert("userlist", null, initialValues);
	    }

//	  public boolean updateList(long rowId, String name, String age) {
//	        ContentValues args = new ContentValues();
//	        args.put(KEY_NAME, name);
//	        args.put(KEY_AGE, age);
//
//	        
//	        return sqldb.update(dbhelp.DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
//	       
//	 }

	  
//		public Cursor fetchAll(String where, String limit) {
//			return sqldb.query(dbhelp.DATABASE_TABLE, allColumns, where, null, null, null, limit);
//		}
//
//		public Cursor fetch(long rowId) throws SQLException {
//			Cursor mCursor = sqldb.query(true, dbhelp.DATABASE_TABLE, allColumns, "_id=" + rowId,
//					null, null, null, null, null);
//
//			if (mCursor != null) {
//				mCursor.moveToFirst();
//			}
//
//			return mCursor;
//		}


		private UserClass cursorToComment(Cursor cursor) {
			UserClass comment = new UserClass();
			comment.setName(cursor.getString(0));
			comment.setAge(cursor.getString(1));
			return comment;
		}
		
		public List<UserClass> getAllComments() {
			List<UserClass> list = new ArrayList<UserClass>();

			Cursor cursor = sqldb.query(dbhelp.DATABASE_TABLE,
					allColumns, null, null, null, null, null);

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				UserClass comment = cursorToComment(cursor);
				list.add(comment);
				cursor.moveToNext();
			}
			// Make sure to close the cursor
			cursor.close();
			return list;
		}

		
		public List<UserClass> getNAME(String name) {
			List<UserClass> comments = new ArrayList<UserClass>();
			// Select All Query
			String selectQuery = "SELECT * FROM userdetails WHERE NAME= "+ name;
			
			Cursor cursor = sqldb.rawQuery(selectQuery, null);

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				UserClass comment = cursorToComment(cursor);
				comments.add(comment);
				cursor.moveToNext();
			}
			// Make sure to close the cursor
			cursor.close();
			return comments;
		}		
	  
}
