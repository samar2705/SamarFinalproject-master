package comsdwecv.example.hp1.finalproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
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

import java.util.Calendar;

public class
    Home extends AppCompatActivity implements View.OnClickListener{
    Button exercise,test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setNotification();

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
    }
    public void setNotification() {//delay is after how much time(in millis) from current time you want to schedule the notification

        Intent intent1 = new Intent(Home.this, Notifacation.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(Home.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) Home.this.getSystemService(Home.this.ALARM_SERVICE);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 2);
        calendar.set(Calendar.MINUTE, 15);
        calendar.set(Calendar.SECOND, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5000, pendingIntent);


    }
}


