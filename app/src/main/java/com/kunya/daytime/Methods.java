package com.kunya.daytime;


import com.kunya.daytime.bean.PickerViewData;
import com.kunya.daytime.timeconditions.model.IPickerViewData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by 于浩 on 2018/9/18.
 *
 * @TODO:
 */

public class Methods {
    /**
     * 方法描述:获取某天，-1是昨天，0是今天，1是明天依次类推
     *
     * @return 返回值描述
     * @throws <异常类型> {@inheritDoc} 异常描述
     * @param参数描述 格式M月dd日这样的话显示的就是3月12日，否则 MM月dd日格式会选择03月12日
     */

    public static String getmoutianMD(int i) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        String dateString = "";
        try {
            calendar.add(calendar.DATE, i);//把日期往后增加一天.整数往后推,负数往前移动
            date = calendar.getTime(); //这个时间就是日期往后推一天的结果
            dateString = formatter.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;


    }


    /**
     * 今天 点
     */
    public static ArrayList<String> getTodayHourData() {
        int max = currentHour();
        if (max < 23 && currentMin() > 45) {
            max = max + 1;
        }
        ArrayList<String> lists = new ArrayList<>();
        for (int i = max; i < 24; i++) {
            lists.add(i + "点");
        }
        return lists;
    }

    /**
     * 明天 后天 点
     */
    public static ArrayList<String> getHourData() {
        ArrayList<String> lists = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            lists.add(i + "点");
        }
        return lists;
    }

    /**
     * 明天 后天  分
     */
    public static ArrayList<IPickerViewData> getMinData() {
        ArrayList<IPickerViewData> dataArrayList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            dataArrayList.add(new PickerViewData((i * 10) + "分"));
        }
        return dataArrayList;
    }


    /**
     * 明天 后天
     */
    public static ArrayList<ArrayList<IPickerViewData>> getmD() {
        ArrayList<ArrayList<IPickerViewData>> d = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            d.add(getMinData());
        }
        return d;
    }

    /**
     * 明天 后天  2222
     */
    public static ArrayList<ArrayList<IPickerViewData>> getmD2() {
        //14
        int max = currentHour();
        if (currentMin() > 45) {
            max = max + 1;
        }
        int value = 24 - max;
        ArrayList<ArrayList<IPickerViewData>> d = new ArrayList<>();
        for (int i = 0; i < value; i++) {
            if (i == 0) {
                d.add(getTodyMinData());
            } else {
                d.add(getMinData());
            }

        }
        return d;
    }


    /**
     * 明天 后天  分2222
     */
    public static ArrayList<IPickerViewData> getTodyMinData() {

        int min = currentMin();
        int current = 0;
        if (min > 35 && min <= 45) {
            current = 0;
        } else if (min > 45 && min <= 55) {
            current = 1;
        } else if (min > 55) {
            current = 2;
        } else if (min <= 5) {
            current = 2;
        } else if (min > 5 && min <= 15) {
            current = 3;
        } else if (min > 15 && min <= 25) {
            current = 4;
        } else if (min > 25 && min <= 35) {
            current = 5;
        }
        int max = currentHour();
        if (max > 23 && min > 35) {
            current = 5;
        }

        ArrayList<IPickerViewData> dataArrayList = new ArrayList<>();
        for (int i = current; i < 6; i++) {
            dataArrayList.add(new PickerViewData((i * 10) + "分"));
        }
        return dataArrayList;
    }


    public static int currentMin() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MINUTE);
    }


    public static int currentHour() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.HOUR_OF_DAY);
    }

}
