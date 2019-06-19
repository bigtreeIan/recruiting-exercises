package solution;

import java.util.HashMap;

/**
 * The Order class has two member variable, order and totalAmount
 * order indicates which item is needed for which amount.
 * totalAmount is the number of items in this order.
 */
public class Order {
	HashMap<Items, Integer> order;
	int totalAmount;
	//constructors:
	public Order() {
		order = new HashMap<Items, Integer>();
		totalAmount = 0;
	}
	
	public Order(HashMap<Items, Integer> existOrder) {
		order = new HashMap<Items, Integer>(existOrder);
		for(Items inv : order.keySet()) {
			totalAmount += order.get(inv);
		}
	}
	//member functions:
	public HashMap<Items, Integer> getOrder(){
		return order;
	}
	
	public int getOrderSize() {
		return order.size();
	}
	
	public int getInventoryAmount(Items type) {
		return order.getOrDefault(type, -1);
	}
	
	public void addtoOrder(Items type, int amount) {
		order.put(type, order.getOrDefault(type, 0) + amount);
	}
	
}
