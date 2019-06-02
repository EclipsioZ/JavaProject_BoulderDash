package controller;

import contract.ControllerOrder;
import contract.IController;
import model.IModel;
import view.IView;

/**
 * The Class Controller
 * 
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public final class Controller implements IController {

	/** The view. */
	private IView view;

	/** The model. */
	private IModel model;

	ElementThread elementThread;

	/**
	 * Instantiates a new controller.
	 *
	 * @param view  the view
	 * @param model the model
	 */
	public Controller(final IView view, final IModel model) {
		this.setView(view);
		this.setModel(model);

		this.elementThread = new ElementThread(model, this);
		Thread eThread = new Thread(this.elementThread);
		eThread.start();

		Sound sound = new Sound();
		sound.setSoundName("LevelMusic");
		Thread th = new Thread(sound);
		th.start();
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
	 * @throws InterruptedException
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see contract.IController#orderPerform(contract.ControllerOrder)
	 */
	public void orderPerform(final ControllerOrder controllerOrder) {

		switch (controllerOrder) {
		case UP:
			model.getMap().getPlayer().move(model.getMap().getPlayer().getX(), model.getMap().getPlayer().getY() - 1);
			break;
		case DOWN:
			model.getMap().getPlayer().move(model.getMap().getPlayer().getX(), model.getMap().getPlayer().getY() + 1);
			break;
		case LEFT:
			model.getMap().getPlayer().move(model.getMap().getPlayer().getX() - 1, model.getMap().getPlayer().getY());
			break;
		case RIGHT:
			model.getMap().getPlayer().move(model.getMap().getPlayer().getX() + 1, model.getMap().getPlayer().getY());
			break;
		case TEX1:
			model.changeTexture(1);
			break;
		case TEX2:
			model.changeTexture(2);
			break;
		case TEX3:
			model.changeTexture(3);
			break;
		case TEX4:
			model.changeTexture(4);
			break;
		case TEX5:
			model.changeTexture(5);
			break;
		case TEX6:
			model.changeTexture(6);
			break;
		case QUIT:
			this.returnToMenu();
			break;
		default:
//			Random r = new Random();
//			model.loadMap(r.nextInt(6), "13");
			break;
		}
//		model.getMap().printConsole();
		model.updateMap(model.getMap());

	}

	@Override
	public void setLevel(int level) {
		this.model.setLevelId(level);
	}

	public void returnToMenu() {
		this.view.setCurrentFrame(1);
		this.view.changeView();
	}

}
