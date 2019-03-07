package ro.ase.rocket.rocketquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class AboutNav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_nav);
    }

    public void openAboutUs(View view)
    {
        Intent i = new Intent(AboutNav.this, AboutDevs.class);
        startActivity(i);
    }

    public void openContact(View view)
    {
        Intent i=new Intent(AboutNav.this, ContactActivity.class);
        startActivity(i);
    }

    public void seeMap(View view)
    {
        Intent i=new Intent(AboutNav.this, MapOnline.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}