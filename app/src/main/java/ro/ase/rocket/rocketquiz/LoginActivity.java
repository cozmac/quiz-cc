package ro.ase.rocket.rocketquiz;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import ro.ase.rocket.rocketquiz.Helpers.DataBaseHelperLog;
import ro.ase.rocket.rocketquiz.Helpers.DatabaseHelper;
import ro.ase.rocket.rocketquiz.contracts.DatabaseContract;
import ro.ase.rocket.rocketquiz.model.User;


public class LoginActivity extends AppCompatActivity {

    private EditText usern_login;
    private EditText passn_login;
    private ArrayList<User> usersList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usern_login = findViewById(R.id.editText);
        passn_login = findViewById(R.id.editText2);

        //usersList.add((User)getIntent().getParcelableExtra("RegisteredUser"));

        SharedPreferences sp =
                getApplication().getSharedPreferences("rocket_quiz", MODE_PRIVATE);
        String username = sp.getString("Username", "");
        String password = sp.getString("Password", "");

        usern_login.setText(username);
        passn_login.setText(password);
    }

    public void buttonClick(View view) {

        DatabaseHelper helper =
                new DatabaseHelper(LoginActivity.this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                DatabaseContract.UserTable.TABLE_NAME +
                " WHERE " +
                DatabaseContract.UserTable.COLUMN_NAME_USERNAME +
                " = '" + usern_login.getText().toString() + "'", null);

        User user = new User();
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(
                    DatabaseContract.UserTable.COLUMN_NAME_USERNAME);
            user.setUsername(cursor.getString(index));
            index = cursor.getColumnIndex(DatabaseContract.UserTable.COLUMN_NAME_PASSWORD);
            user.setPassword(cursor.getString(index));
            index = cursor.getColumnIndex(DatabaseContract.UserTable.COLUMN_NAME_AGE);
            user.setAge(cursor.getInt(index));
            index = cursor.getColumnIndex(DatabaseContract.UserTable.COLUMN_NAME_EMAIL);
            user.setEmail(cursor.getString(index));
            usersList.add(user);
        }


        if (user.getUsername() != null) {
            if (user.getUsername().equals(usern_login.getText().toString()) && user.getPassword().equals(passn_login.getText().toString())) {

                DatabaseHelper helperLog = new DatabaseHelper(LoginActivity.this);
                SQLiteDatabase dbLog = helperLog.getWritableDatabase();
                ContentValues cv = new ContentValues();
                cv.put(DatabaseContract.LogTable.COLUMN_NAME_USERNAME, user.getUsername());

                SimpleDateFormat sdf = new SimpleDateFormat("DD-MM-YYYY -> H:m:s");
                String date = sdf.format(Calendar.getInstance().getTime());

                cv.put(DatabaseContract.LogTable.COLUMN_NAME_DATE, date);
                db.insert(DatabaseContract.LogTable.TABLE_NAME, null, cv);

                this.finish();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("Parola e gresita").setTitle("Eroare");
                AlertDialog dialog = builder.create();
                dialog.show();
                usern_login.setText("");
                passn_login.setText("");
            }
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Nu se regaseste in baza de date").setTitle("Eroare");
            AlertDialog dialog = builder.create();
            dialog.show();
            usern_login.setText("");
            passn_login.setText("");
        }

    }


        /*if (usern_login.getText().toString().equals("admin") && passn_login.getText().toString().equals("admin"))
        {
            this.finish();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
        else
            {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setMessage("Username gresit (Corect: admin), Parola: admin").setTitle("Eroare");
            AlertDialog dialog = builder.create();
            dialog.show();
            usern_login.setText("");
            passn_login.setText("");
        }*/



    public void registerClick(View view)
    {
        Intent i = new Intent(LoginActivity.this, registerActivity.class);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            User user = (User) data.getParcelableExtra("RegisteredUser");
            usersList.add(user);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sp =
                getApplication().getSharedPreferences("rocket_quiz",
                        MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("Username", usern_login.getText().toString());
        editor.putString("Password", passn_login.getText().toString());
        editor.apply();
    }
}