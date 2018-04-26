package comsdwecv.example.hp1.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Hp1 on 23/01/2018.
 */

public class DBHandling extends SQLiteOpenHelper {
    private static DBHandling sInstance;
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "theory.db"; //database name
    public static final String TABLE_QUIZ = "quiz";// data table name

    //column names
    public static final String COL_ID = "id";
    public static final String COL_QUESTION = "question";
    public static final String COL_ANSWER1 = "answer1";
    public static final String COL_ANSWER2 = "answer2";
    public static final String COL_ANSWER3 = "answer3";
    public static final String COL_CORRECTANSWER= "correctanswer";

     //constructor
    public DBHandling(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public static synchronized DBHandling getsInstance(Context context){
        if(sInstance == null){
            sInstance = new DBHandling(context.getApplicationContext());
        }
        return sInstance;
    }

// create table users this methods is called when application is loaded
    @Override
    public void onCreate(SQLiteDatabase db) {
        //in case table already exists need to drop the re-build
        //save query to create table in database according to requrements in a string variable

        String query = " CREATE TABLE " + TABLE_QUIZ
                + "(" + COL_ID	+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_QUESTION + " TEXT, "
                + COL_ANSWER1 + " TEXT, "
                + COL_ANSWER2 + " TEXT, "
                + COL_ANSWER3 + " TEXT, "
                + COL_CORRECTANSWER + " TEXT "
                +");";
        Log.d("QUERY", query);

        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);
            db.execSQL(query);
        }catch(Exception e){
            Log.d("Couldn't add table", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);
        onCreate(db);
    }




    public void addQuestion(Question question) {
        //create ContentValue containning values to be inserted/ updated in database in this table
        ContentValues values = new ContentValues();

        values.put(COL_QUESTION, question.getQuestion());
        values.put(COL_ANSWER1, question.getAnswer1());
        values.put(COL_ANSWER2, question.getAnswer2());
        values.put(COL_ANSWER3, question.getAnswer3());
        values.put(COL_CORRECTANSWER, question.getCorrectanswer());
        // create SQLiteDatabase object to enable writing/reading in database
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TABLE_QUIZ, null, values);
        question.setId(id);//update the user ID according to the auto increment..

        //logging for debugging purposes
        Log.d("ID ", "Question id: " + id + " added to DB");

        //close connection to database
        db.close();

    }
    //search data from DB
    public ArrayList<Question> getData(){
        String[] r = new String[6];
        int[] col = new int[6];
        String query = "SELECT * FROM "+ TABLE_QUIZ ;

        ArrayList<Question> questions= new ArrayList<Question>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        col[0]=c.getColumnIndex(COL_ID);
        col[1]=c.getColumnIndex(COL_QUESTION);
        col[2]=c.getColumnIndex(COL_ANSWER1);
        col[3]=c.getColumnIndex(COL_ANSWER2);
        col[4]=c.getColumnIndex(COL_ANSWER3);
        col[5]=c.getColumnIndex(COL_CORRECTANSWER);

        while(!c.isAfterLast()){
            for(int i=0;i<col.length;i++){
                r[i]=c.getString(col[i]);
            }
            questions.add(new Question(Long.parseLong(r[0]),r[1],r[2],r[3],r[4],r[5]));
            c.moveToNext();
        }
        return questions;
    }
}
