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

	/**
	 * Instantiates a new view.
	 *
	 * @param model the model
	 */
	public View(final IModel model) {
		this.viewFrame = new ViewFrame(model, "Boulderdash");
		this.menuframe.setView(this);
		this.levelframe.setMenuframe(menuframe);
		this.changeView();
	}

	public void changeView() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println(menuframe.getFrame());
				if (menuframe.getFrame() == 0) {
					System.out.println(menuframe.getFrame());
					menuframe.MenuFrame();
				} else if (menuframe.getFrame() == 1) {

					levelframe.LevelFrame();
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
		if (menuframe.getFrame() == 2) {
			controller.setLevel(levelframe.getLevelpanel().getLevel());
			this.viewFrame.setController(controller);
			this.viewFrame.getModel().loadMap(2, Integer.toString(levelframe.getLevelpanel().getLevel()));
			this.viewFrame.getModel().getMap().running = false;
		}
	}

	public void updateMap() {

	}

}
