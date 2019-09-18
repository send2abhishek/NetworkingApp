package com.attra.networkingapp;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.attra.networkingapp.Models.SampleModel;
import com.attra.networkingapp.Services.MyIntentService;
import com.attra.networkingapp.utils.NetworkHelper;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView serviceMsg;
    private boolean isNetworkAvailable;
    private ProgressDialog progressDialog;
    private static final String JSON_URL="https://jsonplaceholder.typicode.com/posts/1";
    private SampleModel sampleModel=null;
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


                String data=intent.getStringExtra(MyIntentService.MSG_KEY);

                // Return a json object from String data source
                //JSONObject response=new JSONObject(data);

                Gson gson=new Gson();
                sampleModel=  gson.fromJson(data,SampleModel.class);

                // If you want to parse data into SampleModel array, then you have to do
                //SampleModel[] sampleModel=gson.fromJson(data,SampleModel[].class);


            logMsg(data);
            progressDialog.hide();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceMsg=findViewById(R.id.activity_main_text);

        isNetworkAvailable= NetworkHelper.isNetworkAvailable(this);

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Attempting to fetch data ");
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
        if(isNetworkAvailable){
            progressDialog.show();
            Intent intent=new Intent(this,MyIntentService.class);
            intent.setData(Uri.parse(JSON_URL));
            startService(intent);
        }

        else {
            Toast.makeText(this,"You are offline",Toast.LENGTH_SHORT).show();
        }


    }

    private void logMsg(String data) {

        serviceMsg.setText(data);
        if(sampleModel!=null){
            serviceMsg.setText(sampleModel.body);
        }
        Log.d("Aryan", "logMsg: "+data);
    }
}
