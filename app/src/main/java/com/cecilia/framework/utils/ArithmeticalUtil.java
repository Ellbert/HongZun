package com.cecilia.framework.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ArithmeticalUtil {

    private static final int DEF_DIV_SCALE=10;

    private ArithmeticalUtil(){}
    //相加
    public static double add(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.add(b2).doubleValue();

    }
    //相减
    public static double sub(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.subtract(b2).doubleValue();

    }
    //相乘
    public static double mul(double d1,double d2){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.multiply(b2).doubleValue();

    }
    //相除
    public static double div(double d1,double d2){

        return div(d1,d2,DEF_DIV_SCALE);

    }

    public static double div(double d1,double d2,int scale){
        if(scale<0){
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(d2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String getMoneyString(double d1){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(100));
        DecimalFormat df1 = new DecimalFormat("0.00");
        return "¥ " + df1.format(b1.divide(b2,2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    public static String getMoneyStringWithoutSymbol(double d1){
        BigDecimal b1=new BigDecimal(Double.toString(d1));
        BigDecimal b2=new BigDecimal(Double.toString(100));
        DecimalFormat df1 = new DecimalFormat("0.00");
        return df1.format(b1.divide(b2,2,BigDecimal.ROUND_HALF_UP).doubleValue());
    }

}
