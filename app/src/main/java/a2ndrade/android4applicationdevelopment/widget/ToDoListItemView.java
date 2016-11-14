package a2ndrade.android4applicationdevelopment.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import a2ndrade.android4applicationdevelopment.R;

public class ToDoListItemView extends TextView {
    public ToDoListItemView(Context context) {
        super(context);
        init();
    }

    public ToDoListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ToDoListItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ToDoListItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private Paint marginPaint;
    private Paint linePaint;
    private int paperColor;
    private float margin;

    private void init() {
        Resources resources = getResources();

        // Create the paint brushes we will use in the onDraw method
        marginPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        marginPaint.setColor(resources.getColor(R.color.notepad_margin));

        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(resources.getColor(R.color.notepad_lines));

        // Get the paper background color and the margin width
        paperColor = resources.getColor(R.color.notepad_paper);
        margin = resources.getDimension(R.dimen.notepad_maring);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(paperColor);

        // Draw ruled lines
        canvas.drawLine(0, 0, 0, getMeasuredHeight(), linePaint);
        canvas.drawLine(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight(), linePaint);

        // Draw margin
        canvas.drawLine(margin, 0, margin, getMeasuredHeight(), marginPaint);

        // Move the text accross from the margin
        canvas.save();
        canvas.translate(margin, 0);

        // Use the base TexView class to render the text.
        super.onDraw(canvas);
        canvas.restore();
    }
}
