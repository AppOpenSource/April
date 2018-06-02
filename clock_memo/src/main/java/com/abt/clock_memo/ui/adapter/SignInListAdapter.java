package com.abt.clock_memo.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.abt.clock_memo.R;
import com.abt.clock_memo.bean.SignIn;
import com.abt.clock_memo.util.DateChangeUtil;

import java.util.List;

/**
 * @描述： @SignInListAdapter
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class SignInListAdapter extends BaseAdapter {

	private static final String TAG = SignInListAdapter.class.getSimpleName();
	private LayoutInflater mInflater;
	private Context mContext;
	private List<SignIn> mList;

	public SignInListAdapter(Context context, List<SignIn> list) {
		this.mContext = context;
		//this.mList = decommitment(list);
		this.mList = list;
		this.mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public List<SignIn> decommitment(List<SignIn> list) {
		SignIn tmp;
		for (int i=0; i<list.size()/2; i++) {
			int index = list.size()-1-i;
			tmp = list.get(index);
			list.set(index, list.get(i));
			list.set(i, tmp);
		}
		return list;
	}

	@Override
	public int getCount() {
		int count = 0;
		if (mList != null) {
			count = mList.size();
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class Holder {
		private TextView name;
		private TextView time;
		private TextView status;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.list_item_signin, parent, false);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			holder.status = (TextView) convertView.findViewById(R.id.status);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		SignIn sign = mList.get(position);
		if (sign != null) {
			String name = sign.getName();
			if(!TextUtils.isEmpty(name)) {
				holder.name.setText(name);
			}
			String time = sign.getTime();
			if(!TextUtils.isEmpty(time)) {
				String str = DateChangeUtil.toStringHourMinute(time);
				holder.time.setText(str);
			}
			String status = sign.getStatus();
			if(!TextUtils.isEmpty(status)) {
				holder.status.setText(status);
			}
		}
		return convertView;
	}

}
