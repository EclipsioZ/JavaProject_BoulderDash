package controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import contract.ControllerOrder;
import model.Model;
import model.db.DBGetData;
import view.View;

/**
 * The test class for the controller
 * 
 * @author Florian Rossi
 * @author Baptiste Miquel
 *
 */
public class ControllerTest {
	
	private Model model;
	private View view;
	private DBGetData bddGetData;
	private Controller controller;
	
	/**
	 * Alert that the test will be executed
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Testing the controller");
	}
	
	/**
	 * Setting up a new map
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.model = new Model();
		this.view = new View(this.model);
		this.controller = new Controller(view, model);
		this.bddGetData = new DBGetData();
		this.bddGetData.loadLevel("1", this.model.getMap());
	}


	/**
	 * When the key down is pressed
	 */
	@Test
	public void testOrderPerformDown() {
		int playerPrevPosY = this.model.getMap().getPlayer().getY();
		this.controller.orderPerform(ControllerOrder.DOWN);
		int playerNewPosY = this.model.getMap().getPlayer().getY();
		
		Assert.assertEquals(playerPrevPosY, playerNewPosY - 1);
	}
	
	/**
	 * When the key up is pressed
	 */
	@Test
	public void testOrderPerformUp() {
		int playerPrevPosY = this.model.getMap().getPlayer().getY();
		this.controller.orderPerform(ControllerOrder.UP);
		int playerNewPosY = this.model.getMap().getPlayer().getY();
		
		Assert.assertEquals(playerPrevPosY, playerNewPosY + 1);
	}
	
	/**
	 * When all the keys are pressed
	 */
	@Test
	public void testOrderPerformMultipleMovement() {
		int playerPrevPosX = this.model.getMap().getPlayer().getX();
		int playerPrevPosY = this.model.getMap().getPlayer().getY();
		this.controller.orderPerform(ControllerOrder.DOWN);
		this.controller.orderPerform(ControllerOrder.LEFT);
		this.controller.orderPerform(ControllerOrder.RIGHT);
		this.controller.orderPerform(ControllerOrder.UP);
		int playerNewPosX = this.model.getMap().getPlayer().getX();
		int playerNewPosY = this.model.getMap().getPlayer().getY();
		
		Assert.assertEquals(true, playerPrevPosX == playerNewPosX);
		Assert.assertEquals(true, playerPrevPosY == playerNewPosY);
	}
}
