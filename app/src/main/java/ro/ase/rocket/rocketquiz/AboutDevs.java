package ro.ase.rocket.rocketquiz;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import ro.ase.rocket.rocketquiz.CustomAdapter.CustomAdapter;
import ro.ase.rocket.rocketquiz.model.Developer;

public class AboutDevs extends AppCompatActivity {

    ArrayList<Developer> developers = new ArrayList<>();

    ListView lvDevs;
    CustomAdapter devsAdapter;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_devs);

        progressBar = findViewById(R.id.progressBar);


        Bitmap iconSarpe = BitmapFactory.decodeResource(getResources(), R.drawable.sarpe);
        Bitmap resized1 = Bitmap.createScaledBitmap(iconSarpe, 103, 120, true);
        Developer sarpe = new Developer(resized1, getResources().getString(R.string.tw_cozma),
                getResources().getString(R.string.tw_sarpe1));
        developers.add(sarpe);

        Bitmap iconCap = BitmapFactory.decodeResource(getResources(), R.drawable.cap);
        Bitmap resized2 = Bitmap.createScaledBitmap(iconCap, 103, 120, true);
        Developer cap = new Developer(resized2, getResources().getString(R.string.tw_leo),
                getResources().getString(R.string.tw_cap1));
        developers.add(cap);

        Bitmap iconDan = BitmapFactory.decodeResource(getResources(), R.drawable.dani);
        Bitmap resized3 = Bitmap.createScaledBitmap(iconDan, 103, 120, true);
        Developer dan = new Developer(resized3, getResources().getString(R.string.tw_dani),
                getResources().getString(R.string.tw_dani1));
        developers.add(dan);

        ArrayList<String> list = new ArrayList<>();
        for(Developer d: developers)
        {
            list.add(d.toString());
        }

        lvDevs = findViewById(R.id.listView_Devs);

        devsAdapter = new CustomAdapter(this, developers);

        lvDevs.setAdapter(devsAdapter);


    }

}
