package com.gendevs.tripodsample;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;

public abstract class BaseActivity extends ActionBarActivity {

	private static final String TAG = BaseActivity.class.getSimpleName();
	protected Context context;
	protected Toolbar toolbar;

	protected abstract int getLayoutResource();
	protected abstract int getToolbarResource();

	protected void setTitels(String title, String subTitle){
		if(toolbar!=null){
			toolbar.setTitle(title);
			toolbar.setSubtitle(subTitle);
		}
	}

	protected void setTitels(int title, int subTitle){
		if(toolbar!=null){
			toolbar.setTitle(title);
			toolbar.setSubtitle(subTitle);
		}
	}


	protected void setActionBarIcon(int iconRes) {
		toolbar.setNavigationIcon(iconRes);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResource());
		context = this;
		setToolbar(getToolbarResource());

	}
	
	protected void setToolbar(int id) {
		if(id>0){
			toolbar = (Toolbar) findViewById(id);
			if (toolbar != null) {
				//setSupportActionBar(toolbar);
				toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.tripod_logo_white));
			}
		}
	}


	protected String getText(EditText editText) {
		if (editText != null && editText.getText() != null && editText.getText().toString() != null)
			return editText.getText().toString().trim();
		else
			return "";
	}

	protected boolean isEmpty(String str){
		return TextUtils.isEmpty(str);
	}


}
