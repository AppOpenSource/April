package com.abt.price.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.abt.basic.arch.mvvm.view.load.BaseAdapter;
import com.abt.basic.arch.mvvm.view.load.BaseViewHolder;
import com.abt.price.BR;
import com.abt.price.R;
import com.abt.price.bean.gank.Meizhi;
import com.abt.price.ui.viewmodel.GankVM;

/**
 * @描述： @GankAdapter
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class GankAdapter extends BaseAdapter<Meizhi, BaseViewHolder> {

    private GankVM gankVM;

    public GankAdapter(Context context) {
        super(context);
    }

    public void setGankVM(GankVM vm) {
        gankVM = vm;
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_gank_meizi, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.gankVM, gankVM);
        binding.setVariable(BR.meizhi, mList.get(position));
        binding.setVariable(BR.position,position);
        binding.setVariable(BR.adapter,this);
        binding.executePendingBindings(); //防止闪烁
    }

}
