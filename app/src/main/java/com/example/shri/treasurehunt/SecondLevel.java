package com.example.shri.treasurehunt;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SecondLevel extends AppCompatActivity {

    public int total_score,level;
    int hint_number,hints_attempted;
    CountDownTimer mCountDownTimer;
    static final long START_TIME_IN_MILLIS=1200000;
    long mTimeLeftInMillis=START_TIME_IN_MILLIS;
    TextView mTextViewCountDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second_level );


        TextView marquee = (TextView)findViewById( R.id.marquee );
        mTextViewCountDown=findViewById(R.id.timer);
        marquee.setSelected( true );
        startTimer();
        total_score = 0;
        hint_number = 0;
        hints_attempted=0;


        final Button check = (Button)findViewById( R.id.check );

        final String hints[] = {"Whats runs all day but never gets tired???",
                "I get bigger when I eat, but die weaker when I drink.",
                "I move very fast but i dont have feet,You can hear me but not for my mouth, I can bring down a building yet i'm not a machine.",
                "What has roots as nobody see; is taller than trees; up, up it goes, and yet it never grows?",
                "A thing which is green. It grows tall or stays cut and clean"};

        final String answers[] = {"river","fire","wind","mountain","grass"};

        final TextView hint = (TextView)findViewById( R.id.hintquestion );

        final EditText answer = (EditText)findViewById( R.id.ans );

        hint.setText(hints[hint_number]);

        Intent i = getIntent();
        level = i.getIntExtra( "levels",0 );
        total_score = i.getIntExtra( "mScore",0 );


        check.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getApplicationContext(),"Checking your Answer ..." ,Toast.LENGTH_SHORT).show();

                /*if (hints_attempted==2)
                {
                    score = score+10;
                    ++hint_number;
                    hints_attempted++;
                    Toast.makeText( getApplicationContext(),"Great! your Last Clue !!!" ,Toast.LENGTH_SHORT).show();
                    hint.setText( hints[hint_number] );
                    answer.setText( "" );
                }*/
                if(answer.getText().toString().equalsIgnoreCase(answers[hint_number]) && hints_attempted==4)
                {
                    total_score = total_score+10;
                    hints_attempted++;
                    //Toast.makeText( getApplicationContext(),"Great! Your next Clue !!!" ,Toast.LENGTH_SHORT).show();
                    hint.setText( hints[hint_number] );
                    answer.setText( "" );
                    Intent intent = new Intent( SecondLevel.this, Main2Activity.class );
                    intent.putExtra("score",total_score);
                    intent.putExtra( "level",level );
                    startActivity(intent);
                    finish();
                }
                else if(answer.getText().toString().equalsIgnoreCase(answers[hint_number]) && hints_attempted<=3)
                {
                    total_score = total_score+10;
                    ++hint_number;
                    hints_attempted++;
                    Toast.makeText( getApplicationContext(),"Great! Your next Clue !!!" ,Toast.LENGTH_SHORT).show();
                    hint.setText( hints[hint_number] );
                    answer.setText( "" );
                }
                else
                {
                    Toast.makeText( getApplicationContext(),"Sorry :( Please try again !" ,Toast.LENGTH_SHORT).show();
                    total_score = total_score-2;
                }
                //
            }
        } );



    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUnitilFinished) {
                mTimeLeftInMillis = millisUnitilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
            }
        }.start();
    }

    private void updateCountDownText()
    {
        int minutes=(int) (mTimeLeftInMillis/1000)/60;
        int seconds=(int) (mTimeLeftInMillis/1000)%60;
        String timeLeftFormatted=String.format( Locale.getDefault(),"%02d:%02d",minutes,seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }
}
