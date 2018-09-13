package com.abt.price.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.abt.basic.arch.mvvm.view.load.BaseAdapter;
import com.abt.basic.arch.mvvm.view.load.BaseViewHolder;
import com.abt.price.BR;
import com.abt.price.R;
import com.abt.price.core.bean.zhihu.Stories;
import com.abt.price.ui.viewmodel.ZhihuVM;

/**
 * @描述： @ZhihuAdapter
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class ZhihuAdapter extends BaseAdapter<Stories, BaseViewHolder> {

    private ZhihuVM zhihuVM;

    public ZhihuAdapter(Context context) {
        super(context);
    }

    public void setZhihuVM(ZhihuVM vm) {
        zhihuVM = vm;
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_zhihu, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.zhihuVM, zhihuVM);
        binding.setVariable(BR.simpleZhihuBean, mList.get(position));
        binding.setVariable(BR.position,position);
        binding.setVariable(BR.adapter,this);
        binding.executePendingBindings(); //防止闪烁
    }

}
