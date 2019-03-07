package ro.ase.rocket.rocketquiz;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import ro.ase.rocket.rocketquiz.Helpers.DatabaseHelper;
import ro.ase.rocket.rocketquiz.contracts.DatabaseContract;
import ro.ase.rocket.rocketquiz.model.User;
import ro.ase.rocket.rocketquiz.model.UserType;

public class registerActivity extends AppCompatActivity {

    private RadioButton rb_teacher;
    private RadioButton rb_student;
    private EditText et_email;
    private EditText et_userid;
    private EditText et_password;
    private SeekBar sb_varsta;
    private TextView tv_seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rb_teacher = findViewById(R.id.rb_teacher);
        rb_student = findViewById(R.id.rb_student);
        et_email = findViewById(R.id.et_mail);
        et_userid = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        sb_varsta = findViewById(R.id.seekBar_varsta);
        tv_seekbar = findViewById(R.id.tv_seekbar);

        tv_seekbar.setText(getString(R.string.seekbartext, sb_varsta.getProgress()));

        sb_varsta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int progressSeek = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressSeek = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tv_seekbar.setText(getString(R.string.seekbartext, progressSeek));
            }
        });
    }


    public void registerFormClick(View view)
    {
        if((!rb_teacher.isChecked() && !rb_student.isChecked()) || et_email.getText().toString().trim().equals("") ||
                et_userid.getText().toString().trim().equals("") || et_password.getText().toString().trim().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Every field is mandatory", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Register made with success", Toast.LENGTH_SHORT).show();

            UserType temp;

            if(rb_teacher.isChecked())
                temp=UserType.TEACHER;
            else
                temp=UserType.STUDENT;

            User obj = new User(temp, et_userid.getText().toString().trim(), et_password.getText().toString().trim(),
                    et_email.getText().toString().trim(), sb_varsta.getProgress());


            DatabaseHelper helper = new DatabaseHelper(registerActivity.this);
            SQLiteDatabase db = helper.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(DatabaseContract.UserTable.COLUMN_NAME_USERNAME, obj.getUsername());
            cv.put(DatabaseContract.UserTable.COLUMN_NAME_PASSWORD, obj.getPassword());
            cv.put(DatabaseContract.UserTable.COLUMN_NAME_EMAIL, obj.getEmail());
            cv.put(DatabaseContract.UserTable.COLUMN_NAME_AGE, obj.getAge());
            db.insert(DatabaseContract.UserTable.TABLE_NAME,null, cv);

            Intent i = new Intent(registerActivity.this, LoginActivity.class);
            i.putExtra("RegisteredUser", obj);
            setResult(RESULT_OK, i);
            startActivity(i);
            this.finish();
        }
    }

    @Override
    public void onBackPressed()
    {
        this.finish();
    }

}