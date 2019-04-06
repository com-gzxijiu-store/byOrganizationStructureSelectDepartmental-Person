package com.example.zhonglang.myapplication;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
/*
*开源框架BaseRecyclerViewAdapterHelper使用——RecyclerView万能适配器
*用法自行百度
* */
public class orgAdapter extends BaseQuickAdapter<orgBean,BaseViewHolder> {
    public orgAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, orgBean item) {
    helper.setText(R.id.tv_menus_name,item.getName());
    }
}
