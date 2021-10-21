package com.koreait.myapplication.ch10;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KobisService {
    // 일별 박스오피스
    @GET("boxoffice/searchDailyBoxOfficeList.json")
    Call<BoxOfficeResultBodyVO> boxOfficeSearchDailyBoxOfficeList(@Query("key") String key, @Query("targetDt") String targetDt);

    @GET("boxoffice/searchWeeklyBoxOfficeList.json")
    Call<BoxOfficeResultBodyVO> boxOfficeSearchWeeKBoxOfficeList(@Query("key") String key, @Query("targetDt") String targetDt, @Query("weekGb") String weekGb);

}
