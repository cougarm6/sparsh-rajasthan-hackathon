package com.kungfupandas.sparsh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kungfupandas.sparsh.listener.OnPatientClickListener;
import com.kungfupandas.sparsh.pojo.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientListActivity extends ToolbarActivity implements OnPatientClickListener {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        setToolbarTitle("Patient's List");
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_patient_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PatientListAdapter(createDummyPatients(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Patient> createDummyPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("Mrs. Kavya Srivastava","26","","112, Mahape","7838726020",1512155683275L,151212683275L,1512155683275L, 1512155683275L,false, null));
        patients.add(new Patient("Mrs. Alisha Dash","26","","112, Mahape","7838726020",1512155683275L,151212683275L,1512155683275L, 1512155683275L,false, null));
        patients.add(new Patient("Mrs. Pooja Sharma","26","","112, Mahape","7838726020",1512155683275L,151212683275L,1512155683275L, 1512155683275L,false, null));
        patients.add(new Patient("Mrs. Sneha Verma","26","","112, Mahape","7838726020",1512155683275L,151212683275L,1512155683275L, 1512155683275L,false, null));
        patients.add(new Patient("Mrs. Sadhana Upadhaya","26","","112, Mahape","7838726020",1512155683275L,151212683275L,1512155683275L, 1512155683275L,false, null));
        return patients;
    }

    @Override
    public void onPatientClicked(Patient patient) {
        startActivity(new Intent(this, PatientDetailActivity.class));
    }
}
