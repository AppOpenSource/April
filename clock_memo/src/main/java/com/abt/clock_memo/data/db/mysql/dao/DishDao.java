package com.abt.clock_memo.data.db.mysql.dao;

import android.util.Log;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.point.april.data.db.mysql.bean.Dish;

import java.sql.SQLException;
import java.util.List;

public class DishDao extends BaseDaoImpl<Dish, String> {
	
	private static final String TAG = DishDao.class.getSimpleName();
	private Dao<Dish, String> mDishDao;
	private ConnectionSource mConnSource;
	
	public DishDao(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Dish.class);
		mConnSource = connectionSource;
		mDishDao = DaoManager.createDao(mConnSource, Dish.class);
	}
	
	public List<Dish> queryAllDish() throws SQLException {
		List<Dish> list = null;
		if (mDishDao != null) {
			list = mDishDao.queryForAll();
		}
		for (Dish dish : list) {
			Log.d(TAG, "dish.getName(): "+dish.getName());
		}
		return list;
	}
	
	public Dish get(int id) throws SQLException {
		if (mDishDao == null) return null;
		
		return mDishDao.queryForId(id+"");
	}
	
	public List<Dish> listByMenuId(int menuId) throws SQLException {
		if (mDishDao == null) return null;
		
    	return mDishDao.queryBuilder().where().eq("menu_id", menuId).query();
    }
	
}
