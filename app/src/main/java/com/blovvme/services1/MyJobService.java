package com.blovvme.services1;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;


/**
 * Created by Blovvme on 8/15/17.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {


    private static final String TAG = "MyJobService";

    @Override
    public boolean onStartJob(JobParameters params) {
        //to start the service
        Log.d(TAG, "onStartJob");
        Intent intent = new Intent(getApplicationContext(), MyScheduleService.class);
        getApplicationContext().startService(intent);




        //if return true it will restart job
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {


        return false;
    }
}
