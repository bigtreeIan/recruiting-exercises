package solution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AllocateTest {
	Items apple;
	Items banana;
	Items orange;
	Items grape;
	Items pineapple;
	

	@BeforeEach
	void setUp() throws Exception {
		//Items for test
		apple = new Items("apple");
		banana = new Items("banana");
		orange = new Items("orange");
		grape = new Items("grape");
		pineapple = new Items("pineapple");
	}

	/**
	 * Test#1
	 * Test the case when there is only one item in the order and there is enough item in the warehouses.
	 * @input1 {apple : 5}
	 * @input2 [name: w1, {apple : 5}]
	 * @output [name: w1, {apple : 5}]
	 */
	@Test
	void testOneItemMatch() {
		//order
		HashMap<Items, Integer> mp1 = new HashMap<>();
		mp1.put(apple, 5);
		Order order1 = new Order(mp1);
		//WareHouse for test
		HashMap<Items, Integer> enoughApple = new HashMap<>();
		enoughApple.put(apple, 5);
		WareHouse warehouse1 = new WareHouse("w1", -1, enoughApple);
		//ItemsAllocator
		List<WareHouse> list1 = Arrays.asList(warehouse1);
		InventoryAllocator input1 = new InventoryAllocator(list1, order1);
		List<WareHouse> ans1 = input1.allocate();
		assertTrue("w1".equals(ans1.get(0).name));
		assertTrue(1 == ans1.get(0).inventory.size());
		assertTrue(5 == ans1.get(0).inventory.get(apple));
	}
	
	/**
	 * Test#2
	 * Test the case when there is only one item in the order and there is NOT enough item in the warehouses.
	 * @input1 {apple : 5}
	 * @input2 [name: w1, {banana : 5}]
	 * @output []
	 */
	@Test
	void testOneItemNotMatch() {
		HashMap<Items, Integer> mp2 = new HashMap<>();
		mp2.put(apple, 5);
		Order order2 = new Order(mp2);
		//WareHouse for test
		HashMap<Items, Integer> notEnoughApple = new HashMap<>();
		notEnoughApple.put(banana, 5);
		WareHouse warehouse2 = new WareHouse("w2", -1, notEnoughApple);
		//ItemsAllocator
		List<WareHouse> list2 = Arrays.asList(warehouse2);
		InventoryAllocator input2 = new InventoryAllocator(list2, order2);
		List<WareHouse> ans2 = input2.allocate();
		assertTrue(0 == ans2.size());		
	}
	
	/**
	 * Test#3
	 * Test the case when there is two items in the order and there is enough item in the warehouses.
	 * @input1 {apple : 15}
	 * @input2 [{name: w1, {apple : 5}}, {name: w2, {apple : 10}}]
	 * @output [{name: w1, {apple : 5}}, {name: w2, {apple : 10}}]
	 */
	@Test
	void testTwoWareHouseMatch() {
		HashMap<Items, Integer> mp3 = new HashMap<>();
		mp3.put(apple, 15);
		Order order3 = new Order(mp3);
		//WareHouse for test
		HashMap<Items, Integer> splitApple1 = new HashMap<>();
		splitApple1.put(apple, 5);
		HashMap<Items, Integer> splitApple2 = new HashMap<>();
		splitApple2.put(apple, 10);
		WareHouse warehouse1 = new WareHouse("w1", -1, splitApple1);
		WareHouse warehouse2 = new WareHouse("w2", -1, splitApple2);
		//ItemsAllocator
		List<WareHouse> list3 = Arrays.asList(warehouse1, warehouse2);
		InventoryAllocator input3 = new InventoryAllocator(list3, order3);
		List<WareHouse> ans3 = input3.allocate();
		assertTrue(2 == ans3.size());	
		assertTrue("w1".equals(ans3.get(0).name));
		assertTrue(1 == ans3.get(0).inventory.size());
		assertTrue(5 == ans3.get(0).inventory.get(apple));
		assertTrue("w2".equals(ans3.get(1).name));
		assertTrue(1 == ans3.get(1).inventory.size());
		assertTrue(10 == ans3.get(1).inventory.get(apple));
	}
	
	/**
	 * Test#4
	 * Test the case when there is two items in the order and there is enough item in the warehouses.
	 * @input1 {apple : 5, banana : 5}
	 * @input2 [{name: w1, {apple : 10}}, {name: w2, {banana : 10}}]
	 * @output [{name: w1, {apple : 5}}, {name: w2, {banana : 5}}]
	 */
	@Test
	void testTwoItemMatch() {
		HashMap<Items, Integer> mp4 = new HashMap<>();
		mp4.put(apple, 5);
		mp4.put(banana, 5);
		Order order4 = new Order(mp4);
		//WareHouse for test
		HashMap<Items, Integer> twoItem1 = new HashMap<>();
		twoItem1.put(apple, 10);
		HashMap<Items, Integer> twoItem2 = new HashMap<>();
		twoItem2.put(banana, 10);
		WareHouse warehouse1 = new WareHouse("w1", -1, twoItem1);
		WareHouse warehouse2 = new WareHouse("w2", -1, twoItem2);
		//ItemsAllocator
		List<WareHouse> list4 = Arrays.asList(warehouse1, warehouse2);
		InventoryAllocator input4 = new InventoryAllocator(list4, order4);
		List<WareHouse> ans4 = input4.allocate();
		assertTrue(2 == ans4.size());	
		
		assertTrue("w1".equals(ans4.get(0).name));
		assertTrue(5 == ans4.get(0).inventory.get(apple));
		
		assertTrue("w2".equals(ans4.get(1).name));
		assertTrue(5 == ans4.get(1).inventory.get(banana));
	}
	
	/**
	 * Test#5
	 * Test the case when there is two items in the order and there is NOT enough item in the warehouses.
	 * @input1 {apple : 5, banana : 5}
	 * @input2 [{name: w1, {apple : 10}}, {name: w2, {orange : 10}}]
	 * @output []
	 */
	@Test
	void testTwoItemNotMatch() {
		HashMap<Items, Integer> mp5 = new HashMap<>();
		mp5.put(apple, 5);
		mp5.put(banana, 5);
		Order order5 = new Order(mp5);
		//WareHouse for test
		HashMap<Items, Integer> notTwoItem1 = new HashMap<>();
		notTwoItem1.put(apple, 10);
		HashMap<Items, Integer> notTwoItem2 = new HashMap<>();
		notTwoItem2.put(orange, 10);
		WareHouse warehouse1 = new WareHouse("w1", -1, notTwoItem1);
		WareHouse warehouse2 = new WareHouse("w2", -1, notTwoItem2);
		//ItemsAllocator
		List<WareHouse> list5 = Arrays.asList(warehouse1, warehouse2);
		InventoryAllocator input5 = new InventoryAllocator(list5, order5);
		List<WareHouse> ans5 = input5.allocate();
		assertTrue(0 == ans5.size());		
	}
	
	/**
	 * Test#6
	 * Test the case when there is two items in the order and there is enough item in the warehouses.
	 * @input1 {apple : 5, banana : 5, orange : 5}
	 * @input2 [{name: w1, {apple : 10}}, {name: w2, {banana : 10}}, {name: w3, {orange : 10}}]
	 * @output [{name: w1, {apple : 5}}, {name: w2, {banana : 5}}, {name: w3, {orange : 5}}]
	 */
	@Test
	void testThreeItemMatch() {
		HashMap<Items, Integer> mp6 = new HashMap<>();
		mp6.put(apple, 5);
		mp6.put(banana, 5);
		mp6.put(orange, 5);
		Order order6 = new Order(mp6);
		//WareHouse for test
		HashMap<Items, Integer> ThreeItem1 = new HashMap<>();
		ThreeItem1.put(apple, 10);
		HashMap<Items, Integer> ThreeItem2 = new HashMap<>();
		ThreeItem2.put(banana, 10);
		HashMap<Items, Integer> ThreeItem3 = new HashMap<>();
		ThreeItem3.put(orange, 10);
		WareHouse warehouse1 = new WareHouse("w1", -1, ThreeItem1);
		WareHouse warehouse2 = new WareHouse("w2", -1, ThreeItem2);
		WareHouse warehouse3 = new WareHouse("w3", -1, ThreeItem3);
		//ItemsAllocator
		List<WareHouse> list6 = Arrays.asList(warehouse1, warehouse2, warehouse3);
		InventoryAllocator input6 = new InventoryAllocator(list6, order6);
		List<WareHouse> ans6 = input6.allocate();
		assertTrue(3 == ans6.size());	
		
		assertTrue("w1".equals(ans6.get(0).name));
		assertTrue(1 == ans6.get(0).inventory.size());
		assertTrue(5 == ans6.get(0).inventory.get(apple));
		
		assertTrue("w2".equals(ans6.get(1).name));
		assertTrue(1 == ans6.get(1).inventory.size());
		assertTrue(5 == ans6.get(1).inventory.get(banana));	
		
		assertTrue("w3".equals(ans6.get(2).name));
		assertTrue(1 == ans6.get(2).inventory.size());		
		assertTrue(5 == ans6.get(2).inventory.get(orange));
	}
	
	/**
	 * Test#7
	 * Test the case when there is two items in the order and there is NOT enough item in the warehouses.
	 * @input1 {apple : 5, banana : 5, grape : 5}
	 * @input2 [{name: w1, {apple : 10}}, {name: w2, {banana : 10}}, {name: w3, {orange : 10}}]
	 * @output []
	 */
	@Test
	void testThreeItemNotMatch() {
		HashMap<Items, Integer> mp7 = new HashMap<>();
		mp7.put(apple, 5);
		mp7.put(banana, 5);
		mp7.put(grape, 5);
		Order order7 = new Order(mp7);
		//WareHouse for test
		HashMap<Items, Integer> notThreeItem1 = new HashMap<>();
		notThreeItem1.put(apple, 10);
		HashMap<Items, Integer> notThreeItem2 = new HashMap<>();
		notThreeItem2.put(banana, 10);
		HashMap<Items, Integer> notThreeItem3 = new HashMap<>();
		notThreeItem3.put(orange, 10);
		WareHouse warehouse1 = new WareHouse("w1", -1, notThreeItem1);
		WareHouse warehouse2 = new WareHouse("w2", -1, notThreeItem2);
		WareHouse warehouse3 = new WareHouse("w3", -1, notThreeItem3);
		//ItemsAllocator
		List<WareHouse> list7 = Arrays.asList(warehouse1, warehouse2, warehouse3);
		InventoryAllocator input7 = new InventoryAllocator(list7, order7);
		List<WareHouse> ans7 = input7.allocate();
		assertTrue(0 == ans7.size());	
	}
	
	/**
	 * Test#8
	 * Test the case when there is five items in the order and there is enough item in the warehouses, and all the warehouse is used.
	 * @input1 {apple : 20, banana : 15, orange : 30, pineapple : 10, grape : 30}
	 * @input2 [{name: w1, {apple : 5, pineapple : 5}}, {name: w2, {banana : 5, pineapple : 5, grape : 5}}, {name: w3, {apple : 20, orange : 10}},
	 * 			{name: w4, {orange : 10, banana : 5}}, {name: w5, {orange : 15, banana : 5, pineapple : 5}}, {name: w6, {apple : 5, grape : 25, banana : 5}]
	 * @output [{name: w1, {apple : 5, pineapple : 5}}, {name: w2, {banana : 5, pineapple : 5, grape : 5}}, {name: w3, {apple : 15, orange : 10}}, 
	 * 			{name: w4, {banana : 5, orange : 10}}, {name: w5, {banana : 5, orange : 10}}, {name: w6, {grape : 20}}]
	 */
	@Test
	void aLittleComplexMatch() {
		HashMap<Items, Integer> mp8 = new HashMap<>();
		mp8.put(apple, 20);
		mp8.put(banana, 15);
		mp8.put(orange, 30);
		mp8.put(pineapple, 10);
		mp8.put(grape, 30);
		Order order8 = new Order(mp8);
		//WareHouse for test
		HashMap<Items, Integer> complex1 = new HashMap<>();
		complex1.put(apple, 5);
		complex1.put(pineapple, 5);
		HashMap<Items, Integer> complex2 = new HashMap<>();
		complex2.put(banana, 5);
		complex2.put(pineapple, 5);
		complex2.put(grape, 10);
		HashMap<Items, Integer> complex3 = new HashMap<>();
		complex3.put(apple, 20);
		complex3.put(orange, 10);
		HashMap<Items, Integer> complex4 = new HashMap<>();
		complex4.put(orange, 10);
		complex4.put(banana, 5);
		HashMap<Items, Integer> complex5 = new HashMap<>();
		complex5.put(orange, 15);
		complex5.put(banana, 5);
		complex5.put(pineapple, 5);
		HashMap<Items, Integer> complex6 = new HashMap<>();
		complex6.put(apple, 5);
		complex6.put(grape, 25);
		complex6.put(banana, 5);
		
		WareHouse warehouse1 = new WareHouse("w1", -1, complex1);
		WareHouse warehouse2 = new WareHouse("w2", -1, complex2);
		WareHouse warehouse3 = new WareHouse("w3", -1, complex3);
		WareHouse warehouse4 = new WareHouse("w4", -1, complex4);
		WareHouse warehouse5 = new WareHouse("w5", -1, complex5);
		WareHouse warehouse6 = new WareHouse("w6", -1, complex6);

		//ItemsAllocator
		List<WareHouse> list8 = Arrays.asList(warehouse1, warehouse2, warehouse3, warehouse4, warehouse5, warehouse6);
		InventoryAllocator input8 = new InventoryAllocator(list8, order8);
		List<WareHouse> ans8 = input8.allocate();
		assertTrue(6 == ans8.size());	
		
		assertTrue("w1".equals(ans8.get(0).name));
		assertTrue(2 == ans8.get(0).inventory.size());
		assertTrue(5 == ans8.get(0).inventory.get(apple));
		assertTrue(5 == ans8.get(0).inventory.get(pineapple));
		
		assertTrue("w2".equals(ans8.get(1).name));
		assertTrue(3 == ans8.get(1).inventory.size());
		assertTrue(5 == ans8.get(1).inventory.get(banana));
		assertTrue(5 == ans8.get(1).inventory.get(pineapple));
		assertTrue(10 == ans8.get(1).inventory.get(grape));
		
		assertTrue("w3".equals(ans8.get(2).name));
		assertTrue(2 == ans8.get(2).inventory.size());
		assertTrue(15 == ans8.get(2).inventory.get(apple));
		assertTrue(10 == ans8.get(2).inventory.get(orange));
		
		assertTrue("w4".equals(ans8.get(3).name));
		assertTrue(2 == ans8.get(3).inventory.size());
		assertTrue(5 == ans8.get(3).inventory.get(banana));
		assertTrue(10 == ans8.get(3).inventory.get(orange));
		
		assertTrue("w5".equals(ans8.get(4).name));
		assertTrue(2 == ans8.get(4).inventory.size());
		assertTrue(5 == ans8.get(4).inventory.get(banana));
		assertTrue(10 == ans8.get(4).inventory.get(orange));
		assertTrue(null == ans8.get(4).inventory.get(pineapple));
		
		assertTrue("w6".equals(ans8.get(5).name));
		assertTrue(1 == ans8.get(5).inventory.size());
		assertTrue(20 == ans8.get(5).inventory.get(grape));
		assertTrue(null == ans8.get(5).inventory.get(banana));
		assertTrue(null == ans8.get(5).inventory.get(apple));
	}
	
	/**
	 * Test#9
	 * Test the case when there is five items in the order and there is enough item in the warehouses, but not all the warehouse is used.
	 * @input1 {apple : 20, banana : 10, orange : 30, pineapple : 10, grape : 30}
	 * @input2 [{name: w1, {apple : 15, pineapple : 5, grape : 25}}, {name: w2, {banana : 5, pineapple : 5, grape : 10, orange : 25}}, {name: w3, {apple : 20, orange : 15}},
	 * 			{name: w4, {orange : 10, banana : 5}}, {name: w5, {orange : 15, banana : 5, pineapple : 5}}, {name: w6, {apple : 5, grape : 25, banana : 5}]
	 * @output [{name: w1, {apple : 15, pineapple : 5, grape : 25}}, {name: w2, {banana : 5, pineapple : 5, grape : 5, orange : 25}}, {name: w3, {apple : 5, orange : 5}}, 
	 * 			{name: w4, {banana : 5}}]
	 */
	@Test
	void aLittleComplexMatchNotAllWareHouse() {
		HashMap<Items, Integer> mp9 = new HashMap<>();
		mp9.put(apple, 20);
		mp9.put(banana, 10);
		mp9.put(orange, 30);
		mp9.put(pineapple, 10);
		mp9.put(grape, 30);
		Order order9 = new Order(mp9);
		//WareHouse for test
		HashMap<Items, Integer> complex1 = new HashMap<>();
		complex1.put(apple, 15);
		complex1.put(pineapple, 5);
		complex1.put(grape, 25);
		HashMap<Items, Integer> complex2 = new HashMap<>();
		complex2.put(banana, 5);
		complex2.put(pineapple, 5);
		complex2.put(grape, 10);
		complex2.put(orange, 25);
		HashMap<Items, Integer> complex3 = new HashMap<>();
		complex3.put(apple, 20);
		complex3.put(orange, 15);
		HashMap<Items, Integer> complex4 = new HashMap<>();
		complex4.put(orange, 10);
		complex4.put(banana, 5);
		HashMap<Items, Integer> complex5 = new HashMap<>();
		complex5.put(orange, 15);
		complex5.put(banana, 5);
		complex5.put(pineapple, 5);
		HashMap<Items, Integer> complex6 = new HashMap<>();
		complex6.put(apple, 5);
		complex6.put(grape, 25);
		complex6.put(banana, 5);
		
		WareHouse warehouse1 = new WareHouse("w1", -1, complex1);
		WareHouse warehouse2 = new WareHouse("w2", -1, complex2);
		WareHouse warehouse3 = new WareHouse("w3", -1, complex3);
		WareHouse warehouse4 = new WareHouse("w4", -1, complex4);
		WareHouse warehouse5 = new WareHouse("w5", -1, complex5);
		WareHouse warehouse6 = new WareHouse("w6", -1, complex6);

		//ItemsAllocator
		List<WareHouse> list9 = Arrays.asList(warehouse1, warehouse2, warehouse3, warehouse4, warehouse5, warehouse6);
		InventoryAllocator input9 = new InventoryAllocator(list9, order9);
		List<WareHouse> ans9 = input9.allocate();
		assertTrue(4 == ans9.size());	
		
		assertTrue("w1".equals(ans9.get(0).name));
		assertTrue(3 == ans9.get(0).inventory.size());
		assertTrue(15 == ans9.get(0).inventory.get(apple));
		assertTrue(5 == ans9.get(0).inventory.get(pineapple));
		assertTrue(25 == ans9.get(0).inventory.get(grape));
		
		assertTrue("w2".equals(ans9.get(1).name));
		assertTrue(4 == ans9.get(1).inventory.size());
		assertTrue(5 == ans9.get(1).inventory.get(banana));
		assertTrue(25 == ans9.get(1).inventory.get(orange));
		assertTrue(5 == ans9.get(1).inventory.get(grape));
		assertTrue(5 == ans9.get(1).inventory.get(pineapple));
		
		assertTrue("w3".equals(ans9.get(2).name));
		assertTrue(2 == ans9.get(2).inventory.size());
		assertTrue(5 == ans9.get(2).inventory.get(apple));
		assertTrue(5 == ans9.get(2).inventory.get(orange));
		
		assertTrue("w4".equals(ans9.get(3).name));
		assertTrue(1 == ans9.get(3).inventory.size());
		assertTrue(5 == ans9.get(3).inventory.get(banana));
	}
	
	/**
	 * Test#10
	 * Test the case when there is five items in the order and there is enough item in the warehouses, but the item stored in warehouse is not enough.
	 * @input1 {apple : 20, banana : 10, orange : 30, pineapple : 10, grape : 30}
	 * @input2 [{name: w1, {apple : 15, pineapple : 5, grape : 25}}, {name: w2, {banana : 5, pineapple : 5, grape : 10, orange : 25}}, {name: w3, {apple : 20, orange : 15}},
	 * 			{name: w4, {orange : 10, banana : 5}}, {name: w5, {orange : 15, banana : 5, pineapple : 5}}, {name: w6, {apple : 5, grape : 25, banana : 5}]
	 * @output []
	 */
	@Test
	void aLittleComplexNotMatch() {
		HashMap<Items, Integer> mp10 = new HashMap<>();
		mp10.put(apple, 20);
		mp10.put(banana, 10);
		mp10.put(orange, 30);
		mp10.put(pineapple, 10);
		mp10.put(grape, 30);
		Order order10 = new Order(mp10);
		//WareHouse for test
		HashMap<Items, Integer> complex1 = new HashMap<>();
		complex1.put(apple, 15);
		complex1.put(pineapple, 5);
		complex1.put(grape, 5);
		HashMap<Items, Integer> complex2 = new HashMap<>();
		complex2.put(banana, 5);
		complex2.put(pineapple, 5);
		complex2.put(grape, 10);
		complex2.put(orange, 25);
		HashMap<Items, Integer> complex3 = new HashMap<>();
		complex3.put(apple, 20);
		complex3.put(orange, 15);
		HashMap<Items, Integer> complex4 = new HashMap<>();
		complex4.put(orange, 10);
		complex4.put(banana, 5);
		HashMap<Items, Integer> complex5 = new HashMap<>();
		complex5.put(orange, 15);
		complex5.put(banana, 5);
		complex5.put(pineapple, 5);
		HashMap<Items, Integer> complex6 = new HashMap<>();
		complex6.put(apple, 5);
		complex6.put(grape, 5);
		complex6.put(banana, 5);
		
		WareHouse warehouse1 = new WareHouse("w1", -1, complex1);
		WareHouse warehouse2 = new WareHouse("w2", -1, complex2);
		WareHouse warehouse3 = new WareHouse("w3", -1, complex3);
		WareHouse warehouse4 = new WareHouse("w4", -1, complex4);
		WareHouse warehouse5 = new WareHouse("w5", -1, complex5);
		WareHouse warehouse6 = new WareHouse("w6", -1, complex6);

		//ItemsAllocator
		List<WareHouse> list10 = Arrays.asList(warehouse1, warehouse2, warehouse3, warehouse4, warehouse5, warehouse6);
		InventoryAllocator input10 = new InventoryAllocator(list10, order10);
		List<WareHouse> ans10 = input10.allocate();
		assertTrue(0 == ans10.size());	
	}
}
