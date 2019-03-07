package ro.ase.rocket.rocketquiz;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import ro.ase.rocket.rocketquiz.Helpers.DatabaseHelper;

public class EditProfile extends AppCompatActivity {

    private static final String TAG = "EditProfile";

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputUsername;
    //private TextInputLayout textInputPassword;
    private TextInputLayout textInputNewPassword;
    private TextInputLayout textInputOldPassword;

    private TextInputLayout textInputData;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDataSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        textInputEmail = findViewById(R.id.text_input_email);
        textInputUsername = findViewById(R.id.text_input_username);
        textInputOldPassword = findViewById(R.id.text_input_oldPassword);
        textInputNewPassword = findViewById(R.id.text_input_newPassword);
        textInputData = findViewById(R.id.text_input_data);

        mDisplayDate = (TextView) findViewById(R.id.tv_Date);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EditProfile.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDataSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDataSet: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" + year);


                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);

            }
        };


    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = textInputUsername.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputUsername.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            textInputUsername.setError("Username too long");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }


    private boolean validateOldPassword() {
        String oldPasswordInput = textInputEmail.getEditText().getText().toString().trim();

        if (oldPasswordInput.isEmpty()) {
            textInputOldPassword.setError("Field can't be empty");
            return false;
        } else {
            textInputOldPassword.setError(null);
            return true;
        }
    }

    private boolean validateNewPassword() {
        String newPasswordInput = textInputEmail.getEditText().getText().toString().trim();

        if (newPasswordInput.isEmpty()) {
            textInputNewPassword.setError("Field can't be empty");
            return false;
        } else {
            textInputNewPassword.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {


        if (!validateEmail() || !validateUsername() || !validateOldPassword() || !validateNewPassword()) {
            return;
        }

        String input = "Email: " + textInputEmail.getEditText().getText().toString();
        input += "\n";
        input += "Username: " + textInputUsername.getEditText().getText().toString();
        input += "\n";
        input += "Your birthday is: " + mDisplayDate.getText().toString();
        input += "\n";
        input += "Old password: " + textInputOldPassword.getEditText().getText().toString();
        input += "\n";
        input += "New password:" + textInputNewPassword.getEditText().getText().toString();

        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();

        String strSQL = "UPDATE users SET password = '" + textInputNewPassword.getEditText().getText().toString().trim() +
                "' WHERE username = '"+ textInputUsername.getEditText().getText().toString().trim() + "'";

        DatabaseHelper helper = new DatabaseHelper(EditProfile.this);
        SQLiteDatabase dbE = helper.getWritableDatabase();

        dbE.execSQL(strSQL);

        this.finish();
    }

    public void deleteAcc(View view)
    {

        if(!textInputUsername.getEditText().getText().toString().trim().equals("") && !textInputOldPassword.getEditText().getText().toString().equals("")) {
            String strSQL = "DELETE FROM users WHERE username = '" + textInputUsername.getEditText().getText()
                    .toString().trim() + "'";

            DatabaseHelper helper = new DatabaseHelper(EditProfile.this);
            SQLiteDatabase dbE = helper.getWritableDatabase();

            dbE.execSQL(strSQL);

            this.finish();
            Intent i = new Intent(EditProfile.this, LoginActivity.class);
            startActivityForResult(i, 1);
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
            builder.setMessage("Numele contului si parola sa fie introduse").setTitle("Eroare");
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}