package comsdwecv.example.hp1.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class
    Home extends AppCompatActivity implements View.OnClickListener{
    Button exercise,test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        exercise= (Button) findViewById(R.id.exercise);
        test= (Button) findViewById(R.id.test);
        exercise.setOnClickListener(this);
        test.setOnClickListener(this);

    }


    public void onClick(View v) {
        if (v == exercise) {
            Intent i = new Intent(this, Exercises.class);
            startActivity(i);
        } else {
            Intent i1 = new Intent(this, Tests.class);
            startActivity(i1);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("loguot");
                builder.setMessage("are you sure you want to logout");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i2 = new Intent(getBaseContext(), Login.class);
                        startActivity(i2);


                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();

                break;

            case R.id.profile:
                Intent i7=new Intent(this,CameraActivity.class);
                startActivity(i7);

        }
        return super.onOptionsItemSelected(item);
    }}


