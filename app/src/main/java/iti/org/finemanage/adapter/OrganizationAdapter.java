package iti.org.finemanage.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import iti.org.finemanage.R;
import iti.org.finemanage.entity.OrganizationJson;

/**
 * Created by Aleck_ on 2017/1/11.
 */

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.OrganizationViewHolder> {

    private List<OrganizationJson> mDetailList;

    private View emptyView;

    public static final int EMPTY = 1;


    @Override
    public int getItemViewType(int position) {
        if (mDetailList.size() <= 0) {
            return EMPTY;
        }
        return super.getItemViewType(position);
    }

    /**
     * 构造方法实现初始化数据列表
     *
     * @param detailList
     */
    public OrganizationAdapter(List<OrganizationJson> detailList) {
        this.mDetailList = detailList;
    }

    //创建新View，被Layoutmanager所调用
    @Override
    public OrganizationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (EMPTY == viewType) {
            emptyView = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, parent, false);
            return new OrganizationViewHolder(emptyView);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_finemanage, parent, false);
        OrganizationViewHolder vh = new OrganizationViewHolder(view);
        return vh;
    }

    //将数据和界面绑定在一起
    @Override
    public void onBindViewHolder(OrganizationViewHolder holder, int position) {

        if (getItemViewType(position) == EMPTY) {
            return;
        }
        //绑定数据
        OrganizationJson organizationJson = mDetailList.get(position);

        holder.mTvScore.setText(organizationJson.getOrScore() + "");
        holder.mTvOrganization.setText(organizationJson.getOrName());
        holder.mTvRole.setText(organizationJson.getOrPost());
        holder.mTvCategory.setText(organizationJson.getCaName());
        holder.mTvRank.setText(organizationJson.getRaName());

    }

    @Override
    public int getItemCount() {
        return mDetailList.size() > 0 ? mDetailList.size() : 1;
    }

    //自定义的ViewHolder,持有每个Item的所有界面元素
    class OrganizationViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvScore;
        private TextView mTvOrganization;
        private TextView mTvRole;
        private TextView mTvCategory;
        private TextView mTvRank;

        public OrganizationViewHolder(View itemView) {
            super(itemView);
            if (itemView == emptyView) return;
            mTvScore = (TextView) itemView.findViewById(R.id.tv_detail_score);
            mTvOrganization = (TextView) itemView.findViewById(R.id.tv_detail_organization);
            mTvRole = (TextView) itemView.findViewById(R.id.tv_detail_role);
            mTvCategory = (TextView) itemView.findViewById(R.id.tv_detail_category);
            mTvRank = (TextView) itemView.findViewById(R.id.tv_detail_rank);
        }
    }
}
