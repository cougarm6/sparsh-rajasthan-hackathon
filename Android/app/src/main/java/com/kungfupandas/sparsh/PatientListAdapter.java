package com.kungfupandas.sparsh;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kungfupandas.sparsh.listener.OnPatientClickListener;
import com.kungfupandas.sparsh.pojo.Patient;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

/**
 * Created by tusharchoudhary on 02/12/17.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.ViewHolder> {
    private List<Patient> patientList;
    private OnPatientClickListener onPatientClickListener;
    public PatientListAdapter(List<Patient> patientList, OnPatientClickListener onPatientClickListener) {
        this.patientList = patientList;
        this.onPatientClickListener = onPatientClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, age, nextMeeting;
        public CardView bg;
        public ViewHolder(View v) {
            super(v);
            bg = (CardView) v.findViewById(R.id.cv_patient);
            name = (TextView) v.findViewById(R.id.tv_name);
            age = (TextView) v.findViewById(R.id.tv_age);
            nextMeeting = (TextView) v.findViewById(R.id.tv_next_meeting);
        }
    }

    @Override
    public PatientListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_patient_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Patient patient = patientList.get(position);
        holder.name.setText(patient.getName());
        DateTimeFormatter fmt = DateTimeFormat.forPattern("E, d MMMM");
        String date = fmt.print(new DateTime(patient.getNextMeetingDate()));
        holder.age.setText(patient.getAge()+" yrs");
        holder.nextMeeting.setText("follow up : "+date);
        holder.bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPatientClickListener.onPatientClicked(patient);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }
}



