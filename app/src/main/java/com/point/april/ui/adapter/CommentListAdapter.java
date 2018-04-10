package com.point.april.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.point.april.R;
import com.point.april.bean.Dialogue;
import com.point.april.data.StringFormatUtil;

import java.util.List;

public class CommentListAdapter extends BaseAdapter {
	
	private LayoutInflater mInflater; // 视图容器
	private Context mContext;
	private List<Dialogue> mDialogueList;

	public CommentListAdapter(Context context,List<Dialogue> dialogueList) {
		mContext = context;
		this.mDialogueList = dialogueList;
		mInflater = LayoutInflater.from(mContext);
	}

	public int getCount() {
		int count = 0;
		if (mDialogueList != null) {
			count = mDialogueList.size();
		}
		return count;
	}

	public Object getItem(int position) {
		return mDialogueList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	/**
	 * ListView Item设置
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.aa_item_comment, parent,false);
			holder = new ViewHolder();
			holder.mTvComment = (TextView) convertView.findViewById(R.id.tv_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		String content=mDialogueList.get(position).getContent();
		String name=mDialogueList.get(position).getUser_name();
		String str=name+": "+content;
		StringFormatUtil formatUtil=new StringFormatUtil(mContext, str, name+": ", R.color.line_gray).fillColor();
		holder.mTvComment.setText(formatUtil.getResult());
		return convertView;
	}

	public class ViewHolder {
		public TextView mTvComment;
	}
}