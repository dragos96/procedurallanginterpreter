
public class DisplayResult extends AbstractResult{

	protected StringBuffer text = new StringBuffer();
	protected int level;
	
	public void addString(String str, int level){
		for(int i=0; i<level; i++){
			text.append("\t");
		}
		text.append(str);
		text.append("\n");
	}
	
	public void addString(String str){
		text.append(str);
		text.append("\n");
	}
	
	
	public DisplayResult() {
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
}
