package com.kungfupandas.sparsh.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.kungfupandas.sparsh.R;


/**
 * Created by tusharchaudhary on 12/02/17.
 */
public class DashboardCounterView extends View {
    private final Typeface typeface;
    private final Typeface comforta;
    GestureDetector mDetector = new GestureDetector(this.getContext(), new mListener());
    Drawable smallCircle, bigCircle, redCircle, pinkCircle, yellowCircle;
    boolean isProfileVisible = true;
    float modifier = 2;
    int activeCount, meetingsCount, siteVisitCount,followUpsCount;
    private OnDashboardCountersClickListeners listener;

    public void setDashboardCounts(OnDashboardCountersClickListeners listener,int activeCount, int meetingsCount, int siteVisitCount, int followUpsCount){
        this.listener = listener;
        this.activeCount=activeCount;
        this.meetingsCount = meetingsCount;
        this.siteVisitCount = siteVisitCount;
        this.followUpsCount = followUpsCount;
        invalidate();
    }

    public DashboardCounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/proximanova-regular-webfont.ttf");
        comforta = Typeface.createFromAsset(context.getAssets(), "fonts/Comfortaa-Light.ttf");
        bigCircle = ContextCompat.getDrawable(context, R.drawable.icon_dashboard_big_circle_bg);
        smallCircle = ContextCompat.getDrawable(context, R.drawable.icon_dashboard_small_circle_bg);
        redCircle = ContextCompat.getDrawable(context, R.drawable.dashboard_counter_red);
        pinkCircle = ContextCompat.getDrawable(context, R.drawable.dashboard_counter_pink);
        yellowCircle = ContextCompat.getDrawable(context, R.drawable.dashboard_counter_yellow);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = mDetector.onTouchEvent(event);
        if (!result) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                int x = (int) event.getX(), y = (int) event.getY();
                Log.e("Touch UP","x:"+event.getX()+" , y:"+event.getY());
                if(x>bigCircle.getBounds().left && x<bigCircle.getBounds().right && y > bigCircle.getBounds().top && y< bigCircle.getBounds().bottom)
                {
//                    onActiveCounterCliked();
                    listener.onActiveCounterClicked();

                }
                if(x>pinkCircle.getBounds().left && x<pinkCircle.getBounds().right && y > pinkCircle.getBounds().top && y< pinkCircle.getBounds().bottom)
                {
                    listener.onFollowUpCounterClicked();
                    Log.e("Touch:", " follow up!!");

                }
                if(x>redCircle.getBounds().left && x<redCircle.getBounds().right && y > redCircle.getBounds().top && y< redCircle.getBounds().bottom)
                {
                    listener.onSiteVisitCounterClicked();
                    Log.e("Touch:", " siteVisit!!");

                }
                if(x>yellowCircle.getBounds().left && x<yellowCircle.getBounds().right && y > yellowCircle.getBounds().top && y< yellowCircle.getBounds().bottom)
                {
                    listener.onMeetingCounterClicked();
                    Log.e("Touch:"," meetings!!");

                }
                result = true;
            }
        }
        return result;
    }

    private void onActiveCounterCliked() {
        Log.e("Touch:", " active leads!!");
        isProfileVisible = !isProfileVisible;
        invalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = canvas.getWidth();
        Log.e("width",""+width);
        if(width >1400)
            modifier = 3;
        int centerX = width / 2;
        Paint myPaint = new Paint();
        myPaint.setColor(Color.parseColor("#dcdcdc"));
        myPaint.setAntiAlias(true);
        myPaint.setStrokeWidth(3);

        while((centerX + 195 * modifier) > width - 50)
            modifier -= 0.1;

        canvas.drawLine(centerX, 60 * modifier, centerX + 125 * modifier, 200 * modifier, myPaint);
        canvas.drawLine(centerX, 70 * modifier, centerX + 125 * modifier, 210 * modifier, myPaint);//top


        canvas.drawLine(centerX - 125 * modifier, 190 * modifier, centerX + 125 * modifier, 190 * modifier, myPaint);//left
        canvas.drawLine(centerX - 125 * modifier, 198 * modifier, centerX + 125 * modifier, 198 * modifier, myPaint);


        canvas.drawLine(centerX, 330 * modifier, centerX + 125 * modifier, 200 * modifier, myPaint);//bottom
        canvas.drawLine(centerX, 340 * modifier, centerX + 125 * modifier, 210 * modifier, myPaint);

        if(modifier>=3)
            bigCircle.setBounds(Math.round(centerX + 50 * modifier), Math.round(120 * modifier), Math.round(centerX + 200 * modifier), Math.round(280 * modifier));//right
        else
            bigCircle.setBounds(Math.round(centerX + 55 * modifier), Math.round(130 * modifier), Math.round(centerX + 195 * modifier), Math.round(270 * modifier));//right


        bigCircle.draw(canvas);

        if(modifier>=3)
            smallCircle.setBounds(Math.round(centerX - 60 * modifier), Math.round(20 * modifier), Math.round(centerX + 60 * modifier), Math.round(140 * modifier));//top
        else
            smallCircle.setBounds(Math.round(centerX - 50 * modifier), Math.round(30 * modifier), Math.round(centerX + 50 * modifier), Math.round(130 * modifier));//top

        smallCircle.draw(canvas);
        yellowCircle.setBounds(Math.round(smallCircle.getBounds().left + 10 * modifier), Math.round(smallCircle.getBounds().top + 10 * modifier), Math.round(smallCircle.getBounds().right - 17 * modifier), Math.round(smallCircle.getBounds().bottom - 17 * modifier));
        yellowCircle.draw(canvas);

        if(modifier>=3)
            smallCircle.setBounds(Math.round(centerX - 160 * modifier), Math.round(140 * modifier), Math.round(centerX - 40 * modifier), Math.round(260 * modifier));//left
        else
            smallCircle.setBounds(Math.round(centerX - 150 * modifier), Math.round(150 * modifier), Math.round(centerX - 50 * modifier), Math.round(250 * modifier));//left

        smallCircle.draw(canvas);
        redCircle.setBounds(Math.round(smallCircle.getBounds().left + 10 * modifier), Math.round(smallCircle.getBounds().top + 10 * modifier), Math.round(smallCircle.getBounds().right - 17 * modifier), Math.round(smallCircle.getBounds().bottom - 17 * modifier));
        redCircle.draw(canvas);

        if(modifier>=3)
            smallCircle.setBounds(Math.round(centerX - 60 * modifier), Math.round(260 * modifier), Math.round(centerX + 60 * modifier), Math.round(380 * modifier));//bottom
        else
           smallCircle.setBounds(Math.round(centerX - 50 * modifier), Math.round(270 * modifier), Math.round(centerX + 50 * modifier), Math.round(370 * modifier));//bottom


        smallCircle.draw(canvas);
        pinkCircle.setBounds(Math.round(smallCircle.getBounds().left + 10 * modifier), Math.round(smallCircle.getBounds().top + 10 * modifier), Math.round(smallCircle.getBounds().right - 17 * modifier), Math.round(smallCircle.getBounds().bottom - 17 * modifier));
        pinkCircle.draw(canvas);

        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        if(modifier >=3) {
            textPaint.setTextSize(112f);
            textPaint.setStrokeWidth(24);
        }else if(modifier >=2)
            textPaint.setTextSize(72f);
        else
            textPaint.setTextSize(38f);

        textPaint.setAntiAlias(true);
        textPaint.setTypeface(comforta);
        textPaint.setTextAlign(Paint.Align.CENTER);
        float textHeight = textPaint.descent() - textPaint.ascent();
        float textOffset = (textHeight / 2) - textPaint.descent();

        Rect boundsPink = pinkCircle.getBounds();
        Rect boundsRed = redCircle.getBounds();
        Rect boundsYellow = yellowCircle.getBounds();

        canvas.drawText("" + followUpsCount, boundsPink.centerX(), boundsPink.centerY() + textOffset+10, textPaint);
        canvas.drawText("" + siteVisitCount, boundsRed.centerX(), boundsRed.centerY() + textOffset + 10, textPaint);
        canvas.drawText("" + meetingsCount, boundsYellow.centerX(), boundsYellow.centerY() + textOffset + 10, textPaint);

        Rect bigCircleBounds =bigCircle.getBounds();
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.red));
        if(modifier >=3)
            canvas.drawText("" + activeCount, bigCircleBounds.centerX(), bigCircleBounds.centerY() - 30 + textOffset, textPaint);
        else if(modifier >=2)
            canvas.drawText("" + activeCount, bigCircleBounds.centerX(), bigCircleBounds.centerY() - 15 + textOffset, textPaint);
        else
            canvas.drawText("" + activeCount, bigCircleBounds.centerX(), bigCircleBounds.centerY() - 5 + textOffset, textPaint);


        if(modifier >=3)
            textPaint.setTextSize(48f);
        else if(modifier >=2)
            textPaint.setTextSize(34f);
        else
            textPaint.setTextSize(16f);

        textPaint.setTypeface(typeface);
        canvas.drawText("Active Patients", bigCircleBounds.centerX(), bigCircleBounds.centerY() + 20 + textOffset, textPaint);

        if(modifier >=3)
            textPaint.setTextSize(52f);
        else if(modifier >=2)
            textPaint.setTextSize(38f);
        else
            textPaint.setTextSize(22f);

        textOffset = textOffset + 20;
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.yellow));
        canvas.drawText("Meetings", boundsYellow.centerX(), boundsYellow.bottom + 20 * modifier + textOffset, textPaint);
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.red));
        canvas.drawText("Deliveries", boundsRed.centerX(), boundsRed.bottom + 20*modifier + textOffset, textPaint);
        textPaint.setColor(ContextCompat.getColor(getContext(), R.color.pink));
        canvas.drawText("Follow ups", boundsPink.centerX(), boundsPink.bottom + 20*modifier + textOffset, textPaint);

    }


    private class mListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }

    public interface OnDashboardCountersClickListeners{
        void onSiteVisitCounterClicked();
        void onFollowUpCounterClicked();
        void onMeetingCounterClicked();
        void onActiveCounterClicked();
    }
}
