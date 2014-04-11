package com.aro.qa.arosdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aro.android.sdk.api.CategoryApi;
import com.aro.android.sdk.api.ObservationApi;
import com.aro.android.sdk.api.PlaceApi;
import com.aro.android.sdk.api.PersonApi;
import com.aro.android.sdk.http.WebException;
import com.aro.android.sdk.http.WebListener;
import com.lge.ia.place.ActivitySensor;
import com.lge.ia.place.GenericSensor;
import com.lge.ia.place.GeoFencer;
import com.lge.ia.place.LocationProvider;
import com.lge.ia.place.LocationTracker;
import com.lge.ia.place.MicSensor;
import com.lge.ia.place.MovementSensor;
import com.lge.ia.place.PlaceManagerFacilities;
import com.lge.ia.place.RESTTransactor;
import com.lge.ia.place.StepSensor;
import com.lge.ia.place.WIFISensor;
import com.aro.android.sdk.apiobjects.Category;
import com.aro.android.sdk.apiobjects.Place;

public class QaAroApi{

	Context mContext;
	String TAG = "QA_test";
	String strPlace = "{\"id\":\"33867974-82bc-4b2b-a65e-54ecb2227103\",\"name\":\"QA01CenturyLinkFieldQA01\",\"phone\":\"2063817500\",\"rating\":2.25,\"webUrl\":\"http://www.centurylinkfield.com\",\"address\":{\"city\":\"Seattle\",\"stateProvince\":\"WA\",\"postalCode\":\"98134\",\"neighborhood\":\"QADowntown,PioneerSquareQA\",\"street\":\"800OccidentalAveS\"},\"location\":\"geo:47.59507995257869,-122.33173370361328\",\"totalStays\":0,\"totalMinutes\":0,\"recentStays\":0,\"recentMinutes\":0,\"favorite\":false,\"public\":true,\"links\":[{\"rel\":\"self\",\"href\":\"https://lifestream-dev0.aro.com/v1/places/33867974-82bc-4b2b-a65e-54ecb2227103\"},{\"rel\":\"image\",\"href\":\"https://irs1.4sqi.net/img/general/original/29486512_JxsRZa-Qn5mP3CqjG7Iinxp_HFdFKBsv_7RBdxAW0uU.jpg\"},{\"rel\":\"primaryCategory\",\"href\":\"https://lifestream-dev0.aro.com/v1/categories/27\"},{\"rel\":\"category\",\"href\":\"https://lifestream-dev0.aro.com/v1/categories/7\"},{\"rel\":\"category\",\"href\":\"https://lifestream-dev0.aro.com/v1/categories/27\"}]},\"_metadata\":{\"logTrace\":9584,\"requestUri\":\"/v1/places/33867974-82bc-4b2b-a65e-54ecb2227103\",\"requestTime\":\"2014-01-24T23:21:34.109Z\",\"responseTimeMs\":18,\"httpCode\":200}";
	
	
	public QaAroApi(Context mContext) {
		super();
		this.mContext = mContext.getApplicationContext();
	}

	public void callAroApi(int sdkNumber) throws InterruptedException, ExecutionException, TimeoutException{
		
		QaPlaceApi qaPlaceApi =  new QaPlaceApi(mContext);
		QAObservationsApi qaObservation = new QAObservationsApi(mContext);
		QaPersonApi qaPersonApi = new QaPersonApi(mContext);
		
		
		QaCategoryApi qaCategoryApi = null;
		QaTraitsApi qaTraitsApi = null;
		QaActivityApi qaActivity = null;
		
		
		
		 qaCategoryApi = new QaCategoryApi(mContext);
		 qaTraitsApi = new QaTraitsApi(mContext);
		 qaActivity = new QaActivityApi(mContext);
		 

			switch (sdkNumber){
			
			case AroApiList.PLACE_GET_ALL_FAVORITES:
				//qaPlaceApi.getAllFavorites_test();
				
				qaPlaceApi.execute(AroApiList.PLACE_GET_ALL_FAVORITES);
			
				
				qaPlaceApi.execute(AroApiList.PLACE_GET_ALL_FREQUENTLY_VISITED);
				qaPlaceApi.execute(AroApiList.PLACE_CREATE_API);
				qaPlaceApi.execute(AroApiList.PLACE_SET_PLACE_AS_FAVORITE);
				qaPlaceApi.execute(AroApiList.PLACE_GET_BY_ID);
				qaPlaceApi.execute(AroApiList.PLACE_MAKE_BULK_PLACES_REQUEST);
				qaPlaceApi.execute(AroApiList.PLACE_MODIFY);
				qaPlaceApi.execute(AroApiList.PLACE_GET_LIKELY_CURRENT_PLACES);
				qaPlaceApi.execute(AroApiList.PLACE_GET_PLACE_TRANSITION_LINK);
				qaPlaceApi.execute(AroApiList.PLACE_GET_PLACE_HREF);
				qaPlaceApi.execute(AroApiList.PLACE_SEARCH_FOR_FAVORITES);
				qaPlaceApi.execute(AroApiList.PLACE_SEARCH_FOR_FREQUENTLY);
				qaPlaceApi.execute(AroApiList.PLACE_SEARCH_FOR_PLACES_NAME);
				qaPlaceApi.execute(AroApiList.PLACE_SEARCH_FOR_PLACES_CATEGORY_NAME);
				qaPlaceApi.execute(AroApiList.PLACE_SEARCH_FOR_PLACES_RADIUS);
				qaTraitsApi.execute(AroApiList.TRAITS_GET_BY_ID);
				qaTraitsApi.execute(AroApiList.TRAITS_GET_BY_ID);
				qaCategoryApi.execute( AroApiList.CATEGORY_GET_BY_NAME);
				qaCategoryApi.execute( AroApiList.CATEGORY_GET_BY_ID);
				qaCategoryApi.execute( AroApiList.CATEGORY_GET_TOP_CATEGORIES);
				qaPersonApi.execute(AroApiList.PERSON_GET_PEOPLE);
				qaPersonApi.execute(AroApiList.PERSON_GET_BY_ID);				
				qaActivity.execute(AroApiList.ACTIVITY_GET_ACTIVITIES);
				qaActivity.execute(AroApiList.ACTIVITY_GET_ACTIVITIES_WITH_PLACES);
				qaActivity.execute(AroApiList.ACTIVITY_GET_BY_ID);
				qaActivity.execute(AroApiList.ACTIVITY_UPDATE_ACTIVITY);
				qaActivity.execute(AroApiList.ACTIVITY_POST_NOW);
				qaTraitsApi.execute(AroApiList.AUTH_VALIDATE_CREDENTIALS);
				qaObservation.execute(AroApiList.OBSERV_UPLOAD);
				break;				
				
			case AroApiList.PLACE_GET_ALL_FREQUENTLY_VISITED:
//				qaPlaceApi.getAllFrequentlyVisitedPlaces_test();
				qaPlaceApi.execute(AroApiList.PLACE_GET_ALL_FREQUENTLY_VISITED);
				break;				
				
			case AroApiList.PLACE_CREATE_API:
				//qaPlaceApi.createPlace_test();
				qaPlaceApi.execute(AroApiList.PLACE_CREATE_API);
				break;
				
			case AroApiList.PLACE_SET_PLACE_AS_FAVORITE:
				//qaPlaceApi.setPlaceAsFavorite_test();
				qaPlaceApi.execute(AroApiList.PLACE_SET_PLACE_AS_FAVORITE);
				break;					
				
			case AroApiList.PLACE_GET_BY_ID:
				//qaPlaceApi.getById_test();
				qaPlaceApi.execute(AroApiList.PLACE_GET_BY_ID);
				break;				

				
			case AroApiList.PLACE_MAKE_BULK_PLACES_REQUEST:
//				qaPlaceApi.makeBulkPlacesRequest_test();
				qaPlaceApi.execute(AroApiList.PLACE_MAKE_BULK_PLACES_REQUEST);
				break;		

			case AroApiList.PLACE_MODIFY:
//				qaPlaceApi.modifyPlace_test();
				qaPlaceApi.execute(AroApiList.PLACE_MODIFY);

				break;					
				
			case AroApiList.PLACE_GET_LIKELY_CURRENT_PLACES:
				qaPlaceApi.execute(AroApiList.PLACE_GET_LIKELY_CURRENT_PLACES);
				break;						
				
			case AroApiList.PLACE_GET_PLACE_TRANSITION_LINK:
				qaPlaceApi.execute(AroApiList.PLACE_GET_PLACE_TRANSITION_LINK);
				break;	
				
			case AroApiList.PLACE_GET_PLACE_HREF:
				qaPlaceApi.execute(AroApiList.PLACE_GET_PLACE_HREF);
				break;				
				
			case AroApiList.PLACE_SEARCH_FOR_FAVORITES:
				qaPlaceApi.execute(AroApiList.PLACE_SEARCH_FOR_FAVORITES);
				break;	
								
			case AroApiList.PLACE_SEARCH_FOR_FREQUENTLY:
				qaPlaceApi.execute(AroApiList.PLACE_SEARCH_FOR_FREQUENTLY);
				break;					
							
			case AroApiList.PLACE_SEARCH_FOR_PLACES_NAME:
				qaPlaceApi.execute(AroApiList.PLACE_SEARCH_FOR_PLACES_NAME);
				break;		
				
			case AroApiList.PLACE_SEARCH_FOR_PLACES_CATEGORY_NAME:
				qaPlaceApi.execute(AroApiList.PLACE_SEARCH_FOR_PLACES_CATEGORY_NAME);
				break;
				
			case AroApiList.PLACE_SEARCH_FOR_PLACES_RADIUS:
				qaPlaceApi.execute(AroApiList.PLACE_SEARCH_FOR_PLACES_RADIUS);
				break;	
	
			case AroApiList.PLACE_GET_RECENT_PLACES:
				qaPlaceApi.execute(AroApiList.PLACE_GET_RECENT_PLACES);
				break;					

//////////////////				
								
			case AroApiList.TRAITS_GET_BY_ID:
				qaTraitsApi.execute(AroApiList.TRAITS_GET_BY_ID);
				break;
				
			case AroApiList.TRAITS_MAKE_BULK_PLACES_REQUEST:
				qaTraitsApi.execute(AroApiList.TRAITS_GET_BY_ID);
				break;					
					
///////////////////						
							
			case AroApiList.CATEGORY_GET_BY_NAME:
				qaCategoryApi.execute( AroApiList.CATEGORY_GET_BY_NAME);
				break;			
				
			case AroApiList.CATEGORY_GET_BY_ID:
				qaCategoryApi.execute( AroApiList.CATEGORY_GET_BY_ID);
				break;
			case AroApiList.CATEGORY_GET_TOP_CATEGORIES:
				qaCategoryApi.execute( AroApiList.CATEGORY_GET_TOP_CATEGORIES);
				break;
				
///////////////////				

			case AroApiList.PERSON_GET_PEOPLE:
//				qaPersonApi.getPeople_test();
				qaPersonApi.execute(AroApiList.PERSON_GET_PEOPLE);
			break;	
				
			case AroApiList.PERSON_GET_BY_ID:
//				qaPersonApi.getById_test();
				qaPersonApi.execute(AroApiList.PERSON_GET_BY_ID);				
				break;
			
///////////////////			
		
			case AroApiList.ACTIVITY_GET_ACTIVITIES:
				qaActivity.execute(AroApiList.ACTIVITY_GET_ACTIVITIES);
				break;
			
			case AroApiList.ACTIVITY_GET_ACTIVITIES_WITH_PLACES:
				qaActivity.execute(AroApiList.ACTIVITY_GET_ACTIVITIES_WITH_PLACES);
				break;
			
			case AroApiList.ACTIVITY_GET_BY_ID:
				qaActivity.execute(AroApiList.ACTIVITY_GET_BY_ID);
				break;
			
			case AroApiList.ACTIVITY_UPDATE_ACTIVITY:
				qaActivity.execute(AroApiList.ACTIVITY_UPDATE_ACTIVITY);
				break;
			
			case AroApiList.ACTIVITY_POST_NOW:
				qaActivity.execute(AroApiList.ACTIVITY_POST_NOW);
				break;			
			
//////////////////
				
			case AroApiList.AUTH_VALIDATE_CREDENTIALS:
				qaTraitsApi.execute(AroApiList.AUTH_VALIDATE_CREDENTIALS);
				break;					
				
//////////////////
				
			case AroApiList.OBSERV_UPLOAD:
				//qaObservation.Observation_test();
				qaObservation.execute(AroApiList.OBSERV_UPLOAD);
				break;
				
			case AroApiList.OBSERV_GET_ENCRYPTED:
				//qaObservation.Observation_test();
				qaObservation.execute(AroApiList.OBSERV_GET_ENCRYPTED);
				break;				
				
			default:				
				Log.e(TAG,"API not found on QA tool");
			
			
			}
			
			
			//return null;
		}
		
//	}

	
}



