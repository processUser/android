package com.koreait.myapplication.ch10;

import com.koreait.myapplication.ch10.boxofficemodel.BoxOfficeResultBodyVO;
import com.koreait.myapplication.ch10.searchmoviemodel.MovieInfoResultBodyVO;
import com.koreait.myapplication.ch10.searchmoviemodel.MovieListResultBodyVO;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface KobisService {
    // https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=c368df0859f6194cec7d22fe3a4756dc&targetDt=20201022
    @GET("boxoffice/searchDailyBoxOfficeList.json")
    Call<BoxOfficeResultBodyVO> boxOfficeSearchDailyBoxOfficeList(@Query("key") String key, @Query("targetDt") String targetDt);
    // https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=c368df0859f6194cec7d22fe3a4756dc&targetDt=20201022&weekGb=0
    @GET("boxoffice/searchWeeklyBoxOfficeList.json")
    Call<BoxOfficeResultBodyVO> boxOfficeSearchWeeKBoxOfficeList(@Query("key") String key, @Query("targetDt") String targetDt, @Query("weekGb") String weekGb);
    // https://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=c368df0859f6194cec7d22fe3a4756dc
    @GET("movie/searchMovieList.json")
    Call<MovieListResultBodyVO> searchMovieList(@Query("key") String key, @Query("itemPerPage") String itemPerPage, @Query("curPage") int curPage);
    // https://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=c368df0859f6194cec7d22fe3a4756dc&movieCd=20202433
    @GET("movie/searchMovieInfo.json")
    Call<MovieInfoResultBodyVO> searchMovieInfo(@Query("key") String key, @Query("movieCd") String movieCd);

}
