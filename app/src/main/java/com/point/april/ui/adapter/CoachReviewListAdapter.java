package com.point.april.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.point.april.R;
import com.point.april.bean.Coach;

import java.util.List;

public class CoachReviewListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext;
	private List<Coach> mList;

	public CoachReviewListAdapter(Context context, List<Coach> list) {
		this.mContext = context;
		this.mList = list;
		this.mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
		private ImageView pic;
		private TextView name;
		private TextView star;
		private TextView school;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.list_item_coach_review, parent, false);
			holder.pic = (ImageView) convertView.findViewById(R.id.pic);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.star = (TextView) convertView.findViewById(R.id.star);
			holder.school = (TextView) convertView.findViewById(R.id.school);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		Coach coach = mList.get(position);
		if (coach != null) {
			holder.pic.setImageResource(R.drawable.tab_btn_me_sel);
			String name = coach.getName();
			if(!TextUtils.isEmpty(name)) {
				holder.name.setText(name);
			}
			String star = coach.getStar();
			if(!TextUtils.isEmpty(star)) {
				holder.star.setText(star);
			}
			String school = coach.getWhich_school();
			if(!TextUtils.isEmpty(school)) {
				holder.school.setText(school);
			}
		}
		return convertView;
	}

}
