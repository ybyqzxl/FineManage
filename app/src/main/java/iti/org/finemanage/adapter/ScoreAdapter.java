package iti.org.finemanage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import iti.org.finemanage.R;
import iti.org.finemanage.entity.OrderAndScore;

/**
 * Created by xueli on 2017/1/11.
 */

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private View emptyView;

    public static final int EMPTY = 1;

    private List<OrderAndScore> mAndScores;

    public void setAndScores(List<OrderAndScore> andScores) {
        mAndScores = andScores;
    }

    public ScoreAdapter(List<OrderAndScore> andScores) {
        mAndScores = andScores;
    }

    @Override
    public int getItemViewType(int position) {
        if (mAndScores.size() <= 0) {
            return EMPTY;
        }
        return super.getItemViewType(position);
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (EMPTY == viewType) {
            emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, parent, false);
            return new ScoreViewHolder(emptyView);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_every_score, parent, false);
        ScoreViewHolder viewHolder = new ScoreViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        if (getItemViewType(position) == EMPTY) {
            return;
        }
        OrderAndScore score = mAndScores.get(position);
        holder.mTvPymb.setText(score.getCatename());
        holder.mTvScore.setText(score.getScore());
        holder.mTvBjpm.setText(score.getClaorder());
        holder.mTvZypm.setText(score.getDeporder());

    }

    @Override
    public int getItemCount() {
        return mAndScores.size() > 0 ? mAndScores.size() : 1;
    }

    class ScoreViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvPymb;
        private TextView mTvScore;
        private TextView mTvBjpm;
        private TextView mTvZypm;

        public ScoreViewHolder(View itemView) {
            super(itemView);
            if (itemView == emptyView) return;
            mTvPymb = (TextView) itemView.findViewById(R.id.tv_pymb);
            mTvScore = (TextView) itemView.findViewById(R.id.tv_score);
            mTvBjpm = (TextView) itemView.findViewById(R.id.tv_bjpm);
            mTvZypm = (TextView) itemView.findViewById(R.id.tv_zypm);
        }
    }
}
