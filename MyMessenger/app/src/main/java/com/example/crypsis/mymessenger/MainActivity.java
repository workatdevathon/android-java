package com.example.crypsis.mymessenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean status =false;
    Messenger mMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startMethod(View v)
    {
        if(status) {
            Toast.makeText(getApplicationContext(),"Service already Started",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(this, MyService.class);
            bindService(i, mConnection, Context.BIND_AUTO_CREATE);
            status = true;
            Toast.makeText(getApplicationContext(), "Service succesfully started", Toast.LENGTH_SHORT).show();
        }
    }
    public void stopMethod(View v)
    {
        if(status) {
            mConnection = null;
            status = false;
            Toast.makeText(getApplicationContext(), "Service stoppped", Toast.LENGTH_SHORT).show();
            TextView t=(TextView)findViewById(R.id.textView3);

        }
        else {
            Toast.makeText(getApplicationContext(),"service already stopped",Toast.LENGTH_SHORT).show();
        }

    }
    public void invokeMethod(View v)
    {
        EditText e1=(EditText)findViewById(R.id.textView);
        String n1=e1.getText().toString();
        EditText e2=(EditText)findViewById(R.id.textView2);
        String n2=e2.getText().toString();

        Message message=Message.obtain(null,1,0,0,0);
       // String s="This is message from activity";
        Bundle bn=new Bundle();
        bn.putString("first",n1);
        bn.putString("second",n2);
        message.setData(bn);
        try {
            mMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    ServiceConnection mConnection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger=new Messenger(service);
            status=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMessenger=null;

        }
    };
}
