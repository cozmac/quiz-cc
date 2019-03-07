package ro.ase.rocket.rocketquiz;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import ro.ase.rocket.rocketquiz.Helpers.DataBaseHelperLog;
import ro.ase.rocket.rocketquiz.Helpers.DatabaseHelper;
import ro.ase.rocket.rocketquiz.contracts.DatabaseContract;
import ro.ase.rocket.rocketquiz.model.User;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        String[] languages = new String[]{
                "English", "Deutsch"
        };

        Spinner s = (Spinner) findViewById(R.id.spinnerLanguage);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }

    public void applyClick(View view) {
        Toast.makeText(Settings.this, R.string.apply_settings, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void saveCsv(View view) {

        if (ActivityCompat.checkSelfPermission(Settings.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Settings.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "MyCSV");
        if (!fileDir.exists()) {
            try {
                fileDir.mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "RocketData" + File.separator + "LogRocket.csv");
         if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(file.exists()){
            try {
                FileWriter fileWriter  = new FileWriter(file);
                BufferedWriter bfWriter = new BufferedWriter(fileWriter);

                DatabaseHelper helper =
                        new DatabaseHelper(Settings.this);
                SQLiteDatabase db = helper.getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM " +
                        DatabaseContract.LogTable.TABLE_NAME, null);

                while (cursor.moveToNext()) {
                    int index = cursor.getColumnIndex(
                            DatabaseContract.LogTable.COLUMN_NAME_USERNAME);
                    bfWriter.write(cursor.getString(index));
                    index = cursor.getColumnIndex(DatabaseContract.LogTable.COLUMN_NAME_DATE);
                    bfWriter.write(cursor.getString(index));
                }

                bfWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(Settings.this, R.string.success, Toast.LENGTH_SHORT).show();

    }
}
