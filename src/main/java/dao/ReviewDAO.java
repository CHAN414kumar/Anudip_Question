package dao;

import java.util.List;

import model.Review;

public interface ReviewDAO {
	boolean addReview(Review rev);
	Review viewReviewByEventId(int id);
	List<Review> viewAllEventsReview();
}
