package com.abt.price.ui.viewmodel;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.R;
import com.abt.price.app.PriceApp;
import com.abt.price.core.bean.zhihu.News;
import com.abt.price.databinding.ActivityWebViewBinding;
import com.abt.price.model.zhihu.IZhihuModel;
import com.abt.price.model.zhihu.ZhihuModelImpl;
import com.abt.price.ui.IZhihuWebView;
import com.abt.price.ui.constant.PageConstant;
import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @描述： @ZhihuWebVM
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class ZhihuWebVM implements BaseLoadListener<News> {
    private static final String TAG = "ZhihuWebVM";
    private IZhihuModel mZhihuModel;
    private ActivityWebViewBinding mBinding;
    private WeakReference<IZhihuWebView> mZhihuView;
    private int loadType; //加载数据的类型

    public ZhihuWebVM(ActivityWebViewBinding binding, IZhihuWebView webView, String id) {
        this.mZhihuView = new WeakReference<>(webView);
        mBinding = binding;
        mZhihuModel = new ZhihuModelImpl();
        getDetailNews(id);
    }

    /**第一次加载数据*/
    private void getDetailNews(String newsId) {
        loadType = PageConstant.LoadData.FIRST_LOAD;
        mZhihuModel.getDetailNews(newsId, this);
    }

    @Override
    public void loadSuccess(List<News> list) {
        if (null != list && list.size() > 0) {
            setWebView(list.get(0));
        }
    }

    @Override
    public void loadFailure(String message) {
        // 加载失败后的提示
        if (null != mZhihuView.get()) {
            mZhihuView.get().loadFailure(message);
        }
    }

    @Override
    public void loadStart() {
        if (null != mZhihuView.get()) {
            mZhihuView.get().loadStart(loadType);
        }
    }

    @Override
    public void loadComplete() {
        if (null != mZhihuView) {
            mZhihuView.get().loadComplete();
        }
    }

    private void setWebView(News news) {
        WebView webView = mBinding.nested.findViewById(R.id.web_view);
        //WebView webView = getView().getWebView();
        ImageView webImg = mBinding.ivWebImg;
        // webImg = getView().getWebImg();
        TextView imgTitle = mBinding.tvImgTitle;//getView().getImgTitle();
        TextView imgSource = mBinding.tvImgSource;//getView().getImgSource();

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String head = "<head>\n" +
                "\t<link rel=\"stylesheet\" href=\"" + news.getCss()[0] + "\"/>\n" +
                "</head>";
        String img = "<div class=\"headline\">";
        String html = head + news.getBody().replace(img, " ");
        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        Glide.with(PriceApp.getAppContext()).load(news.getImage()).centerCrop().into(webImg);

        imgTitle.setText(news.getTitle());
        imgSource.setText(news.getImage_source());
    }
}

