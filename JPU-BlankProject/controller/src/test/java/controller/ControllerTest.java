package controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import contract.ControllerOrder;
import model.Model;
import model.bdd.BDDGetData;
import view.View;

public class ControllerTest {
	
	private Model model;
	private View view;
	private BDDGetData bddGetData;
	private Controller controller;
	
	@Before
	public void setUp() throws Exception {
		this.model = new Model();
		this.view = new View(this.model);
		this.controller = new Controller(view, model);
		this.bddGetData = new BDDGetData();
		this.bddGetData.loadLevel("1", this.model.getMap());
	}


	@Test
	public void testOrderPerformDown() {
		int playerPrevPosY = this.model.getMap().getPlayer().getY();
		this.controller.orderPerform(ControllerOrder.DOWN);
		int playerNewPosY = this.model.getMap().getPlayer().getY();
		
		Assert.assertEquals(playerPrevPosY, playerNewPosY - 1);
	}
	
	@Test
	public void testOrderPerformUp() {
		int playerPrevPosY = this.model.getMap().getPlayer().getY();
		this.controller.orderPerform(ControllerOrder.UP);
		int playerNewPosY = this.model.getMap().getPlayer().getY();
		
		Assert.assertEquals(playerPrevPosY, playerNewPosY + 1);
	}
	
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
