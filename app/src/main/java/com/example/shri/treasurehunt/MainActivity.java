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

public class MainActivity extends AppCompatActivity {

    public int score,level,chance;
    int hint_number,hints_attempted;
    CountDownTimer mCountDownTimer;
    static final long START_TIME_IN_MILLIS=1200000;
    long mTimeLeftInMillis=START_TIME_IN_MILLIS;
    TextView mTextViewCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        TextView marquee = (TextView)findViewById( R.id.marquee );
        mTextViewCountDown=findViewById(R.id.timer);
        marquee.setSelected( true );
        startTimer();
        score = 0;
        hint_number = 0;
        hints_attempted=0;

        final Button check = (Button)findViewById( R.id.check );

        final String hints[] = {"What invention lets you look right through a wall?",
                                "I have keys but no locks, with space but no room and allow you to enter but not get in.",
                                "The only place in the world, where Saturday comes before Thursday !",
                                "What is filled six days a week but if you don't own it you can't take a peek",
                                 "It has a million stories but cannot tell them !"};

        final String answers[] = {"windows","keyboard","dictionary","mailbox","library"};

        final TextView hint = (TextView)findViewById( R.id.hintquestion );

       final EditText answer = (EditText)findViewById( R.id.ans );


        hint.setText(hints[hint_number]);






        Intent i = getIntent();
        level = i.getIntExtra( "levels",0 );



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
                    score = score+10;
                    hints_attempted++;
                    //Toast.makeText( getApplicationContext(),"Great! Your next Clue !!!" ,Toast.LENGTH_SHORT).show();
                    hint.setText( hints[hint_number] );
                    answer.setText( "" );
                    Intent intent = new Intent( MainActivity.this, Main2Activity.class );
                    intent.putExtra("score",score);
                     intent.putExtra( "level",level );
                    startActivity(intent);
                    finish();
                }
                else if(answer.getText().toString().equalsIgnoreCase(answers[hint_number]) && hints_attempted<=3)
                {
                  score = score+10;
                  ++hint_number;
                  hints_attempted++;
                  Toast.makeText( getApplicationContext(),"Great! Your next Clue !!!" ,Toast.LENGTH_SHORT).show();
                  hint.setText( hints[hint_number] );
                  answer.setText( "" );
                }
                else
                {
                    Toast.makeText( getApplicationContext(),"Sorry :( Please try again !" ,Toast.LENGTH_SHORT).show();
                    score = score-2;
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

