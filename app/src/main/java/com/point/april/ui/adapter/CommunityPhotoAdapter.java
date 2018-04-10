package com.point.april.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.point.april.R;
import com.point.april.network.NetworkManager;

import java.util.List;

public class CommunityPhotoAdapter extends BaseAdapter {

	private LayoutInflater mInflater; // 视图容器
	private Context mContext;
	private List<String> mDrawablelist;
	private int mColumnWidth;

	public CommunityPhotoAdapter(Context context,List<String> list, int ColumnWidth) {
		this.mContext = context;
		this.mDrawablelist = list;
		this.mColumnWidth=ColumnWidth;
		this.mInflater = LayoutInflater.from(mContext);
	}

	public int getCount() {
		int count = 0;
		if (mDrawablelist != null) {
			count = mDrawablelist.size();
		}
		return count;
	}

	public Object getItem(int position) {
		return mDrawablelist.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.aa_item_communty_photo,parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.item_grid_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String picString=mDrawablelist.get(position);
		ViewGroup.LayoutParams params = holder.image.getLayoutParams();
		params.width = mColumnWidth;
		params.height = mColumnWidth;
		holder.image.setLayoutParams(params);
		if(!TextUtils.isEmpty(picString)){
//			String newStr=picString.substring(0, picString.length()-4);
//			NetworkOpt.getInstace().loadImg(holder.image,newStr+"_zip.jpg");
			NetworkManager.getInstace().loadImg(holder.image,picString);
		}
		return convertView;
	}

	public class ViewHolder {
		public ImageView image;
	}
}