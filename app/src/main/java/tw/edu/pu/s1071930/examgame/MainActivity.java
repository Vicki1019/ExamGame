package tw.edu.pu.s1071930.examgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.Notification;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseUser user;

    EditText edtMail, edtPwd, edtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mAuth = FirebaseAuth.getInstance();
        edtMail = (EditText) findViewById(R.id.edtMail);
        edtPwd = (EditText) findViewById(R.id.edtPwd);
        //edtMsg = (EditText) findViewById(R.id.edtMsg);

    }
    public void Register(View v){
        String Mail = edtMail.getText().toString();
        String Pwd = edtPwd.getText().toString();

        mAuth.createUserWithEmailAndPassword(Mail,Pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(MainActivity.this, "註冊成功，請至信箱收信進行認證", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(MainActivity.this, "註冊失敗", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void Login(View v){
        String Mail = edtMail.getText().toString();
        String Pwd = edtPwd.getText().toString();

        mAuth.signInWithEmailAndPassword(Mail, Pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = mAuth.getCurrentUser();
                            String uid = user.getUid();

                            boolean emailVerified = user.isEmailVerified();
                            if (emailVerified) {
                                Toast.makeText(MainActivity.this, "登入成功, uid=" + uid , Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(MainActivity.this, "您的電子郵件信箱尚未通過認證" , Toast.LENGTH_SHORT).show();
                                mAuth.signOut();  //登出
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "登入失敗", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}
