package ro.ase.rocket.rocketquiz.contracts;

import android.provider.BaseColumns;

import java.sql.Date;

public class DatabaseContract {

    public static final String DB_NAME = "rocketquiz.db";
    public static final int DB_VERSION = 1;

    public class UserTable implements BaseColumns {
        public static final String TABLE_NAME = "users";

        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_AGE = "age";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_USERNAME + " TEXT, " +
                        COLUMN_NAME_PASSWORD + " TEXT, " +
                        COLUMN_NAME_EMAIL + " EMAIL, " +
                        COLUMN_NAME_AGE + " INTEGER)";

        public static final String DROP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

    public class LogTable implements BaseColumns
    {
        public static final String TABLE_NAME = "log";

        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_DATE = "data";

        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME_USERNAME + " TEXT, " +
                        COLUMN_NAME_DATE+ " TEXT)";

        public static final String DROP_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}


