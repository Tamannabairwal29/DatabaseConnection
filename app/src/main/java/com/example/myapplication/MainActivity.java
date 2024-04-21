package com.example.myapplication;

import android.os.Bundle;
import android.telecom.Connection;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.ResultSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ConnectionClass connectionClass;
    Connection con;
    ResultSet rs;
    String name,str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        connectionClass=new ConnectionClass();
        connect();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnclick(View view) {
    }
    public void connect(){
        ExecutorService executorService= Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                con = (Connection) connectionClass.CONN();
                if (con == null) {
                    str = "ERROR IN CONNECTION WITH MYSQL SEVER";
                } else {
                    str = "connected to mysql";
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            runOnUiThread(() -> {
             try {
                 Thread.sleep(1000);
             }catch(InterruptedException e){
                 e.printStackTrace();
             }
                Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
            });
        });
    }
}