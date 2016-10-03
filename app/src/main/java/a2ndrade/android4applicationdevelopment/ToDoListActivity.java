package a2ndrade.android4applicationdevelopment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        ListView lv = (ListView) findViewById(R.id.todo_list_view);
        final EditText editText = (EditText) findViewById(R.id.todo_edit_text);

        final ArrayList<String> todoItems = new ArrayList<>();
        final ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoItems);
        lv.setAdapter(arrayAdapter);

        // NOTE: This should work in devices with a keyboard without adding the following xml attrs
        // android:inputType="textCapSentences|textAutoCorrect" and android:focusableInTouchMode="true"
        // The inputType is necessary to make the OnKeyListener method to work and we need to make the EditText
        // focusable in order to show the keyboard.
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        // Add to the start of the list
                        todoItems.add(0, editText.getText().toString());
                        arrayAdapter.notifyDataSetChanged();
                        editText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });

    }
}
