package com.point.april.ui.activity.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.point.april.R;
import com.point.april.bean.ErrorInfo;
import com.point.april.bean.News;
import com.point.april.common.log.LogManager;
import com.point.april.contract.NewsContract;
import com.point.april.global.GlobalParams;
import com.point.april.network.NetworkManager;
import com.point.april.network.NetworkUtil;
import com.point.april.presenter.NewsPresenterImpl;
import com.point.april.ui.UIConstant;
import com.point.april.ui.adapter.NewsListAdapter;
import com.point.april.ui.view.waterdroplistview.WaterDropListView;
import com.point.april.widget.CustomCummunityDialog;
import com.point.april.widget.CustomDialogHelper;
import com.point.base.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻精选页面
 */
public class NewsActivity extends BaseActivity implements
        OnClickListener, OnItemLongClickListener, NewsContract.View,
        AdapterView.OnItemClickListener, WaterDropListView.IWaterDropListViewListener {

    private static final String TAG = NewsActivity.class.getSimpleName();
    private WaterDropListView mNewsListView;
    private LinearLayout mLlEditBox;
    private RelativeLayout mRlNoData;
    private Button mBtSend;
    private EditText mEtComment;
    private TextView mTitle;
    private TextView mTopTips;
    private LinearLayout mGoBack;

    private NewsContract.Presenter mPresenter;
    private NewsListAdapter mNewsRecordAdapter;
    private String name;
    private List<News> mNewsList;
    public int mPostion;
    private boolean isNetworkAvailable = false;
    private boolean isRefres = false;
    private boolean isHead = false;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 10010:
                    name = msg.getData().getString("name");
                    mLlEditBox.setVisibility(View.VISIBLE);
                    mEtComment.setHint("回复" + name + ":");
                    InputMethodManager imm = (InputMethodManager) mEtComment
                            .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                    break;
                case UIConstant.INT_ONLINE:
                    isNetworkAvailable = true;
                    CustomDialogHelper.createPrograssDialog(NewsActivity.this, "", 15 * 1000).show();
                    sendNewsRequest();
                    break;
                case UIConstant.INT_OFFLINE:
                    CustomDialogHelper.dismiss();
                    isNetworkAvailable = false;
                    mNewsListView.stopRefresh();
                    break;
                case 2:
                    mNewsListView.stopLoadMore();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void initVariables() {
        mPresenter = new NewsPresenterImpl(NewsActivity.this);
        // CCApplication.getInstance().getEventBus().register(NewsdActivity.this);
        GlobalParams.setNews_id(0);//重置news_id=0, 去获取最新的10条新闻
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_news;
    }

    @Override
    protected void initView() {
        mTitle = (TextView) findViewById(R.id.title_content);
        mTitle.setText("新闻精选");

        mGoBack = (LinearLayout) findViewById(R.id.go_back);
        mGoBack.setOnClickListener(this);
        mTopTips = (TextView) findViewById(R.id.top_tips);

        mNewsListView = (WaterDropListView) findViewById(R.id.lv_commm_record);
        mBtSend = (Button) findViewById(R.id.bt_send_msg);
        mEtComment = (EditText) findViewById(R.id.et_comment);
        mLlEditBox = (LinearLayout) findViewById(R.id.ll_edit_box);
        mRlNoData = (RelativeLayout) findViewById(R.id.rl_warranty);
        setOnListener();
    }

    private void setOnListener() {
        mBtSend.setOnClickListener(this);
        mNewsListView.setWaterDropListViewListener(this);
        mNewsListView.setPullLoadEnable(true);
        mNewsListView.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        CustomDialogHelper.createPrograssDialog(this, "", 15 * 1000).show();
        sendNewsRequest();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        if (NetworkUtil.isNetworkConnected(this)) {
            mTopTips.setVisibility(View.GONE);
        } else {
            // TODO 加载缓存数据
            mTopTips.setVisibility(View.VISIBLE);
            mPresenter.getNewsCache();
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("News Page") // TODO: Define a title for the content shown.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]")) // TODO: Make sure this auto-generated URL is correct.
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        AppIndex.AppIndexApi.start(mClient, getIndexApiAction());
    }

    @Override
    public void onRefresh() {
        isHead = true;
        LogManager.d(TAG, "onRefresh()");
        if (mNewsList != null) {
            int id = mNewsList.get(mNewsList.size()-1).getId();
            GlobalParams.setNews_id(id);
        } else {
            GlobalParams.setNews_id(0);
        }
        mPresenter.getLatestNews();
    }

    @Override
    public void onLoadMore() {
        isHead = false;
        LogManager.d(TAG, "onLoadMore()");
        if (mNewsList != null) {
            int id = mNewsList.get(mNewsList.size()-1).getId();
            GlobalParams.setNews_id(id);
        } else {
            isHead = false;
            GlobalParams.setNews_id(0);
        }
        mPresenter.getMoreNews();
    }

    @Override
    protected void onResume() {
        if (!NetworkUtil.isNetworkAvailable(this)) {
            // CustomDialogHelper.createToastDialog(this, "当前网络不可用", 2000, false).show();
            isNetworkAvailable = false;
        } else {
            isNetworkAvailable = true;
        }
        super.onResume();
    }

    /*public void onEventMainThread(NetworkExceptionHandler publishHandler){
        String result = publishHandler.getResult();
        if(UIConstant.STR_OFFLINE.equals(result)){
            mHandler.sendEmptyMessage(UIConstant.INT_OFFLINE);
        }else if(UIConstant.STR_ONLINE.equals(result)){
            mHandler.sendEmptyMessage(UIConstant.INT_ONLINE);
        }
    }*/

    private void sendNewsRequest() {
        mNewsListView.post(new Runnable() {
            @Override
            public void run() {
                if (mNewsList != null) {
                    int id = mNewsList.get(mNewsList.size()-1).getId();
                    GlobalParams.setNews_id(id);
                } else {
                    isHead = false;
                    GlobalParams.setNews_id(0);
                }
                mPresenter.getMoreNews();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.go_back:
                NewsActivity.this.finish();
                break;
            case R.id.bt_send_msg:
                mLlEditBox.setVisibility(View.GONE);
                mEtComment.setText("");
                try {
                    InputMethodManager inputMethodManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                } catch (Exception e) { }
                break;
            default:
                break;
        }
    }

    @Override
    public void showError(ErrorInfo info) {
        LogManager.e(TAG, "---ErrorInfo is " + info);
        mRlNoData.setVisibility(View.VISIBLE);
        CustomDialogHelper.dismiss();
        /*if (mNewsListView.isRefreshing()) {
            mNewsListView.onRefreshComplete();
        }*/
    }

    @Override
    public void showNewsRecord(List<News> list) {
        // 1: 第一次加载数据，往空列表直接添加数据
        // 2: 已有数据，加载最新数据，往已有列表头部插入新数据
        // 3: 已有数据，加载更多数据，往已有列表底部插入新数据
        // 4: 通知列表更新数据
        // 5: 取消加载数据提示
        if (mNewsList == null) {
            mNewsList = new ArrayList<News>();
            mNewsList.addAll(list);
        } else {
            if (isHead) { // 头部
                for (int i=list.size()-1; i<=0; i--) {
                    News news = list.get(i);
                    int count = 0;
                    for (int j=0; j<mNewsList.size(); j++) {
                        if (mNewsList.get(j).getId() == news.getId()) {
                            break;
                        }
                        count++;
                    }
                    if (count == mNewsList.size()) {
                        mNewsList.add(0, news);
                    }
                }
            } else { // 更多
                for (News news : list) {
                    int count = 0;
                    for (int i=0; i<mNewsList.size(); i++) {
                        if (mNewsList.get(i).getId() == news.getId()) {
                            break;
                        }
                        count++;
                    }
                    if (count == mNewsList.size()) {
                        mNewsList.add(news);
                    }
                }
            }
        }


        if (mNewsList.size() > 0) {
            mRlNoData.setVisibility(View.GONE);
            if (isRefres == false) {
                isRefres = true;
                mNewsRecordAdapter = new NewsListAdapter(this, mHandler, mNewsList, mPresenter);
                mNewsListView.setAdapter(mNewsRecordAdapter);
            } else {
                mNewsRecordAdapter.updateListView(mNewsList);
            }
        } else {
            mRlNoData.setVisibility(View.VISIBLE);
        }


        if (isHead) {
            if (list != null && list.size() > 0) {
                mNewsListView.stopRefresh();
            } else {
                mNewsListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mNewsListView.stopRefresh();
                    }
                }, 1 * 1000);
            }
        } else {
            if (list != null && list.size() > 0) {
                mNewsListView.stopLoadMore();
            } else {
                mNewsListView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mNewsListView.stopLoadMore();
                    }
                }, 1 * 1000);
            }
        }
        CustomDialogHelper.dismiss();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int postions, long arg3) {
        int id = postions - 1;
        int status = mNewsList.get(id).getStatus();
        int recordId = mNewsList.get(id).getId();
        int type = mNewsList.get(id).getType();
        mPostion = id;
        if (status == 3) {
            if (recordId != 0) {
                mPresenter.delRecord(recordId, type);
            }
        } else if (status == 2) {
            showAlertDialog(recordId, type);
        }
        return false;
    }

    private void showAlertDialog(final int recordId, final int type) {
        CustomCummunityDialog.Builder builder = new CustomCummunityDialog.Builder(this);
        builder.setMessage("确定删除次条目吗？");
        builder.setTitle("");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (recordId != 0) {
                    mPresenter.delRecord(recordId, type);
                }
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void showDelError(ErrorInfo info) {
        Log.d(TAG, "showDelError");
        CustomDialogHelper.createToastDialog(this, "删除失败！", 2000, true).show();
    }

    @Override
    public void showDelSuccess(int recordId) {
        Log.d(TAG, "showDelSuccess, recordId: "+recordId);
        Log.d(TAG, "showDelSuccess, mPostion: "+mPostion);
        CustomDialogHelper.createToastDialog(this, "删除成功！", 2000, true).show();
        if (mNewsList.size() >= (mPostion)) {
            mNewsList.remove(mPostion);
            mNewsRecordAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mClient, getIndexApiAction());
        String tag = "volley_get";
        if (!"".equals(tag)) {
            NetworkManager.getInstace().cancelRequest(tag);
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.disconnect();
    }

    @Override
    protected void onDestroy() {
        // April.getInstance().getEventBus().unregister(NewsdActivity.this);
        super.onDestroy();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int pid = position - 1;
        String url = mNewsList.get(pid).getAddress();
        Log.d(TAG, "pid: " + pid);
        Log.d(TAG, "url: " + url);
        if (!TextUtils.isEmpty(url) && url.startsWith("http://")) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            mNewsList.get(pid).setIsRead(1);// TODO 把状态更新到云端
            mNewsRecordAdapter.updateListView(mNewsList);
        }
    }

    @Override
    public void setPresenter(NewsContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
