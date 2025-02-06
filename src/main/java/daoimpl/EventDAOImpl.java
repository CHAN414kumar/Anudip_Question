package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EventDAO;
import model.Event;
import utility.ConnectionProvider;

public class EventDAOImpl implements EventDAO{
Connection con=ConnectionProvider.getConnection();

	@Override
	public boolean saveEvent(Event e) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pstm=con.prepareStatement("insert into event values(?,?,?,?,?)");
			pstm.setInt(1,e.getEventId());
			pstm.setString(2,e.getEventName());
			pstm.setString(3,e.getLocation());
			pstm.setString(4, e.getDate());
			pstm.setString(5, e.getTime());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Problem in assigning the event.");
		}
		
		return false;
	}

	@Override
	public Event viewEventById(int id) {
		// TODO Auto-generated method stub
		Event event=new Event();
		try {
			PreparedStatement pstm=con.prepareStatement("Select * from event where eventId=?");
			pstm.setInt(1, id);
			ResultSet rs=pstm.executeQuery();
			boolean res=rs.next();
			if(res==false) {
				System.out.println("No event with this id.");
				return null;
			}else {
				event.setEventId(rs.getInt(1));
				event.setEventName(rs.getString(2));
				event.setLocation(rs.getString(3));
				event.setDate(rs.getString(4));
				event.setTime(rs.getString(5));
				return event;
			}
			
		} catch (SQLException et) {
			// TODO Auto-generated catch block
			et.printStackTrace();
			System.out.println("Something went wrong while retrieving record.");
			
		}
		return null;
	}

	@Override
	public List<Event> viewAllEvents() {
		// TODO Auto-generated method stub
		
		List<Event> elist=new ArrayList<>();
		try {
			PreparedStatement pstm=con.prepareStatement("select * from event");
			ResultSet rs=pstm.executeQuery();
			while(rs.next()) {//retrieving each event according to row and assign to a object of Event class event.
				Event event=new Event();
				event.setEventId(rs.getInt(1));
				event.setEventName(rs.getString(2));
				event.setLocation(rs.getString(3));
				event.setDate(rs.getString(4));
				event.setTime(rs.getString(5));
				elist.add(event); // assign each event data in a list
				if(elist.isEmpty()) {
					System.out.println("No event yet");
					return null;
				}
			}
				return elist;
			}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Something went wrong while retrieving event list");
			return null;
		}
		
		
	}

	@Override
	public boolean updateEvent(Event e) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pstm=con.prepareStatement("update event set eventName=?,Location=?,date=?,time=? where eventid=?");
			pstm.setString(1, e.getEventName());
			pstm.setString(2, e.getLocation());
			pstm.setString(3, e.getDate());
			pstm.setString(4, e.getTime());
			pstm.setInt(5, e.getEventId());
			int i=pstm.executeUpdate();
			if(i>0)
				return true;
			return false;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Something went wrong while updating Event");
			return false;
		}
		
	}

	@Override
	public boolean cancleEvent(int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pstm=con.prepareStatement("delete from event where eventId=? ");
			pstm.setInt(1, id);
			int i=pstm.executeUpdate();
			if(i>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something went wrong while cancleing the event");
			return false;
		}
		
		
	}
	
}
