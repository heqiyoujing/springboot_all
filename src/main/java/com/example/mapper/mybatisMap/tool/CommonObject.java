package com.example.mapper.mybatisMap.tool;

import com.example.mapper.mybatisMap.constant.Constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: yiqq
 * @date: 2018/7/20
 * @description:
 */
public class CommonObject {
    /**
     * 获取当前时间
     */
    public static String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     * 获取手机号
     */
    public static String getTel() {
        int index=getNum(0, Constant.telFirst.length-1);
        String first=Constant.telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }
    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
    public static String name_sex = "";

    /**
     * 获取姓名
     */
    public static String getChineseName() {
        int index=getNum(0, Constant.firstName.length()-1);
        String first=Constant.firstName.substring(index, index+1);
        int sex=getNum(0,1);
        String str=Constant.boy;
        int length=Constant.boy.length();
        if(sex==0){
            str=Constant.girl;
            length=Constant.girl.length();
            name_sex = "女";
        }else {
            name_sex="男";
        }
        index=getNum(0,length-1);
        String second=str.substring(index, index+1);
        int hasThird=getNum(0,1);
        String third="";
        if(hasThird==1){
            index=getNum(0,length-1);
            third=str.substring(index, index+1);
        }
        return first+second+third;
    }
    /**
     * 返回Email
     */
    public static String getEmail(int lMin,int lMax) {
        int length=getNum(lMin,lMax);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = (int)(Math.random()*Constant.base.length());
            sb.append(Constant.base.charAt(number));
        }
        sb.append(Constant.email_suffix[(int)(Math.random()*Constant.email_suffix.length)]);
        return sb.toString();
    }
    /**
     * 返回地址
     */
    public static String getRoad() {
        int index=getNum(0,Constant.road.length-1);
        String first=Constant.road[index];
        String second=String.valueOf(getNum(11,150))+"号";
        String third="-"+getNum(1,20)+"-"+getNum(1,10);
        return first+second+third;
    }
}
