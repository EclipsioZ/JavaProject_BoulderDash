package controller;

import contract.ControllerOrder;
import contract.IController;
import model.IModel;
import view.IView;

/**
 * The Class Controller.
 */
public final class Controller implements IController {

	/** The view. */
	private IView view;

	/** The model. */
	private IModel model;

	// State of the game: 0 = in menu, 1 = in game
	private int state;

	/**
	 * Instantiates a new controller.
	 *
	 * @param view  the view
	 * @param model the model
	 */
	public Controller(final IView view, final IModel model) {
		this.setView(view);
		this.setModel(model);
		this.state = 1;
	}

	/**
	 * Control.
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#control()
	 */
	public void control() {
		this.view.printMessage(
				"Appuyer sur les touches 'E', 'F', 'D' ou 'I', pour afficher Hello world dans la langue d votre choix.");
	}

	/**
	 * Sets the view.
	 *
	 * @param pview the new view
	 */
	private void setView(final IView pview) {
		this.view = pview;
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	private void setModel(final IModel model) {
		this.model = model;
	}

	/**
	 * Order perform.
	 *
	 * @param controllerOrder the controller order
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#orderPerform(contract.ControllerOrder)
	 */
	public void orderPerform(final ControllerOrder controllerOrder) {

		switch (controllerOrder) {
		case UP:
			if (this.state == 1) {
				model.getMap().getPlayer().move(model.getMap().getPlayer().getX(),
						model.getMap().getPlayer().getY() - 1);
			}
			break;
		case DOWN:
			if (this.state == 1) {
				model.getMap().getPlayer().move(model.getMap().getPlayer().getX(),
						model.getMap().getPlayer().getY() + 1);
			}
			break;
		case LEFT:
			if (this.state == 1) {
				model.getMap().getPlayer().move(model.getMap().getPlayer().getX() - 1,
						model.getMap().getPlayer().getY());
			}
			break;
		case RIGHT:
			if (this.state == 1) {
				model.getMap().getPlayer().move(model.getMap().getPlayer().getX() + 1,
						model.getMap().getPlayer().getY());
			}
			break;
		default:
			break;
		}
		model.getMap().printConsole();
		model.updateMap(model.getMap());
	}

}
