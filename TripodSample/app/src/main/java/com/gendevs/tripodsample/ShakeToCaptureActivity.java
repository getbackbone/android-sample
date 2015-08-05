package com.gendevs.tripodsample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gendevs.tripodsample.db.Comment;
import com.gendevs.tripodsample.db.CommentsDataSource;

import java.util.Random;


public class ShakeToCaptureActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mImageView;
    private Button button;
    private CommentsDataSource datasource;
    private int mFeatureId;
    private TextView mInfoTv;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_shake_to_capture;

    }

    @Override
    protected int getToolbarResource() {
        return R.id.activity_main_toolbar;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datasource = new CommentsDataSource(this);
        datasource.open();
        mImageView = (ImageView)findViewById(R.id.feature_detail_iv);
        button = (Button)findViewById(R.id.tripod_detail_btn);
        button.setOnClickListener(this);
        mInfoTv = (TextView)findViewById(R.id.trpod_info_tv);

        toolbar.setTitle(getIntent().getStringExtra("title"));
        mFeatureId = getIntent().getIntExtra("image", 0);
        switch (mFeatureId){
            case 0:
                mInfoTv.setText("Shake the device to capture the screen");
                button.setVisibility(View.GONE);
                mImageView.setImageResource(R.drawable.shake_device);
                break;
            case 1:
                mInfoTv.setText("Swipe right with three fingers to start screen recording");
                button.setVisibility(View.GONE);
                mImageView.setImageResource(R.drawable.three_finger_swipe_right);
                break;
            case 2:
                mInfoTv.setText("Tap on below button and check on tripod portal to view the crash ");
                button.setText("Create Crash");
                mImageView.setVisibility(View.GONE);
                break;
            case 3:
                mInfoTv.setText("Swipe up with three fingers to start Live Engineering.\nTap on below button to insert records into DB and check on Live engineering to view the changes");
                button.setText("Insert records to DB");
                mImageView.setImageResource(R.drawable.three_finger_swipe_up);
                break;
        }
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
        switch (v.getId()){
            case R.id.tripod_detail_btn:
                if(mFeatureId == 2 ){
                        Comment s= null;
                        s.toString();

                }else if(mFeatureId == 3){
                    insertRecords();
                }



                break;
        }

    }

    private void insertRecords() {
        new InsetTask().execute();
		/* try {
			 socket = new ServerSocket(7777);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }


    private void addComment(int i){
        System.out.println("count " +i);
        String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
        int nextInt = new Random().nextInt(3);
        // save the new comment to the database
        datasource.createComment(comments[nextInt]);
    }
    private class InsetTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            for(int i=0;i<=1000;i++)
                addComment(i);
            return null;
        }

    }
}
