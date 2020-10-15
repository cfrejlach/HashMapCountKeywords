package chris;

public class Keys {

	private String keyword = null;
	private int value = 0;
	
	public Keys(String keyword, int value){
		this.keyword = keyword;
		this.value = value;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	
}
