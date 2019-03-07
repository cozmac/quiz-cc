package ro.ase.rocket.rocketquiz.chartView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChartView extends View {

    public HashMap<String, Integer> topMap = new HashMap<>();

    public ChartView(Context context) {
        super(context);
    }

    public ChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setData(HashMap<String, Integer> topMapp) {
        if (topMapp != null) {
            topMap = new HashMap<>();
            topMap.putAll(topMapp);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(topMap != null) {
            float xGap = (canvas.getWidth()  - 100) /
                    (topMap.size() - 1);
            int max = 0;

            for(Map.Entry mapE : topMap.entrySet())
            {
                if(Integer.parseInt(mapE.getValue().toString()) > max)
                {
                    max = Integer.parseInt(mapE.getValue().toString());
                }
            }
            float x = 50;
            float oldX = 0;
            float oldY = 0;
            for(Map.Entry mapE : topMap.entrySet()) {
                Paint pointPaint =
                        new Paint();
                pointPaint.setColor(Color.BLUE);
                pointPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                pointPaint.setStrokeWidth(4);
                float y =0;
                if(Integer.parseInt(mapE.getValue().toString()) > 0) {
                    y = canvas.getHeight() / 2 -
                            (canvas.getHeight() / 2 - 50) * (float)Integer.parseInt(mapE.getValue().toString()) / max;
                    canvas.drawCircle(x, y,
                            10, pointPaint);
                }
                else {
                    y = canvas.getHeight() / 2 - 50 +
                            (canvas.getHeight() / 2 - 50) * (float)-Integer.parseInt(mapE.getValue().toString()) / max;
                    canvas.drawCircle(x, y,
                            10, pointPaint);
                }
                if(oldX != 0 && oldY != 0) {
                    canvas.drawLine(oldX, oldY, x, y, pointPaint);
                }
                Paint textPaint = new Paint();
                textPaint.setColor(Color.BLACK);
                textPaint.setTextSize(40);
                canvas.drawText(mapE.getKey().toString(), x-10, y-20, textPaint);
                oldX = x;
                oldY = y;
                x += xGap;
            }
        }
    }
}
