package a2ndrade.android4applicationdevelopment;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        final Cursor c = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        String[] from = new String[] {ContactsContract.Contacts.DISPLAY_NAME_PRIMARY};
        int[] to = new int[] {R.id.contact_name};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.contact_item, c, from, to);
        final ListView lv = (ListView) findViewById(R.id.contact_list);
        lv.setAdapter(simpleCursorAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (c == null) {
                    return;
                }
                // move cursor
                c.moveToPosition(position);

                // get row id
                int rowId = c.getInt(c.getColumnIndexOrThrow(BaseColumns._ID));
                Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, rowId);
                Intent outData = new Intent();
                outData.setData(uri);
                setResult(RESULT_OK, outData);
                c.close();
                finish();
            }
        });
    }
}
