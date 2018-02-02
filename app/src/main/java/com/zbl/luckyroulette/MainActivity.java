package com.zbl.luckyroulette;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Random;


/**
 * @author benlongzhu
 * @data 2018年02月01日
 * 轮盘抽奖主界面
 */
public class MainActivity extends AppCompatActivity {

    private LuckyPan mLuckyPan;
    private ImageView mStartBtn;
    private final static int REQUESTCODE = 1; // 返回的结果码
    //数据
    private ArrayList<DataList> mDataList;

    // 是否指定概率中奖： 默认是
    private boolean isLuckyer = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SettingActivity.class);
                startActivityForResult(intent, REQUESTCODE);
            }
        });

        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isLuckyer = true;
                return true;
            }
        });

        mLuckyPan = (LuckyPan) findViewById(R.id.id_luckyPan);
        mStartBtn = (ImageView) findViewById(R.id.id_start_btn);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mLuckyPan.isStart()) {
                    mLuckyPan.luckyStart(LuckProbability());
                    mStartBtn.setImageResource(R.mipmap.stop);
                } else {
                    if (!mLuckyPan.isShouldEnd()) {
                        mLuckyPan.luckyEnd();
                        mStartBtn.setImageResource(R.mipmap.start);
                    }
                }
            }
        });
    }


    /**
     * 是否是指定幸运中奖率 ： 默认是指定概率中奖
     * @return
     */
    public boolean isLucky() {
        return isLuckyer;
    }


    /**
     * 中奖率
     */
    private int LuckProbability() {

        if (!isLucky()) { // 返回默认中奖率
            int[] arr = {0,1,2,3,4,5};
            //产生0-(arr.length-1)的整数值,也是数组的索引
            int index = (int) (Math.random() * arr.length);
            int rand = arr[index];
            return rand;
        }

        // 指定概率中奖

        int min = 1;
        int max = 1000;
        Random random = new Random();
        int num = random.nextInt(max) % (max - min + 1) + min;

        // 返回0的概率（单反相机）
        if (num == 1 || num == 999) {
            return 0;
        }

        int min1 = 1;
        int max1 = 100;
        Random random1 = new Random();
        int num1 = random1.nextInt(max1) % (max1 - min1 + 1) + min1;
        // 返回1的概率（IPAD）
        if (num1 == 8 || num1 == 88) {
            return 1;
        }

        // 返回3的概率（iphone）
        if (num1 == 77 || num1 == 22) {
            return 3;
        }

        // 返回4的概率（衣服）
        if (num1 == 66 || num1 == 67) {
            return 4;
        }


        // 抽到笑脸 恭喜发财
        int[] arr = {2, 5};
        //产生0-(arr.length-1)的整数值,也是数组的索引
        int index = (int) (Math.random() * arr.length);
        int rand = arr[index];

        return rand;
    }


    /**
     * 设置手动设置轮盘上的内容 返回后的结果处理
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // RESULT_OK，判断另外一个activity已经结束数据输入功能，Standard activity result:  operation succeeded. 默认值是-1
        if (requestCode == REQUESTCODE && data != null) {
            mDataList = data.getParcelableArrayListExtra("data");
            if (mDataList != null) {
                isNotifyData(true);
            }
        }
    }

    /**
     * 是否更新数据
     */
    public void isNotifyData(boolean isNotif){
        if (isNotif){
            mLuckyPan.notifyData(mDataList);
        }
    }
}
