package com.example.ambrosio.enhancedtodolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ambrosio.enhancedtodolist.data.Contract;
import com.example.ambrosio.enhancedtodolist.data.DBHelper;
import com.example.ambrosio.enhancedtodolist.data.ToDoItem;

import java.util.ArrayList;

/**
 * Created by Ambrosio on 7/18/2017.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ItemHolder> {

    private Cursor cursor;
    private ItemClickListener listener;
    private String TAG = "todolistadapter";

    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;


    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item, parent, false);
        ItemHolder holder = new ItemHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(holder, position);
        holder.checkBox.setOnCheckedChangeListener(onCheckedChangeListener);

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }



    public interface ItemClickListener {
        void onItemClick(int pos, String description, String priority, String isDone, String duedate, long id);
    }



    public ToDoListAdapter(Cursor cursor, ItemClickListener listener) {
        this.cursor = cursor;
        this.listener = listener;


    }



    public void swapCursor(Cursor newCursor){
        if (cursor != null) cursor.close();
        cursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }


    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView descr;
        TextView due;
        TextView prio;
        String priority;
        String duedate;
        String description;
        String checkDone="done";
        CheckBox checkBox;
        String isDone;
        long id;


        ItemHolder(View view) {
            super(view);
            prio = (TextView) view.findViewById(R.id.priority);
            descr = (TextView) view.findViewById(R.id.description);
            due = (TextView) view.findViewById(R.id.dueDate);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            view.setOnClickListener(this);
        }



        public void bind(ItemHolder holder, int pos) {
            cursor.moveToPosition(pos);
            id = cursor.getLong(cursor.getColumnIndex(Contract.TABLE_TODO._ID));
            Log.d(TAG, "deleting id: " + id);





            duedate = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_DUE_DATE));
            description = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_DESCRIPTION));

            //gets the string value of priority from database
            priority = cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_PRIORITY));
            //gets the string value of isDone from database
            isDone= cursor.getString(cursor.getColumnIndex(Contract.TABLE_TODO.COLUMN_NAME_IS_DONE));

            descr.setText(description);
            due.setText(duedate);
            prio.setText(priority);

            //checks whether the isDone string in database for this to-do item
            //if it is "false" it will be unchecked. It will be checked otherwise
            if(isDone.equals("false")) {
                checkBox.setText(checkDone);
                checkBox.setChecked(false);
            }
            else{
                checkBox.setChecked(true);
                checkBox.setText(checkDone);
            }

            //holder will listen for clicks on the checkbox
            // and calls function isBoxChecked if clicked
            holder.checkBox.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                   isBoxChecked(cb.isChecked());
                }
            });



            holder.itemView.setTag(id);

        }

        //If the checkbox is checked for isDone will be set to true.
        public void isBoxChecked(boolean isChecked){

            if(isChecked){
                Log.d(TAG, "checkbox is checked. id:" + id);
                isDone="true";
            }
            else{
                Log.d(TAG, "checkbox is not checked. id:" + id);
                isDone="false";
            }
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(pos, description, priority,isDone,duedate, id);


        }





    }

}
