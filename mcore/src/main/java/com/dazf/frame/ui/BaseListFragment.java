package com.dazf.frame.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dazf.frame.R;
import com.dazf.frame.base.BasePresenter;
import com.dazf.frame.widget.pulltorefresh.RefreshLayout;
import com.dazf.frame.widget.status.PageState;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhuZiBo
 * @date 2018/1/16
 * 由于大部分列表刷新、加载更多逻辑一模一样，所以公用此通用逻辑
 */
public abstract class BaseListFragment<P extends BasePresenter> extends BaseFragment<P> implements IViewList {

    /**
     * 下拉刷新模式
     */
    public static final int MODE_PULL_DOWN_TO_REFRESH = 1234;
    /**
     * 上拉刷新模式
     */
    public static final int MODE_PULL_UP_TO_LOAD_MORE = 1235;
    /**
     * 上、下拉刷新模式均支持
     */
    public static final int MODE_BOTH = 1236;
    /**
     * 无刷新模式
     */
    public static final int MODE_NONE = 1237;


    private RecyclerView recyclerView;
    private List mDatas;
    private BaseQuickAdapter mAdapter;
    private int loadMoreSize = 10;   //默认为10
    private boolean isLoadMore = false;  //是否是加载更多
    private RefreshLayout refreshLayout;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_base_list;
    }


    /**
     * 开启状态布局
     *
     * @return
     */
    @Override
    protected boolean isBindStatusView() {
        return true;
    }

    @Override
    public void init() {
        refreshLayout = rootView.findViewById(R.id.refreshLayout);
        setPageState(PageState.LOADING);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        mDatas = new ArrayList();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = getAdapter();
        recyclerView.setAdapter(mAdapter);

        int mode = getMode();
        switch (mode) {
            case MODE_PULL_DOWN_TO_REFRESH:
                mAdapter.setEnableLoadMore(true);
                refreshLayout.setEnableRefresh(true);
                break;
            case MODE_PULL_UP_TO_LOAD_MORE:
                mAdapter.setEnableLoadMore(false);
                refreshLayout.setEnableRefresh(true);
                break;
            case MODE_BOTH:
                mAdapter.setEnableLoadMore(true);
                refreshLayout.setEnableRefresh(true);
                break;
            case MODE_NONE:
                mAdapter.setEnableLoadMore(false);
                refreshLayout.setEnableRefresh(false);
                break;
            default:
                break;
        }

        mAdapter.setOnLoadMoreListener(() -> {
            isLoadMore = true;
            loadMore();
        }, recyclerView);
        refreshLayout.setOnRefreshingListener(() -> {
            isLoadMore = false;
            refresh();
        });
        mAdapter.setOnItemClickListener((adapter, view, position) -> {

        });
        mAdapter.setOnItemLongClickListener((adapter, view, position) -> false);
    }

    protected void refresh() {
    }

    protected void loadMore() {
    }

    public int getLoadMorePageSize() {
        return this.loadMoreSize;
    }


    protected void updata() {
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 支持模式修改，默认为无刷新模式
     *
     * @return
     */
    protected int getMode() {
        return MODE_NONE;
    }


    /**
     * 带上下拉刷新的，获取到数据成功后的默认处理
     * 包括数据为空
     *
     * @param datas
     */
    public void showSuccessData(List datas) {
        if (datas != null && datas.size() > 0) {
            if (isLoadMore) {
                addData(datas);
                if (datas.size() < getLoadMorePageSize()) {
                    mAdapter.loadMoreEnd(isLoadMore);
                } else {
                    mAdapter.loadMoreComplete();
                }
                //复位标记状态
                isLoadMore = false;
            } else { //如果是刷新，需要先清除原有数据
                setData(datas);
                refreshLayout.refreshCompleted();
                //如果是支持加载更多
                if (getMode() == MODE_BOTH || getMode() == MODE_PULL_UP_TO_LOAD_MORE) {
                    if (datas.size() < getLoadMorePageSize()) {
                        mAdapter.loadMoreEnd(isLoadMore);
                    }
                }

            }
        } else {
            if (isLoadMore) {
                mAdapter.loadMoreEnd(isLoadMore);
            } else {
                refreshLayout.refreshCompleted();
                setPageState(PageState.EMPTY);
            }
            //复位标记状态
            isLoadMore = false;
        }
    }


    /**
     * 带上下拉刷新的，获取到数据失败后的默认处理
     */
    public void showErrorData() {
        if (isLoadMore) {
            mAdapter.loadMoreFail();
        } else {
            refreshLayout.refreshCompleted();
            setPageState(PageState.NO_NETWORK);
        }
    }


    public abstract BaseQuickAdapter getAdapter();

    public void setData(List data) {
        mAdapter.setNewData(data);
    }

    public void addData(List data) {
        mAdapter.addData(data);
    }

    public List getData() {
        return mDatas;
    }

}
