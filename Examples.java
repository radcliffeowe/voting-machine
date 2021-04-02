import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Examples {

	ElectionData Setup1() {

		ElectionData ED = new ElectionData();

		try {
			ED.addCandidate("Gompei");
			ED.addCandidate("Husky");
			ED.addCandidate("Ziggy");
		}
		catch(CandidateExistsException e) {}
		
		try {
			ED.processVote("Gompei", "Husky", "Ziggy");
			ED.processVote("Gompei", "Ziggy", "Husky");
			ED.processVote("Husky", "Gompei", "Ziggy");
		}
		catch(DuplicateVotesException e) {}
		catch(UnknownCandidateException e) {};
		
		return ED;
	}
	
	ElectionData Setup2() {

		ElectionData ED = new ElectionData();

		try {
			ED.addCandidate("Gompei");
			ED.addCandidate("Husky");
			ED.addCandidate("Ziggy");
		}
		catch(CandidateExistsException e) {}
		
		try {
			ED.processVote("Gompei", "Husky", "Ziggy");
			ED.processVote("Husky", "Gompei", "Ziggy");
		}
		catch(DuplicateVotesException e) {}
		catch(UnknownCandidateException e) {};
		
		return ED;
	}
	
	@Test
	public void testMostFirstWinner() {
		assertEquals("Gompei", Setup1().findWinnerMostFirstVotes());
	}
	
	@Test
	public void testMostPointsWinner() {
		assertEquals("Gompei", Setup1().findWinnerMostPoints());
	}
	
	@Test
	public void testMostPointsTie() {
		assertTrue(Setup2().findWinnerMostPoints().equals("Gompei")||
				   Setup2().findWinnerMostPoints().equals("Husky"));
	}
	
	@Test
	public void testMostFirstRunoffRequired() {
		assertEquals("Runoff required", Setup2().findWinnerMostFirstVotes());
	}
	
	@Test (expected= UnknownCandidateException.class)
	public void testProcessVoteFirstNotFound() throws UnknownCandidateException, DuplicateVotesException {
		Setup1().processVote("Obamna", "Gompei", "Husky");
	}
	
	@Test (expected= UnknownCandidateException.class)
	public void testProcessVoteSecondNotFound() throws UnknownCandidateException, DuplicateVotesException {
		Setup1().processVote("Gompei", "Obamna", "Husky");
	}
	
	@Test (expected= UnknownCandidateException.class)
	public void testProcessVoteThirdNotFound() throws UnknownCandidateException, DuplicateVotesException {
		Setup1().processVote("Gompei", "Husky", "Obamna");
	}
	
	@Test (expected= DuplicateVotesException.class)
	public void testProcessVoteFirstDuplicate() throws UnknownCandidateException, DuplicateVotesException {
		Setup1().processVote("Gompei", "Gompei", "Husky");
	}
	
	@Test (expected= DuplicateVotesException.class)
	public void testProcessVoteSecondDuplicate() throws UnknownCandidateException, DuplicateVotesException {
		Setup1().processVote("Husky", "Gompei", "Gompei");
	}
	
	@Test (expected= DuplicateVotesException.class)
	public void testProcessVoteThirdDuplicate() throws UnknownCandidateException, DuplicateVotesException {
		Setup1().processVote("Husky", "Gompei", "Husky");
	}
	
	@Test (expected= CandidateExistsException.class)
	public void testaddCandidateFirstDuplicate() throws CandidateExistsException {
		Setup1().addCandidate("Gompei");
	}
	
	@Test (expected= CandidateExistsException.class)
	public void testaddCandidateSecondDuplicate() throws CandidateExistsException {
		Setup1().addCandidate("Husky");
	}
	
	@Test (expected= CandidateExistsException.class)
	public void testaddCandidateThirdDuplicate() throws CandidateExistsException {
		Setup1().addCandidate("Ziggy");
	}
}
