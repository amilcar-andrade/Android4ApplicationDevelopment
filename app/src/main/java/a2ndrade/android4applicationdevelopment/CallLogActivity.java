package a2ndrade.android4applicationdevelopment;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.BatteryManager;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class CallLogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calllog);
        listView = (ListView) findViewById(R.id.call_log_list_view);
        getSupportLoaderManager().initLoader(0, null, CallLogActivity.this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, CallLog.Calls.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        String[] fromColumns = new String[] {CallLog.Calls.CACHED_NAME, CallLog.Calls.NUMBER};
        int[] toLayoutIds = new int[] {R.id.name_textview, R.id.number_textview};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(CallLogActivity.this, R.layout.call_log_item, data, fromColumns, toLayoutIds);
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // no-op
    }
}
