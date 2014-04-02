package com.aro.qa.arosdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import junit.framework.Assert;

import com.example.arosdktesttool.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TestToolMainActivity extends Activity {

	protected static final String TAG = "QA_test";
	private Spinner spinnerAroApi;
	private Button btnSubmit;	
	private QaAroApi qaAroApi;
	QaHelper qaHelper;
	Context mc;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_tool_main);

		qaAroApi = new QaAroApi(this);
		myListenerOnButton();
		listenerForOnSpinnerItemSelection();
		qaHelper = new QaHelper(this);
		mc=this;
	}


	public void listenerForOnSpinnerItemSelection() {
		spinnerAroApi = (Spinner) findViewById(R.id.spinnerAroApi);
		spinnerAroApi.setOnItemSelectedListener(new myOnItemSelectedListener());
	}



	// get the selected dropdown list value
	public void myListenerOnButton() {

		spinnerAroApi = (Spinner) findViewById(R.id.spinnerAroApi);
		btnSubmit = (Button) findViewById(R.id.btnSubmit);
	
		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				qaHelper.qaLogsClean();
				int sdkNum = getSelectedSDKNumber(String.valueOf(spinnerAroApi.getSelectedItem()));
				Log.d(TAG,"Sdk: " + String.valueOf(spinnerAroApi.getSelectedItem()));
			
				try {
					qaAroApi.callAroApi(sdkNum);
				}catch (Exception e) {
					Log.e(TAG,"Excpetion onClick:" + e);
				}
				Toast.makeText(TestToolMainActivity.this,"OnClickListener : " + spinnerAroApi.getSelectedItemPosition() + "\nSpinner 1 : "+ String.valueOf(spinnerAroApi.getSelectedItem()),Toast.LENGTH_SHORT).show();
				
			}

		});
	}	
	
/*	
	private class CallApiAsync extends AsyncTask<Integer, Void, Void>{

		@Override
		protected Void doInBackground(Integer... arg0) {
			Log.e(TAG,"2222222");
			//qaAroApi.callAroApi(sdkNum);
			qaAroApi.singleThreadVersion(arg0[0]);
			Log.e(TAG,"2FFFFFFFFF");
			return null;
		}
		
		 @Override
		protected void onPostExecute(Void result) {
			Log.e(TAG,"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

		}

		
	}	
*/
	
	
/***
 * Parses the option selected on UI's dropbox to extract the Number to the selected SDK method.
 * 	
 * @param selectedSDK
 * @return
 */
	
	int getSelectedSDKNumber(String selectedSDK){

		try {
			StringBuilder selectedSDKsb = new StringBuilder(selectedSDK);
			StringBuilder sdkNumber = new StringBuilder();
			int beg=-1;
			int end=-1;

			beg = selectedSDKsb.indexOf("=") + 1;
			end = selectedSDKsb.length();
			
			sdkNumber.append(selectedSDK.substring(beg,end).trim());			
			Log.d(TAG,"sdkNumber : " + sdkNumber + "   selectedSDK: " + selectedSDKsb);
			return Integer.valueOf(sdkNumber.toString());

		}catch (Exception e){
			Log.e(TAG,"getSelectedSDKNumber " + e);
			return -1;
		}



	}
}

