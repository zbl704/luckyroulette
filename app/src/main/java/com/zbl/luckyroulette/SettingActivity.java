package com.zbl.luckyroulette;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by benlongzhu on 2018/2/1.
 * <p>
 * 设置页面
 */

public class SettingActivity extends AppCompatActivity {

    // listview
    private RecyclerView setlist;

    // list适配器
    private MyAdapter myAdapter;

    // 数据体
    private ArrayList<DataList> data;

    // 取消按钮
    private TextView set_cancel;

    // 确定按钮
    private TextView set_ok;

    // 返回的结果码
    private int REQUESTCODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_setting);

        initView();
    }

    // 初始化view
    private void initView() {
        setlist = (RecyclerView) findViewById(R.id.setlist);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        setlist.setLayoutManager(mLayoutManager);
        myAdapter = new MyAdapter(this, getData());
        setlist.setAdapter(myAdapter);

        set_cancel = (TextView) findViewById(R.id.set_cancel);
        set_ok = (TextView) findViewById(R.id.set_ok);

        clickListener();

    }


    /**
     * 点击事件监听
     */
    private void clickListener() {
        set_cancel.setOnClickListener(clickListener);
        set_ok.setOnClickListener(clickListener);
    }


    /**
     * 点击事件接口
     */
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.set_cancel:

                    Toast.makeText(SettingActivity.this, "取消...", Toast.LENGTH_SHORT).show();

                    REQUESTCODE = -1;

                    break;

                case R.id.set_ok:

                    Toast.makeText(SettingActivity.this, "确定...", Toast.LENGTH_SHORT).show();

                    REQUESTCODE = 1;

                    break;
            }

        }
    };


    /**
     * 添加默认数据
     */
    private ArrayList<DataList> getData() {

        data = new ArrayList<>();

        data.add(0, new DataList("左边的喝一杯", R.mipmap.p_xiaolian));
        data.add(1, new DataList("喝一杯", R.mipmap.p_xiaolian));
        data.add(2, new DataList("真心话(任意人提问)", R.mipmap.p_xiaolian));
        data.add(3, new DataList("喝一瓶", R.mipmap.p_xiaolian));
        data.add(4, new DataList("再来一次", R.mipmap.p_xiaolian));
        data.add(5, new DataList("亲右边的异性", R.mipmap.p_xiaolian));

        return data;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (event.getKeyCode()) {

            case KeyEvent.KEYCODE_BACK:

                // 保存当前修改
                saveEdit();

                break;

        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 保存修改后的数据
     */
    private void saveEdit() {

        if (REQUESTCODE == 1) {
            Intent intent = new Intent();
            intent.setClass(SettingActivity.this, MainActivity.class);
            intent.putExtra("data", myAdapter.getData());
            setResult(REQUESTCODE, intent);
        }

        finish();

    }

}
