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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    EditText name,age,etemail1,etpass1;
    Button signup1;
    Intent intent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup1= (Button) findViewById(R.id.signup1);
        name=(EditText)findViewById(R.id.name);
        age=(EditText)findViewById(R.id.age);
        etemail1=(EditText)findViewById(R.id.etemail1);
        etpass1=(EditText)findViewById(R.id.etpass1);
        signup1.setOnClickListener(this);
        intent1=new Intent(this,Home.class);




    }

    @Override
    public void onClick(View v) {
        if(etemail1.getText().toString().equals("")||etpass1.getText().toString().equals("")||age.getText().toString().equals("")||name.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(),"please fill all the information",Toast.LENGTH_SHORT).show();
        }else {
            startActivity(intent1);
            etpass1.setText("");
            etemail1.setText("");
            name.setText("");
            age.setText("");
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
