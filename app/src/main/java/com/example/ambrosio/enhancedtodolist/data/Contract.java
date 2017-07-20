package com.example.ambrosio.enhancedtodolist.data;

import android.provider.BaseColumns;

/**
 * Created by Ambrosio on 7/18/2017.
 */

public class Contract {

    public static class TABLE_TODO implements BaseColumns {
        public static final String TABLE_NAME = "todoitemEnhanced1";

        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_PRIORITY = "priority";
        public static final String COLUMN_NAME_IS_DONE = "isdone";
        public static final String COLUMN_NAME_DUE_DATE = "duedate";


    }
}
