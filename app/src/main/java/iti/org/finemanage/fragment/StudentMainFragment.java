package iti.org.finemanage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import iti.org.finemanage.R;
import iti.org.finemanage.adapter.ScoreAdapter;
import iti.org.finemanage.callback.StudentInfoCallback;
import iti.org.finemanage.callback.StudentScoreCallback;
import iti.org.finemanage.callback.YearCallBack;
import iti.org.finemanage.constant.Constant;
import iti.org.finemanage.constant.Constant.HttpResponseState;
import iti.org.finemanage.entity.BaseJson;
import iti.org.finemanage.entity.OrderAndScore;
import iti.org.finemanage.entity.StudentInfo;
import iti.org.finemanage.entity.StudentScore;
import iti.org.finemanage.entity.YearJson;
import iti.org.finemanage.service.BaseService;
import iti.org.finemanage.utils.LogUtil;
import okhttp3.Call;

/**
 * Created by xueli on 2017/1/11.
 */

public class StudentMainFragment extends Fragment {

    private static final String TERM_YEAR = "termYear";


    @BindView(R.id.score_recyclerview)
    RecyclerView mScoreRecyclerview;

    private ScoreAdapter mScoreAdapter;
    private LinearLayoutManager mManager;
    private String mYearId = "";

    //分数集合
    private List<OrderAndScore> mOrderAndScores = new ArrayList<>();


    public static StudentMainFragment newInstance() {
        return new StudentMainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        mManager = new LinearLayoutManager(getActivity());
        mScoreAdapter = new ScoreAdapter(mOrderAndScores);
        mScoreRecyclerview.setLayoutManager(mManager);
        mScoreRecyclerview.setAdapter(mScoreAdapter);
        initEveryScore(mYearId);
        return view;
    }

    //初始化学生每项分数
    public void initEveryScore(String yearId) {
        OkHttpUtils.post().url(BaseService.STUDENT_EVERY_SCORE).addParams("yearId", yearId).build().execute(new StudentScoreCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(getActivity(), "获取分数失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(StudentScore response, int id) {
                if (response.getResponState().equals(HttpResponseState.REQ_SUCCESS.name())) {
                    LogUtil.i("MainActivity", response.getResponResult());
                    mOrderAndScores.clear();
                    mOrderAndScores = new Gson().fromJson(response.getResponResult(), new
                            TypeToken<List<OrderAndScore>>() {
                            }.getType());
                    // if (mOrderAndScores.size() > 0 && mOrderAndScores != null) {
                    mScoreAdapter.setAndScores(mOrderAndScores);
                    mScoreAdapter.notifyDataSetChanged();
                    // } else {
                    //     Toast.makeText(getActivity(), "暂无该年的分数", Toast.LENGTH_SHORT).show();
                    // }

                }
            }
        });

    }
}
