package com.example1;

import org.springframework.stereotype.Service;

@Service
public class TimeServiceImpl implements TimeService{

	//Constants and vars
	
	public static final String[] PERIOD = {"[PM]", "[H1]", "[HT]", "[H2]", "[FT]"};
	
	public boolean correctPeriod = false;
	
	boolean correctTime = false;
	
	//We implement here the method and develop it
	public String TimeConverter(String str) {
		
		//First part of the time (PERIOD)
		
		for(int i = 0; i < PERIOD.length; i++) {
			
			if(str.substring(0, 4).equals(PERIOD[i]))
			{
				
				correctPeriod = true;
			}
			
		}
		
		String period = str.substring(0,  4);
		
		if (true == correctPeriod) {
			
			System.out.println("Correct period given: " + period);

		}else {
			System.out.println("Invalid");
			return "Invalid";
		}
		
		//Second part of the time (Minutes and seconds)
		
		String time ="";
		
		String minutes = "";
		
		try {
			
		time = str.substring(5);
		minutes = time.split(":")[0];
			
			
		} catch (Exception e) {
			System.out.println(e);
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
		
		if(miliSeconds.startsWith("5")) {
			
			seconds = seconds +1;
			
		}
		
		//Final time variable
		
		String timeFinalFormat = minutes +":"+ seconds;
		
		//Final variable para periodo
		
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
		
		
		//Use \\ if . doesn't work
		// String example = time.split("\\.")[1];
		
		//EXAMPLES:
		
		String example1 ="[PM] 0:00.000";
		// output1: "00:00 – PRE_MATCH" 
		String example2 ="[H1] 0:15.025";
		// Output2: "00:15 – FIRST_HALF"
		String example3 = "[H1] 3:07.513";
		//Output3: "03:08 – FIRST_HALF" 
		String example4 = "[H1] 45:00.001";
		// Output 4: "45:00 +00:00 – FIRST_HALF" 
		String example5 = "[H1] 46:15.752";
		//Output 5: "45:00 +01:16 – FIRST_HALF" 
		String example6 = "[HT] 45:00.000";
		//Output 6: "45:00 – HALF_TIME" 
		String example7 = "[H2] 45:00.500";
		// Output 7: "45:01 – SECOND_HALF" 
		String example8 = "[H2] 90:00.908";
		// Output8: "90:00 +00:01 – SECOND_HALF"
		String example9 = "[FT] 90:00.000";
		//Output 9: "90:00 +00:00 – FULL_TIME" 
		String invalid1 = "90:00";
		//Output invalid1:  “INVALID”
		String invalid2 = "[H3] 90:00.000";
		String invalid3 = "[PM] -10:00.000";
		

		return timeFinalFormat+" - "+finalPeriod;
	}


}
