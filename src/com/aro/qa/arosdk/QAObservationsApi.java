package com.aro.qa.arosdk;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

import com.aro.android.sdk.api.ObservationApi;
import com.aro.android.sdk.apiobjects.Category;
import com.aro.android.sdk.http.AROApiComm;
import com.aro.android.sdk.http.AROApiCommQA;
import com.aro.android.sdk.http.WebException;
import com.aro.android.sdk.http.WebListener;


public class QAObservationsApi extends AsyncTask<Integer, Void,Void>{

	private Context mContext;
	private static final String TAG = "QA_test";
	List<TestCaseData> testValues;
	TestCaseData mOneTestCase; 
	QaHelper qaHelper;
	ObservationApi observationApi = new ObservationApi(new AROApiCommQA(AroApiList.token));
	List<TestCaseData> mTestValues;

	public QAObservationsApi(Context mContext) {
		super();
		this.mContext = mContext;
		qaHelper = new QaHelper(mContext);
	}	
	
	
	 @Override
	protected void onPostExecute(Void result) {
		Log.e(TAG,"XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

	}
	 
	@Override
	protected Void doInBackground(Integer... params) {

		switch (params[0]){
		
		case AroApiList.OBSERV_UPLOAD:
			Observation_test();										
		break;
		
		case AroApiList.OBSERV_GET_ENCRYPTED:
			Observation_encryption();										
		break;		
			
		}
		return null;
	}	


	void Observation_encryption(){
		String fileLine;

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(qaHelper.openFileContentAssets("SdkObservations.txt")));
		
	
		qaHelper.qaLogs("=====  Start Get Encripted Observations   ===== ");
		try{
			
			fileLine = bufferedReader.readLine();
	
			while(fileLine != null){
				
				Log.d("AroQA",fileLine);
		//		observationApi.uploadObservations(mContext, fileLine, System.currentTimeMillis(), webListenerObservation);				
				fileLine = bufferedReader.readLine();	
				Thread.sleep(2000);
			
			}

		} catch (Exception e){
			Log.e(TAG,e.toString());
		}
		
		qaHelper.qaLogs("=====  End Get Encripted Observations   =====");
	}
	
	
	void Observation_test(){
	
		mTestValues = TestCasesObservation.uploadObservations_testCases();
		JSONArray obsrvJsonArray;
		String obrvString;
		
		
		
			
		for (TestCaseData theTestCase : mTestValues){
			mOneTestCase = theTestCase;
			qaHelper.qaLogs("=====  Observation.uploadObservations   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue().get(0) + ".");
			obrvString = theTestCase.getTestValue().get(0);

			try {
				obsrvJsonArray = new JSONArray(obrvString);
				//observationApi.uploadObservations(mContext, obsrvJS, System.currentTimeMillis(), webListenerObservation);			
				observationApi.uploadObservations(mContext, obsrvJsonArray, System.currentTimeMillis(), webListenerObservation);

				Thread.sleep(2000);
			} catch (Exception e) {
				qaHelper.qaLogs("Observation.uploadObservations Exception="+e,"e");
			}
		}			
		
	}



	WebListener webListenerObservation = new WebListener() {

		@SuppressWarnings("unchecked")
		@Override
		public void sendWebSuccess(Object arg0) {	

			Log.i(TAG,"WebSuccess <==");
		}

		@Override
		public void sendWebFailure(WebException arg0) {
			Log.e(TAG, "WebFailure " + arg0.getMessage());

		}

		@Override
		public void sendWebFailure() {
			Log.e(TAG, "WebFailure ");

		}
	};


}
