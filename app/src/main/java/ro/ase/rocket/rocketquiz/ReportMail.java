package ro.ase.rocket.rocketquiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import ro.ase.rocket.rocketquiz.Helpers.DatabaseHelper;
import ro.ase.rocket.rocketquiz.contracts.DatabaseContract;

public class ReportMail extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner spinner;
    private TextView tv_Mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_mail);

        spinner = (Spinner) findViewById(R.id.spinnerMail);
        tv_Mail = (TextView) findViewById(R.id.tv_reportMail);

        ArrayList<String> spinnerArray = new ArrayList<>();
        spinnerArray.add("List of users");

        int poz=0;

        DatabaseHelper helper =
                new DatabaseHelper(ReportMail.this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT username FROM " +
                DatabaseContract.UserTable.TABLE_NAME, null);


        while (cursor.moveToNext()) {

            int index = cursor.getColumnIndex(
                    DatabaseContract.LogTable.COLUMN_NAME_USERNAME);
            Log.d("Testing", cursor.getString(index));
            spinnerArray.add( cursor.getString(index));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        DatabaseHelper helper =
                new DatabaseHelper(ReportMail.this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT username, email, age FROM " +
                DatabaseContract.UserTable.TABLE_NAME + " WHERE username = '" + spinner.getSelectedItem().toString() + "'" , null);


        while (cursor.moveToNext()) {

            String displayed = "Username: ";

            int index = cursor.getColumnIndex(
                    DatabaseContract.UserTable.COLUMN_NAME_USERNAME);
            displayed += cursor.getString(index);

            displayed += "\nEmail: ";

            index = cursor.getColumnIndex(DatabaseContract.UserTable.COLUMN_NAME_EMAIL);
            displayed += cursor.getString(index);

            displayed +="\nAge: ";

            index = cursor.getColumnIndex(DatabaseContract.UserTable.COLUMN_NAME_AGE);
            displayed += cursor.getString(index);

            tv_Mail.setText(displayed);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        return;
    }
}
