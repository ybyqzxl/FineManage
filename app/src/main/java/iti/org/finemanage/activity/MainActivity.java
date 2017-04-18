package iti.org.finemanage.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import iti.org.finemanage.R;
import iti.org.finemanage.base.BaseActivity;
import iti.org.finemanage.callback.StudentInfoCallback;
import iti.org.finemanage.callback.YearCallBack;
import iti.org.finemanage.constant.Constant;
import iti.org.finemanage.entity.BaseJson;
import iti.org.finemanage.entity.StudentInfo;
import iti.org.finemanage.entity.YearJson;
import iti.org.finemanage.fragment.StudentDetailFragment;
import iti.org.finemanage.fragment.StudentMainFragment;
import iti.org.finemanage.fragment.StudentRadarScoreFragment;
import iti.org.finemanage.service.BaseService;
import iti.org.finemanage.utils.LogUtil;
import iti.org.finemanage.utils.NetWorkUtils;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by xueli on 2017/1/11.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.main_toolbar)
    Toolbar mMainToolbar; 
    @BindView(R.id.userName)
    TextView mUserName;
    @BindView(R.id.userCode)
    TextView mUserCode;
    @BindView(R.id.userCollege)
    TextView mUserCollege;
    @BindView(R.id.tv_term)
    TextView mTvTerm;


    private String mYearId = "";

    //year年份
    private String[] years;

    //学期集合
    private List<YearJson> mYearJsons = new ArrayList<>();

    //title
    private String[] mTitles = {"总览", "详细", "图表"};
    //首页
    private StudentMainFragment mStudentMainFragment;
    //详情
    private StudentDetailFragment mStudentDetailFragment;
    //雷达图
    private StudentRadarScoreFragment mStudentRadarScoreFragment;

    private List<Fragment> mFragments = new ArrayList<>();

    private TabAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finemanage_main);
        ButterKnife.bind(this);
        mMainToolbar.setTitle("第二课堂");
        setSupportActionBar(mMainToolbar);
        if (!NetWorkUtils.isNetworkAvailable()) {
            Toast.makeText(this, "网络不可用，请检查网络设置！", Toast.LENGTH_SHORT).show();
            return;
        }
        initTerm();
        //初始化学生信息
        initUserInfo();
        initViewPager();
    }

    //初始化学生信息
    private void initUserInfo() {
        OkHttpUtils.post().url(BaseService.SECOND_STUDENT_INFO).build().execute(new StudentInfoCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MainActivity.this, "获取信息失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(StudentInfo response, int id) {
                LogUtil.i("MainActivity", response.getResponResult());
                if (response.getResponState().equals(Constant.HttpResponseState.REQ_SUCCESS.name())) {
                    mUserName.setText(response.getUserName());
                    mUserCollege.setText(response.getUserCollege());
                    mUserCode.setText(response.getUserCode());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    private void initViewPager() {

        mStudentMainFragment = StudentMainFragment.newInstance();
        mStudentDetailFragment = StudentDetailFragment.newInstance();
        mStudentRadarScoreFragment = StudentRadarScoreFragment.newInstance();
        mTablayout.addTab(mTablayout.newTab().setText(mTitles[0]));
        mTablayout.addTab(mTablayout.newTab().setText(mTitles[1]));
        mTablayout.addTab(mTablayout.newTab().setText(mTitles[2]));
        mFragments.add(mStudentMainFragment);
        mFragments.add(mStudentDetailFragment);
        mFragments.add(mStudentRadarScoreFragment);
        mAdapter = new TabAdapter(getSupportFragmentManager());
        mViewpager.setAdapter(mAdapter);
        mViewpager.setOffscreenPageLimit(3);
        mTablayout.setupWithViewPager(mViewpager, true);

    }

    class TabAdapter extends FragmentPagerAdapter {

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.term:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请选择学期");
                builder.setItems(years, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mTvTerm.setText(years[i]);
                        mYearId = mYearJsons.get(i).getId();
                        mStudentMainFragment.initEveryScore(mYearId);
                        mStudentDetailFragment.initData(mYearId);
                        mStudentRadarScoreFragment.initRadarChartScore(mYearId);
                        dialogInterface.dismiss();
                    }
                });
                builder.create();
                builder.show();
                break;
            case R.id.logout:
                new AlertDialog.Builder(this).setTitle("退出登录").setMessage("您确定要退出登录吗？").setPositiveButton("确定", new
                        DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(MainActivity.this, LoginActivity.class).putExtra("logout",
                                        true));
                                MainActivity.this.finish();
                            }
                        }).setNegativeButton("取消", null).create().show();

                break;
        }
        return true;
    }


    //初始化学期
    private void initTerm() {
        OkHttpUtils.post().url(BaseService.INIT_YEAR).build().execute(new YearCallBack() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MainActivity.this, "获取学期失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(BaseJson response, int id) {
                if (response.getResponState().equals(Constant.HttpResponseState.REQ_SUCCESS.name())) {
                    mYearJsons = new Gson().fromJson(response.getResponResult(), new TypeToken<List<YearJson>>() {
                    }.getType());
                    LogUtil.i("Mainactivity", mYearJsons.get(0).getYear());
                    if (mYearJsons.size() > 0 && mYearJsons != null) {
                        initYears(mYearJsons);
                    }
                }

            }
        });
    }


    //初始化年份
    private void initYears(List<YearJson> yearJsons) {
        int num = yearJsons.size();
        List<String> values = new LinkedList<>();
        if (yearJsons != null && num > 0) {
            for (int i = 0; i < num; i++) {
                values.add(yearJsons.get(i).getYear() + (yearJsons.get(i).getSemester().equals("0") ? "春" : "秋"));
            }
        }
        years = values.toArray(new String[values.size()]);
        for (int i = 0; i < yearJsons.size(); i++) {
            if (!yearJsons.get(i).getYearState().equals("1")) {
                continue;
            } else {
                mTvTerm.setText(years[i]);
            }
        }
    }
}
