package com.gendevs.tripodsample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.gendevs.tripod.fragments.ScreenCaptureFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mShakeToCaptureBtn;
    private Button mVideoRecordingBtn;
    private Button mLiveEngineeringBtn;
    private Button mCrashReportBtn;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;

    }

    @Override
    protected int getToolbarResource() {
        return R.id.activity_main_toolbar;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar.setTitle("Tripod");
        mShakeToCaptureBtn = (Button)findViewById(R.id.screen_catpure_btn);
        mShakeToCaptureBtn.setOnClickListener(this);
        mVideoRecordingBtn = (Button)findViewById(R.id.screen_record_btn);
        mVideoRecordingBtn.setOnClickListener(this);
        mLiveEngineeringBtn = (Button)findViewById(R.id.live_engineering_btn);
        mLiveEngineeringBtn.setOnClickListener(this);
        mCrashReportBtn= (Button)findViewById(R.id.reoprt_crash_btn);
        mCrashReportBtn.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, ShakeToCaptureActivity.class);
        switch (v.getId()){
            case R.id.screen_catpure_btn:
                intent.putExtra("title","Shake to Capture");
                intent.putExtra("image",0);
                startActivity(intent);
                break;
            case R.id.screen_record_btn:
                intent.putExtra("title","Screen Recording");
                intent.putExtra("image",1);
                startActivity(intent);
                break;
            case R.id.reoprt_crash_btn:
                intent.putExtra("title","Crash Reporting");
                intent.putExtra("image",2);
                startActivity(intent);
                break;
            case R.id.live_engineering_btn:
                intent.putExtra("title","Live Engineering");
                intent.putExtra("image",3);
                startActivity(intent);
                break;
        }

    }
}
