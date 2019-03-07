package ro.ase.rocket.rocketquiz;

import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import ro.ase.rocket.rocketquiz.model.Developer;

public class ContactActivity extends AppCompatActivity {
    ProgressBar progressBar;
    ArrayList<Developer> devs = new ArrayList<Developer>();

    TextView textView4;
    TextView textView6;
    TextView textView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        textView4 = findViewById(R.id.tv_dan);
        textView6 = findViewById(R.id.tv_cap);
        textView7 = findViewById(R.id.tv_cozma);

        progressBar = findViewById(R.id.progressBar);

        ContactWorker worker = new ContactWorker();
        worker.execute();

    }

    public void setUnderLineText(TextView tv, String textToUnderLine) {
        String tvt = tv.getText().toString();
        int ofe = tvt.indexOf(textToUnderLine, 0);

        UnderlineSpan underlineSpan = new UnderlineSpan();
        SpannableString wordToSpan = new SpannableString(tv.getText());
        for (int ofs = 0; ofs < tvt.length() && ofe != -1; ofs = ofe + 1) {
            ofe = tvt.indexOf(textToUnderLine, ofs);
            if (ofe == -1)
                break;
            else {
                wordToSpan.setSpan(underlineSpan, ofe, ofe + textToUnderLine.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv.setText(wordToSpan, TextView.BufferType.SPANNABLE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }


    class ContactWorker extends AsyncTask<Void, Integer, Void>
    {

        @Override
        protected void onPreExecute() {
            if(progressBar != null )
            {
                progressBar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String address = "https://api.myjson.com/bins/1ajbom";
            HttpURLConnection connection = null;
            try
            {
                URL url = new URL(address);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = null;
                while((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                String result = stringBuilder.toString();

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("Developers");

                for(int i = 0; i< jsonArray.length(); i++)
                {
                    Developer dev = new Developer();
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    dev.setName(jsonObj.getString("name"));
                    jsonObj = jsonObj.getJSONObject("Contact");
                    dev.setPhoneNumber(jsonObj.getString("phoneNr"));
                    dev.setEmail(jsonObj.getString("email"));
                    devs.add(dev);
                }

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if(connection != null)
                {
                    connection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void a) {
            if(progressBar != null) {
                progressBar.setVisibility(View.INVISIBLE);

                if(devs.size() > 0)
                {

                    String str = String.format(devs.get(0).getName() + "%s", " (click)");
                    textView4.setText(str);
                    textView6.setText(devs.get(1).getName());
                    textView7.setText(devs.get(2).getName());
                    setUnderLineText(textView4, devs.get(0).getName());
                    setUnderLineText(textView6, textView6.getText().toString());
                    setUnderLineText(textView7, textView7.getText().toString());


                    textView4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                            builder.setMessage(devs.get(0).toString2()).setTitle(devs.get(0).getName());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });

                    textView6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                            builder.setMessage(devs.get(1).toString2()).setTitle(devs.get(1).getName());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });

                    textView7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ContactActivity.this);
                            builder.setMessage(devs.get(2).toString2()).setTitle(devs.get(2).getName());
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
                }

            }
        }

    }
}
