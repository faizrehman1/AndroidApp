package com.example.faiz.udacityexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    int pointsA=0,pointsB=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

   public void addthreepointsTeamA(View view){

       pointsA = pointsA+3;
       displayTeamA(pointsA);

   }

    public void addtwoPointsTeamA(View view){

        pointsA=pointsA+2;
        displayTeamA(pointsA);
    }

    public void freeThrowTeamA(View view){
        pointsA = ++pointsA;
        displayTeamA(pointsA);
    }

    public void resetScore(View view){
        pointsA = pointsA*0;
        pointsB = pointsB*0;
        displayTeamA(pointsA);
        displayTeamB(pointsB);
    }

    public void displayTeamA(int points){
        TextView score=(TextView)findViewById(R.id.teamAscore);
        score.setText(""+points);
    }


    public void addthreepointsTeamB(View view){

        pointsB = pointsB+3;
        displayTeamB(pointsB);

    }

    public void addtwoPointsTeamB(View view){

        pointsB=pointsB+2;
        displayTeamB(pointsB);
    }

    public void freeThrowTeamB(View view){
        pointsB = ++pointsB;
        displayTeamB(pointsB);

    }


    public void displayTeamB(int points){
        TextView score=(TextView)findViewById(R.id.teamBscore);
        score.setText(""+points);
    }


}
