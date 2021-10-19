package com.koreait.myapplication;

import android.widget.Toast;

public class Utils {
    public static int perseStringToInt(String val) {

        int intVal = 0;
        //예외처리
        try {
            intVal = Integer.parseInt(val);



        } catch (Exception e) {
            // 예외가 발생되었을 때 실행하고 싶은 것을 여기에다 작성.
            e.printStackTrace(); // 에러 내용 로그에 찍는다.
        } finally { // finally 옵션
            //에러 유무 상관없이 실행되어야 하는 것 작성.
        }
        return intVal;
    }
}
