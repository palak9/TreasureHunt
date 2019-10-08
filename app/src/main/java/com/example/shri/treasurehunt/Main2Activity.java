package com.example.shri.treasurehunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public int level,mScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );

        final TextView s = (TextView)findViewById( R.id. my_score);
        Intent i=getIntent();
        mScore=i.getIntExtra("score",0);
        level =i.getIntExtra("level",0);
        s.setText( " "+mScore );

        Button next = (Button)findViewById( R.id.next_level );

        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( Main2Activity.this, FirstActivity.class );
                intent.putExtra( "level" ,level);
                intent.putExtra( "mScore", mScore );
                startActivity( intent );
            }
        } );
    }
}
