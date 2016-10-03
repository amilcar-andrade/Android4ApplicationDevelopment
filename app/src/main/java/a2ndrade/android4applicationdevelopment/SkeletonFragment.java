package a2ndrade.android4applicationdevelopment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by amilcar.andrade on 10/2/16.
 */
public class SkeletonFragment extends Fragment {

    // 1 - Called when the fragment is attached to its parent Activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // You can get a reference to the parent Activity using the 'context' variable
    }

    // 2 - Called to do the initial creation of the fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the fragment
    }

    // 3 - Called once the Fragment has been created in order for it to create its user interface
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Create or inflate the Fragments UI, and return it.
        // If this fragment has no UI then just return null;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // 4 - Called once the parent Activity and the Fragments' UI have been created
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Complete the Fragment initialization - particulary antyhing that requires the parent Activity
        // to be initialized or the Fragments view to be fully inflated.
    }

    // 5 - Called at the start of the visible lifetime
    @Override
    public void onStart() {
        super.onStart();
        // Apply any required UI change now that the Fragment is visible
    }

    // 6 - Called at the start of the active lifetime
    @Override
    public void onResume() {
        super.onResume();
        // Resume any paused UI updates, threads, or processes required by the Fragment but suspended when
        // it became inactive
    }

    // 7 - Called at the onf the active lifetime
    @Override
    public void onPause() {
        super.onPause();
        // Suspend UI updates, threads, or CPU intensity processes that dont need to be updated when the Activity
        // is not the active foreground activity
        // Persist all edits or state changes as after this call the process is likely to be killed
    }

    // 8 - Called to save UI state changes at the end of the activity lifecycle
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save ui state changes to the saveInstanceState.
        // This bundle will be passed to onCreate, onCreateView and onCreateView if the parent Activity
        // is killed and restarted.
    }

    // 9 - Called at the end of the visible fragment
    @Override
    public void onStop() {
        super.onStop();
        // Suspend remaining UI updates, threads, or processing that are not required when the Fragment is not visible
    }

    // 10 - Called when the Fragments view has been detached
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Clean up resources related to the view
    }

    // 11 - Called at the end of the full lifetime
    @Override
    public void onDestroy() {
        super.onDestroy();
        // Clean up any resources including ending threads, closing databases connections, etc..
    }

    // 12 - Called when the Fragment has been detached from it is parent Activity.
    @Override
    public void onDetach() {
        super.onDetach();
    }
}
