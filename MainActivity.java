package com.example.tictactoe;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button B0, B1, B2, B3, B4, B5, B6, B7, B8;
    TextView T1;
    int player_o = 0;
    int player_x = 1;
    int[] filled = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int active_player = player_o;
    boolean gameactive=true;
    boolean isdraw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        T1 = (TextView) findViewById(R.id.header1);
        B0 = (Button) findViewById(R.id.button1);
        B1 = (Button) findViewById(R.id.button2);
        B2 = (Button) findViewById(R.id.button3);
        B3 = (Button) findViewById(R.id.button4);
        B4 = (Button) findViewById(R.id.button5);
        B5 = (Button) findViewById(R.id.button6);
        B6 = (Button) findViewById(R.id.button7);
        B7 = (Button) findViewById(R.id.button8);
        B8 = (Button) findViewById(R.id.button9);
        B0.setOnClickListener(this);
        B1.setOnClickListener(this);
        B2.setOnClickListener(this);
        B3.setOnClickListener(this);
        B4.setOnClickListener(this);
        B5.setOnClickListener(this);
        B6.setOnClickListener(this);
        B7.setOnClickListener(this);
        B8.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if( !gameactive){
            return;
        }
        Button clickedbutton = (Button) findViewById(v.getId());
        int clickedtag = Integer.parseInt(v.getTag().toString());
        if (filled[clickedtag] != -1) {
            return;
        }
        filled[clickedtag] = active_player;
        if (active_player == player_o) {
            T1.setText("X turn");
            clickedbutton.setText("O");
            active_player = player_x;
            clickedbutton.setBackground(getDrawable(android.R.color.holo_blue_bright));
        } else if (active_player == player_x) {
            T1.setText("O turn");
            clickedbutton.setText("X");
            active_player = player_o;
            clickedbutton.setBackground(getDrawable(android.R.color.holo_orange_light));
        }

        checkforwin();

    }

    private void checkforwin() {
        int[][] pois = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for (int i = 0; i < 8; i++) {

            int val0 = pois[i][0];
            int val1 = pois[i][1];
            int val2 = pois[i][2];
            if (filled[val0] == filled[val1] && filled[val1] == filled[val2]&& filled[val2]==filled[val0]) {
                if(filled[val0]!=-1) {
                    gameactive = false;
                    if (filled[val0] == player_o) {
                        showdialog("O is Winner");
                    } else if (filled[val0] == player_x) {
                        showdialog("X is Winner");

                    }
                    return;

                }
            }
            }
        isdraw=true;
        for (int cell:filled){
            if(cell==-1){
                isdraw=false;
                break;
            }
        }
        if(isdraw){
            gameactive=false;
            T1.setText("Draw");
            showdialog("IT's a Draw");
        }
    }

private void showdialog(String winnerText){
    new AlertDialog.Builder(this).setTitle(winnerText).setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            restartgame();
        }
    }).show();
}
private void restartgame(){
    filled = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};
     active_player = player_o;
     B0.setText("");
    B1.setText("");
    B2.setText("");
    B3.setText("");
    B4.setText("");
    B5.setText("");
    B6.setText("");
    B7.setText("");
    B8.setText("");
    gameactive=true;
}}