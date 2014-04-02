package com.aro.qa.arosdk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aro.android.sdk.api.ActivityApi;
import com.aro.android.sdk.apiobjects.Activity;
import com.aro.android.sdk.apiobjects.Address;
import com.aro.android.sdk.apiobjects.Place;
import com.aro.android.sdk.apiobjects.Activity;
import com.aro.android.sdk.apiobjects.StayActivity;
import com.aro.android.sdk.http.AROApiComm;
import com.aro.android.sdk.http.AROApiCommQA;
import com.aro.android.sdk.http.WebException;
import com.aro.android.sdk.http.WebListener;

public class QaActivityApi extends AsyncTask<Integer, Void,Void>{


	Context mContext;
	private static final String TAG = "QA_test";

	List<TestCaseData> testValues;
	ActivityApi  activityApi = new ActivityApi(new AROApiCommQA(AroApiList.token));
	QaHelper qaHelper;
	TestCaseData mOneTestCase; 
	Set<String> mSetResults = new HashSet<String>();

	public QaActivityApi(Context mContext) {
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

		case AroApiList.ACTIVITY_GET_ACTIVITIES:
			getActivities_test();
			break;

		case AroApiList.ACTIVITY_GET_ACTIVITIES_WITH_PLACES:
			getActivitiesWithPlaces_test();
			break;

		case AroApiList.ACTIVITY_GET_BY_ID:
			getById_test();
			break;

		case AroApiList.ACTIVITY_UPDATE_ACTIVITY:
			updateActivity_test();
			break;

		case AroApiList.ACTIVITY_POST_NOW:
			postNowActivity_test();
			break;			

		}

		return null;
	}
	void postNowActivity_test(){
		Place qaPlace = null;
		JSONObject jsonObject;
		
		testValues = TestCasesActivity.postNowActivityTestCases();
		try{

			for (TestCaseData theTestCase : testValues){

				qaHelper.qaLogs("=======     activityApi.postNowActivity      =======");
				qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + "." );				
				mOneTestCase = theTestCase;

				String placeString = "{\"id\":\"" + Place.PLACEHOLDER_ID+ theTestCase.getTestValue().get(0);
				qaHelper.qaLogs("Test value="  + placeString);
				
				//jsonObject = new JSONObject(placeString);
				//qaPlace = new Place(jsonObject);
				
				JSONObject	jsnObjt = new JSONObject(theTestCase.getTestValue().get(0));
				qaPlace = new Place(jsnObjt);						

				activityApi.postNowActivity(mContext, qaPlace, webListenerActivity);

				Thread.sleep(3000);
			}
		}catch(Exception e){
			qaHelper.qaLogs("Error calling activityApi.postNowActivity. " + e.toString());
		}	


	}	


	void updateActivity_test(){
		String activityString=null;
		testValues = TestCasesActivity.updateActivityTestCases();
		try{

			for (TestCaseData theTestCase : testValues){

				activityString = theTestCase.getTestValue().get(0);
				JSONObject jsonActivity = new JSONObject(activityString);

				Activity updAct =  new Activity(jsonActivity) {
				};

				qaHelper.qaLogs("=======     activityApi.updateActivity      =======");
				qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + "." );			
				qaHelper.qaLogs("Test values " + activityString);
				mOneTestCase = theTestCase;

				activityApi.updateActivity(mContext, updAct, webListenerActivity);

				Thread.sleep(3000);
			}
		}catch(Exception e){
			qaHelper.qaLogs("Error calling activityApi.updateActivity. " + e.toString());
			qaHelper.qaLogs("JSON = " + activityString );
		}		
	}	



	void getById_test(){

		testValues = TestCasesActivity.getByIdTestCases();
		String activityId = null;

		for (TestCaseData theTestCase : testValues){
			qaHelper.qaLogs("=======     activityApi.getById      =======");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + "." );			
			qaHelper.qaLogs("Test values " + theTestCase.getTestValue().get(0));
			mOneTestCase = theTestCase;

			try{
				activityId=theTestCase.getTestValue().get(0);

				activityApi.getById(mContext,activityId, webListenerActivity);

				Thread.sleep(3000);
			}catch(Exception e){
				qaHelper.qaLogs("Error calling activityApi.getById. " + e.toString());
			}
		}		
	}	


	void getActivitiesWithPlaces_test(){

		testValues = TestCasesActivity.getActivitiesWithPlacesTestCases();
		long endTimestamp = 0;
		long beginTimestamp = 0;

		for (TestCaseData theTestCase : testValues){
			String personId = theTestCase.getTestValue().get(0);
			qaHelper.qaLogs("=======     activityApi.getActivitiesWithPlaces      =======");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + "." );			
			qaHelper.qaLogs("Test values " + theTestCase.getTestValue().get(0) + "end time " + theTestCase.getTestValue().get(1));
			mOneTestCase = theTestCase;

			try{
				beginTimestamp=Long.valueOf(theTestCase.getTestValue().get(0));
				endTimestamp=Long.valueOf(theTestCase.getTestValue().get(1));

				activityApi.getActivitiesWithPlaces(mContext,beginTimestamp , endTimestamp, webListenerActivity);

				Thread.sleep(3000);
			}catch(Exception e){
				qaHelper.qaLogs("Error calling  activityApi.getActivitiesWithPlaces. " + e.toString());
			}
		}		
	}	



	void getActivities_test(){

		testValues = TestCasesActivity.getActivitiesTestCases();
		long endTimestamp = 0;
		long beginTimestamp = 0;

		for (TestCaseData theTestCase : testValues){
			String personId = theTestCase.getTestValue().get(0);
			qaHelper.qaLogs("=======     activityApi.getActivities      =======");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + "." );			
			qaHelper.qaLogs("Test values " + theTestCase.getTestValue().get(0) + "end time " + theTestCase.getTestValue().get(1) + "Limit "+  theTestCase.getTestValue().get(2));
			mOneTestCase = theTestCase;

			try{
				beginTimestamp=Long.valueOf(theTestCase.getTestValue().get(0));
				endTimestamp=Long.valueOf(theTestCase.getTestValue().get(1));

				activityApi.getActivities(mContext,beginTimestamp , endTimestamp, Integer.valueOf(theTestCase.getTestValue().get(2)), webListenerActivity);

				Thread.sleep(5000);
			}catch(Exception e){
				qaHelper.qaLogs("Error calling activityApi.getActivities. " + e.toString(),"e");
			}
		}		
	}		


	WebListener webListenerActivity = new WebListener() {

		@SuppressWarnings("unchecked")
		@Override
		public void sendWebSuccess(Object arg0) {	
			int i=0;
			qaHelper.qaLogs("WebSuccess webListenerActivity<==");
			Activity resultedActivity = null;
			ArrayList<Activity> ActivityList = new ArrayList<Activity>();


			if (arg0 instanceof Activity){

				resultedActivity = (Activity) arg0;		
				showActivityInfo(resultedActivity);
				qaHelper.verifyResult(mOneTestCase, mSetResults);


			}else if (arg0 instanceof ArrayList<?>){
				ActivityList = (ArrayList<Activity>) arg0;
				qaHelper.qaLogs("Activities List. Total = " + ActivityList.size());
				for (Activity activity : ActivityList){
					qaHelper.qaLogs("====  Activity # " + ++i);
					showActivityInfo(activity);
				}
				qaHelper.verifyResult(mOneTestCase, mSetResults);
			}

			mSetResults.clear();
			mOneTestCase=null;
		}

		@Override
		public void sendWebFailure(WebException arg0) {
			qaHelper.qaLogs( "WebFailure " + arg0.getMessage());
		}

		@Override
		public void sendWebFailure() {
			qaHelper.qaLogs( "WebFailure ");
		}
	};	

	
/***
 * Display fields values for Activity object.
 * If StayActivity object is detected, display Place object address.
 * 
 * 	
 * @param activity
 */
	
	void showActivityInfo(Activity activity){
		JSONObject placeJson;
		Place place;
		Address placeAddress;
		StayActivity stayActivity;
		
		try{
			qaHelper.qaLogs("\r\nJSON:\r\n" +activity.getJsonObject());


			qaHelper.qaLogs(" Activity Id=" + activity.getId() + 
					"\r\n Activity Name =" + activity.getName() +
					"\r\n getDescription="+ activity.getDescription() + 
					"\r\n getStartTime  =" + activity.getStartTime() + 
					"\r\n getEndTime    =" + activity.getEndTime() + 
					"\r\n isConfirmed   =" + activity.isConfirmed() + 
					"\r\n getSource     ="+ activity.getSource());

			try{
			qaHelper.qaLogs("\r\n getLatitude=" + activity.getLocation().getLatitude() +
					"\r\n getLongitude =" +  activity.getLocation().getLongitude() +
					"\r\n getAltitude  =" +  activity.getLocation().getAltitude() +
					"\r\n getSource    =" +  activity.getLocation().getSource() +
					"\r\n getAccuracy  =" +  activity.getLocation().getAccuracy());
			}catch(Exception e){
				qaHelper.qaLogs("No Geo Activity for this Actvity." + e ,"e");
			}
			
			if (activity instanceof StayActivity) {
			
				stayActivity = (StayActivity)activity;
				place = stayActivity.getPlace();
				
				if (place == null){
					qaHelper.qaLogs("Place object is Null","e");
				}else{
					qaHelper.qaLogs("Place id = " + place.getId());
					if (place.getAddress() != null){
	
						placeAddress = place.getAddress();
						qaHelper.qaLogs("ADDRESS:" + "\r\n\t"+
								"Street    :"+ placeAddress.getStreet() + "\r\n\t"+
								"City      :"+placeAddress.getCity() + "\r\n\t"+
								"StateProvince:"+placeAddress.getStateProvince() + "\r\n\t" +
								"CityState :"+ placeAddress.getCityState() + "\r\n\t"+
								"PostalCode:"+ placeAddress.getPostalCode() + "\r\n\t"+
								"Country   :"+ placeAddress.getCountry());
					}else{
						qaHelper.qaLogs("Place ADDRESS object = NULL" ,"e");
					}
				}
			}
			mSetResults.add(activity.getId());
			mSetResults.add(activity.getName());
			mSetResults.add(activity.getDescription());		

		}catch(Exception e){
			qaHelper.qaLogs("Error on showActivityInfo." + e ,"e");
		}

	}
}
