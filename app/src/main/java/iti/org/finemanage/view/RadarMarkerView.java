package iti.org.finemanage.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import iti.org.finemanage.R;

/**
 * Created by xueli on 2017/1/13.
 */

public class RadarMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */

    private TextView mTvContent;


    public RadarMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        mTvContent = (TextView) findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        mTvContent.setText(e.getY() + "");
        mTvContent.setTextColor(Color.BLACK);
//        mTvContent.setTextColor(Color.WHITE);
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight() - 10);
    }
}
