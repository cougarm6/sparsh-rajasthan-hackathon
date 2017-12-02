package com.kungfupandas.sparsh;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kungfupandas.sparsh.pojo.Examination;

import java.util.List;

/**
 * Created by tusharchoudhary on 02/12/17.
 */

public class ExaminationAdapter extends RecyclerView.Adapter<ExaminationAdapter.ViewHolder> {
    private List<Examination> examinations;
    public ExaminationAdapter(List<Examination> examinations) {
        this.examinations = examinations;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bp, weight, ecg, height, num;
        public CardView bg;
        public ViewHolder(View v) {
            super(v);
            bg = (CardView) v.findViewById(R.id.cv_test);
            bp = (TextView) v.findViewById(R.id.tv_bp_val);
            weight = (TextView) v.findViewById(R.id.tv_weight_val);
            ecg = (TextView) v.findViewById(R.id.tv_ecg_val);
            height = (TextView) v.findViewById(R.id.tv_height_val);
            num = (TextView) v.findViewById(R.id.tv_title);
        }
    }

    @Override
    public ExaminationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_test_list, parent, false);
        ExaminationAdapter.ViewHolder vh = new ExaminationAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ExaminationAdapter.ViewHolder holder, int position) {
        final Examination examination = examinations.get(position);
        holder.weight.setText(examination.getWeight());
        holder.num.setText(examination.getNumber());
        holder.height.setText(examination.getHeight());
        holder.bp.setText(examination.getBp());
        holder.ecg.setText(examination.getEcg());
        holder.bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return examinations.size();
    }
}

