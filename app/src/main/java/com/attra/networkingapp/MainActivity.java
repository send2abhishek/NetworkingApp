package com.attra.networkingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.attra.networkingapp.Services.MyIntentService;

public class MainActivity extends AppCompatActivity {

    private TextView serviceMsg;
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            logMsg(intent.getStringExtra(MyIntentService.MSG_KEY));
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceMsg=findViewById(R.id.activity_main_text);
    }

    @Override
    protected void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(receiver,new IntentFilter(MyIntentService.MSG_INTENT_FILTTER));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    public void RunCode(View view) {

        Intent intent=new Intent(this,MyIntentService.class);
        startService(intent);
    }

    private void logMsg(String data) {

        serviceMsg.setText(data);
        Log.d("Aryan", "logMsg: "+data);
    }
}
