package ro.ase.rocket.rocketquiz;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import ro.ase.rocket.rocketquiz.Helpers.DataBaseHelperLog;
import ro.ase.rocket.rocketquiz.Helpers.DatabaseHelper;
import ro.ase.rocket.rocketquiz.contracts.DatabaseContract;
import ro.ase.rocket.rocketquiz.model.User;

public class ReportLog extends AppCompatActivity {

    private ListView listView;

    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_log);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }


        listView = (ListView) findViewById(R.id.listViewLOG);


        DatabaseHelper helper =
                new DatabaseHelper(ReportLog.this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                DatabaseContract.LogTable.TABLE_NAME, null);

        while (cursor.moveToNext()) {
            String willBeAdded = "";
            int index = cursor.getColumnIndex(
                    DatabaseContract.LogTable.COLUMN_NAME_USERNAME);
            willBeAdded += cursor.getString(index);

            willBeAdded += " - ";


            index = cursor.getColumnIndex(DatabaseContract.LogTable.COLUMN_NAME_DATE);
            willBeAdded += cursor.getString(index);

            arrayList.add(willBeAdded);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }

    public void clearLog(View view) {
        String strSQL = "DELETE FROM log";

        DatabaseHelper helper = new DatabaseHelper(ReportLog.this);
        SQLiteDatabase dbE = helper.getWritableDatabase();

        dbE.execSQL(strSQL);

        arrayList.clear();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }


    public void writeToFile(View view) {

        String filename = "RaportLog.txt";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            for (String textToSave : arrayList) {
                fos.write(textToSave.getBytes());
                fos.write("\r\n".getBytes());
            }
            fos.close();

            Toast.makeText(this, "TXT saved", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1000:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show();
                }
        }

    }
}
