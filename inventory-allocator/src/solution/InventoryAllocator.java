package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Assumption:
 * Since the cost of each warehouse is not clearly mentioned. I am assume this problem can be solved using greedy algorithm.
 * However, if the cost is take into consideration, which means each warehouse has a specific cost, I will use tree to solve this problem.
 * In the constructor of inventoryAllocator class, construction n-ary tree use the warehouse list, where n is the size of warehouse list.
 * For example, if there are 3 warehouse, the first level of tree will be w1, w2, w3. The child of w1 will be w2, w3 and similar for w2, w3.
 * In this way, I can use DFS to search the minimum cost.
*/
public class InventoryAllocator {
	List<WareHouse> warehouse;
	Order inputOrder;
	
	public InventoryAllocator(List<WareHouse> warehouse, Order newOrder) {
		this.warehouse = new ArrayList<WareHouse>(warehouse);
		this.inputOrder = newOrder;
	}

	/**
	 * The function loop through the List of WareHouse and take as much as needed in each WareHouse. Return when all the item is been cleared.
	 * Assume that: If the I have went through the WareHouse List and the total number of item is not 0, it means the requirement is not fulfilled, function should return an empty list
	 */
	public List<WareHouse> allocate(){
		int totalItem = inputOrder.totalAmount;
		List<WareHouse> result = new ArrayList<>();
		HashMap<Items, Integer> od = inputOrder.order;
		for(WareHouse wh : warehouse) {
			if(totalItem == 0) break;
			HashMap<Items, Integer> resultMap = new HashMap<>();	
			HashMap<Items, Integer> mp = wh.getInventory();
			for(Items inv : mp.keySet()) {
				if(od.get(inv) != null && od.get(inv) > 0) {
					if(od.get(inv) > mp.get(inv)) {
						resultMap.put(inv, mp.get(inv));
						totalItem -= mp.get(inv);
						od.put(inv, od.get(inv) - mp.get(inv));
					}
					else {
						resultMap.put(inv, od.get(inv));
						totalItem -= od.get(inv);
						od.put(inv, 0);
					}
				}
			}
			if(resultMap.size() != 0) {
				WareHouse currentHouse = new WareHouse(wh.name, -1, resultMap);
				result.add(currentHouse);	
			}
		}
		if(totalItem != 0) return new ArrayList<WareHouse>();
		return result;
	}
	
}
