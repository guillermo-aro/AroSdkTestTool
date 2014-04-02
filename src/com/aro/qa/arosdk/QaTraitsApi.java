package com.aro.qa.arosdk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aro.android.sdk.api.AuthApi;
import com.aro.android.sdk.api.TraitsApi;
import com.aro.android.sdk.apiobjects.Trait;
import com.aro.android.sdk.http.AROApiComm;
import com.aro.android.sdk.http.AROApiCommQA;
import com.aro.android.sdk.http.WebException;
import com.aro.android.sdk.http.WebListener;
//import com.lge.ia.place.PlaceManagerFacilities;

public class QaTraitsApi extends AsyncTask<Integer, Void,Void>{

	Context mContext;
	private static final String TAG = "QA_test";

	List<TestCaseData> testValues;
	TestCaseData mOneTestCase; 
	Set<String> mSetResults = new HashSet<String>();	

	TraitsApi traits = new TraitsApi(new AROApiCommQA(AroApiList.token));
	AuthApi authApi = new AuthApi(new AROApiCommQA(AroApiList.token));
	QaHelper qaHelper;

	public QaTraitsApi(Context mContext) {
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

		case AroApiList.TRAITS_GET_BY_ID:
			getById_test();
			break;

		case AroApiList.TRAITS_MAKE_BULK_PLACES_REQUEST:
			makeBulkPlacesRequest_test();
			break;				

		}

		return null;
	}	
	void validateCredentials_test(){

		String deviceId="820ec643-a3b7-455e-80b9-86246df971cb";//"89a91167-e01d-462e-bf68-f3b40c1d9ac2";
		String token="ce7a49e22d54c37a076425fb300f68ab315a5fd5";//"e8eef256b4d93be18fea300bfa4c94ff2624032a";


		qaHelper.qaLogs(TAG,"=====   validateCredentials   =====");
		qaHelper.qaLogs(TAG,"Test values="  + deviceId + "/" + token);		

		try {
			authApi.validateCredentials(mContext, deviceId, token, webListenerTrait);
			//		} catch (WebException e) {
			//			qaHelper.qaLogs(TAG,"validateCredentials  .WebException= "  + e);
		}catch (Exception e) {
			qaHelper.qaLogs(TAG,"validateCredentials  .Exception= "  + e);
		}

	}



	void getById_test(){
		testValues = TraitTestCases.getByIdTestCases();

		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====   traits.getById   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			mOneTestCase = theTestCase;
			try {							
				traits.getById(mContext, theTestCase.getTestValue().get(0), webListenerTrait);

				Thread.sleep(3000);		
			}catch (Exception e) {
				qaHelper.qaLogs("traits.getById Exception="+e,"e");
			}
		}	
			
	}	



	void makeBulkPlacesRequest_test(){

		StringBuilder placeId = new StringBuilder();
		testValues = TraitTestCases.getMakeBulkTraitsRequestTestCases();
		List<String> idsList = new ArrayList<String>();

		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====   traits.makeBulkTraitsRequest   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			mOneTestCase = theTestCase;
			idsList = theTestCase.getTestValue();

			try {
				traits.makeBulkTraitsRequest(mContext, idsList, webListenerTrait);

				Thread.sleep(2000);	
				placeId.setLength(0);
				placeId.trimToSize();

			}catch (Exception e) {
				qaHelper.qaLogs("traits.makeBulkPlacesRequest Exception="+e,"e");
			}
		}	
			
	}	


	/***
	 * WebListener to receive Trait API response containing Trait Object.
	 */

	WebListener webListenerTrait = new WebListener() {

		@SuppressWarnings("unchecked")
		@Override
		public void sendWebSuccess(Object arg0) {	
			int c=0;
			qaHelper.qaLogs(TAG,"WebSuccess <==");
			Trait resultTrait = null;
			ArrayList<Trait> traits = new ArrayList<Trait>();


			if (arg0 instanceof Trait){
				resultTrait = (Trait) arg0;
				showTraitObjectFields(resultTrait);
				qaHelper.verifyResult(mOneTestCase, mSetResults);

			}else if (arg0 instanceof ArrayList<?>){
				traits = (ArrayList<Trait>) arg0;
				qaHelper.qaLogs(TAG,"Trait List. Total = " + traits.size());
				for (Trait place : traits){
					qaHelper.qaLogs("-----   Trait data # " + ++c + "   -----");	
					showTraitObjectFields(place);
				}
				qaHelper.verifyResult(mOneTestCase, mSetResults);
			}
			mSetResults.clear();
			mOneTestCase=null;
		}

		@Override
		public void sendWebFailure(WebException arg0) {
			qaHelper.qaLogs("WebFailure " + arg0.getMessage() + "e");

		}

		@Override
		public void sendWebFailure() {
			qaHelper.qaLogs("WebFailure no Parameters","e");

		}
	};	


	/***
	 * 
	 * Method to display Trait object fields on logs.
	 * 
	 * @param place
	 */

	void showTraitObjectFields(Trait theTrait){

		qaHelper.qaLogs(
				"getId =	" + theTrait.getId() + "\r\n" +
						"getName =	" + theTrait.getName() + "\r\n" +
						"getDescription =	" + theTrait.getDescription() + "\r\n" +
						"getExplanation =" + theTrait.getExplanation() + "\r\n" +				
						"getDescription =	" + theTrait.getDescription() + "\r\n");

		mSetResults.add(theTrait.getId());
		mSetResults.add(theTrait.getName());
		mSetResults.add(theTrait.getDescription());
		mSetResults.add(theTrait.getExplanation());
		mSetResults.add(theTrait.getExplanation());	

	}
}

