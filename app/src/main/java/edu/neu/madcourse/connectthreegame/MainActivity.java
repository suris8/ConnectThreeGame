package edu.neu.madcourse.connectthreegame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    public int turn = 1;
    public boolean won;
    public List<String> colors = new ArrayList<>();
    ImageView counter;
    GridLayout gridLayout;

    // resets the list to all white
    public void resetList(){
        ListIterator<String> iterator = colors.listIterator();
        while (iterator.hasNext()){
            String s = iterator.next();
            iterator.set("white");
        }
    }

    // sets thee image resource in the imageView depending on turn
    public void setImage(View view){
        // check if game has been won
        wonMessage();
        // if the board if full and the game hasn't been won, display message
        if (turn >= 9 && won == false) {
            if (turn == 9) {
                counter.setImageResource(R.drawable.redchip);
            }
            Toast.makeText(this, "Tie game. Try again.", Toast.LENGTH_SHORT).show();
            // if odd turn, put red chip and check if game has been won
        } else if (turn % 2 == 1){
            counter.setImageResource(R.drawable.redchip);
            turn += 1;
            colors.set(Integer.parseInt(counter.getTag().toString()), "red");
            wonMessage();
            // if even turn, put green chip and check if game has been won
        } else {
            counter.setImageResource(R.drawable.greenchip);
            turn += 1;
            colors.set(Integer.parseInt(counter.getTag().toString()), "green");
            wonMessage();
        }
    }

    // if game hasn't been well drop chip down to imageView position
    public void dropIn(View view){
        if (won == false) {
            // will be equal to the imageView that was tapped
            counter = (ImageView) view;
            // put above the screen
            counter.setTranslationY(-1500);
            // set the image for the imageView
            setImage(view);
            // drop image down
            counter.animate().translationYBy(1500).setDuration(300);
            Log.i("turn", String.valueOf(turn));
        } else {
            Toast.makeText(this, "Game already won. Reset to play again!", Toast.LENGTH_SHORT).show();
        }
    }

    // if game has been won, display winner message
    public void wonMessage(){
        String winner = isWon();
        if (won == true) {
            Toast.makeText(this, winner + " is the winner!", Toast.LENGTH_LONG).show();
        }
    }

    // if reset button is clicked, make the board blank
    // reset the list
    // start turns at 1 again
    // change 1 to false
    public void reset(View view){

        for (int i = 0; i < gridLayout.getChildCount(); i++){
            ImageView slot = (ImageView)gridLayout.getChildAt(i);
            slot.setImageDrawable(null);
        }
        turn = 1;
        won = false;
        resetList();
    }

    // checks all winning conditions
    public String isWon(){
        if (colors.get(0) == colors.get(1) && colors.get(1) == colors.get(2) && colors.get(2) != "white"){
            won = true;
            return colors.get(2);
        } else if (colors.get(0) == colors.get(4) && colors.get(4) == colors.get(8) && colors.get(8) != "white") {
            won = true;
            return colors.get(0);
        } else if (colors.get(2) == colors.get(4) && colors.get(4) == colors.get(6) && colors.get(6) != "white"){
            won = true;
            return colors.get(2);
        }  else if (colors.get(3) == colors.get(4) && colors.get(4) == colors.get(5) && colors.get(5) != "white"){
            won = true;
            return colors.get(3);
        } else if (colors.get(6) == colors.get(7) && colors.get(7) == colors.get(8) && colors.get(8) != "white") {
            won = true;
            return colors.get(8);
        } else if (colors.get(0) == colors.get(3) && colors.get(3) == colors.get(6) && colors.get(6) != "white") {
            won = true;
            return colors.get(6);
        } else if (colors.get(1) == colors.get(4) && colors.get(4) == colors.get(7) && colors.get(7) != "white") {
            won = true;
            return colors.get(1);
        } else if (colors.get(2) == colors.get(5) && colors.get(5) == colors.get(8) && colors.get(8) != "white") {
            won = true;
            return colors.get(2);
        } else {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = (GridLayout)findViewById(R.id.gridLayout2);
        for(int i = 0; i < 9; i++){
            colors.add("white");
        }
    }
}