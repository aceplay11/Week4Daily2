package com.example.week4daily2.model.datasource.local;

import android.view.MotionEvent;
import android.view.View;

public interface OnItemTouchHelper {

        boolean onSingleTapUp(MotionEvent event);

        void onClick(View view, int position);

        void onLongClick(View view, int position);

}
