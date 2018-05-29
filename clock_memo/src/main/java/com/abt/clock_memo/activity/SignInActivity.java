package com.abt.clock_memo.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 打卡记录页面
 */
public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
//public class SignInActivity extends BaseActivity implements View.OnClickListener {

    private String TAG = SignInActivity.class.getSimpleName();
    private TextView mTitle;
    private LinearLayout mGoBack;
    private Button mSignInBtn;
    private ProgressDialog mDialog;

    @Override
    public void onClick(View v) {

    }

   /* private ListView mSignInListView;
    private SignInListAdapter mAdapter;
    private List<SignIn> mSignInList;

    private Dao<SignIn, Integer> stuDao;
    private SignIn mSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);
        StatusBarCompat.compat(this, 0xFF393A3E);

        onInitView();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //mSignInList = getRecordList();
        //mSignInList = FileHelper.getStorageEntities(GlobalConstant.FILE_NAME_SIGN_IN);
        mSignInList = FileManager.read(SignInActivity.this, GlobalConstant.FILE_NAME_SIGN_IN);
        if (mSignInList != null) {
            Log.d(TAG, "mSignInList: " + mSignInList.size());
            for (int i = 0; i < mSignInList.size(); i++) {
                Log.d(TAG, "mSignInList(" + i + ")-->time: " + mSignInList.get(i).getTime());
            }
        }
        mAdapter = new SignInListAdapter(this, mSignInList);
        mSignInListView.setAdapter(mAdapter);
        mSignInListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SignInActivity.this, "第" + (position + 1) + "条记录", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        String res = intent.getExtras().getString(GlobalConstant.SIGN_IN_OR_NOT);
        if ("yes".equalsIgnoreCase(res)) {
            handleSignIn();
            // 记录打卡次数
            int count = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_SIGN_IN_COUNT);
            PreferencesUtil.write(this, GlobalConstant.SHARE_KEY_SIGN_IN_COUNT, ++count);
        } else {
            // 记录浏览打卡记录次数
            int count = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_SIGN_IN_RECORD_COUNT);
            PreferencesUtil.write(this, GlobalConstant.SHARE_KEY_SIGN_IN_RECORD_COUNT, ++count);
        }
    }

    @Override
    protected void onInitView() {
        super.onInitView();

        mSignInBtn = (Button) findViewById(R.id.sign_in_again);
        mSignInBtn.setOnClickListener(this);

        mTitle = (TextView) findViewById(R.id.title_content);
        mTitle.setText("打卡记录");

        mGoBack = (LinearLayout) findViewById(R.id.go_back);
        mGoBack.setOnClickListener(this);

        mSignInListView = (ListView) findViewById(R.id.record_list);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                this.finish();
                break;
            case R.id.sign_in_again:
                handleSignIn();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocationUtil util = LocationUtil.getInstance();
        util.stopLocate();
    }

    private void handleSignIn() {
        Log.d(TAG, "handleSignIn()");
        // 处理前 Loading
        mDialog = new ProgressDialog(SignInActivity.this);
        mDialog.setTitle("正在打卡，请稍候...");
        mDialog.setCancelable(true);
        mDialog.show();
        Log.d(TAG, "Dialog showing...");

        // 处理中 Signing
        LocationUtil util = LocationUtil.getInstance();
        boolean res = util.signIn(this);
        Log.d(TAG, "signIn res: " + res);

        // 处理后
        SignIn in = new SignIn();
        long time = System.currentTimeMillis();
        String nickName = PreferencesUtil.getString(SignInActivity.this, GlobalConstant.SHARE_KEY_NICK_NAME);
        int count = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_SIGN_IN_COUNT) + 1;
        if (TextUtils.isEmpty(nickName)) {
            nickName = "第" + count + "条记录";
        }
        in.setName(nickName);
        in.setTime(time + "");
        if (res) { // 打卡成功
            // TODO 更新打卡记录
            in.setStatus("刷卡成功");
        } else {
            in.setStatus("刷卡失败");
            mSignInBtn.setVisibility(View.VISIBLE);
        }
        if (mSignInList != null) {
            mSignInList.add(0, in);
            mAdapter.notifyDataSetChanged();
            FileManager.write(SignInActivity.this, GlobalConstant.FILE_NAME_SIGN_IN, mSignInList);
            //FileHelper.saveStorage2SDCard(mSignInList, GlobalConstant.FILE_NAME_SIGN_IN);
            Log.d(TAG, "mAdapter.notifyDataSetChanged(): " + mSignInList.size());
        }
        if (mDialog != null) {
            mSignInBtn.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDialog.dismiss();
                }
            }, 1 * 1000);
        }
    }

    *//**
     * 查询记录项
     *//*
    *//*private void queryListViewItem() {
        try {
            stuDao = getHelper().getStudentDao();
            //查询所有的记录项
            mSignInList = stuDao.queryForAll();
            for (Iterator iterator = mSignInList.iterator(); iterator.hasNext();) {
                SignIn type = (SignIn) iterator.next();
                Log.i(TAG, type.getStuNO()+"    "+type.getName());


            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }*//*

    *//**
     * 查看记录项
     *
     * @param position
     *//*
    private void viewListViewItem(int position) {
        mSignIn = mSignInList.get(position);
        Intent intent = new Intent();
        intent.setClass(this, SignInActivity.class);
        intent.putExtra("action", "viewone");
        intent.putExtra("entity", mSignIn);
        startActivity(intent);
    }

    *//**
     * 编辑记录项
     *//*
    private void editListViewItem(int position) {
        mSignIn = mSignInList.get(position);
        Intent intent = new Intent();
        intent.setClass(this, SignInActivity.class);
        intent.putExtra("action", "edit");
        intent.putExtra("entity", mSignIn);
        startActivity(intent);
    }

    *//**
     * 删除记录项
     *
     * @param position
     *//*
    private void deleteListViewItem(int position) {
        final int pos = position;
        AlertDialog.Builder builder2 = new AlertDialog.Builder(SignInActivity.this);
        builder2.setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("警告")
                .setMessage("确定要删除该记录");
        builder2.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                SignIn mDelStudent = (SignIn) mSignInListView.getAdapter().getItem(pos);
                try {
                    stuDao.delete(mDelStudent); //删除记录
                    // queryListViewItem();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (java.sql.SQLException e) {
                    e.printStackTrace();
                }

            }
        });
        builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder2.show();
    }*/

}
