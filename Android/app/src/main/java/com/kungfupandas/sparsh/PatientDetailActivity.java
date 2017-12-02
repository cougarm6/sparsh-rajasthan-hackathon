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

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarTitle("Patient Detail");
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
        examinations.add(new Examination("Visit 1","80-100","120-80","62","162", 19213123L));
        examinations.add(new Examination("Visit 2","82-100","130-75","66","162",19213123L));
        examinations.add(new Examination("Visit 3","70-90","122-83","68","162", 19213123L));
        examinations.add(new Examination("Visit 4","80-105","125-85","71","162", 19213123L));
        return examinations;
    }
}
