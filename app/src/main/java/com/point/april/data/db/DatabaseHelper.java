package com.point.april.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.point.april.bean.SignIn;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
	private static final String DATABASE_NAME = "ormlite.db";
	private static final int DATABASE_VERSION = 1;
	
	private Dao<SignIn,Integer> stuDao = null;
	
	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * 创建SQLite数据库
	 */
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, SignIn.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
		}
	}

	/**
	 * 更新SQLite数据库
	 */
	@Override
	public void onUpgrade(
			SQLiteDatabase sqliteDatabase, 
			ConnectionSource connectionSource, 
			int oldVer,
			int newVer) {
		try {
			TableUtils.dropTable(connectionSource, SignIn.class, true);
			onCreate(sqliteDatabase, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), 
					"Unable to upgrade database from version " + oldVer + " to new "
					+ newVer, e);
		}
	}
	
	public Dao<SignIn,Integer> getStudentDao() throws SQLException {
		if(stuDao == null){
			stuDao = getDao(SignIn.class);
		}
		return stuDao;
	}

}