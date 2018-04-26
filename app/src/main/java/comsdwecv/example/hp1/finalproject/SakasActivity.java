package comsdwecv.example.hp1.finalproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SakasActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tvQuestions;
    Button ans1,ans2,ans3,next;
    Question question;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sakas);

        Intent intent=getIntent();
        question= (Question) intent.getSerializableExtra("ques");
        position=intent.getIntExtra("position",0);


        tvQuestions= (TextView) findViewById(R.id.tvQuestion);
        ans1=(Button)findViewById(R.id.button);
        ans2=(Button)findViewById(R.id.button2);
        ans3=(Button)findViewById(R.id.button3);
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);

        tvQuestions.setText(question.getQuestion());
        ans1.setText(question.getAnswer1());
        ans2.setText(question.getAnswer2());
        ans3.setText(question.getAnswer3());

    }

    @Override
    public void onClick(View v) {
        if(v==ans1){
            if(question.getAnswer1().equals(question.getCorrectanswer()))
            {ans1.setBackgroundColor(Color.GREEN);}
            else
                ans1.setBackgroundColor(Color.RED);
        }
        else
            if(v==ans2){
                if(question.getAnswer2().equals(question.getCorrectanswer()))
                {ans2.setBackgroundColor(Color.GREEN);}
                else
                    ans2.setBackgroundColor(Color.RED);
            }else
        if(v==ans3){
            if(question.getAnswer3().equals(question.getCorrectanswer()))
            {ans3.setBackgroundColor(Color.GREEN);}
            else
                ans3.setBackgroundColor(Color.RED);
        }

        if(v==next)
        {
            DBHandling db=new DBHandling(this);
            ArrayList<Question> questions=db.getData();
            position+=1;
            if(position==questions.size())
                position=0;
            tvQuestions.setText(questions.get(position).getQuestion());
            ans1.setText(questions.get(position).getAnswer1());
            ans2.setText(questions.get(position).getAnswer2());
            ans3.setText(questions.get(position).getAnswer3());

            ans1.setBackgroundColor(Color.WHITE);
            ans2.setBackgroundColor(Color.WHITE);
            ans3.setBackgroundColor(Color.WHITE);


        }

    }
}
