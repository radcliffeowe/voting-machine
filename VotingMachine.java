import java.util.Scanner;

public class VotingMachine {
	
	private ElectionData electionData;
	private Scanner keyboard = new Scanner(System.in);
	
	public VotingMachine(ElectionData E1){
		this.electionData = E1;
	}
	
	/**
	 * 
	 * @throws CandidateExistsException if the user inputs a candidate that is already on the ballot
	 * @throws DuplicateVotesException if the user attempts to vote for the same candidate twice
	 * @throws UnknownCandidateException if the user attempts to vote for a candidate that is not on the ballot
	 */
	public void votingScreen() throws CandidateExistsException, DuplicateVotesException, UnknownCandidateException {
		electionData.printBallot();
		System.out.println("Who is your first choice?");  
		String firstChoice = keyboard.next();   
		System.out.println("Who is your second choice?");  
		String secondChoice = keyboard.next();
		System.out.println("Who is your third choice?");  
		String thirdChoice = keyboard.next();
		
		try {
			electionData.processVote(firstChoice, secondChoice, thirdChoice);
		}
		catch(UnknownCandidateException e) {
			System.out.println(e.getName() + "is not on the ballot. Would you like to write them in? Press Y or y for yes. Otherwise press any key.");
			String input = keyboard.next();
			if(input.equals("Y")|| input.equals("y")) {
				this.addWriteIn(e.getName());
				this.votingScreen();
			}
			else this.votingScreen();
		}
		catch(DuplicateVotesException e) {
			System.out.println("You cannot vote for " + e.getName() + " twice. Please vote for a different candidate.");
			this.votingScreen();
		}
	}
	
	/**
	 * 
	 * @param name The name of the candidate that is being written in
	 * @return One of two messages depending on the outcome of the method
	 * @throws CandidateExistsException If the user attempts to write in a candidate that is already on the ballot
	 */
	public String addWriteIn(String name) throws CandidateExistsException {
		try {
			electionData.addCandidate(name);
		}
		catch(CandidateExistsException e) {
			return "Candidate already exists.";
		}
		return "Candidate added succesfully.";
	}

}
