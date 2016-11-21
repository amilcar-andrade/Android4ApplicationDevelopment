package a2ndrade.android4applicationdevelopment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import a2ndrade.android4applicationdevelopment.widget.CompassView;

public class CompassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        CompassView cv = (CompassView) findViewById(R.id.compass_view);
        cv.setBearing(45f);
    }
}
