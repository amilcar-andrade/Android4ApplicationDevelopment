package a2ndrade.android4applicationdevelopment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity implements NewItemFragment.OnNewItemAddedListener {
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> todoItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        ToDoListFragment toDoListFragment =
                (ToDoListFragment) getSupportFragmentManager().findFragmentById(R.id.todo_list_fragment);
        todoItems = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoItems);
        toDoListFragment.setListAdapter(arrayAdapter);
    }

    @Override
    public void onNewItemAdded(String newItem) {
        todoItems.add(newItem);
        arrayAdapter.notifyDataSetChanged();
    }
}
