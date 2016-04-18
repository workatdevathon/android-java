package com.example.crypsis.mymessenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyService extends Service {
   static final int SAY_HELLO = 1;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
    class MessageHandler extends Handler{

        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case SAY_HELLO:
                    Toast.makeText(getBaseContext()," Hello from service",Toast.LENGTH_SHORT).show();
                    Bundle bundle=msg.getData();
                    String message=bundle.getString("my_string");
                    Toast.makeText(getApplicationContext(),"MEssage"+message,Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    }
    Messenger messenger = new Messenger(new MessageHandler());
}
