package com.aro.qa.arosdk;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aro.android.sdk.api.CategoryApi;
import com.aro.android.sdk.api.PersonApi;
import com.aro.android.sdk.apiobjects.Category;
import com.aro.android.sdk.apiobjects.Person;
import com.aro.android.sdk.apiobjects.Trait;
import com.aro.android.sdk.http.AROApiComm;
import com.aro.android.sdk.http.AROApiCommQA;
import com.aro.android.sdk.http.WebException;
import com.aro.android.sdk.http.WebListener;

public class QaPersonApi extends AsyncTask<Integer, Void,Void>{


	Context mContext;
	private static final String TAG = "QA_test";
	
	List<TestCaseData> testValues;
	PersonApi  personApi = new PersonApi(new AROApiCommQA(AroApiList.token));
	QaHelper qaHelper;
	

	public QaPersonApi(Context mContext) {
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
			
			case AroApiList.PERSON_GET_BY_ID:
				getById_test();										
				break;	
			case AroApiList.PERSON_GET_PEOPLE:
				getPeople_test();										
				break;			
				
			}
			return null;
		}	
	
	/*
	 * Executes getPeople method.
	 *  
	 */
	
	void getPeople_test(){

		qaHelper.qaLogs("=======     personAPI      =======");
		qaHelper.qaLogs("personAPI.getPeople");			

		try{	
			personApi.getPeople(mContext, webListenerPerson);

			Thread.sleep(2000);
		}catch(Exception e){
			qaHelper.qaLogs("Error calling personApi.getPeople. " + e.toString());
		}

	}	

	
	
	void getById_test(){
		 
		testValues = TestCasesPerson.getByIdTestCases();
				
		for (TestCaseData theTestCase : testValues){
			String personId = theTestCase.getTestValue().get(0);
			qaHelper.qaLogs("=======  personApi.getById   ========");
			qaHelper.qaLogs("Test Case # " + theTestCase.getTestCaseNumber() + ". " + theTestCase.getTestCaseDescription() + "." );			
			qaHelper.qaLogs("Test value. Person Id = '" + personId + "'");
			
			try{	
				personApi.getById(mContext, personId, webListenerPerson);

				Thread.sleep(2000);
			}catch(Exception e){
				qaHelper.qaLogs("Error calling categoryAPI.getById. " + e.toString());
			}
		}		
	}		
	
	
	
	WebListener webListenerPerson = new WebListener() {

		@SuppressWarnings("unchecked")
		@Override
		public void sendWebSuccess(Object arg0) {	
			
			qaHelper.qaLogs("WebSuccess <==");
			Person resultPerson = null;
			ArrayList<Person> personList = new ArrayList<Person>();
			
			
			if (arg0 instanceof Person){
				
				resultPerson = (Person) arg0;
				
				showPersonData(resultPerson);
				
			}else if (arg0 instanceof ArrayList<?>){
				personList = (ArrayList<Person>) arg0;
				qaHelper.qaLogs("Pesron List. Elementes = " + personList.size());
				for (Person person : personList){
					
					showPersonData(person);
				}
				
			}

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
	
	void showPersonData(Person person){
		
		
		qaHelper.qaLogs(" Person Id=" + person.getId() + 
				  "\r\n First Name=" + person.getName() +
				  "\r\n Last Name =" + person.getLastName() + 
				  "\r\n First Name=" + person.getEmail() +
				  "\r\n getAccountCreated=" + person.getAccountCreated());		
		
/*		
		for (String traitId :   person.getTraitIds()){
			qaHelper.qaLogs("		trait Id=" + traitId);				
		}
		
		for (String traitId : person.getHomeIds()){
			qaHelper.qaLogs("		Home Id=" + traitId);				
		}	
		
		for (String traitId : person.getWorkIds()){
			qaHelper.qaLogs("		WorkId Id=" + traitId);				
		}
*/		
	}
	
}
