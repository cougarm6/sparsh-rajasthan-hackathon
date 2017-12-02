package com.kungfupandas.sparsh;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by tusharchoudhary on 02/12/17.
 */

public class CreatePatientActivity extends ToolbarActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_patient);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToolbarTitle("Add Patient");
    }
}
