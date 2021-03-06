package com.abt.price.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.abt.basic.arch.mvvm.view.load.BaseAdapter;
import com.abt.basic.arch.mvvm.view.load.BaseViewHolder;
import com.abt.price.BR;
import com.abt.price.R;
import com.abt.price.core.bean.gank.Gank;
import com.abt.price.ui.activity.PictureActivity;
import com.abt.price.ui.viewmodel.GankVM;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * @描述： @GankAdapter
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class GankAdapter extends BaseAdapter<Gank, BaseViewHolder> {

    private ImageView imageView;
    private GankVM gankVM;
    private ViewDataBinding dataBinding;
    private WeakReference<Activity> gankActivity;

    @Inject
    public GankAdapter(Activity context) {
        super(context);
        this.gankActivity = new WeakReference<>(context);
    }

    public void setGankVM(GankVM vm) {
        gankVM = vm;
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_gank_meizi, parent, false);
        imageView = dataBinding.getRoot().findViewById(R.id.iv_meizhi);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.gankVM, gankVM);
        binding.setVariable(BR.gank, mList.get(position));
        binding.setVariable(BR.position,position);
        binding.setVariable(BR.adapter,this);
        binding.executePendingBindings(); //防止闪烁
    }

    public void imageClick(Gank gank) {
        Intent intent = PictureActivity.newIntent(gankActivity.get(),gank.getUrl(),gank.getDesc());
        gankActivity.get().startActivity(intent);

        /**
         * 图片逐渐变大打开activiy，图片逐渐变小关闭activity
         */
        /*ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                gankActivity.get(), imageView, PictureActivity.TRANSIT_PIC);
        // Android 5.0+
        try {
            ActivityCompat.startActivity(gankActivity.get(),intent,optionsCompat.toBundle());
        } catch (Exception e) {
            e.printStackTrace();
            gankActivity.get().startActivity(intent);
        }*/
    }

}
