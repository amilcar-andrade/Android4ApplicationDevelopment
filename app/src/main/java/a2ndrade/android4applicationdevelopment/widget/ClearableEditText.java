package a2ndrade.android4applicationdevelopment.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import a2ndrade.android4applicationdevelopment.R;

public class ClearableEditText extends LinearLayout {
    EditText editText;
    Button clearButton;

    public ClearableEditText(Context context) {
        super(context);
        // Inflate view from resource
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(infService);
        layoutInflater.inflate(R.layout.clerable_edit_text, this, true);

        editText = (EditText) findViewById(R.id.edit_text);
        clearButton = (Button) findViewById(R.id.clear_button);
    }

    private void hookupButton() {
        clearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }

}
