package comsdwecv.example.hp1.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Tests extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {

    InputStream is=null;
    InputStreamReader in;
    BufferedReader br;
    int index=0;
    DBHandling db;
    TextView tvQuestion;
    ListView lsquestions;

    ArrayAdapter<Question> adapter;
            //parameters for file reading used in both methods
    String temp="", all="";
    int num;

    ListView lvQuestions;
    ArrayList<String> arrQuestions = new ArrayList<String>();
    ArrayList<Question> arrQuestion = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);
        lvQuestions = (ListView)findViewById(R.id.lsquestions);

        db = new DBHandling(this);

        fillDATABASE();


        adapter = new CustomAdapterQuestion(this,R.layout.custom_row_question ,arrQuestion);
        lvQuestions.setAdapter(adapter);
        lvQuestions.setOnItemClickListener(this);


    }

    public void readFile()
    {
        try{
            temp="";
            all="";
            //4. open the file for reading
            is=getResources().openRawResource(R.raw.myfile2);
            //open a channel for file reading
            in= new InputStreamReader(is);
            br= new BufferedReader(in);
            //while end of file not reached
            //readline() reads one line at a time
            while((temp=br.readLine())!=null) {
                all += temp + "\n";//concatinate all lines to a string
                arrQuestions.add(temp);
            }

            is.close();//file close
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {

     index++;

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
    //this method populates the database for the first
    //time this application is started after intallation
    public void fillDATABASE(){

        db=new DBHandling(this);
        if(db.getData().size()==0){
            db.addQuestion(new Question(0,"Where should you stop your vehilce when you are obliged to stop before a railway crossing","In the place with the best field of view, without disturbing train traffic. ","after the stop line, eight meters before the proximate rail.","Before the stop line. if there is no stop line-before the barrier..if there is no barrier - at least four meters before the nearest rail.","Before the stop line. if there is no stop line-before the barrier..if there is no barrier - at least four meters before the nearest rail." ));
            db.addQuestion(new Question(0,"What is the order of actions to be taken in order to stop or park?", "Check if it is premitted; make a peripheral check and slow down carefully.","The order of actions before parking is of no significance.","Slow down, deveiate, stop and signal.", "Check if it is premitted; make a peripheral check and slow down carefully."));
            db.addQuestion(new Question(0,"What is the maximum freight height (from the road's surface) that may be carried by a commerical cehilce with an all up weight of up to 3,500kg?","3.0 metters.","3.5 metters.","4.0 metters.","2.5 metters."));
            db.addQuestion(new Question(0,"Within which distance from a tunnel is it prohibited to overtake?","Fifty metters before the entry to the tunnel and up to fifty meters after the exit from the tunnel.","there is no overtaking restriction around a tunnel.","100 metters before the entry to the tunnel and up to 100 metters after the exit from the tunnel.","from the entry until the exit of the tunnel."));
            db.addQuestion(new Question(0,"How would you shift to a lower gear while driving uphill in an automatic transformation vehilce, without moving the gear handle?","By a sharp and quick pressure (\"kick down\") on the accelerator, or by applying a designated electric switch.","By taking the foot off the brake pedal without giving gas, in order to get the right speed.","By stepping firmly on thr brake pedal.","By gradually stepping on the accelerator, until getting the desired redult."));
            db.addQuestion(new Question(0,"Is ir premittedto park, in an urban area, vehilces transporting hazardous materials with an al upn weight of more than 3,500kg?","Yes, but only at a distance of more then 400 meters from public or residental buildings.","Yes, only when the tanks are empty.","Yes, provided that nobody smokes next to it.","It is absolutely forbidden to park this type of vehilce on an urban road."));
            db.addQuestion(new Question(0,"What would you do if your driving lane is obstructed?","Quickly cross the obstruction from either its right or letf.","Drive slowlyand disregard the road sign that are posted in the place.","Obey the signals that are given by a policeman, a signaler or a road sign","Obey the signals that are given by a policeman, a signaler or a road sign"
            ));
            db.addQuestion(new Question(0,"the auxiliary (parking/hand) brake is used by the driver: ","to secure his vehilce from any movement","to reinforce the barking force of the service brake","only new drivers use the auxiliary brakre","to secure his vehilce from any movement."));
            db.addQuestion(new Question(0,"What are we required to see in the outer side mirrors of the vehicle?","any moving vehicle to the right of our vehicle.","the tip of the vehicle's side body and the road at the sides of the vehicle.","the back wheel and the area in front of it.","the tip of the vehicle's side body and the road at the sides of the vehicle."));
            db.addQuestion(new Question(0,"What is the risk of using the GPS navigation system while driving?","driver distruction.","disruption of the engine managment system.","fast draining of the vehicle's battery.","driver distruction."));




        }
        arrQuestion = db.getData();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Question q= arrQuestion.get(position);
        Intent i=new Intent(this,SakasActivity.class);
        i.putExtra("ques", q);
        i.putExtra("position",position);
        startActivity(i);

    }
}
