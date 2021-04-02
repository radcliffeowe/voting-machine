
import java.util.Scanner;
import java.util.HashMap;

public class ElectionData {
	
	private HashMap<String, Candidate> candidates =
			new HashMap<String, Candidate>();
	Scanner keyboard = new Scanner(System.in);
	
	ElectionData() {}
	
	/**
	 * @return void but the candidates are printed out on the console
	 */
	public void printBallot() {
		System.out.println("The candidates are ");
		for (String s : candidates.keySet()) {
			System.out.println(s);
		}
	}
	/**
	 * 
	 * @param firstChoice is the voter's first choice candidate
	 * @param secondChoice is the voter's second choice candidate
	 * @param thirdChoice is the voter's third choice candidate
	 * @throws DuplicateVotesException is the exception held if the voter puts the same candidate name in the voting process more than once
	 * @throws UnknownCandidateException is the exception held if the voter writes a candidate name that is not a candidate
	 * @return void but stores a single voter's choices in the data structure
	 */
	public void processVote(String firstChoice, String secondChoice, String thirdChoice)
			throws DuplicateVotesException, UnknownCandidateException{
		if(!this.candidates.containsKey(firstChoice)) {
			throw new UnknownCandidateException(firstChoice);
		}
		if(!this.candidates.containsKey(secondChoice)) {
			throw new UnknownCandidateException(secondChoice);
		}
		if(!this.candidates.containsKey(thirdChoice)) {
			throw new UnknownCandidateException(thirdChoice);
		}
		if(firstChoice.equals(secondChoice)) {
			throw new DuplicateVotesException(firstChoice);
		}
		if(secondChoice.equals(thirdChoice)) {
			throw new DuplicateVotesException(secondChoice);
		}
		if(thirdChoice.equals(firstChoice)) {
			throw new DuplicateVotesException(thirdChoice);
		}
		else {
			candidates.get(firstChoice).addFirst();
			candidates.get(secondChoice).addSecond();
			candidates.get(thirdChoice).addThird();
		}
	}
	
	/**
	 * 
	 * @param name is the name of the candidate that is checked to see if it already exists
	 * @return false if the name parameter does not have the same name as this candidate
	 * @throws CandidateExistsException is the exception caught when a candidate already exists when compared or checked to another candidate 
	 */
	public boolean checkCandidate(String name) throws CandidateExistsException{
		if(this.candidates.containsKey(name)) {
			throw new CandidateExistsException(name);
		}
		else return false;
	}
	
	/**
	 * 
	 * @param name the name of the candidate checked to make sure it does not already exist
	 * @throws CandidateExistsException is the exception held when trying to create a new candidate but the candidate trying to be created already exists
	 */
	public void addCandidate(String name) throws CandidateExistsException{
		try {
			this.checkCandidate(name);
		}
		catch(CandidateExistsException e){
			throw new CandidateExistsException(name);
		}
		this.candidates.put(name, new Candidate(name));

	}
	
	/**
	 * 
	 * @return the winner is the name of the candidate with more than 50% of first place votes, else return return the string "Runoff required"
	 */
	public String findWinnerMostFirstVotes() {
		Candidate currentWinner = new Candidate("");
		int currentLeader = 0;
		int totalVotes = 0;
		for(Candidate candidate : candidates.values()) {
			totalVotes = totalVotes + candidate.countFirst();
			if(candidate.hasMoreVotes(currentLeader)) {
				currentLeader = candidate.countFirst();
				currentWinner = candidate;
			}
		}
		if(currentWinner.isWinner(totalVotes)) {
			return currentWinner.printName();	
		}
		else return "Runoff required";
	}
	
	/**
	 * 
	 * @return the name of the candidate that has the highest number of tallied votes, announcing them the winner
	 */
	public String findWinnerMostPoints() {
		String currentWinner = "";
		int currentLeader = 0;
		for(Candidate candidate : candidates.values()) {
			if(candidate.tallyPoints()> currentLeader) {
				currentWinner = candidate.printName();
				currentLeader = candidate.tallyPoints();
			}
		}
		return currentWinner;
	}
	
	
}