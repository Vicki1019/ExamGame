package tw.edu.pu.s1071930.examgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class NumGame extends AppCompatActivity {

    int[] imgNumberList = {
            R.drawable.zero,
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four,
            R.drawable.five,
            R.drawable.six,
            R.drawable.seven,
            R.drawable.eight,
            R.drawable.nine
    };

    int[] imgWordList = {
            R.drawable.w0,
            R.drawable.w1,
            R.drawable.w2,
            R.drawable.w3,
            R.drawable.w4,
            R.drawable.w5,
            R.drawable.w6,
            R.drawable.w7,
            R.drawable.w8,
            R.drawable.w9
    };

    int[] imgBgList = {
            R.drawable.pzero,
            R.drawable.pone,
            R.drawable.ptwo,
            R.drawable.pthree,
            R.drawable.pfour,
            R.drawable.pfive,
            R.drawable.psix,
            R.drawable.pseven,
            R.drawable.peight,
            R.drawable.pnine
    };
    private Button btnPrev, btnNext;
    private ImageView imgNumber, imgWord, gamebg;
    int index = 0;
    int count = imgNumberList.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_game);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Button end = (Button) findViewById(R.id.end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(NumGame.this, ChoosePage.class);
                startActivity(it);
                finish();
            }
        });

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        imgNumber = (ImageView) findViewById(R.id.imgNumber);
        imgWord = (ImageView) findViewById(R.id.imgWord);
        gamebg = (ImageView) findViewById(R.id.gamebg);

        btnPrev.setOnClickListener(btnPrevListener);
        btnNext.setOnClickListener(btnNextListener);
//        Button next = (Button) findViewById(R.id.next);
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent it = new Intent(NumGame.this, NumGame1.class);
//                startActivity(it);
//                finish();
//            }
//        });
    }

    private Button.OnClickListener btnPrevListener = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            index--;
            if(index < 0){
                index = count - 1;
            }

            imgNumber.setImageResource(imgNumberList[index]);
            imgWord.setImageResource(imgWordList[index]);
            gamebg.setImageResource(imgBgList[index]);
        }
    };

    private Button.OnClickListener btnNextListener = new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            index++;
            if(index == count){
                index = 0;
            }

            imgNumber.setImageResource(imgNumberList[index]);
            imgWord.setImageResource(imgWordList[index]);
            gamebg.setImageResource(imgBgList[index]);
        }
    };

}