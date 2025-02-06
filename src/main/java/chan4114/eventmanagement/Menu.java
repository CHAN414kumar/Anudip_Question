package chan4114.eventmanagement;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

import daoimpl.EventDAOImpl;
import daoimpl.ParticipantDAOImpl;
import daoimpl.ReviewDAOImpl;
import model.Event;
import model.Participant;
import model.Review;
import dao.EventDAO;

public class Menu {
	void displayMainMenu() throws IOException {
		Scanner sc=new Scanner(System.in);
		System.out.println("Main Menu:");
		System.out.println("1. Event");
		System.out.println("2. Participant");
		System.out.println("3. Review");
		
		System.out.println("Please Enter your Choice:");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:
			displayEventMenu();
			break;
		case 2:
			displayParticipantMenu();
			break;
		case 3:
			displayReviewMenu();
			break;
		default:
			System.out.println("Please enter a valid choice:");
			System.out.println("Please enter your Choice:");
			choice=sc.nextInt();
		}
	}
	
	private void displayEventMenu() throws IOException {
		// TODO Auto-generated method stub
		
		EventDAO eventImp=new EventDAOImpl();
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		char choice;
		do {
			System.out.println("Event Menu:");
			System.out.println("1. Add Event:");
			System.out.println("2. View Specific Event :");
			System.out.println("3. View All Event:");
			System.out.println("4. Update Event");
			System.out.println("5. Cancel Event");
			System.out.println("6. Return to Main Menu:");
			
			System.out.println("Please Enter your choice:");
			
			int ch=Integer.parseInt(br.readLine());
			
			switch(ch) {
			case 1:{
				System.out.println("Enter event id:");
				int eventId=Integer.parseInt(br.readLine());
				System.out.println("Enter event name:");
				String eventName=br.readLine();
				System.out.println("Enter event location:");
				String location=br.readLine();
				System.out.println("Enter event date:");
				String date=br.readLine();
				System.out.println("Enter event time:");
				String time=br.readLine();
				
				Event event=new Event(eventId,eventName,location,date,time);
				boolean res=eventImp.saveEvent(event);
				if(res) {
					System.out.println("Event added successfully...");
					
				}else {
					System.out.println("Something wents wrong..");
				}
				break;
			}
			case 2:{
				System.out.println("Enter the event id:");
				int id=Integer.parseInt(br.readLine());
				Event event=eventImp.viewEventById(id);
				if(event==null) {
					System.out.println("No such Event with this id exist:");
					break;
				}else {
					System.out.println(event);
					break;
				}
				}
			case 3:{
				List<Event> eventlist=eventImp.viewAllEvents();
				if(eventlist==null) {
					System.out.println("There is no event for now..");
					break;
				}else {
					for(Event e:eventlist) {
						System.out.println(e);
					}
				}
				break;
			}
			case 4:{
				System.out.println("Enter event id:");
				int eventId=Integer.parseInt(br.readLine());
				Event event=eventImp.viewEventById(eventId);
				if(event==null) {
					System.out.println("Event with this id not exist..");
					break;
				}
				System.out.println("Enter event name:");
				String eventName=br.readLine();
				System.out.println("Enter event location:");
				String location=br.readLine();
				System.out.println("Enter event date:");
				String date=br.readLine();
				System.out.println("Enter event time:");
				String time=br.readLine();
				
				Event events=new Event(eventId,eventName,location,date,time);
				
				boolean res=eventImp.updateEvent(events);
				
				if(res) {
					System.out.println("Event get updated.");
					break;
				}else {
					System.out.println("Something went wrong in updation.");
					break;
				}
				
			}
			case 5:{
				System.out.println("Enter eventId:");
				int eventId=Integer.parseInt(br.readLine());
				Event event=eventImp.viewEventById(eventId);
				if(event==null) {
					System.out.println("Event not exist.");
					break;
				}else {
					boolean res=eventImp.cancleEvent(eventId);
					if(res) {
						System.out.println("Event cancelled successfully..");
						break;
					}else {
						System.out.println("Their is a problem in cancelling the event..");
						break;
					}
				}
			}
			case 6:{
				displayMainMenu();
				break;
			}
			default:{
				System.out.println("Please enter a valid choice.");
				System.out.println("Please enter your choice:");
				ch=Integer.parseInt(br.readLine());
			}
				
			}
			System.out.println("Do you want to continue y/n");
			choice=br.readLine().charAt(0);
			
		}while(choice=='y'||choice=='Y');
	}
	
	private void displayParticipantMenu() throws IOException {
		// TODO Auto-generated method stub
		ParticipantDAOImpl pImp=new ParticipantDAOImpl();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		char choice;
		do {
			System.out.println("Participant Menu:");
			System.out.println("1.Add Participant");
			System.out.println("2.View Participant By ParticipantId");
			System.out.println("3.View Participants By EventId");
			System.out.println("4.View All Participant");
			System.out.println("5.Update Participant");
			System.out.println("6.Remove Participant");
			System.out.println("7.Return to Main Menu");
			
			System.out.println("Please Enter Your Choice:");
			int ch=Integer.parseInt(br.readLine());
			switch(ch) {
			case 1:{
				System.out.println("Enter Event id:");
				int eventId=Integer.parseInt(br.readLine());
				
				System.out.println("Enter ParticipantName:");
				String ParticipantName=br.readLine();
				System.out.println("Enter ParticipantId:");
				int ParticipantId=Integer.parseInt(br.readLine());
				
				Participant p=new Participant(eventId,ParticipantName,ParticipantId);
				
				boolean res=pImp.addParticipant(p);
				if(res) {
					System.out.println("Participant Added successfully..");
				}else {
					System.out.println("Not able to Add participant..");
				}
				break;
			}
			case 2:{
				System.out.println("Enter Participant Id:");
				int id=Integer.parseInt(br.readLine());
				Participant p=pImp.viewParticipantDetailByParticipantId(id);
				if(p==null) {
					System.out.println("No such Participant with this id:");
				}else {
					System.out.println(p);
				}
				break;
			}
			case 3:{
				System.out.println("Enter the EventId:");
				int id=Integer.parseInt(br.readLine());
				List<Participant> plist=pImp.viewParticipantByEventId(id);
				if(plist==null) {
					System.out.println("No such Event.");
				}else {
					for(Participant p:plist) {
						System.out.println(p);
					}
				}
				break;
			}
			case 4:{
				List<Participant> plist=pImp.viewAllParticipants();
				if(plist==null) {
					System.out.println("No Participant Yet.");
				}else {
					for(Participant p:plist) {
						System.out.println(p);
					}
				}
				break;
			}
			case 5:{
				System.out.println("Enter ParticipantId:");
				int id=Integer.parseInt(br.readLine());
				Participant p=pImp.viewParticipantDetailByParticipantId(id);
				if(p==null) {
					System.out.println("No such Participant exist.");
					break;
				}
				System.out.println("Enter EventId:");
				int eventId=Integer.parseInt(br.readLine());
				System.out.println("Enter ParticipantName:");
				String participantName=br.readLine();
				System.out.println("Enter ParticipantId:");
				int participantId=Integer.parseInt(br.readLine());
				
				Participant pts=new Participant(eventId,participantName,participantId);
				boolean res=pImp.updateParticipant(pts);
				if(res) {
					System.out.println("Update successfully.");
					
				}else {
					System.out.println("Something went wrong.");
				}
				break;
			}
			case 6:{
				System.out.println("Enter Participant id:");
				int id=Integer.parseInt(br.readLine());
				boolean res=pImp.removeParticipant(id);
				if(res) {
					System.out.println("Removed successfully.");
				}else {
					System.out.println("Something wents wrong.");
				}
				break;
			}
			case 7:{
				displayMainMenu();
				break;
			}
			default:{
				System.out.println("Please enter a valid choice.");
				System.out.println("Please enter your choice:");
				ch=Integer.parseInt(br.readLine());
			}
			
			}
			System.out.println("Do you want to continue y/n");
			choice=br.readLine().charAt(0);
	}while(choice=='Y'||choice=='y');
	}

	private void displayReviewMenu() throws IOException {
		ReviewDAOImpl rImpl=new ReviewDAOImpl();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("All events:");
		EventDAOImpl events=new EventDAOImpl();
		List<Event> allEvents=events.viewAllEvents();
		for(Event e:allEvents) {
			System.out.println(e);
			System.out.println();
			
		}
		char choice;
		do {
			System.out.println("Review menu:");
			System.out.println("1.Add Review:");
			System.out.println("2.View Review of event by id:");
			System.out.println("3.View Review of all Event:");
			System.out.println("4.Return to main menu:");
			
			int ch=Integer.parseInt(br.readLine());
			switch(ch) {
			case 1:{
			
				System.out.println("Enter eventId:");
				int eventId=Integer.parseInt(br.readLine());
				
				EventDAOImpl e=new EventDAOImpl();
				Event ev=e.viewEventById(eventId);
				if(ev==null) {
					System.out.println("Event id is not present.");
					break;
				}
				System.out.println("Enter review out of 5");
				int asgn_review=Integer.parseInt(br.readLine());
				
				Review rev=new Review(eventId,asgn_review);
				boolean res=rImpl.addReview(rev);
				if(res) {
					System.out.println("Review added successfully..");
				}else {
					System.out.println("Something went in adding Review..");
				}
				break;
			}
			case 2:{
				System.out.println("Enter eventId to view Review:");
				int id=Integer.parseInt(br.readLine());
				Review rs=rImpl.viewReviewByEventId(id);
				if(rs==null) {
					System.out.println("No such event exist.");
				}else {
					System.out.println(rs);
				}
				break;
			}
			case 3:{
				List<Review> rev=rImpl.viewAllEventsReview();
				if(rev==null) {
					System.out.println("NO events review yet done.");
				}else {
					for(Review r:rev) {
						System.out.println(r);
					}
				}
				break;
			}
			case 4:{
				displayMainMenu();
				break;
			}
			 default:{
				System.out.println("Please enter the valid choice.");
				displayReviewMenu();
			
			}
				
			}
			System.out.println("Want to continue(y/n)");
			choice=br.readLine().charAt(0);
			
		}while(choice=='Y'||choice=='y');
		
	}
	

	

	
	
}
