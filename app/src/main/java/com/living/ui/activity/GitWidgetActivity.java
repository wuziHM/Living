package com.living.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.living.R;
import com.living.adapter.DividerItemDecoration;
import com.living.adapter.GitWidgetAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * github上的控件
 */
public class GitWidgetActivity extends BaseAppCompatActivity {

    private RecyclerView rcWidget;
    private List list;
    private List<Class> clazz;
    private GitWidgetAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_widget);
        addData();
        rcWidget = (RecyclerView) findViewById(R.id.recycle_widget);
//        rcWidget.setLayoutManager(new LinearLayoutManager(this));
        rcWidget.setLayoutManager(new GridLayoutManager(this, 2));
        rcWidget.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter = new GitWidgetAdapter(this, R.layout.list_item_widget, list);
        rcWidget.setAdapter(adapter);

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                startActivity(new Intent(GitWidgetActivity.this, clazz.get(position)));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                return false;
            }
        });
    }

    private void addData() {
        list = new ArrayList();
        list.add("咻一咻");
        list.add("Collapsing");
        list.add("隐藏toolbar");
        clazz = new ArrayList();
        clazz.add(XiuActivity.class);
        clazz.add(CollapsingActivity.class);
        clazz.add(ToolbarHideActivity.class);
    }
}
