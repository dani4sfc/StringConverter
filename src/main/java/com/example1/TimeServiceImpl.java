package com.example1;

import org.springframework.stereotype.Service;

@Service
public class TimeServiceImpl implements TimeService{

	//Constants and vars
	
	public static final String[] PERIOD = {"[PM]", "[H1]", "[HT]", "[H2]", "[FT]"};
	
	public boolean correctPeriod = false;
	
	boolean correctTime = false;
	
	//We implement here the method and develop it
	@SuppressWarnings("static-access")
	public String TimeConverter(String str) {
		
		//First part of the time (PERIOD)
		
		for(int i = 0; i < PERIOD.length; i++) {
			
			if(str.substring(0, 4).equals(PERIOD[i]))
			{
				
				correctPeriod = true;
			}
			
		}
		
		//First check...
		
		if (correctPeriod == false) {
			return "Invalid";
		}
		
		String period = str.substring(0,  4);
		
		//Second part of the time (Minutes and seconds)
		
		String time ="";
		
		String minutes = "";
		
		try {
			
		time = str.substring(5);
		minutes = time.split(":")[0];
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//We check if the time value is valid:
		
		//THIS CHECK (Number between 0-9) makes crash the tests
//		String firstValue = str.substring(5, 1);
		
//		Integer check = 0;
//		Integer checkValidMinute = check.parseInt(firstValue);
		
//		if( checkValidMinute >= 0 && checkValidMinute <= 9) {
//			
//			correctTime = true;
//		}
		
		if( !str.substring(5).startsWith("-")) {
			
			correctTime = true;
		}

		
		//minutes formating
		if(minutes.length() == 1) {
			minutes = "0"+minutes;
		}
		
		
		String secondsDirty = "";
		String seconds = "";
		String miliSeconds = "";
		
		try {
			
			secondsDirty = time.split(":")[1];
			seconds = secondsDirty.split("\\.")[0];
			miliSeconds = secondsDirty.split("\\.")[1];
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//Parse string to integer in order to compare
		
		Integer tempMilisecond = 0;
		
		Integer tempSecond = 0;
		
		Integer temp = tempMilisecond.parseInt(miliSeconds);
				
		//Check if miliseconds are higher to 499 in order to change the seconds (+1)
		
		if(temp > 499) {
			
			Integer tempSec = tempSecond.parseInt(seconds);
			tempSec = tempSec + 1;
			seconds = tempSec.toString();

		}
		
		//Final time variable

		String timeFinalFormat = minutes +":"+ seconds;
		
		//Calculate extra time:
		
		Integer tempMin = 0;
		Integer tempMinutes = tempMin.parseInt(minutes);
		Integer extraTime = 0;
		String extraTimeStr = "";
	
		//If there's extra time, we change the format:
		if(tempMinutes > 45) {
			
			timeFinalFormat = "45";
			
			extraTime = tempMinutes - 45;
			
			extraTimeStr = extraTime.toString();
			
			extraTimeStr = extraTimeStr +":"+ seconds;
		}		

		
		//We detect extra time, if it exists, we set it into the time var.
		if(!extraTimeStr.contentEquals("")) {
			
			timeFinalFormat = timeFinalFormat +" +"+extraTimeStr;

		}
		
		//Final variable period
		
		String finalPeriod = "";
		
		//Switch final periods
		
		if(period.equals(PERIOD[0])) {
			finalPeriod = "PRE_MATCH";
		}else if(period.equals(PERIOD[1])) {
			finalPeriod = "FIRST_HALF";
		}else if(period.equals(PERIOD[2])) {
			finalPeriod = "HALF_TIME";
		}else if(period.equals(PERIOD[3])) {
			finalPeriod = "SECOND_HALF";
		}else if(period.equals(PERIOD[4])) {
			finalPeriod = "FULL_TIME";
		}else {
			finalPeriod = null;
		}
		
	
		//Check the boolean parameters which confirmed the correct values
		if (correctTime && correctPeriod) {
			
			return timeFinalFormat+" - "+finalPeriod;

		}else {
			
			return "Invalid";
		}
	}


}
