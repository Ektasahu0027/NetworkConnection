package com.example.tekpreneur.networkconnection;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    ProgressBar simpleProgressBar;
    TextView conn,Noconn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        conn=(TextView)findViewById(R.id.connection);
        Noconn=(TextView)findViewById(R.id.no_connection);

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @SuppressWarnings("unchecked")
                    public void run() {
                        isOnline();
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000);

    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            simpleProgressBar.setVisibility(View.INVISIBLE);
            conn.setVisibility(View.VISIBLE);
            Noconn.setVisibility(View.INVISIBLE);
            /*Toast.makeText(MainActivity.this,"Internet connection available  ",
                    Toast.LENGTH_SHORT).show();*/
            return true;
        }
        simpleProgressBar.setVisibility(View.VISIBLE);
        conn.setVisibility(View.INVISIBLE);
        Noconn.setVisibility(View.VISIBLE);
       /* Toast.makeText(MainActivity.this,"No Internet connection available  ",
                Toast.LENGTH_SHORT).show();*/
        return false;
    }
}