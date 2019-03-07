package ro.ase.rocket.rocketquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;

import static android.graphics.Bitmap.createScaledBitmap;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;
    private TextView yourBest;
    private ImageView imgQuizTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp =
                getApplication().getSharedPreferences("rocket_quiz", MODE_PRIVATE);
        int score = sp.getInt("Score", 0);
        String nameOfUser = sp.getString("Username", "");
        if(nameOfUser!=null) {
            FirebaseDatabase.getInstance().getReference(nameOfUser).setValue(score);
        }

        yourBest = (TextView) findViewById(R.id.yourBest);
        yourBest.setText(String.format(MainActivity.this.getApplicationContext().getString(R.string.your_best), score));


        imgQuizTime = (ImageView) findViewById(R.id.quizTime);

        Picasso.with(MainActivity.this)
                .load("https://cdn.eventfinda.co.nz/uploads/events/transformed/1089736-490626-34.png")
                .resize(480,200)
                .into(imgQuizTime);

        String[] arraySpinner = new String[] {
                "Select your domain", "General Knowledge"
        };

        Spinner s = (Spinner)findViewById(R.id.domainSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        dl = (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this, dl, R.string.navdra_open, R.string.navdra_close );
        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);

        abdt.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav_view = (NavigationView)findViewById(R.id.nav_view);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.myprofile)
                {
                    Intent intentProfile = new Intent(MainActivity.this, MyProfile.class);
                    startActivity(intentProfile);
                }
                else if(id == R.id.settings)
                {
                    Intent intentSettings = new Intent(MainActivity.this, Settings.class);
                    startActivity(intentSettings);
                }
                else if(id == R.id.editprofile)
                {
                    Intent intentEdiProfile = new Intent(MainActivity.this, EditProfile.class);
                    startActivity(intentEdiProfile);
                }
                else if(id == R.id.rating)
                {
                    Intent intentR = new Intent(MainActivity.this, RatingPlace.class);
                    startActivity(intentR);
                }
                else if(id == R.id.about)
                {
                    Intent intentAbout = new Intent(MainActivity.this, AboutNav.class);
                    startActivity(intentAbout);
                }
                else if(id == R.id.goonline)
                {
                    Intent implicitIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.ase.ro/"));
                    startActivity(implicitIntent);
                }
                else if(id == R.id.reports)
                {
                    Intent intent = new Intent(MainActivity.this, ReportsActivity.class);
                    startActivity(intent);
                }
                else if(id==R.id.logout) {
                    Toast.makeText(MainActivity.this, R.string.logout, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public void playHit(View view)
    {
        SharedPreferences sp =
                getApplication().getSharedPreferences("rocket_quiz", MODE_PRIVATE);
        int score = sp.getInt("Score", 0);

        yourBest = (TextView) findViewById(R.id.yourBest);
        yourBest.setText(String.format(MainActivity.this.getApplicationContext().getString(R.string.your_best), score));

        Spinner spinner = (Spinner) findViewById(R.id.domainSpinner);
        if(spinner.getSelectedItem().toString() == "General Knowledge") {
            Intent intent = new Intent(MainActivity.this, quiz.class);
            startActivity(intent);
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Please select a domain").setTitle("Error");
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}