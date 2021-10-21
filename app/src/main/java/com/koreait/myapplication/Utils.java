package com.koreait.myapplication;

import android.widget.Toast;

public class Utils {

    public static int perseStringToInt(String val) {
        return parseStringToInt(val, 0);
    }

    public static int parseStringToInt(String val, int defVal){
        try{
            return Integer.parseInt(val);
        } catch (Exception e) {
            return defVal;
        }
    }
    public static String getNumberComma(int val) {
        return String.format("%,d",val);
    }
    public static String getNumberComma(String val) {
        return String.format(getNumberComma(perseStringToInt(val)));
    }
}
