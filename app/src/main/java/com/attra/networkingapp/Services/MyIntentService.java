package com.attra.networkingapp.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;


@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class MyIntentService extends IntentService {

    public static final String MSG_INTENT_FILTTER="MSGFILTERKEY";
    public static final String MSG_KEY="MSG_KEY";


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        sendMessageToUi("Dummy Data");

    }

    private void sendMessageToUi(String data) {

        Intent intent=new Intent(MSG_INTENT_FILTTER);
        intent.putExtra(MSG_KEY,data);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


}
