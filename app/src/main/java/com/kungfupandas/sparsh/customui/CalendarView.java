package com.kungfupandas.sparsh.customui;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kungfupandas.sparsh.R;
import com.kungfupandas.sparsh.pojo.Patient;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by tusharchoudhary on 02/12/17.
 */
public class CalendarView extends LinearLayout{
    RecyclerView mRecyclerView;
    TextView dateTv;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context context;
    private ArrayList<CalendarItem> calendarItems;

    public DateTime getCurrentSelectedDate() {
        return currentSelectedDate;
    }

    public void setCurrentSelectedDate(DateTime currentSelectedDate) {
        this.currentSelectedDate = currentSelectedDate;
    }

    private DateTime currentSelectedDate = new DateTime();
    public CalendarView(Context context) {
        super(context);
        this.context = context;
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dateTv = (TextView) findViewById(R.id.tv_dashboard_calendar_date_today);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_calendar);
    }

    public void initCalendar(CalendarDateChangeListener dateChangeListener) {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new android.support.v7.widget.LinearLayoutManager(context, android.support.v7.widget.LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        calendarItems = new ArrayList<>();
        LocalDateTime date = new LocalDateTime().minusDays(1);
        for(int i = 0; i < 30; i++){
            calendarItems.add(new CalendarItem(date.plusDays(i).dayOfMonth().getAsShortText(),new DateTime().plusDays(i-1)));
        }
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(calendarItems,dateChangeListener);
        mRecyclerView.setAdapter(mAdapter);
        setDate(new DateTime());
    }

    public void bindData(List<Patient> patients) {
        calendarItems.clear();
        LocalDateTime date = new LocalDateTime().minusDays(1);
        for(int i = 0; i < 30; i++){
            calendarItems.add(new CalendarItem(date.plusDays(i).dayOfMonth().getAsShortText(),new DateTime().plusDays(i-1)));
        }
        mAdapter.notifyDataSetChanged();
    }

    private void setDate(DateTime dateTime) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("E, d MMMM");
        String date = fmt.print(dateTime);
        dateTv.setText(date);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private final CalendarDateChangeListener dateChangeListener;
        private List<CalendarItem> calendarItems;
        private int currentItemSelected = 1;

        public  class ViewHolder extends RecyclerView.ViewHolder {
            public TextView dateTv;
            public ImageView background;

            public ViewHolder(View v) {
                super(v);
                dateTv = (TextView) v.findViewById(R.id.tv_dashboard_calendar_date);
                background = (ImageView) v.findViewById(R.id.iv_calendar_bg);
            }
        }

        public MyAdapter(List<CalendarItem> calendarItems, CalendarDateChangeListener dateChangeListener) {
            this.calendarItems = calendarItems;
            this.dateChangeListener = dateChangeListener;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_dashboard_calendar, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            CalendarItem item = calendarItems.get(position);
            if(position == currentItemSelected) {
                holder.background.setImageResource(R.drawable.icon_calendar_bg_white);
                holder.dateTv.setTextColor(Color.parseColor("#d6473d"));
            }else {
                holder.dateTv.setTextColor(Color.parseColor("#FFFFFF"));
                holder.background.setImageResource(R.drawable.icon_calendar_date_bg_red_bg);

            }
            holder.background.setTag(position);
            holder.background.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAdapter.notifyItemChanged(currentItemSelected);
                    currentItemSelected = position;
                    mAdapter.notifyItemChanged(currentItemSelected);
                    dateChangeListener.onDateSelected(calendarItems.get((Integer) v.getTag()).dateTime);
                    setDate(calendarItems.get((Integer) v.getTag()).dateTime);
                }
            });
            holder.dateTv.setText(calendarItems.get(position).date);
        }

        @Override
        public int getItemCount() {
            return calendarItems.size();
        }


    }
    public class CalendarItem {
        public String date;
        public DateTime dateTime;

        public CalendarItem(String date,DateTime dateTime) {
            this.date = date;
            this.dateTime = dateTime;
        }
    }

    public interface CalendarDateChangeListener{
        void onDateSelected(DateTime dateTime);
    }
}