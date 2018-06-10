package com.abt.price.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.abt.basic.arch.mvvm.view.load.BaseAdapter;
import com.abt.basic.arch.mvvm.view.load.BaseViewHolder;
import com.abt.common.util.ToastUtils;
import com.abt.price.BR;
import com.abt.price.R;
import com.abt.price.bean.price.SimplePriceBean;

/**
 * @描述： @NewsAdapter
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class PriceAdapter extends BaseAdapter<SimplePriceBean, BaseViewHolder> {

    public PriceAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_price, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.simplePriceBean, mList.get(position));
        binding.setVariable(BR.position,position);
        binding.setVariable(BR.adapter,this);
        binding.executePendingBindings(); //防止闪烁
    }

    /**
     * 点赞
     * @param simplePriceBean
     * @param position
     */
    public void clickDianZan(SimplePriceBean simplePriceBean, int position) {
        if (simplePriceBean.done.get()) {
            simplePriceBean.done.set(false);
            ToastUtils.show(mContext, "取消点赞 position=" + position);
        } else {
            simplePriceBean.done.set(true);
            ToastUtils.show(mContext, "点赞成功 position=" + position);
        }
    }
}
