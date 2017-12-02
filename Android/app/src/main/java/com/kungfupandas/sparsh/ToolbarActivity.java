package com.kungfupandas.sparsh;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by tusharchoudhary on 02/12/17.
 */

public class ToolbarActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FrameLayout mContentFrameLayout;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureToolbar();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.toolbar);
        mContentFrameLayout = (FrameLayout) findViewById(R.id.container);
        View view = LayoutInflater.from(this).inflate(layoutResID, mContentFrameLayout, false);

        toolbar = (Toolbar) findViewById(R.id.tb);
        toolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        mContentFrameLayout.addView(view);
    }


    protected void setToolbarTitle(String title) {
        if (toolbarTitle != null)
            toolbarTitle.setText(title);
    }

    private void configureToolbar() {
        setSupportActionBar(toolbar);
        setTitle("");
    }
}
