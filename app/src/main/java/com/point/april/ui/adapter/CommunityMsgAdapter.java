package com.point.april.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.point.april.R;
import com.point.april.bean.Inform;
import com.point.april.common.log.LogManager;
import com.point.april.data.StringFormatUtil;
import com.point.april.network.NetworkManager;
import com.point.april.ui.activity.news.PhotoActivity;
import com.point.april.ui.view.CircleImageView;
import com.point.april.ui.view.FeedGridView;
import com.point.april.util.DateChangeUtil;

import java.util.ArrayList;
import java.util.List;

public class CommunityMsgAdapter extends BaseAdapter {
	private static final String TAG = CommunityMsgAdapter.class.getSimpleName();
	private LayoutInflater mInflater;
	private Context mContext;
	private List<Inform> mList = null;
	private OnCallback mCallback;
	public static List<String> mPicUrl= new ArrayList<String>();

	public interface OnCallback {
		public void click(View v, String isGreat, String informID);
	}

	public CommunityMsgAdapter(Context context, List<Inform> list, OnCallback callback) {
		this.mContext = context;
		this.mList = list;
		this.mCallback = callback;
		this.mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void updateListView(List<Inform> newlist){
		this.mList = newlist;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		int count = 3;
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

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.aa_community_msg_item,parent,false);
			holder.mCImgHeadPortrait = (CircleImageView) convertView.findViewById(R.id.img_head_portrait);
			holder.mFGvPhoto = (FeedGridView) convertView.findViewById(R.id.fgv_photo);
			holder.mImgGreat = (ImageView) convertView.findViewById(R.id.img_great);
			holder.mImgAuthenticate = (ImageView) convertView.findViewById(R.id.img_authenticate);
			holder.mTvUserName = (TextView) convertView.findViewById(R.id.tv_user_name);
			holder.mTvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			holder.mTvDetial = (TextView) convertView.findViewById(R.id.tv_detail);
			holder.mTvReleaseTime = (TextView) convertView.findViewById(R.id.tv_release_time);
			holder.mTvGreatNum = (TextView) convertView.findViewById(R.id.tv_great_num);
			holder.mRlGreat = (RelativeLayout) convertView.findViewById(R.id.rl_great);
			holder.mLlGridView = (LinearLayout) convertView.findViewById(R.id.ll_gridview);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		if (mList == null) return convertView;

		final Inform inform=mList.get(position);

		if(inform==null) return convertView;

		//加载头像
		String headPic = inform.getUser_pic();
		if (headPic != null) {
			NetworkManager.getInstace().loadImg(holder.mCImgHeadPortrait, headPic);
		}

		String theme=inform.getTheme();
		if(!TextUtils.isEmpty(theme)){
			holder.mTvTitle.setVisibility(View.VISIBLE);
			holder.mTvTitle.setText(inform.getTheme());
		}else{
			holder.mTvTitle.setVisibility(View.GONE);
		}

		//图片集合
		List<String> picUrl=inform.getPics();
		if(picUrl.size()!=0) {
			holder.mLlGridView.setVisibility(View.VISIBLE);
			holder.mFGvPhoto.setPhotoAdapter(picUrl);
		}else{
			holder.mLlGridView.setVisibility(View.GONE);
		}
		holder.mTvUserName.setText(inform.getUser_name());

		int count=inform.getCount();
		if(!TextUtils.isEmpty(String.valueOf(count))){
			holder.mTvGreatNum.setText("" +count);
		}
		String status=inform.getUp_ornot();
		if(!TextUtils.isEmpty(status)){
			if ("Y".equals(status)) {
				holder.mImgGreat.setBackgroundResource(R.drawable.icon_great_true);
			} else if("N".equals(status)){
				holder.mImgGreat.setBackgroundResource(R.drawable.icon_great_false);
			}
		}

		String date= DateChangeUtil.getStandardDate(inform.getDate());
		if(!TextUtils.isEmpty(date)){
			holder.mTvReleaseTime.setText(date);
		}
		String detial=inform.getDetail();
		if(!TextUtils.isEmpty(detial)){
			Paint paint=new Paint();
			float twidtn=paint.measureText(detial);
			if(twidtn>900){
				String newdetial=detial.substring(0, 70);
				newdetial=newdetial+"...全文";
				StringFormatUtil formatUtil=new StringFormatUtil(mContext, newdetial, "全文",
						R.color.text_full_red).fillColor();
				holder.mTvDetial.setText(formatUtil.getResult());
			}else{
				holder.mTvDetial.setText(detial);
			}
		}

		holder.mRlGreat.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int count=inform.getCount();
				int allNum = 0;
				String inform_id= String.valueOf(inform.getInform_id());
				if ("Y".equals(inform.getUp_ornot())&&count>0) {
					holder.mImgGreat.setBackgroundResource(R.drawable.icon_great_false);
					allNum =count - 1;
					inform.setUp_ornot("N");
				} else if("N".equals(inform.getUp_ornot())&&count>=0) {
					holder.mImgGreat.setBackgroundResource(R.drawable.icon_great_true);
					allNum = count+1;
					inform.setUp_ornot("Y");
				}
				if(allNum>=0){
					inform.setCount(allNum);
					holder.mTvGreatNum.setText("" +allNum);
					mCallback.click(v, inform.getUp_ornot(), inform_id);
				}

			}
		});

		holder.mFGvPhoto.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mPicUrl.clear();
				if(mList!=null){
					mPicUrl.addAll(mList.get(position).getPics());
				}
				LogManager.d(TAG, "CommunityMsgAdapter--> to CummityPhotoActivity");
				Intent intent = new Intent(mContext, PhotoActivity.class);
				intent.putExtra("postion", arg2);
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}

	class Holder {
		private CircleImageView mCImgHeadPortrait;
		private ImageView  mImgGreat, mImgAuthenticate;
		private RelativeLayout mRlGreat;
		private LinearLayout mLlGridView;
		private FeedGridView mFGvPhoto;
		private TextView mTvUserName, mTvTitle, mTvDetial, mTvReleaseTime, mTvGreatNum;
	}
}
