package com.koreait.myapplication.ch10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koreait.myapplication.R;
import com.koreait.myapplication.Utils;
import com.koreait.myapplication.ch10.boxofficemodel.BoxOfficeVO;

import java.util.List;

public class BoxOfficeAdapter extends RecyclerView.Adapter<BoxOfficeAdapter.MyViewHolder> {
    private List<BoxOfficeVO> list;

    public void setList(List<BoxOfficeVO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_daily_boxoffice,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BoxOfficeVO vo = list.get(position);
        holder.setItem(vo);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private TextView tvAudienceCnt;

        public MyViewHolder(View v){
            super(v);

            tvTitle = v.findViewById(R.id.tvTitle);
            tvAudienceCnt = v.findViewById(R.id.tvAudienceCnt);
        }
        public void setItem(BoxOfficeVO vo) {
            tvTitle.setText(vo.getMovieNm());
            tvAudienceCnt.setText(Utils.getNumberComma(vo.getAudiCnt())+" 명");
        }

    }
}
