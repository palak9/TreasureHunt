package com.example.shri.treasurehunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    public int levels,mScore;
    public int next_level;
    public Button coin1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_first );

        coin1 = (Button)findViewById( R.id.level1 );
       Button coin2 = (Button)findViewById( R.id.level2 );

       Intent i = getIntent();
       levels = i.getIntExtra( "level" ,0);
       mScore = i.getIntExtra( "mScore",0 );

       if(levels==1)
       {
           coin1.setText( "Level1 Done" );
       }


       coin1.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               levels++;
               Intent intent = new Intent( FirstActivity.this, MainActivity.class );
               intent.putExtra( "levels",levels );
               startActivity( intent );
               finish();
           }
       } );

           coin2.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(levels==1)
                   {
                       levels++;
                       Intent intent = new Intent( FirstActivity.this, SecondLevel.class );
                       startActivity( intent );
                       intent.putExtra( "levels",levels );
                       intent.putExtra( "mScore",mScore );
                       finish();
                   }
                   else
                   {
                       Toast.makeText( FirstActivity.this, "Please Clear Level 1 !", Toast.LENGTH_SHORT ).show();
                   }
               }
           } );

    }

}

