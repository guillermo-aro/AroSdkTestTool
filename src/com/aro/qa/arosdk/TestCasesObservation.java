package com.aro.qa.arosdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCasesObservation {

	
static int i=1;
		
	
static List<TestCaseData> uploadObservations_testCases() {
	
	List<TestCaseData> testValues = new ArrayList<TestCaseData>();

/*	
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5980563,-122.3284529,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"2014-03-06T13:33:27.997Z\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"WiFi\",\"scanResults\":[{\"BSSID\":\"f4e1111126a651181a042470773c8ba7b88f95c7\",\"SSID\":\"ecbf034d05660c25ca38a328d10a55c8ae916e0c\",\"capabilities\":\"[WPA-PSK-TKIP][ESS]\",\"frequency\":2412,\"level\":-69}],\"timeObserved\":\"2014-02-27T13:33:27.997Z\",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (A.R.O.), 1 good wifi Observation ", new String[] {"aroqa01+1@gmail.com","200"}));
			
		testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.591549075340886,-122.33259201049805,0.0;u=10.0;source=network;test=jng\",\"accuracyMeters\":1,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.001,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"WiFi\",\"timeObserved\":\"2014-03-06T22:00:00.000Z\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Barometer\",\"pressureInMillibars\":12,\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Battery\",\"chargingType\":\"AC\",\"currentLevel\":10,\"maxLevel\":100,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Movement\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Proximity\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Acoustic\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Light\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"}]")),
				"Good Location,Wifi,Barometer,Bettery,Movement,Proximity,Acoustic,Light Observations Safeco Field", new String[] {""}));				
			
*/			


	//String theFullTime = "2014-03-05T22:00:00.000Z";

	int hour = 18;
	int min = 0;
	int timeInterval=14;
	
	String theDate = "2014-04-02T";	
	String theTime = Integer.toString(hour) + ":" + Integer.toString(min) + ":00.000Z";
	
	//QWEST	
		
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.59507995257869,-122.33173370361328,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":00:00.000Z"  +  "\",\"timeZone\":\"America/Los_Angeles\"}]")),"1 good Location Observation (Qwest Field)", new String[] {""}));
	
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5956111,-122.3309111,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+timeInterval) + ":00.000Z"  +  "\",\"timeZone\":\"America/Los_Angeles\"}]")),
		"1 good Location Observation (Qwest Field)", new String[] {""}));
/*	
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5956111,-122.3309111,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+timeInterval) + ":00.000Z"  +  "\",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (Qwest Field)", new String[] {""}));
			

	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5956469,-122.3309685,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+11) + ":00.000Z"  +  "\",\"timeZone\":\"America/Los_Angeles\"}]")),
		"1 good Location Observation (Qwest Field)", new String[] {""}));

	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5956569,-122.3309575,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+11) + ":00.000Z"  +  "\",\"timeZone\":\"America/Los_Angeles\"}]")),
		"1 good Location Observation (Qwest Field)", new String[] {""}));

	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5956329,-122.3309465,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+11) + ":00.000Z"  +  "\",\"timeZone\":\"America/Los_Angeles\"}]")),
		"1 good Location Observation (Qwest Field)", new String[] {""}));
*/			
	
	 hour = hour;
	 min=30;
	
//SAFECO																											     47.591549075340886,-122.33259201049805
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.591549075340886,-122.33259201049805,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":00:00.000Z"  +   "\",\"timeZone\":\"America/Los_Angeles\"}]")),
				"1 good Location Observation (Safeco Field)", new String[] {""}));
	
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.59154907909,-122.3327,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+timeInterval) + ":00.000Z"  +   "\",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (Safeco Field)", new String[] {""}));
/*	
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.59154907909,-122.3327,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+timeInterval) + ":00.000Z"  +   "\",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (Safeco Field)", new String[] {""}));	
	
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5916117,-122.33290,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+11) + ":00.000Z"  + "\",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (Safeco Field)", new String[] {""}));
	
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.591288,-122.33270,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+11) + ":00.000Z"  +   " \",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (Safeco Field)", new String[] {""}));	
	
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5910545,-122.3333500,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+11) + ":00.000Z"  +  "\",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (Safeco Field)", new String[] {""}));	

*/
	
//	[{\"type\":\"Location\",\"location\":\"geo:37.5645377,126.976549,0.0;u=10.0;source=network;test=jng\",\"accuracyMeters\":1,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.001,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"WiFi\",\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":00:00.000Z"  +   "\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Barometer\",\"pressureInMillibars\":12,\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Battery\",\"chargingType\":\"AC\",\"currentLevel\":10,\"maxLevel\":100,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Movement\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Proximity\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Acoustic\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Light\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"}]	

	 
	 hour = hour +1;
	 min=0;
	
	 
//Pyramid	 
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5920110,-122.3346950,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":00:00.000Z"  +   "\",\"timeZone\":\"America/Los_Angeles\"}]")),
				"1 good Location Observation (Pyramid)", new String[] {""}));
	
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5920000,-122.3346840,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+timeInterval) + ":00.000Z"  +   "\",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (Pyramid)", new String[] {""}));	 
	
	hour = hour;
	min=30;
	 
//South of Seoul	 
		testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("	[{\"type\":\"Location\",\"location\":\"geo:37.5645377,126.976549,0.0;u=10.0;source=network;test=jng\",\"accuracyMeters\":1,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.001,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"WiFi\",\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":00:00.000Z"  +   "\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Barometer\",\"pressureInMillibars\":12,\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Battery\",\"chargingType\":\"AC\",\"currentLevel\":10,\"maxLevel\":100,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Movement\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Proximity\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Acoustic\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Light\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"}]")),
				"Good Location,Wifi,Barometer,Bettery,Movement,Proximity,Acoustic,Light Observations", new String[] {""}));			
		testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("	[{\"type\":\"Location\",\"location\":\"geo:37.5645367,126.976559,0.0;u=10.0;source=network;test=jng\",\"accuracyMeters\":1,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.001,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"WiFi\",\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+11) +  ":00.000Z"  + "\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Barometer\",\"pressureInMillibars\":12,\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Battery\",\"chargingType\":\"AC\",\"currentLevel\":10,\"maxLevel\":100,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Movement\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Proximity\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Acoustic\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Light\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"}]")),
				"Good Location,Wifi,Barometer,Bettery,Movement,Proximity,Acoustic,Light Observations", new String[] {""}));	
/*
		testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("	[{\"type\":\"Location\",\"location\":\"geo:37.5645357,126.976569,0.0;u=10.0;source=network;test=jng\",\"accuracyMeters\":1,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.001,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"WiFi\",\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+11) +  ":00.000Z"  + "\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Barometer\",\"pressureInMillibars\":12,\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Battery\",\"chargingType\":\"AC\",\"currentLevel\":10,\"maxLevel\":100,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Movement\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Proximity\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Acoustic\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Light\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"}]")),
				"Good Location,Wifi,Barometer,Bettery,Movement,Proximity,Acoustic,Light Observations", new String[] {""}));	
		testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("	[{\"type\":\"Location\",\"location\":\"geo:37.5645477,126.976449,0.0;u=10.0;source=network;test=jng\",\"accuracyMeters\":1,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.001,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"WiFi\",\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+11) +  ":00.000Z"  + "\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Barometer\",\"pressureInMillibars\":12,\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Battery\",\"chargingType\":\"AC\",\"currentLevel\":10,\"maxLevel\":100,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Movement\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Proximity\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Acoustic\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Light\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"}]")),
				"Good Location,Wifi,Barometer,Bettery,Movement,Proximity,Acoustic,Light Observations", new String[] {""}));	
		testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("	[{\"type\":\"Location\",\"location\":\"geo:37.5645577,126.976349,0.0;u=10.0;source=network;test=jng\",\"accuracyMeters\":1,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.001,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"WiFi\",\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+11) +  ":00.000Z"  + "\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Barometer\",\"pressureInMillibars\":12,\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Battery\",\"chargingType\":\"AC\",\"currentLevel\":10,\"maxLevel\":100,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Movement\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Proximity\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Acoustic\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Light\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"}]")),
				"Good Location,Wifi,Barometer,Bettery,Movement,Proximity,Acoustic,Light Observations", new String[] {""}));
		testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("	[{\"type\":\"Location\",\"location\":\"geo:37.5645577,126.976249,0.0;u=10.0;source=network;test=jng\",\"accuracyMeters\":1,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.001,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"WiFi\",\"timeObserved\":\"" + theDate + Integer.toString(hour) + ":" + Integer.toString(min=min+11) +  ":00.000Z"  + "\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Barometer\",\"pressureInMillibars\":12,\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Battery\",\"chargingType\":\"AC\",\"currentLevel\":10,\"maxLevel\":100,\"timeObserved\":\"${#TestCase#currentTime}\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Movement\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Proximity\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Acoustic\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"},{\"type\":\"Light\",\"timeObserved\":\"XXX\",\"timeZone\":\"America/Los_Angeles\"}]")),
				"Good Location,Wifi,Barometer,Bettery,Movement,Proximity,Acoustic,Light Observations", new String[] {""}));			
*/
	 
	 hour = hour +1;
	 min=0;
	 
	//SBUX
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5807,-122.3360,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour)   + ":00:00.000Z"  +    "\",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (SBUX HQ)", new String[] {""}));
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5700,-122.3300,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour)  + ":" + Integer.toString(min=min+timeInterval) + ":00.000Z"  +   "\",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (SBUX HQ)", new String[] {""}));
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.5750,-122.3330,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour)  + ":" + Integer.toString(min=min+timeInterval) + ":00.000Z"  +   "\",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (SBUX HQ)", new String[] {""}));
	
	 hour = hour;
	 min=30;
	
	
	//SEA-TAC
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.4443140:-122.3027660,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour)   + ":00:00.000Z"  +    "\",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (SEA-TAC)", new String[] {""}));
	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.4443100:-122.3027669,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour)  + ":" + Integer.toString(min=min+timeInterval) + ":00.000Z"  +   "\",\"timeZone\":\"America/Los_Angeles\"}]")),
			"1 good Location Observation (SEA-TAC)", new String[] {""}));
//	testValues.add(new TestCaseData(i++, new ArrayList<String>(Arrays.asList("[{\"type\":\"Location\",\"location\":\"geo:47.4443100:-122.3027669,0.0;u=10.0;tz=America%2FLos_Angeles;source=network;test=jngeee\",\"accuracyMeters\":10,\"bearingDegrees\":89.2,\"speedMetersPerSecond\":0.1,\"timeObserved\":\"" + theDate + Integer.toString(hour)  + ":" + Integer.toString(min=min+timeInterval) + ":00.000Z"  +   "\",\"timeZone\":\"America/Los_Angeles\"}]")),
//			"1 good Location Observation (SEA-TAC)", new String[] {""}));	
	
		return testValues;
}
	
}
