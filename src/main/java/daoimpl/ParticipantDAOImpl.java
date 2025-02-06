package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ParticipantDAO;
import model.Participant;
import utility.ConnectionProvider;


public class ParticipantDAOImpl implements ParticipantDAO{
	Connection con=ConnectionProvider.getConnection();
	@Override
	public boolean addParticipant(Participant pct) {
		try {
			PreparedStatement pstm=con.prepareStatement("insert into participant  values(?,?,?)");
			pstm.setInt(1,pct.getEventId());
			pstm.setString(2, pct.getParticipantName());
			pstm.setInt(3, pct.getParticipantId());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problem is in adding participant");
		
		return false;
		}
	}

	@Override
	public Participant viewParticipantDetailByParticipantId(int id) {
		Participant p=new Participant();
		try {
			PreparedStatement pstm=con.prepareStatement("Select * from participant where participantId=?");
			pstm.setInt(1, id);
			ResultSet rs=pstm.executeQuery();
			boolean res=rs.next();
			if(res==false) {
				System.out.println("No such Paricipant exist.");
				return null;
			}else {
				p.setEventId(rs.getInt(1));
				p.setParticipantName(rs.getString(2));
				p.setParticipantId(rs.getInt(3));
				return p;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("something went wrong in retriving Participant detail.");
			return null;
		}
		
	}

	@Override
	public List<Participant> viewParticipantByEventId(int id) {
		List<Participant> elist=new ArrayList<>();
		try {
			PreparedStatement pstm=con.prepareStatement("Select * from participant where eventId=?");
			pstm.setInt(1, id);
			ResultSet rs=pstm.executeQuery();
			while(rs.next()) {
				Participant p=new Participant();
				p.setEventId(rs.getInt(1));
				p.setParticipantName(rs.getString(2));
				p.setParticipantId(rs.getInt(3));
				elist.add(p);
				if(elist.isEmpty()) {
					System.out.println("No participant in this event.");
					return null;
				}
			}
			return elist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Participant> viewAllParticipants() {
		List<Participant> plist=new ArrayList<>();
		try {
			PreparedStatement pstm=con.prepareStatement("Select * from participant");
			ResultSet rs=pstm.executeQuery();
			while(rs.next()) {
				Participant p=new Participant();
				p.setEventId(rs.getInt(1));
				p.setParticipantName(rs.getString(2));
				p.setParticipantId(rs.getInt(3));
				plist.add(p);
				if(plist.isEmpty()) {
					System.out.println("No Participant.");
					return null;
				}
			}
			return plist;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateParticipant(Participant pct) {
		try {
			PreparedStatement pstm=con.prepareStatement("update participant set eventId=?,eventName=?,participantName=? where participantId=?");
			pstm.setInt(1, pct.getEventId());
			pstm.setString(2, pct.getParticipantName());
			pstm.setInt(3, pct.getParticipantId());
			int i=pstm.executeUpdate();
			if(i>0) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeParticipant(int id) {
		try {
			PreparedStatement pstm=con.prepareStatement("delete from participant where participantId=?");
			pstm.setInt(1, id);
			int i=pstm.executeUpdate();
			if(i>0)
				return true;
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
