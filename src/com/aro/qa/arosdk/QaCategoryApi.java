package com.aro.qa.arosdk;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.aro.android.sdk.api.CategoryApi;
import com.aro.android.sdk.apiobjects.Category;
import com.aro.android.sdk.apiobjects.Place;
import com.aro.android.sdk.http.AROApiComm;
import com.aro.android.sdk.http.AROApiCommQA;
import com.aro.android.sdk.http.WebException;
import com.aro.android.sdk.http.WebListener;
import com.example.arosdktesttool.R;


/***
 * This class test 3 method on the Category Aro Sdk
 * 
 * @author guillerm
 *
 */

public class QaCategoryApi extends AsyncTask<Integer, Void,Void>{
	Context mContext;
	private static final String TAG = "QA_test";
	private Activity activity;
	
	List<TestCaseData> testValues;
	CategoryApi  categoryAPI = new CategoryApi(new AROApiCommQA(AroApiList.token));
	QaHelper qaHelper;
	TestCaseData mOneTestCase; 
	Set<String> mSetResults = new HashSet<String>();

	public QaCategoryApi(Context mContext) {
		super();
		this.mContext = mContext;
		qaHelper = new QaHelper(mContext);
	}

	
	 @Override
	protected void onPostExecute(Void result) {
		Log.e("TAG","XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

	}
	 
	@Override
	protected Void doInBackground(Integer... params) {

		switch (params[0]){
		
		case AroApiList.CATEGORY_GET_BY_NAME:
			CategoryByName_test();	
			break;			
			
		case AroApiList.CATEGORY_GET_BY_ID:
			CategoryById_test();
			break;
		case AroApiList.CATEGORY_GET_TOP_CATEGORIES:
			GetTopCategories_test();
			break;
					
			
		}
		return null;
	}	
	
	/*
	 * Get test test cases values and execute the corresponding Aro SDK method.
	 *  
	 */
	
	void GetTopCategories_test(){
	
			
			qaHelper.qaLogs("======    categoryAPI.getAllCategories  (Top Categories)  ======");
			
			try{	
				categoryAPI.getAllCategories(mContext,webListenerCategory);
				Thread.sleep(1000);
			}catch(Exception e){
				qaHelper.qaLogs("Error calling categoryAPI.getAllCategories. " + e.toString(),"e");
			}
	
	}	
	

	/*
	 * Get test test cases values and execute the corresponding Aro SDK method.
	 *  
	 */
	
	void CategoryByName_test(){
				 
		testValues = TestCasesCategory.getByNameTestCases();
	
		for (TestCaseData theTestCase : testValues){
			String categoryName = theTestCase.getTestValue().get(0);
			qaHelper.qaLogs("=====   categoryAPI.getCategoryByName   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + ".");			
			qaHelper.qaLogs("Test value. Category Name = '" +categoryName + "'");
			mOneTestCase = theTestCase;
			
			try{	
				categoryAPI.getCategoryByName(mContext, categoryName, webListenerCategory);

				Thread.sleep(2000);
			}catch(Exception e){
				qaHelper.qaLogs("Error calling categoryAPI.getCategoryByName. " + e.toString(),"e");
			}
		}		
		
	}
	
		
	void CategoryById_test(){
			 
		testValues = TestCasesCategory.getByIdTestCases();
				
		for (TestCaseData theTestCase : testValues){
			String categoryId = theTestCase.getTestValue().get(0);
			qaHelper.qaLogs("=====   categoryAPI.getById   =====");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + "." );			
			qaHelper.qaLogs("Test value. Category Id = '" + categoryId + "'");
			mOneTestCase = theTestCase;
			
			try{	
				categoryAPI.getById(mContext, categoryId, webListenerCategory);
				Thread.sleep(1000);
			}catch(Exception e){
				qaHelper.qaLogs("Error calling categoryAPI.getById " + e.toString(),"e");
			}
		}		
	}	


	WebListener webListenerCategory = new WebListener() {

		@SuppressWarnings("unchecked")
		@Override
		public void sendWebSuccess(Object arg0) {	
			
			qaHelper.qaLogs("WebSuccess <==");
			Category resultCategory = null;
			ArrayList<Category> topCategories = new ArrayList<Category>();
			
			
			if (arg0 instanceof Category){
				
				resultCategory = (Category) arg0;
				
				qaHelper.qaLogs("Parent Category Name=" + resultCategory.getParentCategoryName() + " / Plural Name=" + resultCategory.getPluralName());
				showCategoryObjectFields(resultCategory);
				qaHelper.verifyResult(mOneTestCase, mSetResults);	
				
			}else if (arg0 instanceof ArrayList<?>){
				
				
				topCategories = (ArrayList<Category>) arg0;
				qaHelper.qaLogs("Category List. Total = " + topCategories.size());
				for (Category catego : topCategories){
					
					qaHelper.qaLogs("Category Id=" + catego.getId() + " / Category Name=" + catego.getName());
					qaHelper.qaLogs("Parent Category Name=" + catego.getParentCategoryName() + " / Plural Name=" + catego.getPluralName());		
					showCategoryObjectFields(catego);
				}
				qaHelper.verifyResult(mOneTestCase, mSetResults);
				mSetResults.clear();
				mOneTestCase=null;
			}

		}

		@Override
		public void sendWebFailure(WebException arg0) {
			qaHelper.qaLogs("WebFailure " + arg0.getMessage());

		}

		@Override
		public void sendWebFailure() {
			qaHelper.qaLogs("WebFailure ");

		}
	};		
	
	void showCategoryObjectFields(Category category){

		JSONObject placeJson;
	
		qaHelper.qaLogs("getId =	" + category.getId() + "\r\n" +
				"getName =	" + category.getName() + "\r\n" +
				"getParentCategoryId =	" + category.getParentCategoryId() + "\r\n" +
				"getParentCategoryId =	" + category.getParentCategoryName()+ "\r\n" +			
				"getParentCategoryName=" + category.getPluralName() + "\r\n","d");

		qaHelper.qaLogs("JSON:","d");

		mSetResults.add(category.getId());
		mSetResults.add(category.getParentCategoryId() );
		mSetResults.add( category.getName());
		mSetResults.add(category.getParentCategoryName());
	
	}
	
	public void showToast(final String toast, final String debug)
    {
        activity.runOnUiThread(new Runnable() {
            public void run()
            {
            	Log.d(TAG,"YYYYYYYYYYYYYY");
            	TextView txtView = (TextView) ((Activity)mContext).findViewById(R.id.editInputParameters);
        		txtView.append("\n\r" + "ZZZZZZZZZZZZZZ");	
            }
        });
    }
}
