package iti.org.finemanage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import iti.org.finemanage.R;
import iti.org.finemanage.adapter.ActivityAdapter;
import iti.org.finemanage.adapter.AwardAdapter;
import iti.org.finemanage.adapter.OrganizationAdapter;
import iti.org.finemanage.callback.StudentScoreCallback;
import iti.org.finemanage.constant.Constant;
import iti.org.finemanage.entity.ActivityJson;
import iti.org.finemanage.entity.AwardJson;
import iti.org.finemanage.entity.OrganizationJson;
import iti.org.finemanage.entity.StudentScore;
import okhttp3.Call;

import static iti.org.finemanage.service.BaseService.STUDENT_ACTIVITY;
import static iti.org.finemanage.service.BaseService.STUDENT_AWARD;
import static iti.org.finemanage.service.BaseService.STUDENT_ORGANIZATION;

/**
 * Created by xueli on 2017/1/11.
 */

public class StudentDetailFragment extends Fragment {

    @BindView(R.id.rv_Organization)
    RecyclerView mRvOrganization;
    @BindView(R.id.rv_Culture)
    RecyclerView mRvCulture;
    @BindView(R.id.rv_Award)
    RecyclerView mRvAward;

    private String mYearId = "";     //当前所显示的年份学期id


    private List<OrganizationJson> mOrganizationJsons = new ArrayList<>();
    private List<ActivityJson> mActivityJsons = new ArrayList<>();
    private List<AwardJson> mAwardJsons = new ArrayList<>();

    private LinearLayoutManager manager;

    public static StudentDetailFragment newInstance() {
        return new StudentDetailFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        initData(mYearId);
        return view;
    }

    public void initData(String yearId) {
        loadDetail(STUDENT_ORGANIZATION + "?yearId=" + yearId, mRvOrganization, 0);
        loadDetail(STUDENT_ACTIVITY + "?yearId=" + yearId, mRvCulture, 1);
        loadDetail(STUDENT_AWARD + "?yearId=" + yearId, mRvAward, 2);
    }

    /**
     * 加载详情页数据
     *
     * @param url
     * @param recyclerView
     */
    public void loadDetail(String url, final RecyclerView recyclerView, final int flag) {
        OkHttpUtils.post().url(url).build()
                .execute(new StudentScoreCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "Detail获取分数失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(StudentScore response, int id) {
                        if (response.getResponState().equals(Constant.HttpResponseState.REQ_SUCCESS.name())) {
                            switch (flag) {
                                case 0:
                                    mOrganizationJsons.clear();
                                    mOrganizationJsons = new Gson().fromJson(response.getResponResult(), new
                                            TypeToken<List<OrganizationJson>>() {
                                            }.getType());
                                    manager = new LinearLayoutManager(getActivity()) {
                                        @Override
                                        public boolean canScrollVertically() {
                                            return false;
                                        }
                                    };
                                    recyclerView.setLayoutManager(manager);
                                    recyclerView.setAdapter(new OrganizationAdapter(mOrganizationJsons));
                                    break;
                                case 1:
                                    mActivityJsons.clear();
                                    mActivityJsons = new Gson().fromJson(response.getResponResult(), new
                                            TypeToken<List<ActivityJson>>() {
                                            }.getType());
                                    manager = new LinearLayoutManager(getActivity()) {
                                        @Override
                                        public boolean canScrollVertically() {
                                            return false;
                                        }
                                    };
                                    ;
                                    recyclerView.setLayoutManager(manager);
                                    recyclerView.setAdapter(new ActivityAdapter(mActivityJsons));
                                    break;
                                case 2:
                                    mAwardJsons.clear();
                                    mAwardJsons = new Gson().fromJson(response.getResponResult(), new
                                            TypeToken<List<AwardJson>>() {
                                            }.getType());

                                    manager = new LinearLayoutManager(getActivity()) {
                                        @Override
                                        public boolean canScrollVertically() {
                                            return false;
                                        }
                                    };
                                    ;
                                    recyclerView.setLayoutManager(manager);
                                    recyclerView.setAdapter(new AwardAdapter(mAwardJsons));
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                });
    }

}


