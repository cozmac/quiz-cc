package ro.ase.rocket.rocketquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ReportsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
    }

    public void reportOne(View view)
    {
        Intent intent = new Intent(ReportsActivity.this, ReportLog.class);
        startActivity(intent);
    }

    public void reportTwo(View view)
    {
        Intent intent = new Intent(ReportsActivity.this, ReportMail.class);
        startActivity(intent);
    }

    public void reportChart(View view)
    {
        Intent intent = new Intent(ReportsActivity.this, ChartActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }
}
