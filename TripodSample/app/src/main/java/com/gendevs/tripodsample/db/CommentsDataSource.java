package com.gendevs.tripodsample.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CommentsDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLiteHelper dbHelper;
	private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
			MySQLiteHelper.COLUMN_COMMENT };

	public CommentsDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Comment createComment(String comment) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
		values.put(MySQLiteHelper.COLUMN_TIME, System.currentTimeMillis());
		
		String[] OS = new String[] { "Android", "iOS", "Windows", "Palm", "Linux", "Unix" };
		int nextInt = new Random().nextInt(6);
		values.put(MySQLiteHelper.COLUMN_OS, OS[nextInt]);

		String[] users = new String[] { "USER 1", "USER 2", "USER 3", "USER 4", "USER 5", "USER 6" };
		int userint = new Random().nextInt(6);
		values.put(MySQLiteHelper.COLUMN_USER, users[userint]);
		values.put(MySQLiteHelper.COLUMN_DATE, new Date().toString());
		
		String[] versionc = new String[] { "2.3", "2.3.3", "4.0", "3.3", "4.3", "4.4", "5.0", "4.2" };

		values.put(MySQLiteHelper.COLUMN_VERSION, versionc[new Random().nextInt(6)]);


		long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
				allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Comment newComment = cursorToComment(cursor);
		cursor.close();
		return newComment;
	}

	public void deleteComment(Comment comment) {
		long id = comment.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Comment> getAllComments() {
		List<Comment> comments = new ArrayList<Comment>();

		Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Comment comment = cursorToComment(cursor);
			comments.add(comment);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return comments;
	}

	private Comment cursorToComment(Cursor cursor) {
		Comment comment = new Comment();
		comment.setId(cursor.getLong(0));
		comment.setComment(cursor.getString(1));
		return comment;
	}
}
