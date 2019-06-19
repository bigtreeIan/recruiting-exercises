package solution;

import java.util.HashMap;

/**
 * The WareHouse class has three member variables, cost, name and inventory. 
 * cost is a double that indicates how much would it cost if shipping from this warehouse
 * name is the name of this warehouse
 * inventory indicates the item stored in this warehouse
 */
public class WareHouse {
	String name;
	double cost;
	HashMap<Items, Integer> inventory;
	//constructors
	public WareHouse() {}
	
	public WareHouse(String name, double cost, HashMap<Items, Integer> inventory) {
		this.name = name;
		this.cost = cost;
		this.inventory = new HashMap<Items, Integer>(inventory);
	}
	//member functions
	public String getWareHouseName() {
		return name;
	}
	
	public double getWareHouseCost() {
		return cost;
	}

	public void setWareHouseName(String newName) {
		this.name = newName;
	}

	public HashMap<Items, Integer> getInventory() {
		return inventory;
	}

	public void setInventory(HashMap<Items, Integer> newInventory) {
		this.inventory = new HashMap<Items, Integer>(newInventory);
	}
}
