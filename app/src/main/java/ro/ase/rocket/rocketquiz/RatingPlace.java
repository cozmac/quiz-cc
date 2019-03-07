package ro.ase.rocket.rocketquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RatingPlace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_place);
    }

    public void noPress(View view)
    {
        finish();
    }

    //temporar
    public void sendPress(View view)
    {
        finish();
        Toast.makeText(RatingPlace.this, R.string.thx, Toast.LENGTH_SHORT).show();
    }
}