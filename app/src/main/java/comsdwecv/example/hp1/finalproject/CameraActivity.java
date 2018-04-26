package comsdwecv.example.hp1.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {


    private Bitmap bitmap;
    private ImageView imageView;
    Button btcamera,btGalery;
    static final int SELECT_IMAGE=1;
    static final int TAKE_IMAGE=0;

    SharedPreferences pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = (ImageView) findViewById(R.id.imageView);

        btcamera =(Button) findViewById(R.id.btcamera);
        btGalery = (Button) findViewById(R.id.btGalery);
        btcamera.setOnClickListener(this);
        btGalery.setOnClickListener(this);

        pref = getSharedPreferences("profile",MODE_PRIVATE);
        if(pref.getString("image",null)!=null){
            bitmap=BitmapFactory.decodeFile(pref.getString("image",null));
            imageView.setImageBitmap(bitmap);
        }

        if(!hasCamera()){
            btcamera.setEnabled(false);
        }
    }
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }
    @Override
    public void onClick(View v) {
        if(v==btcamera){
            //start another activity and receive a result back in case the activity exists return 0
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,TAKE_IMAGE );
        }else{
            Intent i = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, SELECT_IMAGE);
        }
    }
    /**
     *  The resultCode will be RESULT_CANCELED if the activity explicitly returned that, didn't return any result, or crashed during its operation.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == TAKE_IMAGE && resultCode == RESULT_OK){
            Bundle extra = data.getExtras();
            bitmap  = (Bitmap) extra.get("data");
            imageView.setImageBitmap(bitmap);
            saveImage(bitmap);
        }
        else if(requestCode == SELECT_IMAGE && resultCode == RESULT_OK)
        {
            Uri targetUri = data.getData();
            Toast.makeText(getApplicationContext(), targetUri.toString(), Toast.LENGTH_SHORT).show();
            Bitmap bitmap;
            try {

                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public File saveImage(Bitmap bitmap){
        File root = Environment.getExternalStorageDirectory();// internal storage launching .
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


        String filePath = root.getAbsolutePath()+"/DCIM/Camera/IMG_"+timeStamp+".jpg";
        File file = new File(filePath);// determinig the type of the file and its place.

        SharedPreferences.Editor editor=pref.edit();
        editor.putString("image",filePath);
        editor.commit();

        try
        {
            // if gallary nit full create a file and save images
            file.createNewFile();// create new file to save image.
            FileOutputStream ostream = new FileOutputStream(file);//saves root in this file
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);// compass bitmap in file
            ostream.close();// close
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, "Faild to save image", Toast.LENGTH_SHORT).show();
        }
        return file;
    }
}