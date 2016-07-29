package com.living.adapter;

import android.content.Context;

import com.living.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;


/**
 * Author：燕青 $ on 16/7/25 14:38
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class GitWidgetAdapter extends CommonAdapter<String> {

    public GitWidgetAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String o, int position) {
        holder.setText(R.id.tv_name, o);
    }
}
