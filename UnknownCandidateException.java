public class UnknownCandidateException extends Exception {
	
	private String unknownName;
	
	public UnknownCandidateException(String unknownName){
		this.unknownName = unknownName;
	}
	
	/**
	 * 
	 * @return the string of the name of the unknown candidate that was caught in the exception 
	 */
	public String getName() {
		return this.unknownName;
	}
}