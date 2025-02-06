package dao;

import java.util.List;

import model.Participant;

public interface ParticipantDAO {
	
	boolean addParticipant(Participant pct);
	Participant viewParticipantDetailByParticipantId(int id);
	List<Participant> viewParticipantByEventId(int id);
	List<Participant> viewAllParticipants();
	boolean updateParticipant(Participant pct);
	boolean removeParticipant(int id);
}
