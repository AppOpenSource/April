package com.abt.clock_memo.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abt.basic.arch.mvvm.view.load.BaseAdapter;
import com.abt.basic.arch.mvvm.view.load.BaseViewHolder;
import com.abt.clock_memo.BR;
import com.abt.clock_memo.R;
import com.abt.clock_memo.bean.SignIn;
import com.abt.clock_memo.util.DateChangeUtil;

import java.util.List;

/**
 * @描述： @SignInAdapter
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class SignInAdapter extends BaseAdapter<SignIn, BaseViewHolder> {

	private static final String TAG = SignInAdapter.class.getSimpleName();
	private LayoutInflater mInflater;
	private Context mContext;
	private List<SignIn> mList;

	public SignInAdapter(Context context) {
		super(context);
		this.mContext = context;
	}

	public void setListData(List<SignIn> list) {
		mList = list;
	}

	@Override
	public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
		ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.list_item_signin, parent, false);
		return new BaseViewHolder(dataBinding);
	}

	@Override
	public void onBindVH(BaseViewHolder baseViewHolder, int position) {
		ViewDataBinding binding = baseViewHolder.getBinding();
		binding.setVariable(BR.signIn, mList.get(position));
		binding.setVariable(BR.position,position);
		binding.setVariable(BR.adapter,this);
		binding.executePendingBindings(); //防止闪烁
	}

	class Holder {
		private TextView name;
		private TextView time;
		private TextView status;
	}

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


	/**
	 * 点赞
	 * @param simpleNewsBean
	 * @param position
	 */
	/*public void clickDianZan(SimpleNewsBean simpleNewsBean, int position) {
		if (simpleNewsBean.isGood.get()) {
			simpleNewsBean.isGood.set(false);
			ToastUtils.show(mContext, "取消点赞 position=" + position);
		} else {
			simpleNewsBean.isGood.set(true);
			ToastUtils.show(mContext, "点赞成功 position=" + position);
		}
	}*/
}
