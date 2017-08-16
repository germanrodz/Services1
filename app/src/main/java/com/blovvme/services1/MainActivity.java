package com.blovvme.services1;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //for bound service
    MyBoundService myBoundService;
    private static final String TAG = "MyService";
    boolean isBoundConnected =true;
    Button btnSendBound,btnStartSound,btnStopSound,btnScheduleService;
    TextView txtBound;
    EditText etText;


    //Button btnStartNormalService,btnStopNormalService,btnStartIntentService,btnBindService,btnUnbindService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendBound = (Button)findViewById(R.id.btnSendBound);
        btnScheduleService = (Button)findViewById(R.id.btnScheduleService);
//        txtBound = (TextView) findViewById(R.id.txtBound);
       etText = (EditText) findViewById(R.id.etText);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startServices(View view) {
        Intent normalIntent =  new Intent(this, MyNormalService.class);
        Intent inIntent = new Intent(this, MyIntentService.class);
        Intent boundIntent = new Intent(this, MyBoundService.class);
        Intent intent = new Intent(this,SecondActivity.class);
        //Intent musicIntent = new Intent(this,MyService.class);
        switch (view.getId()){

            case R.id.btnStartNormalService:

                normalIntent.putExtra("data","This is a normal service");
                startService(normalIntent);
                break;

            case R.id.btnStopNormalService:
                stopService(normalIntent);
                break;

            case R.id.btnStartIntentService:
                inIntent.putExtra("data","This is an intent service");
                startService(inIntent);
                break;
            //for bound service
            case R.id.btnOnBindService:
                bindService(boundIntent,serviceConnection ,Context.BIND_AUTO_CREATE);
                break;
            //for bound service
            case R.id.btnUnBindService:
                unbindService(serviceConnection);
                //isBoundConnected = false;
                break;
            case R.id.btnSendBound:
                intent.putExtra("text", etText.getText().toString());
                startActivity(intent);
                break;

            case R.id.btnStarSound:
                startService(new Intent(this, MyService.class).setAction("StartAction"));
//                Intent startIntent = new Intent(MainActivity.this, MyNotificationService.class);
//                startIntent.setAction("startAction");
//                startService(startIntent);
//                Intent sIntent = new Intent(this, MyNotificationService.class);
//                //startService(new Intent(this,MyNotificationService.class));
//                sIntent.setAction("startAction");
//                startService(sIntent);
                break;

            case R.id.btnStopSound:
                stopService(new Intent(this, MyService.class).setAction("stopAction"));
//                Intent stopIntent = new Intent(this, MyNotificationService.class);
//                stopIntent.setAction("stopAction");
//                stopService(stopIntent);
                break;
            case R.id.btnScheduleService:
                ComponentName serviceComponent = new ComponentName(this, MyJobService.class);
                JobInfo.Builder jobInfo = new JobInfo.Builder(0, serviceComponent);

                jobInfo.setMinimumLatency(1000);
                JobScheduler jobScheduler = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    jobScheduler = getSystemService(JobScheduler.class);
                }
                jobScheduler.schedule(jobInfo.build());

                break;

        }
    }

    ///for bound service
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG,"onServiceConnectedStarted");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            myBoundService.getRandomData();
            Log.d(TAG, "onServiceConnected: " + myBoundService.getRandomData());
            isBoundConnected =true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG,"Service disconnected");
            isBoundConnected = false;
        }
    };
}//
