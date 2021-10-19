package com.koreait.myapplication.picsum;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//Retrofit - ajxa 통신 라이브러리
//참고사이트 - https://jaejong.tistory.com/33
public interface RetrofitService {
    @GET("/v2/list")
    Call<List<PicsumVO>> getList();
}
