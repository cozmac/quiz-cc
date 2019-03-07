package ro.ase.rocket.rocketquiz.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import ro.ase.rocket.rocketquiz.contracts.DatabaseContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseContract.DB_NAME,
                null, DatabaseContract.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                DatabaseContract.UserTable.CREATE_TABLE);

        sqLiteDatabase.execSQL(DatabaseContract.LogTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(
                DatabaseContract.UserTable.DROP_TABLE);
        sqLiteDatabase.execSQL(
                DatabaseContract.UserTable.CREATE_TABLE);

        sqLiteDatabase.execSQL(DatabaseContract.LogTable.DROP_TABLE);
        sqLiteDatabase.execSQL(DatabaseContract.LogTable.CREATE_TABLE);
    }
}
