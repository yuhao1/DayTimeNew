package com.kunya.daytime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kunya.daytime.bean.TimeBean;
import com.kunya.daytime.timeconditions.OptionsPickerView;
import com.kunya.daytime.timeconditions.model.IPickerViewData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView tvOptions;
    View vMasker;
    OptionsPickerView pvOptions;
    private ArrayList<TimeBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<IPickerViewData>>> options3Items = new ArrayList<>();

    String result="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        tvOptions = (TextView) findViewById(R.id.tvOptions);
        vMasker = findViewById(R.id.vMasker);


        //选项选择器
        pvOptions = new OptionsPickerView(this);
        //选项1
//        options1Items.add(new TimeBean("现在"));
        options1Items.add(new TimeBean("今天"));
        options1Items.add(new TimeBean("明天"));
        options1Items.add(new TimeBean("后天"));

        //选项 1 2
//        ArrayList<String> options2Items_01=new ArrayList<>();
//        options2Items_01.add("--");
        ArrayList<String> options2Items_02= Methods.getTodayHourData();
        ArrayList<String> options2Items_03= Methods.getHourData();
        ArrayList<String> options2Items_04= Methods.getHourData();

//        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);
        options2Items.add(options2Items_04);

        //选项3
        ArrayList<ArrayList<IPickerViewData>> options3Items_01 = new ArrayList<>();
        ArrayList<ArrayList<IPickerViewData>> options3Items_02 = new ArrayList<>();
        ArrayList<ArrayList<IPickerViewData>> options3Items_03 = new ArrayList<>();
        ArrayList<ArrayList<IPickerViewData>> options3Items_04 = new ArrayList<>();

//        ArrayList<IPickerViewData> options3Items_01_01=new ArrayList<>();
//        options3Items_01_01.add(new PickerViewData("--"));
//        options3Items_01.add(options3Items_01_01);
        options3Items_02 =Methods.getmD2();
        options3Items_03 =Methods.getmD();
        options3Items_04 =Methods.getmD();

//        options3Items.add(options3Items_01);
        options3Items.add(options3Items_02);
        options3Items.add(options3Items_03);
        options3Items.add(options3Items_04);

        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
//        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle(" ");
        pvOptions.setCyclic(false, false, false);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(0, 0, 0);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                //今天明天后天
                String days=options1Items.get(options1).getPickerViewText();
                //0-23点
                String when=options2Items.get(options1).get(option2);
                //0-50分
                String points=options3Items.get(options1).get(option2).get(options3).getPickerViewText();

                result="";
                if(days.equals("今天")){
                    //-1是昨天，0是今天，1是明天依次类推
                    result= Methods.getmoutianMD(0);
                }else if(days.equals("明天")){
                    result= Methods.getmoutianMD(1);
                }else if(days.equals("后天")){
                    result= Methods.getmoutianMD(2);
                }
                if(when.length()==2){
                    result+=" 0"+when.substring(0, 1)+":";
                }else if(when.length()==3){
                    result+=" "+when.substring(0, 2)+":";
                }
                if(points.length()==2){
                    result+="0"+points.substring(0, 1);
                }else if(points.length()==3){
                    result+=points.substring(0, 2);
                }

//                String tx = options1Items.get(options1).getPickerViewText()
//                        + options2Items.get(options1).get(option2)
//                        + options3Items.get(options1).get(option2).get(options3).getPickerViewText();

                tvOptions.setText(result);
                vMasker.setVisibility(View.GONE);
            }
        });
        //点击弹出选项选择器
        tvOptions.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }

}
