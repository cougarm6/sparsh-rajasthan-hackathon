package com.kungfupandas.sparsh;


import android.os.Bundle;
import android.widget.TextView;

import com.kungfupandas.sparsh.customui.CalendarView;
import com.kungfupandas.sparsh.customui.DashboardCounterView;
import com.kungfupandas.sparsh.pojo.Patient;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tusharchoudhary on 02/12/17.
 */

public class DashboardActivity extends ToolbarActivity implements CalendarView.CalendarDateChangeListener, DashboardCounterView.OnDashboardCountersClickListeners {
    private CalendarView calendarView;
    private TextView headingTv;
    private long calendarDateSelected;
    private DashboardCounterView dashboardCounterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initView();
    }

    private void initView() {
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.initCalendar(this);
        calendarView.bindData(createDummyPatients());
        dashboardCounterView = (DashboardCounterView) findViewById(R.id.dashboard);
        headingTv = (TextView) findViewById(R.id.heading);
        dashboardCounterView.setDashboardCounts(this, 5, 2, 1, 3);
    }

    private List<Patient> createDummyPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("Mrs. Kava Srivastava","26","","112, Mahape","7838726020",1512155683275L,151212683275L,1512155683275L, 1512155683275L,false, null));
        patients.add(new Patient("Mrs. Alisha Dash","26","","112, Mahape","7838726020",1512155683275L,151212683275L,1512155683275L, 1512155683275L,false, null));
        patients.add(new Patient("Mrs. Pooja Sharma","26","","112, Mahape","7838726020",1512155683275L,151212683275L,1512155683275L, 1512155683275L,false, null));
        patients.add(new Patient("Mrs. Sneha Verma","26","","112, Mahape","7838726020",1512155683275L,151212683275L,1512155683275L, 1512155683275L,false, null));
        patients.add(new Patient("Mrs. Sadhana Upadhaya","26","","112, Mahape","7838726020",1512155683275L,151212683275L,1512155683275L, 1512155683275L,false, null));
        return patients;
    }


    @Override
    public void onDateSelected(DateTime dateTime) {
        calendarView.setCurrentSelectedDate(dateTime);
        DateTime dt = new DateTime(dateTime);
        DateTime now = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("d MMMM");
        String str = fmt.print(dt);
        headingTv.setText(str + "'s Task");
        if (dateTime.getMonthOfYear() == now.getMonthOfYear())
            if (dateTime.getDayOfMonth() == now.getDayOfMonth())
                headingTv.setText("Today's Task");

        calendarDateSelected = dt.toDate().getTime();
        DateTime timeToSend = dateTime.withTimeAtStartOfDay();
        dashboardCounterView.setDashboardCounts(this, 5, 2, 1, 3);
    }

    @Override
    public void onSiteVisitCounterClicked() {

    }

    @Override
    public void onFollowUpCounterClicked() {

    }

    @Override
    public void onMeetingCounterClicked() {

    }

    @Override
    public void onActiveCounterClicked() {
    }
}