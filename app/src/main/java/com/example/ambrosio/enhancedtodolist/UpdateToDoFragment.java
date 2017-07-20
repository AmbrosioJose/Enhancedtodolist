package com.example.ambrosio.enhancedtodolist;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

/**
 * Created by Ambrosio on 7/18/2017.
 */

public class UpdateToDoFragment extends DialogFragment {

    private EditText toDo;
    private DatePicker dp;
    private Button add;
    private Spinner pr;
    private final String TAG = "updatetodofragment";
    private long id;


    public UpdateToDoFragment() {
    }

    public static UpdateToDoFragment newInstance(int year, int month, int day, String descrpition, String priority, String isDone,long id) {
        UpdateToDoFragment f = new UpdateToDoFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("year", year);
        args.putInt("month", month);
        args.putInt("day", day);
        args.putLong("id", id);
        args.putString("description", descrpition);
        args.putString("priority", priority);
        args.putString("isDone",isDone);
        f.setArguments(args);

        return f;
    }

    //To have a way for the activity to get the data from the dialog
    public interface OnUpdateDialogCloseListener {
        void closeUpdateDialog(int year, int month, int day, String description, String priority, String isDone, long id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_adder, container, false);
        toDo = (EditText) view.findViewById(R.id.toDo);
        pr = (Spinner) view.findViewById(R.id.priority);
        dp = (DatePicker) view.findViewById(R.id.datePicker);
        add = (Button) view.findViewById(R.id.add);


        int year = getArguments().getInt("year");
        int month = getArguments().getInt("month");
        int day = getArguments().getInt("day");
        id = getArguments().getLong("id");
        String description = getArguments().getString("description");
        String priority=getArguments().getString("priority");
        dp.updateDate(year, month, day);

        //gets the current string for isDone depending on
        // whether the box is checked or not
        final String isDone=getArguments().getString("isDone");

        //populates the spinner with the string array in the string.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext (),
                R.array.priority_array, android.R.layout.simple_spinner_item);
        // sets the style of the spinner as just a simple spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //applies adapter to spinner
        pr.setAdapter(adapter);

        //sets the spinner to the option that was previously selected
        if(!priority.equals(null)){
            int spinnerPosition=adapter.getPosition(priority);
            pr.setSelection(spinnerPosition);
        }


        toDo.setText(description);

        add.setText("Update");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateToDoFragment.OnUpdateDialogCloseListener activity = (UpdateToDoFragment.OnUpdateDialogCloseListener) getActivity();
                Log.d(TAG, "id: " + id);
                //changes: passes the updated selected item from spinner and isDone string
                //  so that they can be updated in the database
                activity.closeUpdateDialog(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), toDo.getText().toString(), pr.getSelectedItem().toString(), isDone,id);
                UpdateToDoFragment.this.dismiss();
            }
        });

        return view;
    }
}

