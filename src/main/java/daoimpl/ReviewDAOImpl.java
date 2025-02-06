package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ReviewDAO;
import model.Review;
import utility.ConnectionProvider;

public class ReviewDAOImpl implements ReviewDAO {
	Connection con=ConnectionProvider.getConnection();
	@Override
	public boolean addReview(Review rev) {
		try {
			PreparedStatement pstm=con.prepareStatement("insert into review  (?,?)");
			pstm.setInt(1,rev.getEventId());
			pstm.setInt(2, rev.getAsgn_review());
			
			pstm.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Review viewReviewByEventId(int id) {
		Review rev=new Review();
		try {
			PreparedStatement pstm=con.prepareStatement("Select * from review where eventId=?");
			pstm.setInt(1, id);
			
			ResultSet rs=pstm.executeQuery();
			boolean res=rs.next();
			if(res==false) {
				return null;
			}else {
				rev.setEventId(rs.getInt(1));
				rev.setAsgn_review(rs.getInt(2));
				
			}
			return rev;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Review> viewAllEventsReview() {
		 List<Review> allReview =new ArrayList<>();
		try {
			PreparedStatement pstm = con.prepareStatement("Select * from review");
			ResultSet rs=pstm.executeQuery();
			while(rs.next()) {
				Review r=new Review();
				r.setEventId(rs.getInt(1));
				r.setAsgn_review(rs.getInt(2));
				allReview.add(r);
			}
			return allReview;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
