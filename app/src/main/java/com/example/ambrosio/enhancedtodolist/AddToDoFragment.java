package com.example.ambrosio.enhancedtodolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Ambrosio on 7/18/2017.
 */

public class AddToDoFragment extends DialogFragment{

    private EditText toDo;
    private DatePicker dp;
    private Button add;
    private Spinner pr;
    private final String TAG = "addtodofragment";
    private final String isDone="false";

    public AddToDoFragment() {
    }

    //To have a way for the activity to get the data from the dialog
    public interface OnDialogCloseListener {
        void closeDialog(int year, int month, int day, String description, String priority, String isDone);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_to_do_adder, container, false);
        toDo = (EditText) view.findViewById(R.id.toDo);
        pr = (Spinner) view.findViewById(R.id.priority);
        dp = (DatePicker) view.findViewById(R.id.datePicker);
        add = (Button) view.findViewById(R.id.add);


        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dp.updateDate(year, month, day);

        //populates the spinner with the string array in the string.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext (),
                R.array.priority_array, android.R.layout.simple_spinner_item);
        // sets the style of the spinner as just a simple spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //applies adapter to spinner
        pr.setAdapter(adapter);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Spinner option selected: " + pr.getSelectedItem());
                OnDialogCloseListener activity = (OnDialogCloseListener) getActivity();
                //changes: passes the selected item from spinner and isDone string
                //  so that they can be added to the database
                activity.closeDialog(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), toDo.getText().toString(), pr.getSelectedItem().toString(), isDone);
                AddToDoFragment.this.dismiss();
            }
        });

        return view;
    }
}



