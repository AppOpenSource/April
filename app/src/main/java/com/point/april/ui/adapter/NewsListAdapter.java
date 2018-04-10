package com.point.april.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.point.april.R;
import com.point.april.bean.News;
import com.point.april.contract.NewsContract;
import com.point.april.ui.activity.news.NewsActivity;
import com.point.april.ui.view.FeedGridView;
import com.point.april.util.DateChangeUtil;
import com.point.april.widget.CustomCummunityDialog;

import java.util.List;
import java.util.regex.Pattern;

public class NewsListAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext;
	private List<News> mNewsList = null;
	private Handler mHandler;
	private NewsContract.Presenter mPresenter;

	public NewsListAdapter(Context context, Handler handler, List<News> list, NewsContract.Presenter presenter) {
		this.mContext = context;
		this.mHandler = handler;
		this.mNewsList = list;
		this.mPresenter = presenter;
		this.mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public void updateListView(List<News> newlist) {
		this.mNewsList = newlist;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		int count = 0;
		if (mNewsList != null) {
			count = mNewsList.size();
		}
		return count;
	}

	@Override
	public Object getItem(int position) {
		return mNewsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class Holder {
		private TextView mTvDate, mTvDetial, mTVCommentNum, mTvStatus;
		private FeedGridView mGvWannantyPhoto;
		private LinearLayout mImgParent;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = mInflater.inflate(R.layout.news_list_item,parent, false);
			holder.mGvWannantyPhoto = (FeedGridView) convertView.findViewById(R.id.fgv_wannanty_photo);
			holder.mTvStatus = (TextView) convertView.findViewById(R.id.tv_status);
			holder.mTvDate = (TextView) convertView.findViewById(R.id.tv_date);
			holder.mTvDetial = (TextView) convertView.findViewById(R.id.tv_detail);
			holder.mTVCommentNum = (TextView) convertView.findViewById(R.id.tv_comment_num);
			holder.mImgParent = (LinearLayout) convertView.findViewById(R.id.img_parent);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		News news = mNewsList.get(position);
		if(news==null) return convertView;
		List<String> picslist = news.getPics();
		String detial=news.getDescription();
		int status = news.getStatus();
		String date= DateChangeUtil.getStandardDate(news.getDate());

		if (picslist.size() != 0) {
			holder.mImgParent.setVisibility(View.VISIBLE);
			holder.mGvWannantyPhoto.setVisibility(View.VISIBLE);
			holder.mGvWannantyPhoto.setPhotoAdapter(picslist);
		} else {
			holder.mImgParent.setVisibility(View.GONE);
			holder.mGvWannantyPhoto.setVisibility(View.GONE);
		}

		String p1="(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{7,14}|(130|131|132|133|134|135|136|137|138|139|188|181)\\d{8}";
		Pattern patternPhone = Pattern.compile(p1); 
		String scheme1="tel:"; 
		Linkify.addLinks(holder.mTvDetial, patternPhone, scheme1);
		if (!TextUtils.isEmpty(detial)) {
			holder.mTvDetial.setText(detial);
			if (news.getIsRead() == 1) {
				holder.mTvDetial.setTextColor(Color.parseColor("#787878"));
			}
		}

		///holder.mTVCommentNum.setText("" + news.getCount()); TODO
		holder.mTVCommentNum.setText(". . .");
		holder.mTVCommentNum.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int status = mNewsList.get(position).getStatus();
				int recordId = mNewsList.get(position).getId();
				int type = mNewsList.get(position).getType();
				((NewsActivity)mContext).mPostion = position;
				if (status == 3) {
					if (recordId != 0) {
						mPresenter.delRecord(recordId, type);
					}
				} else if(status==2) {
					showAlertDialog(recordId,type);
				}
			}
		});

		holder.mTvDate.setText(date);
		if (status == 1) {
			holder.mTvStatus.setText("已受理");
			holder.mTvStatus.setBackgroundResource(R.drawable.bt_fillet_green);
		} else if (status == 2) {
			holder.mTvStatus.setText("待处理");
			holder.mTvStatus.setBackgroundResource(R.drawable.bt_fillet_orange);
		} else if (status == 3) {
			holder.mTvStatus.setText("已完成");
			holder.mTvStatus.setBackgroundResource(R.drawable.bt_fillet_gray);
		}

		/*holder.mGvWannantyPhoto.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				CommunityMsgAdapter.mPicUrl.clear();
				if(mNewsList !=null) {
					CommunityMsgAdapter.mPicUrl.addAll(mNewsList.get(position).getPics());
				}
				Intent intent = new Intent(mContext,PhotoActivity.class);
				intent.putExtra("postion", arg2);
				mContext.startActivity(intent);
			}
		});*/
		return convertView;
	}

	private void showAlertDialog(final int recordId,final int type) {
		CustomCummunityDialog.Builder builder = new CustomCummunityDialog.Builder(mContext);
		builder.setMessage("确定删除次条目吗？");
		builder.setTitle("");
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.setPositiveButton("删除", new android.content.DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if(recordId!=0){
					mPresenter.delRecord(recordId, type);
				}
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
}
