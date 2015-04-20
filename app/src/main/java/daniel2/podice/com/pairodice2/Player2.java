package daniel2.podice.com.pairodice2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Player2 extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private TextView player1, player2, accumulate, round;
    private int score1 = 0;
    private int score2 = 0;
    private int roundNum = 0;
    private int accumulatedScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player2);

        Intent intent = getIntent();
        score1 = intent.getIntExtra("score1", 0);
        score2 = intent.getIntExtra("score2", 0);
        roundNum = intent.getIntExtra("round", 0);
        //score1 = intent.getIntExtra("score1", 0);
        //score2 = intent.getIntExtra("score2", 0);

        player1 = (TextView)findViewById(R.id.p1);
        player2 = (TextView)findViewById(R.id.p2);
        player1.setText("P1: " + String.valueOf(score1));
        player2.setText("P2: " + String.valueOf(score2));

        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();

            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score2 += accumulatedScore;
                roundNum += 1;
                if (score2 >= 100){
                    score1 = 0;
                    score2 = 0;
                    roundNum = 0;


                    AlertDialog alertDialog = new AlertDialog.Builder(Player2.this).create();
                    alertDialog.setTitle("You Won!");
                    alertDialog.setMessage("Player 2 Wins!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(Player2.this,MainActivity.class);
                                    intent.putExtra("score1", score1);
                                    intent.putExtra("score2", score2);
                                    intent.putExtra("round", roundNum);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);
                                }
                            });
                    alertDialog.show();

                }
                else {
                    Intent intent = new Intent(Player2.this, MainActivity.class);
                    intent.putExtra("score1", score1);
                    intent.putExtra("score2", score2);
                    intent.putExtra("round", roundNum);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }
            }
        });

        accumulate = (TextView) findViewById(R.id.accumulate);
        //accumulate.setText("Sum: " + String.valueOf(accumulatedScore));

        round = (TextView) findViewById(R.id.round);
        round.setText("Round: " + String.valueOf(roundNum));

        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);

    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());
        if((val1 == 1) || (val2 == 1)){
            Toast.makeText(this, "Player 2 rolled a one!", Toast.LENGTH_LONG).show();
            roundNum += 1;
            Intent intent = new Intent(Player2.this,MainActivity.class);
            intent.putExtra("score1", score1);
            intent.putExtra("score2", score2);
            intent.putExtra("round", roundNum);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
        else{
            accumulatedScore += val1 + val2;
            accumulate.setText("Sum: " + String.valueOf(accumulatedScore));
        }
        setDie(val1, die1);
        setDie(val2, die2);

    }

    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }
        layout.setBackground(pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
/*
* AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
alertDialog.setTitle("Alert");
alertDialog.setMessage("Alert message to be shown");
alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
    new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
alertDialog.show();*/