package com.joey.jframe.recycler;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.joey.base.BaseModel;
import com.joey.jframe.R;
import com.joey.ui.CheckedModel;
import com.joey.ui.adapter.BaseModelRecyclerAdapter;
import com.joey.ui.adapter.BaseRecyclerAdapter;
import com.joey.ui.general.BaseActivity;
import com.joey.ui.widget.BadgeView;
import com.joey.ui.widget.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joey on 2018/4/26.
 */

public class RVActivity extends BaseActivity {


    private RecyclerView rvMenu;
    private RVAdapter adapter;

    @Override
    public void onBindView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("网格测试");
        setContentView(R.layout.activity_recyler);
        rvMenu = findViewById(R.id.rv_menu);
        List<CheckedModel> models = new ArrayList<CheckedModel>();
        models.add(new CheckedModel("1111", "1111"));
        models.add(new CheckedModel("2222", "2222"));
        models.add(new CheckedModel("3333", "3333"));
        models.add(new CheckedModel("4444", "4444"));
        models.add(new CheckedModel("5555", "5555"));
        models.add(new CheckedModel("6666", "6666"));
        adapter = new RVAdapter(this, models, R.layout.list_item_recycler);
        rvMenu.setAdapter(adapter);
        rvMenu.setLayoutManager(new GridLayoutManager(this, 3));
        final SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
//        ImageView iv = findViewById(R.id.iv_bubble);
//        iv.setVisibility(View.GONE);
//        BadgeView view = new BadgeView(RVActivity.this);
//        view.setBadgeCount(5);
//        view.setTargetView(iv);
    }

    @Override
    public void initData() {

    }

    @Override
    public void registerListener() {

    }

    public class RVHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;
        View root;

        public RVHolder(View itemView) {
            super(itemView);
            root = itemView;
            textView = itemView.findViewById(R.id.tv_name);
        }
    }


    public class RVAdapter extends BaseModelRecyclerAdapter<CheckedModel, RVHolder> {

        public RVAdapter(Context context, List<CheckedModel> dataList, int layoutId) {
            super(context, dataList, layoutId);
        }

        @Override
        public RVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View convertView = null;
            convertView = LayoutInflater.from(
                    context).inflate(layoutId, parent,
                    false);
            return new RVHolder(convertView);
        }

        @Override
        public void onBindViewHolder(RVHolder holder, final int position) {
            final CheckedModel model = getItem(position);
            setText(holder.textView, model.getId());
            BadgeView view = new BadgeView(RVActivity.this);
            view.setBadgeCount(position);
            view.setTargetView(holder.imageView);

            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastHelper.show(RVActivity.this, String.format("%s:%d", model.getName(), position));
                }
            });
//            setImageView(holder.imageView, model.getName());
        }

    }

}
