/* INSTRUCTION: This is a command line application. 
So please execute this template with the following arguments:

		arg[0] = username
		arg[1] = password
*/

/**
 * @author (Your Name Here)
 *
 */
 
import com.google.gdata.client.Query;
import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.BaseFeed;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.data.calendar.ColorProperty;
import com.google.gdata.data.calendar.HiddenProperty;
import com.google.gdata.data.calendar.TimeZoneProperty;
import com.google.gdata.data.extensions.EventFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.model.atom.Feed;
import com.google.gdata.model.gd.EventEntry;
import com.google.gdata.model.gd.Where;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ParseException;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import sun.util.calendar.BaseCalendar.Date;

/**
 * This is a test template
 */


  public class Calendar {
	 
	  static void getAllCalenders(){
		  try {
			CalendarService myService = new CalendarService("My Application");
			    myService.setUserCredentials("nilesh.d.agrawal@gmail.com","#######");
			    
			    // Get a list of all entries
			    URL metafeedUrl = new URL("http://www.google.com/calendar/feeds/default/allcalendars/full");
			    System.out.println("Getting Calendar entries...\n");
			    CalendarFeed resultFeed = myService.getFeed(metafeedUrl, CalendarFeed.class);
			    List<CalendarEntry> entries = resultFeed.getEntries();
			    
			    for(int i=0; i<entries.size(); i++) {
			      CalendarEntry entry = entries.get(i);
			      String calenderName = entry.getTitle().getPlainText();
			      System.out.println("\t" + entry.getTitle().getPlainText());
			    }
			    System.out.println("\nTotal Calenders "+entries.size());
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  
	  static ArrayList<ExistingCalenderEvents> readCalenderEvents(DateTime globalStartTime,DateTime globalEndTime){
		  
		  try {
		        
		       URL feedUrl1 = new URL("https://www.google.com/calendar/feeds/default/private/full");
		      // URL feedUrl1 = new URL("https://www.google.com/calendar/feeds/nilesh.d.agrawal@gmail.com/public/full?singleevents=true&sortorder=ascending&start-min=2013-10-20T08:00:00&start-max=2013-10-25T23:00:00");
		       
		       CalendarQuery myQuery1 = new CalendarQuery(feedUrl1);
		       myQuery1.setMinimumStartTime(globalStartTime);
		       myQuery1.setMaximumStartTime(globalEndTime);
		      
		       CalendarService myService1 = new CalendarService("exampleCo-exampleApp-1");
		       myService1.setUserCredentials("nilesh.d.agrawal@gmail.com", "#######");
		       
		       ArrayList<ExistingCalenderEvents> listOfExistingEvents = new ArrayList<ExistingCalenderEvents>();
		       
		       CalendarEventFeed myCalendarEventFeeds = myService1.query(myQuery1,CalendarEventFeed.class); 
		       EventFeed mycE = myService1.query(myQuery1, EventFeed.class);
		       List<CalendarEventEntry> simpleListOfEventsTitile = myCalendarEventFeeds.getEntries();
		       
		       System.out.println("Size"+myCalendarEventFeeds.getEntries().size());
		       
		      for(int i=0; i< myCalendarEventFeeds.getEntries().size();i++){
		      ExistingCalenderEvents sampleObject = new ExistingCalenderEvents();
		     // System.out.println("Entry no "+i+""+myCalendarEventFeeds.getEntries().get(i).getTitle().getPlainText());
		     
		      CalendarEventEntry firstMatchEntry = (CalendarEventEntry) myCalendarEventFeeds.getEntries().get(i);
		      
		      
		      
		      String myEntryTitle = firstMatchEntry.getTitle().getPlainText();
		      System.out.println(myEntryTitle);
		      sampleObject.setCalenderEventTitle(myEntryTitle);
		     
		     System.out.println("start time = "+firstMatchEntry.getTimes().get(0).getStartTime());
		     DateTime startTime = firstMatchEntry.getTimes().get(0).getStartTime();
		     sampleObject.setStartTime(startTime);
		     
		     System.out.println("End time = "+firstMatchEntry.getTimes().get(0).getEndTime());
		      DateTime endTime1 = firstMatchEntry.getTimes().get(0).getEndTime();
		      sampleObject.setEndTime(endTime1);
		     
		     listOfExistingEvents.add(i,sampleObject);
		       }
		      return listOfExistingEvents; 
		      }
		      catch(AuthenticationException e) {
		        e.printStackTrace();
		      }
		      catch(MalformedURLException e) {
		        e.printStackTrace();
		      }
		      catch(ServiceException e) {
		        e.printStackTrace();
		      }
		      catch(IOException e) {
		        e.printStackTrace();
		      }
		return null;
		     	  
	  }
    
	  
	  
	  public static void updateCalender(ArrayList<FreeEvents> freeList, ArrayList<NoteRequest> noteRequest){
		
		  	try {
				CalendarService myService = new CalendarService("My Application");
				myService.setUserCredentials("nilesh.d.agrawal@gmail.com","#######");
  //UPDATING THE CALENDER>>>>>>
				URL postURL = new URL("http://www.google.com/calendar/feeds/nilesh.d.agrawal@gmail.com/private/full");
				CalendarEventEntry myEvent = new CalendarEventEntry();

				//Set the title and description
				myEvent.setTitle(new PlainTextConstruct("Free Events "));
				myEvent.setContent(new PlainTextConstruct("Loading this free event with"));

				//Create DateTime events and create a When object to hold them, then add
				//the When event to the event
				DateTime startTime = DateTime.parseDateTime("2013-10-20T15:00:00-08:00");
				DateTime endTime = DateTime.parseDateTime("2013-10-20T17:00:00-08:00");
				When eventTimes = new When();
				eventTimes.setStartTime(startTime);
				System.out.println(eventTimes.getStartTime().toStringRfc822());
				eventTimes.setEndTime(endTime);
				myEvent.addTime(eventTimes);

				// POST the request and receive the response:
				CalendarEventEntry insertedEntry = myService.insert(postURL, myEvent);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AuthenticationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  
	  
	 static void addCalenderTemplate(String str1,String str2){
	
		try {
			// Create the calendar
			 CalendarEntry calendar = new CalendarEntry();
			 calendar.setTitle(new PlainTextConstruct(str1));
			 calendar.setSummary(new PlainTextConstruct(str2));
			 
			 calendar.setTimeZone(new TimeZoneProperty("America/Los_Angeles"));
			 calendar.setHidden(HiddenProperty.FALSE);
			 calendar.setColor(new ColorProperty("#2952A3"));
			 //calendar.addLocation(new Where());

			 CalendarService myService = new CalendarService("My Application");
				myService.setUserCredentials("nilesh.d.agrawal@gmail.com","#######");
			 // Insert the calendar
			 URL postUrl = new URL("https://www.google.com/calendar/feeds/default/owncalendars/full");
			 CalendarEntry returnedCalendar = myService.insert(postUrl, calendar);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	 
	
	 
	 
    public static void main(String[] args) {
      
    	DateTime startTime = DateTime.parseDateTime("2013-10-20T08:00:00-08:00");
		DateTime endTime = DateTime.parseDateTime("2013-10-29T23:00:00-08:00");
		ArrayList<NoteRequest>  noteRequest = new ArrayList<NoteRequest>();
		
		
		NoteRequest obj1 = new NoteRequest("GeeksForGeeks",DateTime.parseDateTime("2013-10-20T10:00:00-08:00"), DateTime.parseDateTime("2013-10-20T30:00:00-08:00"), 5);
		NoteRequest obj2 = new NoteRequest("Interview With Facebook", DateTime.parseDateTime("2013-10-21T10:00:00-08:00"), DateTime.parseDateTime("2013-10-20T11:00:00-08:00"), 1);
		NoteRequest obj3 = new NoteRequest("Extra Class Project", DateTime.parseDateTime("2013-10-21T11:00:00-08:00"), DateTime.parseDateTime("2013-10-21T1:00:00-08:00"), 4);
		String str1 = "Free Time Calender";
		String str2 = "This is the free time calender";
		
		noteRequest.add(obj1);
		noteRequest.add(obj2);
		noteRequest.add(obj3);
		
		String str3 = "Free Time / Existing Calender";
		String str4 = "This calender contains the free time plus existing calender";
	//addCalenderTemplate(str1,str2);	
	//addCalenderTemplate(str3, str4);
		
      getAllCalenders();
      ArrayList<ExistingCalenderEvents> existingCal = readCalenderEvents(startTime,endTime);

      try {

    	 ArrayList<FreeEvents> freeList = runOurAlgo(existingCal, startTime, endTime);
    	 updateCalender(freeList, noteRequest);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (java.text.ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
      

    }


	private static ArrayList<FreeEvents> runOurAlgo(List<ExistingCalenderEvents> input1,DateTime globalStartTime,DateTime globalEndTime) throws java.text.ParseException, ParseException {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        java.util.Date date;
        String dateStart = "";
        date = sdf.parse(globalEndTime.toStringRfc822());
		sdf.applyPattern("d/MM");
		dateStart = sdf.format(date);
		System.out.println("Parsed format of Output "+dateStart);
	

		ArrayList<FreeEvents> listFree = new ArrayList<FreeEvents>();
		int j=0;
		for(int i=0;i<input1.size();i++){
	
			//System.out.println("InputList's Value ");
			System.out.println(input1.get(i).getStartTime().getValue());
			//System.out.println("Global Start Timess");
			System.out.println(globalStartTime.getValue());
			System.out.println(input1.get(i).getStartTime());
			long diff = input1.get(i).getStartTime().getValue() - globalStartTime.getValue();
			//System.out.println("Diff"+diff);
			long diffSeconds = diff / 1000;
	        long diffMinutes = diff / (60 * 1000);
	        //System.out.println(diffMinutes);
	        long diffHours = diff / (60 * 60 * 1000);
	        long diffDays = diff / (24 * 60 * 60 * 1000);
	        
	        System.out.println(input1.get(i).getStartTime());
	        SimpleDateFormat sd = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
	        java.util.Date date2;
	        String currentDate = "";
	        date2 = sd.parse(input1.get(i).getStartTime().toStringRfc822());
			sd.applyPattern("d/MM");
			currentDate = sdf.format(date2);
			System.out.println("Current "+currentDate);
			
			if(currentDate.equals(dateStart)){
				if(((input1.get(i).getStartTime()).compareTo(globalStartTime) > 0) & (diffMinutes> 30)){
					//System.out.println(" Event "+ j );
					FreeEvents sampleObj = new FreeEvents();
					sampleObj.setFreeStartTime(globalStartTime);
					//System.out.println("Start "+globalStartTime);
					sampleObj.setFreeEndTime(input1.get(i).startTime);
					//System.out.println("End"+input1.get(i).startTime);
					globalStartTime = input1.get(i).endTime;
					listFree.add(j++,sampleObj);
					
				}
				
				
			}
			
			else{
				FreeEvents  sample = new FreeEvents();
				sample.setFreeStartTime(globalStartTime);
				
				
				
				SimpleDateFormat s = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
		        java.util.Date da;
		        String dat = "";
		        java.util.Date tim ;
		        int year;
		        int monthOfYear;
		        int dayOfMonth;
		        /*
		        da = s.parse(globalStartTime.toStringRfc822());
		        s.applyPattern("d");
		        dat = s.format(da);
		        Integer dayInteger = Integer.parseInt(dat);
		        dayOfMonth = (int) dayInteger;
		        
		        s.applyPattern("MM");
		        dat = s.format(da);
		        Integer monthInteger = Integer.parseInt(dat);
		        monthOfYear = (int) monthInteger;
		        
		        s.applyPattern("yyyy");
		        dat = s.format(da);
		        Integer IntegerYear = Integer.parseInt(dat);
		        year = (int) IntegerYear;
		        */
		        
		        da = s.parse(globalStartTime.toStringRfc822());
		        s.applyPattern("EEE, d MMM yyyy");
		        dat = s.format(da);
		     
		       // tim = s.parse("2013-10-20T14:00:00.000-04:00");
		        globalEndTime = DateTime.parseDateTime("2013-10-20T23:00:00-08:00");
				
				//globalEndTime = new DateTime().dateTimeChoicePattern(dat+"T"+tim);

				//globalEndTime = new (com.google.gdata.data.DateTime) DateTime(dayOfMonth, monthOfYear,dayOfMonth, 23, 00);
				System.out.println("globalEndTime");
				sample.setFreeEndTime(globalEndTime);
				
				listFree.add(i, sample);
				
				}
	        
			if(((input1.get(i).getStartTime()).compareTo(globalStartTime) > 0) & (diffMinutes> 30)){
				//System.out.println(" Event "+ j );
				FreeEvents sampleObj = new FreeEvents();
				sampleObj.setFreeStartTime(globalStartTime);
				//System.out.println("Start "+globalStartTime);
				sampleObj.setFreeEndTime(input1.get(i).startTime);
				//System.out.println("End"+input1.get(i).startTime);
				globalStartTime = input1.get(i).endTime;
				listFree.add(j++,sampleObj);
				
			}
			
		}
		
		return listFree;
		// TODO Auto-generated method stub
		
	}
    
  }
