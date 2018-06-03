package com.abt.clock_memo.ui.sign.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.abt.basic.arch.mvvm.view.load.BaseAdapter;
import com.abt.basic.arch.mvvm.view.load.BaseViewHolder;
import com.abt.clock_memo.BR;
import com.abt.clock_memo.R;
import com.abt.clock_memo.bean.SignIn;

/**
 * @描述： @SignInAdapter
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class SignInAdapter extends BaseAdapter<SignIn, BaseViewHolder> {

	public SignInAdapter(Context context) {
		super(context);
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

}
