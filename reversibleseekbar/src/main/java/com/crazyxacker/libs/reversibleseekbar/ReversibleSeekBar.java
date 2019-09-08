package com.crazyxacker.libs.reversibleseekbar;

import android.content.Context;
import android.graphics.Canvas;
import androidx.appcompat.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ReversibleSeekBar extends AppCompatSeekBar {

    private boolean mReverse;

    public ReversibleSeekBar(Context context) {
        super(context);
    }

    public ReversibleSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReversibleSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setReverse(boolean reverse) {
        mReverse = reverse;
        invalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        boolean reverse = mReverse;
        int saveCount = 0;
        if (reverse) {
            saveCount = canvas.save();
            float px = this.getWidth() / 2.0f;
            float py = this.getHeight() / 2.0f;
            canvas.scale(-1, 1, px, py);
        }
        super.draw(canvas);
        if (reverse) {
            canvas.restoreToCount(saveCount);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean reverse = mReverse;
        float x = 0.0f, y = 0.0f;
        if (reverse) {
            x = event.getX();
            y = event.getY();
            event.setLocation(getWidth() - x, y);
        }
        boolean result = super.onTouchEvent(event);
        if (reverse) {
            event.setLocation(x, y);
        }
        return result;
    }
}
