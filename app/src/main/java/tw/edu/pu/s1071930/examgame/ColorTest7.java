package tw.edu.pu.s1071930.examgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ColorTest7 extends AppCompatActivity implements DialogInterface.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_test7);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Button ans1 = (Button) findViewById(R.id.ans1);
        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ColorTest7.this, "真厲害，您答對了！" , Toast.LENGTH_SHORT).show();
                Intent it = new Intent(ColorTest7.this, ColorTest8.class);
                startActivity(it);
                finish();
            }
        });
        Button ans2 = (Button) findViewById(R.id.ans2);
        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ColorTest7.this, "好像不是這個哦，再試試吧！" , Toast.LENGTH_SHORT).show();
            }
        });
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(ColorTest7.this)
                        .setTitle("結束測驗")
                        .setMessage("您確定要結束測驗嗎?")
                        .setIcon(R.drawable.ic_launcher_background)
                        .setPositiveButton("確定", ColorTest7.this)
                        .setNegativeButton("取消",ColorTest7.this)
                        .show();
            }
        });
    }
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == DialogInterface.BUTTON_POSITIVE) {
            Intent it = new Intent(ColorTest7.this, CheckPage.class);
            startActivity(it);
            finish();
            Toast.makeText(ColorTest7.this, "已結束測驗" , Toast.LENGTH_SHORT).show();
        }
    }
}