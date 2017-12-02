package com.kungfupandas.sparsh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kungfupandas.sparsh.pojo.Examination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusharchoudhary on 02/12/17.
 */

public class PatientDetailActivity extends ToolbarActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_examinations);
         mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ExaminationAdapter(createDummyExaminations());
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Examination> createDummyExaminations() {
        List<Examination> examinations = new ArrayList<>();
        examinations.add(new Examination("Visit 1","60-90","70-150","62","162"));
        examinations.add(new Examination("Visit 2","70-1100","70-150","62","162"));
        examinations.add(new Examination("Visit 3","50-110","70-150","62","162"));
        examinations.add(new Examination("Visit 4","60-95","70-150","62","162"));
        return examinations;
    }
}
