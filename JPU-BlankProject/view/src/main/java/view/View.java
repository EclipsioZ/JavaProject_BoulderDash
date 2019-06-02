package view;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import contract.ControllerOrder;
import contract.IController;
import model.IModel;
import view.menu.LevelFrame;
import view.menu.MenuFrame;

/**
 * The Class View
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public final class View implements IView, Runnable {

	public ViewFrame viewFrame;
	public LevelFrame levelframe = new LevelFrame();
	public MenuFrame menuframe = new MenuFrame();

	public IController controller;
	public IModel model;

	private int currentFrame = 0;

	/**
	 * Instantiates a new view
	 *
	 * @param model The model
	 */
	public View(final IModel model) {
		this.model = model;
		this.viewFrame = new ViewFrame(model, "Boulderdash");
		this.menuframe.setView(this);
		this.levelframe.setMenuframe(menuframe);
		this.changeView();
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	/**
	 * Reset the view frame and the menu frame
	 */
	public void resetViews() {
		this.viewFrame.setVisible(false);
		this.viewFrame = new ViewFrame(model, "Boulderdash");
		this.menuframe.setView(this);
	}

	/**
	 * Change the view (menu, map or game)
	 */
	public void changeView() {
		this.resetViews();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (getCurrentFrame() == 0) {
					menuframe.MenuFrame();
				} else if (getCurrentFrame() == 1) {
					levelframe.loadLevelFrame();
				} else {
					viewFrame.buildViewFrame();
					try {
						updateController();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			
		});
	}

	/**
	 * Key code to controller order
	 *
	 * @param keyCode The key code
	 * @return The controller order
	 */
	protected static ControllerOrder keyCodeToControllerOrder(final int keyCode) {
		switch (keyCode) {
		case KeyEvent.VK_UP:
			return ControllerOrder.UP;
		case KeyEvent.VK_DOWN:
			return ControllerOrder.DOWN;
		case KeyEvent.VK_LEFT:
			return ControllerOrder.LEFT;
		case KeyEvent.VK_RIGHT:
			return ControllerOrder.RIGHT;
		case KeyEvent.VK_1:
			return ControllerOrder.TEX1;
		case KeyEvent.VK_2:
			return ControllerOrder.TEX2;
		case KeyEvent.VK_3:
			return ControllerOrder.TEX3;
		case KeyEvent.VK_4:
			return ControllerOrder.TEX4;
		case KeyEvent.VK_5:
			return ControllerOrder.TEX5;
		case KeyEvent.VK_6:
			return ControllerOrder.TEX6;
		case KeyEvent.VK_ESCAPE:
			return ControllerOrder.QUIT;
		default:
			return ControllerOrder.NOP;
		}
	}

	public void run() {
		this.viewFrame.setVisible(true);
	}

	/**
	 * Sets the controller
	 *
	 * @param controller The new controller
	 */
	public void setController(final IController controller) {
		this.controller = controller;
	}

	/**
	 * Update the controller with a new level
	 * @throws Exception 
	 */
	public void updateController() throws Exception {
		if (this.getCurrentFrame() == 2) {
			controller.setLevel(levelframe.getLevelpanel().getLevel());
			this.viewFrame.setController(controller);
			this.viewFrame.getModel().loadMap(1, Integer.toString(levelframe.getLevelpanel().getLevel() - 1));
			this.viewFrame.getModel().getMap().running = false;
		}
	}

	@Override
	public MenuFrame getMenuFrame() {
		return this.menuframe;
	}

}
