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
import android.widget.TextView;

public class Firstpage extends AppCompatActivity implements View.OnClickListener,DialogInterface.OnClickListener {
    Button login, signup;
    TextView thoery;
    Intent i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);

        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        i1 = new Intent(this, Signup.class);

    }
    @Override
    public void onClick(View v) {
        if (v == login) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
        }else{

            startActivity(i1);
        }

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == dialog.BUTTON_POSITIVE) {
            super.onBackPressed();
            dialog.cancel();

            if (which == dialog.BUTTON_NEGATIVE) {
                dialog.cancel();
            }
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
            case R.id.settings:
                break;
            case R.id.profile:
                Intent i7=new Intent(this,CameraActivity.class);
                startActivity(i7);

        }
        return super.onOptionsItemSelected(item);
    }
}
