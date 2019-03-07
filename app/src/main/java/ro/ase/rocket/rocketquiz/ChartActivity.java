package ro.ase.rocket.rocketquiz;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import ro.ase.rocket.rocketquiz.chartView.ChartView;

public class ChartActivity extends AppCompatActivity {

    public HashMap<String, Integer> topMap = new HashMap<>();;
    ChartView chartView;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();



    public void showData(DataSnapshot dataSnapshot)
    {
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            String name = ds.getKey();

            String value = ds.getValue().toString();

            topMap.put(name, Integer.parseInt(value));

            Log.d("Prontolemn", name + ' ' + value);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        chartView = findViewById(R.id.chartView);


        topMap.put("Salut", 10);
        topMap.put("Merge", 20);
        topMap.put("Dan", 12);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        chartView.setData(topMap);
        chartView.invalidate();
    }
}
