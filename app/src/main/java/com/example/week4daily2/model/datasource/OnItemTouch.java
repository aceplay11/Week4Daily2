package com.example.week4daily2.model.datasource;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.week4daily2.model.datasource.local.OnItemTouchHelper;

public class OnItemTouch implements RecyclerView.OnItemTouchListener {

    private OnItemTouchHelper touchHelper;
    private GestureDetector gestureDetector;

    public OnItemTouch(Context context, final RecyclerView recycleView, final OnItemTouchHelper touchHelper) {

        this.touchHelper = touchHelper;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent event) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent event) {
                View child = recycleView.findChildViewUnder(event.getX(), event.getY());
                if (child != null && touchHelper != null) {
                    touchHelper.onLongClick(child, recycleView.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent event) {
        View child = recyclerView.findChildViewUnder(event.getX(), event.getY());

        if (child != null && touchHelper != null && gestureDetector.onTouchEvent(event)) {
            touchHelper.onClick(child, recyclerView.getChildAdapterPosition(child));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent event) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

}






