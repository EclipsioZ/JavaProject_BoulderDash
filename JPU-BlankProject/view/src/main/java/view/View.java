package view;

import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

import contract.ControllerOrder;
import contract.IController;
import model.IModel;
import view.menu.LevelFrame;
import view.menu.MenuFrame;

/**
 * The Class View.
 *
 * @author Jean-Aymeric Diet
 */
public final class View implements IView, Runnable {

	/** The frame. */
	public ViewFrame viewFrame;
	public LevelFrame levelframe = new LevelFrame();
	public MenuFrame menuframe = new MenuFrame();
	
	public IController controller;
	public IModel model;
	
	private int currentFrame = 0;

	/**
	 * Instantiates a new view.
	 *
	 * @param model the model
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

	public void resetViews() {
		this.viewFrame.setVisible(false);
		this.viewFrame = new ViewFrame(model, "Boulderdash");
		this.menuframe.setView(this);
	}

	public void changeView() {
		this.resetViews();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (getCurrentFrame() == 0) {
					System.out.println(menuframe.getFrame());
					menuframe.MenuFrame();
				} else if (getCurrentFrame() == 1) {
					levelframe.loadLevelFrame();
				} else {
					viewFrame.buildViewFrame();
					updateController();
				}
			}
		});
	}

	/**
	 * Key code to controller order.
	 *
	 * @param keyCode the key code
	 * @return the controller order
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
		default:
			return ControllerOrder.NOP;
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IView#printMessage(java.lang.String)
	 */
	public void printMessage(final String message) {
		;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		this.viewFrame.setVisible(true);
	}

	/**
	 * Sets the controller.
	 *
	 * @param controller the new controller
	 */
	public void setController(final IController controller) {
		this.controller = controller;
	}
	
	public void updateController() {
		if (this.getCurrentFrame() == 2) {
			controller.setLevel(levelframe.getLevelpanel().getLevel());
			this.viewFrame.setController(controller);
			this.viewFrame.getModel().loadMap(2, Integer.toString(levelframe.getLevelpanel().getLevel()));
			this.viewFrame.getModel().getMap().running = false;
		}
	}

	public void updateMap() {

	}

	@Override
	public MenuFrame getMenuFrame() {
		return this.menuframe;
	}

}
