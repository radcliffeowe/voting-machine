
public class DuplicateVotesException extends Exception {
	
	private String duplicateName;

	public DuplicateVotesException(String duplicateName){
		this.duplicateName = duplicateName;
	}
	
	/**
	 * 
	 * @return the string of the name of the candidate that was duplicated in the exception
	 */
	public String getName() {
		return this.duplicateName;
	}
}
