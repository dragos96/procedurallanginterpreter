

public class AbstractResult {

	private String result = "";

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public void addString(String s){
		result += s;
	}
	
}

