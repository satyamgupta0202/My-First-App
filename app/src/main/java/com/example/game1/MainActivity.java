package com.example.game1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 0=yellow 1=red 2=EmptyPlayer
    String message = " ";
    int [] GameState = {2,2,2,2,2,2,2,2,2};
    int [][] WinningPosition = { {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int ActivePlayer=0;
    boolean GameActive = true;
    public void zoom(View view) {
        ImageView counter = (ImageView) view;
        Log.i("tagNumber", counter.getTag().toString());
        int TappedCounter = Integer.parseInt(counter.getTag().toString());
        if(GameState[TappedCounter]==2  && GameActive) {
            GameState[TappedCounter] = ActivePlayer;
            counter.setTranslationY(-1500);
            if (ActivePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                ActivePlayer = 1;
            } else {
                ActivePlayer = 0;
                counter.setImageResource(R.drawable.red);
            }
            counter.animate().translationYBy(1500).rotationXBy(1800).setDuration(150);
            for (int[] WinningPosition : WinningPosition) {
                if (GameState[WinningPosition[0]] == GameState[WinningPosition[1]] && GameState[WinningPosition[1]] == GameState[WinningPosition[2]] && GameState[WinningPosition[0]] != 2) {
                    if (ActivePlayer == 0)
                        message="Red has won";
                        //Toast.makeText(this, "Red has won", Toast.LENGTH_SHORT).show();
                    else
                        message= "Yellow has won";
                       // Toast.makeText(this, "Yellow has won", Toast.LENGTH_SHORT).show();
                    GameActive = false;
                    Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
                    TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
                    winnerTextView.setText(message);
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility((View.VISIBLE));
                }
            }
        }
    }
    public void playAgain(View view) {
        Button playAgainButton = (Button)findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView)findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility((View.INVISIBLE));
        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<GameState.length;i++)
            GameState[i]=2;
        message = " ";
        ActivePlayer=0;
        GameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}