public class Candidate {
	
	private String name;
	private int firstChoice;
	private int secondChoice;
	private int thirdChoice;
	
	public Candidate(String name) {
		this.name = name;
		this.firstChoice = 0;
		this.secondChoice = 0;
		this.thirdChoice = 0;
	}
	
	/**
	 * @return void but firstChoice is incremented
	 */
	public void addFirst() {
		this.firstChoice = this.firstChoice+ 1;
	}
	
	/**
	 * @return void but secondChoice is incremented
	 */
	public void addSecond() {
		this.secondChoice = this.secondChoice+ 1;
	}
	
	/**
	 * @return void but thirdChoice is incremented
	 */
	public void addThird() {
		this.thirdChoice = this.thirdChoice+ 1;
	}
	
	/**
	 * 
	 * @return the name of the candidate
	 */
	public String printName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return the value of the amount of firstChoice votes
	 */
	public int countFirst() {
		return this.firstChoice;
	}
	
	/**
	 * 
	 * @param opponentVotes is the amount of votes the opponent has to compare to this candidate
	 * @return true if this candidate has more votes than the opponent, false if this candidate does not
	 */
	public boolean hasMoreVotes(int opponentVotes) {
		if(this.firstChoice > opponentVotes) {
			return true;
		}
		else return false;
	}
	
	/**
	 * 
	 * @return the value of the amount of secondChoice votes
	 */
	public int countSecond() {
		return this.secondChoice;
	}
	
	/**
	 * 
	 * @return the value of the amount of thirdChoice votes
	 */
	public int countThird() {
		return this.thirdChoice;
	}
	
	/**
	 * 
	 * @param totalVotes is the total amount of votes in the election
	 * @return true if the first choice candidate has more than 50% of the total votes
	 */
	public boolean isWinner(int totalVotes) {
		if (this.firstChoice > (totalVotes/2)) {
			return true;
		}
		else return false;
	}
	
	/**
	 * 
	 * @return the total number of points given in the entire election
	 */
	public int tallyPoints() {
		return (this.firstChoice*3) + (this.secondChoice*2) + (this.thirdChoice);
	}
}