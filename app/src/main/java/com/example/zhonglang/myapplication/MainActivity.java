package com.example.zhonglang.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;

public class MainActivity extends Activity {

    RecyclerView recyclerView;
    orgAdapter adapter;
    orgBean pitem;
    JSONArray array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        array = JSONArray.parseArray(orgData.getData());
        adapter = new orgAdapter(R.layout.select_item_layout);
        /*
        * 给item设置点击事件
        * */
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                orgBean item = (orgBean) adapter.getData().get(position);
                boolean isEndNOde = true;
                /*
                * 遍历列表，判断是不是叶子节点（没有节点的父节点id是当前节点的id）
                * 使用树结构或者本身item包含当前是否为叶子节点的自动这不需要这个循环
                * 使用树结构会提高效率列表逻辑简单
                * */
                for (int i = 0; i < array.size(); i++) {
                    orgBean item1 = (orgBean) JSONObject.parseObject(array.getString(i), orgBean.class);
                    /*
                    * 有节点的父节点id是当前节点的id（当前不是叶子节点）
                    * */
                    if (item1.getPId().equals(item.getId())) {
                        isEndNOde = false;
                        break;
                    }
                }
                if (isEndNOde) {
                    /*
                    * 得到选择数据就可以使用eventBus发消息通知需要的页面（这里只是弹个吐司）
                    * */
                    Toast.makeText(getApplicationContext(), "您选择了:"+item.getName(), Toast.LENGTH_SHORT).show();
//                    finish();
                    return;
                }
                /*
                * 不是叶子节点那么，当前节点作为父节点传入下一个选择页面并结束当前页面
                * */
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.putExtra("pItem", item);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        /*
        * 获取上一个页面传来的父节点
        * */
        pitem = (orgBean) getIntent().getSerializableExtra("pItem");
        /*
        * 如果没取到上个页面传来的父节点则默认重根节点开始
        * */
        if (pitem == null) {
            for (int i = 0; i < array.size(); i++) {
                orgBean item = (orgBean) JSONObject.parseObject(array.getString(i), orgBean.class);
                if (item.getPId().equals("0")) {
                    pitem = item;
                }
            }
        }
        /*
        * 通过循环找到当前父节点的所有子节点加入adapter（使用树结构会提高效率列表逻辑简单）
        * */
        for (int i = 0; i < array.size(); i++) {
            orgBean item = (orgBean) JSONObject.parseObject(array.getString(i), orgBean.class);
            if (item.getPId().equals(pitem.getId())) {
                adapter.addData(item);
            }
        }

        adapter.notifyDataSetChanged();
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBack();
        }
        return super.onKeyDown(keyCode, event);
    }
/*
* 返回上一个页面需要找到当前页面的父节点的父节点
* */
    private void onBack() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        if(!pitem.getPId().equals("0")) {
            for (int i = 0; i < array.size(); i++) {
                orgBean item = (orgBean) JSONObject.parseObject(array.getString(i), orgBean.class);
                if (item.getId().equals(pitem.getPId())) {
                    intent.putExtra("pItem", item);
                    break;
                }
            }
        }else {
            intent.putExtra("pItem", pitem);
        }
        startActivity(intent);
        finish();
    }
}
