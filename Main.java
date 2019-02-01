//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           (SwimSim2)
// Files:           (SwimSim.jar)
// Course:          (CS300, Fall, 2017)
//
// Author:          (Dustin Li)
// Email:           (dli284@wisc.edu)
// Lecturer's Name: (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (Brennan Fife)
// Partner Email:   (bfife@wisc.edu)
// Lecturer's Name: (Gary Dahl)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _x_ Write-up states that pair programming is allowed for this assignment.
//   _x_ We have both read and understand the course Pair Programming Policy.
//   _x_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (NONE)
// Online Sources:  (NONE)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * @author Dustin Li
 * @version 1.0
 * 
 * 
 */
public class Main {
	/**
	 * Calls the Utility object to open the GUI and start the simulation.
	 */
	public static void main(String[] args) {
	// TODO Auto-generated method stub
			//Data data = new Data();
			//data.tank = new char[32][8];
			//setup(data);
			//while(true) {
			//    update(data);
			//}
		Utility.startSimulation();		
	}
	/**
	 * Sets up dimensions of the tank and generates random positions of objects 
	 * to be placed in the tank.
	 * 
	 * @param data the data object that contains all of the arrays that are utilized
	 * in the method
	 */
	public static void setup(Data data)	{
		data.tank = new char[600][800];
		int width = data.processing.width;
		int height = data.processing.height;
		data.fishPositions = generateRandomPositions(4,width,height);
		data.foodPositions = generateRandomPositions(6,width,height);
		data.hookPositions = generateRandomPositions(1,width,height);	
	}
	/**
	 * Updates the GUI by repeatedly calling other methods. 
	 * 
	 * @param data The data object that contains all of the arrays that are utilized
	 * in the method
	 */
	public static void update(Data data) {
		int width = data.processing.width;
		int height = data.processing.height;
		data.processing.background(0,255,255);
		moveAllObjects(data.fishPositions,1,0,width,height); 
		moveAllObjects(data.foodPositions,-1,1,width,height); 
		moveAllObjects(data.hookPositions,0,-(height + 50 - data.hookPositions[0][1])/50,width,height);
		// as the hook moves upward in the tank, the speed at which it does so increases 
		// at the rate of -(height + 50 - y)/50
		for (int i = 0; i < data.fishPositions.length; i++) {
			int rowFish = data.fishPositions[i][0];
			int columnFish = data.fishPositions[i][1];
			if(columnFish < height) {
				placeObjectInTank("><))'>",data.processing,rowFish,columnFish);
			}
		}
		for (int i = 0; i < data.foodPositions.length; i++) {
			int rowFood = data.foodPositions[i][0];
			int columnFood = data.foodPositions[i][1];
			if (columnFood < height) {
				placeObjectInTank("*",data.processing,rowFood,columnFood);;
			}
		}
		for (int i = 0; i < data.hookPositions.length; i++) {
			int rowHook = data.hookPositions[i][0];
			int columnHook = data.hookPositions[i][1];
			if (columnHook < height) {
				placeObjectInTank("J",data.processing,rowHook,columnHook);
			}
		}
		
	}
	/**
	 * Fills the tank with the water.
	 * 
	 * @param tank The array to be filled with chars
	 * @param water The char that fills the array
	 */
	public static void fillTank(char[][] tank, char water) {
		for (int i = 0; i < tank.length; i++) {
		    for (int j = 0; j < tank[i].length; j++) {
			   	   tank[i][j] = water;
			   } 
		}
	}
	/**
	 * Draws out the tank in the console.	 
	 * 
	 * @param tank Array of chars to be drawn.
	 */
	public static void renderTank(char[][] tank) {
		for (int i = 0; i < tank.length; i++) {
			for (int j = 0; j < tank[i].length; j++) {
				System.out.print(tank[i][j]);
			}
			System.out.println();
		}
	}
	/**
	 * Generates random positions to be used in placing the objects in the tank.
	 * 
	 * @param number Number of random positions to be generated
	 * @param width Width of the tank array
	 * @param height Height of the tank array
	 * @return 2D Array of randomly generated positions
	 */
	public static int[][] generateRandomPositions(int number, int width, int height) {
		int[][] random = new int[number][2];
		for (int i = 0; i < number; i++) {
			for (int j = 0; j <  2; j++) {
				int posX = Utility.randomInt(width);
				int posY = Utility.randomInt(height);
					if (j == 0) {
						random[i][j] = posX;
					}
					else if (j == 1) {
						random[i][j] = posY;
					}
				}
			}
			return random;
	}
	/**
	 * Places the object in the tank at the specified position.
	 * 
	 * @param object The string which will be placed in the tank
	 * @param processing Changes the string into an image in the GUI
	 * @param column The X position of the location where the string will be placed 
	 * @param row The Y position of the location where the string will be placed 
	 */
	public static void placeObjectInTank(String object, PApplet processing, int column, int row) {
		PImage fishImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
		PImage foodImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
		PImage hookImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
		if (object.equals("*")) {
			processing.image(foodImage, column, row);
		}
		if (object.equals("J")) {
			processing.image(hookImage, column, row);
			processing.line(column + 5, row, column + 5, 0);
		}
		if (object.length() > 2) {
			processing.image(fishImage, column, row);
		}
	}
	/**
	 * Moves all of the objects in the specified directions.
	 * 
	 * @param positions Positions of the objects
	 * @param dx The distance of X the objects will move
	 * @param dy The distance of Y the objects will move
	 * @param width The width of the tank
	 * @param height The height of the tank
	 */
	public static void moveAllObjects(int[][] positions, int dx, int dy, int width, int height) {
		for (int i = 0; i < positions.length; i++) {
			if (positions[i][0] + dx > width - 1) {
				positions[i][0] = 0;
			}
			else if (positions[i][0] + dx < 0) {
				positions[i][0] = width - 1;
			}
			else {
				positions[i][0] += dx;
			}
			
			if (positions[i][1] + dy > height - 1) {
				positions[i][1] = 0;
			}
			else if (positions[i][1] + dy < 0) {
				positions[i][1] = height - 1;
			}
			else {
				positions[i][1] += dy;
			}
		}
	}
	/**
	 * Records the position of the user's cursor when a click is detected.
	 * 
	 * @param data Data object which contains the method for detecting the user's click 
	 * @param mouseX The x position of the user's cursor
	 * @param mouseY The y position of the user's cursor
	 */
	public static void onClick(Data data, int mouseX, int mouseY) {
		data.hookPositions[0][0] = mouseX;
		data.hookPositions[0][1] = data.processing.height - 1;
	}
}