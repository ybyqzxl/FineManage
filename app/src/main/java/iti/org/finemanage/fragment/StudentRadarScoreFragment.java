package iti.org.finemanage.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import iti.org.finemanage.R;
import iti.org.finemanage.callback.RadarScoreCallback;
import iti.org.finemanage.constant.Constant;
import iti.org.finemanage.entity.RadarScore;
import iti.org.finemanage.service.BaseService;
import iti.org.finemanage.view.RadarMarkerView;
import okhttp3.Call;

/**
 * Created by xueli on 2017/1/12.
 */

public class StudentRadarScoreFragment extends Fragment {


    @BindView(R.id.radar_score_chart)
    RadarChart mRadarScoreChart;
    @BindView(R.id.ll_empty_view)
    LinearLayout mLlEmptyView;


    private String mYearId = "";

    //学院雷达分数
    private List<Float> mCollegeRadarScores = new ArrayList<>();

    //学校雷达分数
    private List<Float> mSchoolRadarScores = new ArrayList<>();

    //个人雷达分数
    private List<Float> mStudentRadarScores = new ArrayList<>();

    //雷达学科名称
    private List<String> mSubjectNames = new ArrayList<>();

    public static StudentRadarScoreFragment newInstance() {
        return new StudentRadarScoreFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_radar, container, false);
        ButterKnife.bind(this, view);
        initRadarChartScore(mYearId);
        return view;
    }

    //初始化雷达图控件
    private void initRadarView() {

        mRadarScoreChart.setBackgroundColor(Color.WHITE);
        //雷达图描述
        Description description = new Description();
        description.setText("分数图");
        description.setTextColor(Color.BLACK);
        //设置雷达图描述
        mRadarScoreChart.setDescription(description);
        mRadarScoreChart.setWebColor(Color.LTGRAY);
        //设置雷达放射线条宽度
        mRadarScoreChart.setWebLineWidth(1f);
        mRadarScoreChart.setWebColorInner(Color.LTGRAY);
        //设置雷达圆弧线条宽度
        mRadarScoreChart.setWebLineWidthInner(1.0f);
        //设置雷达透明度
        mRadarScoreChart.setWebAlpha(100);

        //点击时候弹出对应布局的显示数据
        MarkerView markerView = new RadarMarkerView(getActivity(), R.layout.radar_markview);
        markerView.setChartView(mRadarScoreChart);
        mRadarScoreChart.setMarker(markerView);
        //禁止旋转
        mRadarScoreChart.setRotationEnabled(false);

        mRadarScoreChart.animateXY(
                1400, 1400,
                Easing.EasingOption.EaseInOutQuad,
                Easing.EasingOption.EaseInOutQuad);

        //x坐标轴
        XAxis xAxis = mRadarScoreChart.getXAxis();
        xAxis.setTextSize(8f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        final String[] subjectName = mSubjectNames.toArray(new String[mSubjectNames.size()]);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return subjectName[(int) value % subjectName.length];
            }
        });

        xAxis.setTextColor(Color.BLACK);

        //Y轴坐标系
        YAxis yAxis = mRadarScoreChart.getYAxis();
        yAxis.setLabelCount(10, true);
        yAxis.setTextSize(6f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(4f);
        yAxis.setDrawLabels(true);

        Legend l = mRadarScoreChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(10f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.BLACK);
    }

    private void initRadarScore() {

        initRadarView();
        setData();

    }

    //填充雷达数据
    private void setData() {
        ArrayList<RadarEntry> studentEntry = new ArrayList<>();
        ArrayList<RadarEntry> schoolEntry = new ArrayList<>();
        ArrayList<RadarEntry> collegeEntry = new ArrayList<>();
        for (int i = 0; i < mSubjectNames.size(); i++) {
            studentEntry.add(new RadarEntry(mStudentRadarScores.get(i)));
            schoolEntry.add(new RadarEntry(mSchoolRadarScores.get(i)));
            collegeEntry.add(new RadarEntry(mCollegeRadarScores.get(i)));
        }

        RadarDataSet studentSet = new RadarDataSet(studentEntry, "学生平均分");
        RadarDataSet schoolSet = new RadarDataSet(schoolEntry, "学校平均分");
        RadarDataSet collegeSet = new RadarDataSet(collegeEntry, "学院平均分");
        //学生线条填充
        studentSet.setColor(Color.rgb(231, 203, 178));
        studentSet.setFillColor(Color.rgb(231, 203, 178));
        studentSet.setDrawFilled(true);
        studentSet.setFillAlpha(180);
        studentSet.setLineWidth(2f);
        studentSet.setDrawHighlightCircleEnabled(true);
        studentSet.setDrawHighlightIndicators(false);
        //学校线条填充
        schoolSet.setColor(Color.rgb(121, 162, 175));
        schoolSet.setFillColor(Color.rgb(121, 162, 175));
        schoolSet.setDrawFilled(true);
        schoolSet.setFillAlpha(180);
        schoolSet.setLineWidth(2f);
        schoolSet.setDrawHighlightCircleEnabled(true);
        schoolSet.setDrawHighlightIndicators(false);

        //学院线条填充
        collegeSet.setColor(Color.rgb(218, 247, 196));
        collegeSet.setFillColor(Color.rgb(218, 247, 196));
        collegeSet.setDrawFilled(true);
        collegeSet.setFillAlpha(180);
        collegeSet.setLineWidth(2f);
        collegeSet.setDrawHighlightCircleEnabled(true);
        collegeSet.setDrawHighlightIndicators(false);

        //将数据添加至雷达点数据集
        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(studentSet);
        sets.add(schoolSet);
        sets.add(collegeSet);
        RadarData data = new RadarData(sets);
        data.setValueTextSize(6f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.BLACK);
        mRadarScoreChart.setData(data);
        mRadarScoreChart.invalidate();
    }


    //初始化雷达分数图
    public void initRadarChartScore(String yearId) {
        OkHttpUtils.post().url(BaseService.STUDNET_RADAR_SCORE).addParams("yearId", yearId).build().execute(new RadarScoreCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(getActivity(), "获取雷达图分数失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(RadarScore response, int id) {
                if (response.getResponState().equals(Constant.HttpResponseState.REQ_SUCCESS.name())) {
                    mSchoolRadarScores.clear();
                    mCollegeRadarScores.clear();
                    mStudentRadarScores.clear();
                    mSubjectNames.clear();
                    mLlEmptyView.setVisibility(View.GONE);
                    mRadarScoreChart.setVisibility(View.VISIBLE);
                    mSchoolRadarScores = new Gson().fromJson(response.getSchooRadarScore(), new TypeToken<List<Float>>
                            () {
                    }.getType());

                    mCollegeRadarScores = new Gson().fromJson(response.getCollegeRadarScore(), new
                            TypeToken<List<Float>>
                                    () {
                            }.getType());

                    mStudentRadarScores = new Gson().fromJson(response.getStudentRadarScore(), new
                            TypeToken<List<Float>>
                                    () {
                            }.getType());
                    mSubjectNames = new Gson().fromJson(response.getSubjectName(), new TypeToken<List<String>>() {
                    }
                            .getType());
                    if (mSubjectNames.size() > 0 && mCollegeRadarScores.size() > 0 && mStudentRadarScores.size()
                            > 0 && mCollegeRadarScores.size() > 0) {
                        initRadarScore();
                    } else {
                        mRadarScoreChart.clear();
                        mRadarScoreChart.setVisibility(View.GONE);
                        mLlEmptyView.setVisibility(View.VISIBLE);
                    }

                }
            }
        });
    }

}
