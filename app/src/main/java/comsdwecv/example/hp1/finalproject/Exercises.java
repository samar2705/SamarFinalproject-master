package comsdwecv.example.hp1.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class Exercises extends AppCompatActivity {

    ListView lvSigns;
    ArrayList<Item> items = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        items.add(new Item("No enter",R.drawable.noenter));
        items.add(new Item("No u turn",R.drawable.noparsa));
        items.add(new Item("There is no right turn",R.drawable.nopniyayamena));
        items.add(new Item("Parking",R.drawable.parking));
        items.add(new Item("Stop",R.drawable.stopsign));
        items.add(new Item("There is a train",R.drawable.train));
        items.add(new Item("No parking",R.drawable.noparking));
        items.add(new Item("closed road",R.drawable.fshfotemnljehaten));
        items.add(new Item("Crosswalk",R.drawable.mmrmosha));
        items.add(new Item("Work site",R.drawable.ashghalbltaree));
        items.add(new Item("A municipal road area",R.drawable.soraalghamsen));
        items.add(new Item("Curve right and then left or left and then right",R.drawable.tareemoltweye));


        lvSigns = (ListView) findViewById(R.id.lvSigns);

        CustomAdapter adapter = new CustomAdapter(this, R.layout.custom_row,items);
        lvSigns.setAdapter(adapter);

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
