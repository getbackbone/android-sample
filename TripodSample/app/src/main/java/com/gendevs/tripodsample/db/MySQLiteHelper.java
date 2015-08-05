package com.gendevs.tripodsample.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	  public static final String TABLE_COMMENTS = "comments";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_COMMENT = "comment";
	  public static final String COLUMN_TIME = "time";
	  public static final String COLUMN_DATE = "date";
	  public static final String COLUMN_USER = "user";
	  public static final String COLUMN_OS = "os";
	  public static final String COLUMN_VERSION = "version";




	  private static final String DATABASE_NAME = "commments.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_COMMENTS + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " + 
	      COLUMN_COMMENT + " text not null," +
	      COLUMN_USER + " text not null," +
	      COLUMN_TIME + " text not null," +
	      COLUMN_DATE + " text not null," +
	      COLUMN_OS + " text not null," +
	      COLUMN_VERSION + " text not null" + 
	      ");";

	  public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(MySQLiteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
	    onCreate(db);
	  }

	} 