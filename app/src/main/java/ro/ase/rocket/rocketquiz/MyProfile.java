package ro.ase.rocket.rocketquiz;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class MyProfile extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        imgView = (ImageView) findViewById(R.id.iv_Profile);

        SharedPreferences sp =
                getApplication().getSharedPreferences("rocket_quiz", MODE_PRIVATE);

        String encoded = sp.getString("Image", "");

        if(!encoded.equals("")) {
            byte[] imageAsBytes = Base64.decode(encoded.getBytes(), Base64.DEFAULT);
            imgView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        }

    }


    public void imageClick(View view) {

        if (ActivityCompat.checkSelfPermission(MyProfile.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MyProfile.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_IMAGE);
        }

        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (requestCode == PICK_IMAGE && data != null) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                Bitmap imgP = BitmapFactory.decodeFile(picturePath);
                Bitmap newB= Bitmap.createScaledBitmap(imgP, 103, 120, true);
                imgView.setImageBitmap(newB);

                SharedPreferences sp =
                        getApplication().getSharedPreferences("rocket_quiz", MODE_PRIVATE);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                newB.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                String encoded = Base64.encodeToString(b, Base64.DEFAULT);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("Image", encoded);
                editor.apply();

            }
        }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
