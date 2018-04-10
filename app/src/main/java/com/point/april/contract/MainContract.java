package com.point.april.contract;

import android.app.Fragment;

import com.point.april.bean.UpdateItem;

import java.util.ArrayList;

public interface MainContract {

	public interface IMain {
		void initMenu(ArrayList<Fragment> fragments, ArrayList<Integer> titles);

		void showUpdate(UpdateItem updateItem);
	}

}
