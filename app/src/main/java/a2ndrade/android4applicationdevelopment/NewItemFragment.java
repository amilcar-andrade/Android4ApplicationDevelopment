package a2ndrade.android4applicationdevelopment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class NewItemFragment extends Fragment {
    private OnNewItemAddedListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.new_item_fragment, container, false);
        final EditText editText = (EditText) view.findViewById(R.id.todo_edit_text);
        // NOTE: Adding the following xml attributes should not be needed for devices with with a physical keyboard
        // android:inputType="textCapSentences|textAutoCorrect" and android:focusableInTouchMode="true"
        // The inputType is necessary to make the OnKeyListener method to work and we need to make the EditText
        // focusable in order to show the keyboard.
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER) ||
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        listener.onNewItemAdded(editText.getText().toString());
                        editText.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnNewItemAddedListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnNewItemAddedListener");
        }
    }

    public interface OnNewItemAddedListener {
        void onNewItemAdded(String newItem);
    }
}
