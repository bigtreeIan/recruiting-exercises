package solution;

/**
 * The Items class has one variable, type
 * type is the name is this Items.
 */
public class Items {
	String type;
	
	public Items(String type) {
		this.type = type;
	}

	public String getInventoryType() {
		return type;
	}

	public void setInventoryType(String newType) {
		this.type = newType;
	}
}
