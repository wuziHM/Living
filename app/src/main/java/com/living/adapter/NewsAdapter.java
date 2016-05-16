package com.living.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.living.R;
import com.living.bean.NewsSearchBean;
import com.living.util.DensityUtils;
import com.living.util.ImageUtil;
import com.living.util.LogUtil;
import com.living.util.ScreenUtils;
import com.living.util.glide.GlideImageUtil;
import com.living.util.glide.GlideRoundTransform;
import com.living.widget.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

/**
 * author： Tobin on 2016-04-27.
 */
public class NewsAdapter extends RecyclerView.Adapter {
    private final int TYPE_NORMAL = 0;
    private final int TYPE_FOOT = 1;
    private Context mContext;
    private List<NewsSearchBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> mDatas  = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private boolean isLoading;
    private String mError = null;

    private int space;

    public NewsAdapter(Context context) {
        this.mContext = context;
        // 控件之间的间距之和，屏幕的宽度减去这个间距再除以3，得到每个ImageView的宽度
        space = DensityUtils.dp2px(context,40);
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public void addDatas(List<NewsSearchBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> datas){
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void setDatas(List<NewsSearchBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> datas){
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOT;
        }
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_listview_item, parent, false);
            return new ItemViewHolder(view);
        }
        if (viewType == TYPE_FOOT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item_footer, parent, false);
            return new FootHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).title.setText(mDatas.get(position).getTitle());
            ((ItemViewHolder) holder).desc.setText(mDatas.get(position).getDesc());
            ((ItemViewHolder) holder).date.setText(mDatas.get(position).getPubDate());
            ((ItemViewHolder) holder).source.setText(mDatas.get(position).getSource());

            ViewGroup.LayoutParams layoutParams = ((ItemViewHolder) holder).iv_new_image1.getLayoutParams();
            layoutParams.width = (ScreenUtils.getScreenWidth(mContext) - space) / 3;
            layoutParams.height = (ScreenUtils.getScreenWidth(mContext) - space) / 3;
            ((ItemViewHolder) holder).iv_new_image1.setLayoutParams(layoutParams);
            ((ItemViewHolder) holder).iv_new_image2.setLayoutParams(layoutParams);
            ((ItemViewHolder) holder).iv_new_image3.setLayoutParams(layoutParams);

            if (mDatas.get(position).getImageurls() != null && mDatas.get(position).getImageurls().size() > 0 ){
                if(mDatas.get(position).getImageurls().size() >= 3){
                    ((ItemViewHolder) holder).ll_news_images.setVisibility(View.VISIBLE);
                    GlideImageUtil.setBigPhotoFast((Activity)mContext, new GlideRoundTransform(mContext),mDatas.get(position).getImageurls().get(0).getUrl(),((ItemViewHolder) holder).iv_new_image1, ImageUtil.PhotoType.BIG);
                    GlideImageUtil.setBigPhotoFast((Activity)mContext, new GlideRoundTransform(mContext),mDatas.get(position).getImageurls().get(1).getUrl(),((ItemViewHolder) holder).iv_new_image2, ImageUtil.PhotoType.BIG);
                    GlideImageUtil.setBigPhotoFast((Activity)mContext, new GlideRoundTransform(mContext),mDatas.get(position).getImageurls().get(2).getUrl(),((ItemViewHolder) holder).iv_new_image3, ImageUtil.PhotoType.BIG);
                }else{
                    ((ItemViewHolder) holder).ll_news_images.setVisibility(View.GONE);
                }
                for(int i=0;i<mDatas.get(position).getImageurls().size();i++){
                    LogUtil.e("tobin onBindViewHolder" + mDatas.get(position).getImageurls().get(i).getUrl() + "//position:" + position+ "//size: " + mDatas.get(position).getImageurls().size());
                }
            }else{
                LogUtil.e("tobin onBindViewHolder" + "getImageurls is null //position: " + position);
            }

            // 如果设置了回调，则设置点击事件
            if (mOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemClick(holder.itemView, pos);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickListener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }
        }
        if (holder instanceof FootHolder) {
            ((FootHolder) holder).foot.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            if (mError != null) {
                ((FootHolder) holder).progressBar.setVisibility(View.GONE);
                ((FootHolder) holder).message.setText(mError);
            } else {
                ((FootHolder) holder).progressBar.setVisibility(View.VISIBLE);
                ((FootHolder) holder).message.setText("加载中....");
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size()+1;
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_news_images;
        ImageView iv_new_image1;
        ImageView iv_new_image2;
        ImageView iv_new_image3;

        TextView title;
        TextView desc;
        TextView date;
        TextView source;

        public ItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_new_title);
            desc = (TextView) itemView.findViewById(R.id.tv_desc);
            date = (TextView) itemView.findViewById(R.id.tv_pubDate);
            source = (TextView) itemView.findViewById(R.id.tv_source);
            ll_news_images =(LinearLayout) itemView.findViewById(R.id.ll_news_images);
            iv_new_image1 =(ImageView) itemView.findViewById(R.id.iv_new_image1);
            iv_new_image2 =(ImageView) itemView.findViewById(R.id.iv_new_image2);
            iv_new_image3 =(ImageView) itemView.findViewById(R.id.iv_new_image3);
        }
    }

    class FootHolder extends RecyclerView.ViewHolder {
        LinearLayout foot;
        ProgressWheel progressBar;
        TextView message;
        public FootHolder(View itemView) {
            super(itemView);
            foot = (LinearLayout) itemView.findViewById(R.id.item_news_foot);
            progressBar = (ProgressWheel) itemView.findViewById(R.id.item_news_progressbar);
            message = (TextView) itemView.findViewById(R.id.item_news_message);
        }
    }


    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public List<NewsSearchBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> getDatas() {
        return mDatas;
    }

    public String getmError() {
        return mError;
    }

    public void setmError(String mError) {
        this.mError = mError;
    }

}
