package com.aro.qa.arosdk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;

import com.example.arosdktesttool.R;

import junit.framework.Assert;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;

import android.util.Log;
import android.widget.TextView;

import com.aro.qa.arosdk.TestToolMainActivity;


public class QaHelper {

	Context mContext;
	private static final String TAG = "QA_test";
	public static boolean mWait=true;

	public QaHelper(Context mContext) {
		super();
		this.mContext = mContext;
	}


	
	
	
	void qaLogs(String message){		
		qaLogs(message, null);		
	}
	
	
	void qaLogs(String message, String debugLevel){

		if (debugLevel == null){
			Log.i(TAG,message);
		}else if (debugLevel.indexOf('e') != -1) {
			Log.e(TAG,message);			
		}else if (debugLevel.indexOf('d') != -1) {
			Log.d(TAG,message);			
		}
		Assert.assertNotNull(mContext);

	
/*
		TextView txtView = (TextView) ((Activity)mContext).findViewById(R.id.editInputParameters);
		txtView.append("\n\r" + message);			

		if (txtView.getLineCount() > 200){
			txtView.setText("Logs:");
		}
*/

	}

	void qaLogsClean(){
		
	    TextView txtView = (TextView) ((Activity)mContext).findViewById(R.id.editInputParameters);
	    txtView.setText("Logs");
		
	}
	
/***
 * 	
 * @param waitInSeconds The time to wait for the WebListener.
 */
	
	
	void waitForWebListenerResponse(int waitInSeconds){
		int i=0;
		while (mWait == true){

			try {
				Thread.sleep(1000);

				if (i++ > waitInSeconds){
					Log.e(TAG,"SDK WebResponse DID NOT came back after 10 sec");
					break;
				
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		mWait=true;
	}
	

	
	void verifyResult(TestCaseData mOneTestCase, Set<String> mSetResults){

	//	qaLogs("-----   Expected Results   -----");
		if (mOneTestCase == null){
			qaLogs("mOneTestCase is null","e");
			return;
		}
		String[] expectedRes = mOneTestCase.getExpectedResult();	
//		for (String expRes : expectedRes){
//			sb.append(expRes);
//			sb.append(" / ");
//		}
//		qaHelper.qaLogs(sb.toString(),"d");
				
		for (String result : expectedRes){

			if (mSetResults.contains(result) == true){
				qaLogs("-----   Expected Results   -----");
				qaLogs("Test case PASS. '" + result + "' found.");				
			}else{
		//		qaLogs("Test case FAIL. Expected Result '" + result + "' NOT FOUND on response.","e");
		//		for (String res:mSetResults){
		//			qaLogs("Actual Result =" + res);
		//		}
			}	
		}
	}	
	
	
	/***
	 * Open and get content on from text file.
	 * 
	 * @param fileName
	 * @return
	 */

	public InputStream openFileContentAssets(String fileName){

		InputStream fileContent = null;

		AssetManager am = mContext.getResources().getAssets();

		try {		
			fileContent = am.open(fileName,Context.MODE_PRIVATE);

		} catch (FileNotFoundException e) {
			Log.e(TAG,e.toString());

		} catch (IOException e){
			Log.e(TAG,e.toString());
		}

		return fileContent; 	
	}

	
	
	/***
	 * Reads text file in to a String.
	 * 
	 * @param fileName
	 * @return
	 */
	
	
	public String readTextFile(String fileName)
	{
		String fileContent = null;
		Scanner scanner;	
		InputStream inputStream = openFileContentAssets(fileName);

		try{
			scanner = new java.util.Scanner(inputStream).useDelimiter("\\A");

			fileContent =  scanner.hasNext() ? scanner.next() : "";

		} catch (Exception e){
			Log.e(TAG,e.toString());
		}

		return fileContent;

	}	

}
