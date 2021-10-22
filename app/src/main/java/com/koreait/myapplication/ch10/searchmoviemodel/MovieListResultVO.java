package com.koreait.myapplication.ch10.searchmoviemodel;

import java.util.List;

public class MovieListResultVO {
    private int totCnt;
    private String source;
    private List<MovieVO> movieList;

    public int getTotCnt() {
        return totCnt;
    }

    public void setTotCnt(int totCnt) {
        this.totCnt = totCnt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<MovieVO> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieVO> movieList) {
        this.movieList = movieList;
    }
}
