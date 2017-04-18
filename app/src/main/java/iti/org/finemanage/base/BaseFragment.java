package iti.org.finemanage.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xueli on 2017/1/12.
 */

//延迟加载Fragment
public abstract class BaseFragment extends Fragment {

    protected boolean mIsVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    //可见加载数据
    protected void onVisible() {
        initData();
    }

    //不可见无操作
    protected void onInvisible() {
    }

    protected abstract void initData();
}
