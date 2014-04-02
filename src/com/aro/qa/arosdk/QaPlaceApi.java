package com.aro.qa.arosdk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aro.android.sdk.AROPlaceRecognizer;
import com.aro.android.sdk.AROUserPlaceInformant;
import com.aro.android.sdk.api.PlaceApi;
import com.aro.android.sdk.apiobjects.Address;
import com.aro.android.sdk.apiobjects.Place;
import com.aro.android.sdk.apiobjects.PlaceTransitionLink;
import com.aro.android.sdk.http.AROApiCommQA;
import com.aro.android.sdk.http.WebException;
import com.aro.android.sdk.http.WebListener;
//import com.aro.android.sdk.apiobjects.Activity;

import com.lge.ia.place.listener.ActivityResultListener;

//import com.lge.ia.place.type.Activity;
import com.lge.ia.place.type.ResultStatus;


public class QaPlaceApi extends AsyncTask<Integer, Void,Void>{

	Context mContext;
	String[] mExpectedResults; //Needs to be global so Async WebListener response can process its data.
	TestCaseData mOneTestCase; 
	Set<String> mSetResults = new HashSet<String>();	
	
	AROPlaceRecognizer aroPlaceRecognizer;
	AROUserPlaceInformant aroUserPlaceInformant;


	private static final String TAG = "QA_test";
	QaHelper qaHelper;
	//PlaceApi  placeAPI = new PlaceApi(new AROApiCommQA(AroApiList.token));

	PlaceApi placeAPI = new PlaceApi(new AROApiCommQA(AroApiList.token));

	public QaPlaceApi(Context mContext) {
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

		case AroApiList.PLACE_GET_ALL_FAVORITES:
			getAllFavorites_test();											
			break;				

		case AroApiList.PLACE_GET_ALL_FREQUENTLY_VISITED:
			getAllFrequentlyVisitedPlaces_test();
			break;				

		case AroApiList.PLACE_CREATE_API:
			createPlace_test();
			break;

		case AroApiList.PLACE_SET_PLACE_AS_FAVORITE:
			setPlaceAsFavorite_test();
			break;					

		case AroApiList.PLACE_GET_BY_ID:
			getById_test();
			break;				

		case AroApiList.PLACE_MAKE_BULK_PLACES_REQUEST:
			makeBulkPlacesRequest_test();
			break;		

		case AroApiList.PLACE_MODIFY:
			modifyPlace_test();		
			break;					

		case AroApiList.PLACE_GET_LIKELY_CURRENT_PLACES:
			getLikelyCurrentPlaces_test();
			break;						

		case AroApiList.PLACE_GET_PLACE_TRANSITION_LINK:
			getPlaceTransitionLinks_test();
			break;	

		case AroApiList.PLACE_GET_PLACE_HREF:
			getPlaceHref_test();
			break;				

		case AroApiList.PLACE_SEARCH_FOR_FAVORITES:
			searchForFavorites_test();
			break;	

		case AroApiList.PLACE_SEARCH_FOR_FREQUENTLY:
			searchForFrequentlyVisited_test();
			break;					

		case AroApiList.PLACE_SEARCH_FOR_PLACES_NAME:
			searchForPlacesPName_test();
			break;		

		case AroApiList.PLACE_SEARCH_FOR_PLACES_CATEGORY_NAME:
			searchForPlacesPCategory_test();
			break;

		case AroApiList.PLACE_SEARCH_FOR_PLACES_RADIUS:
			searchForPlacesPRadius_test();
			break;	

		}

		return null;
	}



	/***
	 * 
	 *  Gets a full HREF for the given Place ID like "0688bbd3-3588-4def-8c0c-ad10bcab6d75" to https://lifestream-dev0.aro.com/v1/places/0688bbd3-3588-4def-8c0c-ad10bcab6d75
	 *  
	 */

	void getPlaceHref_test(){

		List<TestCaseData> testValues = TestCasesPlace.getGetPlaceHrefTestCases();

		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====   placeAPI.getGetPlaceHrefTestCases   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");

			try {		

				qaHelper.qaLogs(PlaceApi.getPlaceHref(theTestCase.getTestValue().get(0)));

				Thread.sleep(3000);		
			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.getGetPlaceHrefTestCases Exception="+e);
			}
		}		
	}	


	void searchForPlacesPCategory_test(){

		List<TestCaseData> testValues = TestCasesPlace.getsearchForPlacesPCategoryTestCases();

		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====   placeAPI.searchForPlacesPCategory   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + "." + theTestCase.getTestCaseDescription());
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			try {

				placeAPI.searchForPlaces(mContext,Double.valueOf(theTestCase.getTestValue().get(0)), Double.valueOf(theTestCase.getTestValue().get(1)),
						Integer.valueOf(theTestCase.getTestValue().get(2)),Integer.valueOf(theTestCase.getTestValue().get(3)),
						theTestCase.getTestValue().get(4),webListenerPlace);

				Thread.sleep(3000);		
			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.searchForPlaces Exception="+e);
			}
		}		
	}	


	void searchForPlacesPRadius_test(){

		List<TestCaseData> testValues = TestCasesPlace.searchForPlacesPRadiousTestCases();

		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====   placeAPI.searchForPlaces Per Radius   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription());
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			mOneTestCase = theTestCase;
			try {

				//placeAPI.searchForPlaces(mContext,Double.valueOf(theTestCase.getTestValue().get(0)), Double.valueOf(theTestCase.getTestValue().get(1)),2000,100,webListenerPlace);
				placeAPI.searchForPlaces(mContext,Double.valueOf(theTestCase.getTestValue().get(0)), Double.valueOf(theTestCase.getTestValue().get(1)),
						Integer.valueOf(theTestCase.getTestValue().get(2)),Integer.valueOf(theTestCase.getTestValue().get(3)), webListenerPlace) ;

				Thread.sleep(3000);		
			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.searchForPlaces Exception="+e);
			}
		}		
	}		


	void searchForPlacesPName_test(){

		//	List<TestCaseData> testValues = TestCasesPlace.getsearchForPlacesPNameTestCases();
		List<TestCaseData> testValues = TestCasesPlace.searchForPlacesPRadiousTestCases();

		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====   placeAPI.searchForPlaces Per Name   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription());
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			mOneTestCase = theTestCase;
			try {

				//		placeAPI.searchForPlaces(context, latitude, longitude, name, placesWebAndCacheListener)
				//		placeAPI.searchForPlaces(context, latitude, longitude, radius, maxResults, placesWebAndCacheListener)
				//		placeAPI.searchForPlaces(context, latitude, longitude, radius, maxResults, categories, placesWebAndCacheListener)

				//				placeAPI.searchForPlaces(mContext,Double.valueOf(theTestCase.getTestValue().get(0)), Double.valueOf(theTestCase.getTestValue().get(1)),
				//						 theTestCase.getTestValue().get(2) , webListenerPlace) ;

				double lat = Double.valueOf(theTestCase.getTestValue().get(0));
				double lon =  Double.valueOf(theTestCase.getTestValue().get(1));
				int radius = Integer.valueOf(theTestCase.getTestValue().get(2));
				int max= Integer.valueOf(theTestCase.getTestValue().get(3));



				placeAPI.searchForPlaces(mContext,lat,lon,radius,max, webListenerPlace) ;


				Thread.sleep(3000);		
			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.searchForPlaces Exception="+e);
			}
		}		
	}		


	void searchForFrequentlyVisited_test(){

		List<TestCaseData> testValues = TestCasesPlace.getSearchForFrequentlyVisitedTestCases();

		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====   placeAPI.searchForFrequentlyVisited   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription());
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			mOneTestCase = theTestCase;
			try {

				placeAPI.searchForFrequentlyVisited(mContext,Double.valueOf(theTestCase.getTestValue().get(0)), Double.valueOf(theTestCase.getTestValue().get(1)),
						Integer.valueOf(theTestCase.getTestValue().get(2)),Integer.valueOf(theTestCase.getTestValue().get(3)), webListenerPlace) ;

				Thread.sleep(3000);		
			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.searchForFrequentlyVisited Exception="+e);
			}
		}		
	}		



	void searchForFavorites_test(){

		List<TestCaseData> testValues = TestCasesPlace.getSearchForFavoritesTestCases();

		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====   placeAPI.searchForFavorites   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription());
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			mOneTestCase = theTestCase;
			try {

				placeAPI.searchForFavorites(mContext,Double.valueOf(theTestCase.getTestValue().get(0)), Double.valueOf(theTestCase.getTestValue().get(1)),
						Integer.valueOf(theTestCase.getTestValue().get(2)), webListenerPlace) ;

				Thread.sleep(3000);		
			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.searchForFavorites Exception="+e);
			}
		}		
	}		


	void getPlaceTransitionLinks_test(){

		List<TestCaseData> testValues = TestCasesPlace.getGetPlaceTransitionLinksTestCases();

		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====   placeAPI.getPlaceTransitionLinks   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			mOneTestCase = theTestCase;

			try {				
				placeAPI.getPlaceTransitionLinks(mContext, theTestCase.getTestValue().get(0), webListenerPlaceTransitionLink);
				Thread.sleep(3000);		
			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.getPlaceTransitionLinks Exception="+e);
			}
		}		
	}	



	void getLikelyCurrentPlaces_test(){

		List<TestCaseData> testValues = TestCasesPlace.getGetLikelyCurrentPlacesTestCases();

		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====   placeAPI.getLikelyCurrentPlaces   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			mOneTestCase = theTestCase;

			Double lat = 	Double.valueOf(theTestCase.getTestValue().get(0));
			Double lon =    Double.valueOf(theTestCase.getTestValue().get(1));
			
			try {				
				placeAPI.getLikelyCurrentPlaces(mContext, lat,lon, webListenerPlace);		
				Thread.sleep(1000);		
			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.getLikelyCurrentPlaces Exception="+e);
			}
		}		
	}	


	void makeBulkPlacesRequest_test(){

		StringBuilder placeId = new StringBuilder();

		List<TestCaseData> testValues = TestCasesPlace.getMakeBulkPlacesRequestTestCases();
		List<String> idsList = new ArrayList<String>();

		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====    placeAPI.makeBulkPlacesRequest   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			mOneTestCase = theTestCase;
			idsList = theTestCase.getTestValue();

			try {

				placeAPI.makeBulkPlacesRequest(mContext, idsList, webListenerPlace);

				qaHelper.waitForWebListenerResponse(10);

				Thread.sleep(2000);	
				placeId.setLength(0);
				placeId.trimToSize();

			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.makeBulkPlacesRequest Exception="+e);
			}
		}			
	}		


	/***
	 * Reads test cases values and calls Place.getById method.	
	 */

	void getById_test(){
		StringBuilder placeId = new StringBuilder();
		List<TestCaseData> testValues = TestCasesPlace.getGetByIdTestCases();

		for (TestCaseData theTestCase : testValues){
			qaHelper.qaLogs("=====    placeAPI.getById   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			mOneTestCase = theTestCase;

			try {
				placeId.append(theTestCase.getTestValue().get(0));

				placeAPI.getById(mContext, placeId.toString(), webListenerPlace);

				Thread.sleep(2000);	
				placeId.setLength(0);
				placeId.trimToSize();

			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.getById Exception="+e);
			}
		}		
	}	


	/***
	 * Reads test cases values and calls Place.setPlaceAsFavorite method.	
	 */

	void createPlace_test(){
		
		Place qaPlace = null;
		List<TestCaseData> testValues = TestCasesPlace.getCreatePlaceTestCases();

		JSONObject jsonObject;
/*
		try {
			jsonObject = new JSONObject("{\"id\":\"" + Place.PLACEHOLDER_ID + "\",\"totalMinutes\":49,\"totalStays\":18,\"alias\":\"QA UPDATED ALIAS ONE\",\"address\":{\"stateProvince\":\"WA\",\"provenance\":\"userdefined\",\"postalCode\":\"98104\",\"city\":\"Seattle\",\"country\":\"\",\"street\":\"505 5TH Ave S Ste 120\"},\"location\":\"geo:47.59507395257869,90.99999399999999;u=200.0\",\"name\":\"QA UPDATED PLACE NAME ONE\",\"recentStays\":0,\"links\":[{\"href\":\"https://lifestream-dev0.aro.com/v1/places/6986b9e9-3204-473b-b379-853848442cd6\",\"rel\":\"self\"},{\"href\":\"https://lifestream-dev0.aro.com/v1/categories/73\",\"rel\":\"primaryCategory\"},{\"href\":\"https://lifestream-dev0.aro.com/v1/categories/73\",\"rel\":\"category\"}],\"recentMinutes\":0,\"favorite\":true,\"public\":false}");
			qaPlace = new Place(jsonObject);
		} catch (JSONException e1) {
			qaHelper.qaLogs("placeAPI.createPlace JSON Exception=" +e1); 
		}
*/		

		for (TestCaseData theTestCase : testValues){
			qaHelper.qaLogs("=====   placeAPI.createPlace   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
		
			mOneTestCase = theTestCase;
			String placeString = "{\"id\":\"" + Place.PLACEHOLDER_ID + theTestCase.getTestValue().get(0);
			qaHelper.qaLogs("Test value="  + placeString);
			try {
				
				if (theTestCase.getTestValue().size() == 2){
					String placeName = theTestCase.getTestValue().get(0);
					String geoLocation = theTestCase.getTestValue().get(1);
					Place.Builder placeBuilder = new Place.Builder(Place.PLACEHOLDER_ID, placeName, geoLocation);	
					qaPlace=placeBuilder.build();				
				}else{
					jsonObject = new JSONObject(placeString);
					qaPlace = new Place(jsonObject);
				}
			
				placeAPI.createPlace(mContext, qaPlace, webListenerPlace);

				Thread.sleep(1000);		

			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.createPlace Exception="+e);
			}
		}			 
	}		


	/***
	 *  Calls Place.setPlaceAsFavorite method.	
	 */
	void setPlaceAsFavorite_test(){
		JSONObject jsnObj;
		Place qaPlace;

		List<TestCaseData> testValues = TestCasesPlace.getSetPlaceAsFavoriteTestCases();

		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====   placeAPI.setPlaceAsFavorite   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			mOneTestCase = theTestCase;

			try {
				jsnObj = new JSONObject(theTestCase.getTestValue().get(0));
				qaPlace = new Place(jsnObj);

				placeAPI.setPlaceAsFavorite(mContext, qaPlace, Boolean.valueOf(theTestCase.getTestValue().get(1)), webListenerPlace);

				Thread.sleep(1000);
			} catch (JSONException e1) {
				qaHelper.qaLogs("placeAPI.setPlaceAsFavorite JSON Exception=" +e1);
			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.setPlaceAsFavorite Exception="+e);
			}
		}		
	}	



	void getAllFrequentlyVisitedPlaces_test(){

		List<TestCaseData> testValues = TestCasesPlace.getAllFrequentlyVisitedPlaces_TestCases();

		for (TestCaseData theTestCase : testValues){
			mOneTestCase = theTestCase;
			qaHelper.qaLogs("=====   placeAPI.getAllFrequentlyVisitedPlaces   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");

			try {
				placeAPI.getAllFrequentlyVisitedPlaces(mContext, webListenerPlace);

				Thread.sleep(2000);
			} catch (Exception e) {
				qaHelper.qaLogs("placeAPI.getAllFrequentlyVisitedPlaces Exception="+e,"e");
			}
		}
	}


	void getAllFavorites_test(){

		List<TestCaseData> testValues = TestCasesPlace.getAllFavorites_TestCases();

		for (TestCaseData theTestCase : testValues){
			mOneTestCase = theTestCase;
			qaHelper.qaLogs("=====   placeAPI.getAllFavorites   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");

			try {
				placeAPI.getAllFavorites(mContext, webListenerPlace);

				Thread.sleep(2000);
			} catch (Exception e) {
				qaHelper.qaLogs("placeAPI.getAllFavorites Exception="+e ,"e");
			}
		}
	}


	/***
	 * WebListener to receive Place API response containing Place Object.
	 */

	WebListener webListenerPlace = new WebListener() {

		@SuppressWarnings("unchecked")
		@Override
		public void sendWebSuccess(Object arg0) {	
			int c=0;
			qaHelper.qaLogs("WebSuccess <==");
			QaHelper.mWait=false;
			Place resultPlace = null;
			ArrayList<Place> places = new ArrayList<Place>();

			if (arg0 instanceof Place){
				resultPlace = (Place) arg0;
				showPlaceObjectFields(resultPlace);
				qaHelper.verifyResult(mOneTestCase, mSetResults);	

			}else if (arg0 instanceof ArrayList<?>){
				places = (ArrayList<Place>) arg0;
				qaHelper.qaLogs("Place List. Total = " + places.size());
				for (Place place : places){
					qaHelper.qaLogs("-----   Place data # " + ++c + "   -----","d");	
					showPlaceObjectFields(place);				
				}				
				qaHelper.verifyResult(mOneTestCase, mSetResults);		
			}
			mSetResults.clear();
			mOneTestCase=null;
		}

		@Override
		public void sendWebFailure(WebException arg0) {
			qaHelper.qaLogs("WebFailure " + arg0.getMessage());
			QaHelper.mWait=false;
		}

		@Override
		public void sendWebFailure() {
			qaHelper.qaLogs("WebFailure empty exception string");
			QaHelper.mWait=false;
		}
	};	



	/***
	 * WebListener to receive Place API response containing Place Transition Links Object.
	 */

	WebListener webListenerPlaceTransitionLink = new WebListener() {

		@SuppressWarnings("unchecked")
		@Override
		public void sendWebSuccess(Object arg0) {	

			qaHelper.qaLogs("WebSuccess <== webListenerPlaceTransitionLink");
			QaHelper.mWait=false;
			mSetResults = new HashSet<String>();

			ArrayList<PlaceTransitionLink> placesTL = new ArrayList<PlaceTransitionLink>();
			placesTL = (ArrayList<PlaceTransitionLink>) arg0;

			qaHelper.qaLogs("Place List. Total = " + placesTL.size());

			for(PlaceTransitionLink ptl : placesTL){
				qaHelper.qaLogs("*****      Place Transition links data     ******");
				JSONObject jo = ptl.getJsonObject();
				qaHelper.qaLogs( jo.toString());
			}
		}

		@Override
		public void sendWebFailure(WebException arg0) {
			qaHelper.qaLogs("WebFailure " + arg0.getMessage());
			QaHelper.mWait=false;
		}

		@Override
		public void sendWebFailure() {
			qaHelper.qaLogs("WebFailure no Parameters  Transition links");
			QaHelper.mWait=false;
		}
	};		


	ActivityResultListener activityResultListener = new ActivityResultListener() {
		
		@Override
		public void onResultActivities(List<com.lge.ia.place.type.Activity> arg0,
				ResultStatus arg1) {
			
			aroUserPlaceInformant.getAllActivities(30, activityResultListener);
			
			
			for (com.lge.ia.place.type.Activity lgActivity : arg0){
				
				qaHelper.qaLogs("getId:"+lgActivity.getId());
				qaHelper.qaLogs("getName:"+lgActivity.getName());
				
			}
			
		}
	};	

	
	/***
	 * Reads test cases values and calls Place.setPlaceAsFavorite method.	
	 */

	void modifyPlace_test(){

		Place qaPlace;
		JSONObject jsnObjt;
		List<TestCaseData> testValues = TestCasesPlace.getModifyPlaceTestCases();
		
		
		for (TestCaseData theTestCase : testValues){

			qaHelper.qaLogs("=====   placeAPI.modifyPlace   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");
			qaHelper.qaLogs("Test value="  + theTestCase.getTestValue() + ".");
			mOneTestCase = theTestCase;
			try {

				jsnObjt = new JSONObject(theTestCase.getTestValue().get(0));
				qaPlace = new Place(jsnObjt);

				placeAPI.modifyPlace(mContext, qaPlace, webListenerPlace);

				Thread.sleep(3000);		
			} catch (JSONException e1) {
				qaHelper.qaLogs("placeAPI.modifyPlace JSON Exception=" +e1);
			}catch (Exception e) {
				qaHelper.qaLogs("placeAPI.modifyPlace Exception="+e);
			}
		}		
		

	}	
	/***
	 * 
	 * Method to display Place object fields on logs AND To store all sdk results to be compared.
	 * 
	 * @param place
	 */

	void showPlaceObjectFields(Place place){
		JSONObject placeJson;

		qaHelper.qaLogs("\r\nJSON:\r\n" +place.getJsonObject());
		
		if (place.getRoles() != null){
			for (String role : place.getRoles()){
				
				qaHelper.qaLogs("\r\nRole = " + role);
				
			}
		}
		
		qaHelper.qaLogs("\r\ngetCreatedTime =	" + place.getCreatedTime() + "\r\n" +
				"getId =	" + place.getId() + "\r\n" +
				"getName =	" + place.getName() + "\r\n" +
				"getAlias =	" + place.getAlias() + "\r\n" +
				"getLocation =	" + place.getLocation() + "\r\n" +
				"getLastVisitedTime=" + place.getLastVisitedTime() + "\r\n" +				
				"getPhoneNumber =	" + place.getPhoneNumber() + "\r\n" +
				"getRadiusInMeters=" + place.getRadiusInMeters() + "\r\n" +
				"getRating =	" + place.getRating() + "\r\n" +
				"getRecentMinutes =	" + place.getRecentMinutes() + "\r\n" +
				"getRecentStays =	" + place.getRecentStays() + "\r\n" +
				"getTotalMinutes =	" + place.getTotalMinutes() + "\r\n" +
				"getTotalStays =	" + place.getTotalStays() + "\r\n" +
				"isFavorite =	" + place.isFavorite() + "\r\n" +
				"isFrequent =	" + place.isFrequent() + "\r\n" +
				"isPublic =	" + place.isPublic() + "\r\n" +		
				"getBusinessHours=" + place.getBusinessHours() + "\r\n","d");


		if (place.getAddress() != null){

			Address placeAddress = place.getAddress();
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

		mSetResults.add(place.getId());
		mSetResults.add(place.getName());
		mSetResults.add(place.getLocation().toString());
		mSetResults.add(Boolean.toString(place.isFavorite()));
		mSetResults.add(place.getAlias());
	
		if (place.getRoles() != null){ 
			for (String role: place.getRoles()){
				mSetResults.add(role);
			}
		}

		placeJson = place.getJsonObject();
		qaHelper.qaLogs( placeJson.toString(),"d");

	}



}
