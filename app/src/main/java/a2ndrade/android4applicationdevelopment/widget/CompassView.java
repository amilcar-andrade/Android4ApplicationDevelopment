package a2ndrade.android4applicationdevelopment.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;

import a2ndrade.android4applicationdevelopment.R;

public class CompassView extends View {
    private float bearing;
    private Paint markerPaint;
    private Paint textPaint;
    private Paint circlePaint;
    private String nS;
    private String eS;
    private String sS;
    private String wS;
    private int textHeight;

    public CompassView(Context context) {
        super(context);
        initCompassView();
    }

    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCompassView();
    }

    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCompassView();
    }

    private void initCompassView() {
        setFocusable(true);
        Resources res = getResources();

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(res.getColor(R.color.compass_bg));
        circlePaint.setStrokeWidth(1);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        nS = res.getString(R.string.cardinal_north);
        eS = res.getString(R.string.cardinal_east);
        sS = res.getString(R.string.cardinal_south);
        wS = res.getString(R.string.cardinal_west);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(res.getColor(R.color.compass_text));

        textHeight = (int)textPaint.measureText("yY");
        markerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        markerPaint.setColor(res.getColor(R.color.compass_marker));
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
        // fire accessibility
        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
    }

    public float getBearing(){
        return bearing;
    }


    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.dispatchPopulateAccessibilityEvent(event);
        if (isShown()) {
         String bearingStr = String.valueOf(bearing);
            if (bearingStr.length() > AccessibilityEvent.MAX_TEXT_LENGTH) {
                bearingStr = bearingStr.substring(0, AccessibilityEvent.MAX_TEXT_LENGTH);
                event.getText().add(bearingStr);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Find the center of the control and store the lenght of the smalles side as the compass radius
        int measuredWidth = getMeasuredWidth();
        int measureH = getMeasuredHeight();

        int px = measuredWidth / 2;
        int py = measureH / 2;

        int radius = Math.max(px, py);

        // Draw outer boundary and color the background of the Compass face using the drawCircle method

        // Draw the background
        canvas.drawCircle(px, py, radius, circlePaint);

        // Rotate canvas in the opposite direction
        // Rotate our perspective so that the top is facing the current bearing
        canvas.save();
        canvas.rotate(-bearing, px, py);

        // Rotate the markings
        int textWidth = (int)textPaint.measureText("W");
        int cardinalX = px - textWidth / 2;
        int cardinalY = py - radius + textHeight;

        // Draw the marker every 15 degrees and text every 45
        for (int i = 0; i < 24; i ++) {
            // Draw a marker
            canvas.drawLine(px, py-radius, px, py-radius+10, markerPaint);
            canvas.save();

            canvas.translate(0, textHeight);
            // Draw the cardinal points
            if (i % 6 == 0) {
                String dirString = "";
                switch (i) {
                    case(0) : {
                        dirString = nS;
                        int arrowY = 2 * textHeight;
                        canvas.drawLine(px, arrowY, px-5, 3*textHeight, markerPaint);
                        canvas.drawLine(px, arrowY, px+5, 3*textHeight, markerPaint);
                        break;
                    }

                    case(6): dirString = eS; break;
                    case(12): dirString = sS; break;
                    case(18): dirString = wS; break;
                }
                canvas.drawText(dirString, cardinalX, cardinalY, textPaint);
            } else if (i % 3 == 0) {
                // Draw the text every alternate 45deg
                String angle = String.valueOf(i*15);
                float angleTextWidth = textPaint.measureText(angle);

                int angleTextX = (int) (px-angleTextWidth/2);
                int angleTextY = py -radius + textHeight;
                canvas.drawText(angle, angleTextX, angleTextY, textPaint);
            }
            canvas.restore();
            canvas.rotate(15, px, py);
        }
        canvas.restore();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // The compass is a circle that fills as much space as possible.
        // Set the measured dimensions by figuring out the shortest boundary, height or width.
        int measuredW = measure0(widthMeasureSpec);
        int measuredH = measure0(heightMeasureSpec);
        // perfect circle
        int d = Math.min(measuredH, measuredW);
        // setMeasuredDimension it is a must here
        setMeasuredDimension(d, d);
    }

    private int measure0(int measureSpec) {
        // Decode measurements
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (MeasureSpec.UNSPECIFIED == specMode) {
            // Default size of 200 if no bounds are specified
            return 200;
        }

        // return full available bounds
        return specSize;
    }
}
