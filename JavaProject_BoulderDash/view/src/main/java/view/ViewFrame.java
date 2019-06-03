package view;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import contract.IController;
import model.IModel;

/**
 * The Class ViewFrame
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
class ViewFrame extends JFrame implements KeyListener {

	private IModel model;
	private IController controller;
	
	/** Current time */
	long lastShoot = System.currentTimeMillis();
	
	/** Time between each key press */
	final long threshold = 80;

	private static final long serialVersionUID = -697358409737458175L;

	/**
	 * Instantiates a new view frame
	 * 
	 * @param model The model
	 * @param title The title of the window
	 * @throws HeadlessException
	 */
	public ViewFrame(final IModel model, final String title) throws HeadlessException {
		super(title);
		this.setModel(model);
	}

	private IController getController() {
		return this.controller;
	}

	protected void setController(final IController controller) {
		this.controller = controller;
	}

	protected IModel getModel() {
		return this.model;
	}

	private void setModel(final IModel model) {
		this.model = model;
	}

	/**
	 * Build a view frame
	 */
	public void buildViewFrame() {
		this.setModel(this.getModel());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
		this.addKeyListener(this);
		final ViewPanel panel = new ViewPanel(new GraphicBuilder(), this.getModel());
		this.setContentPane(panel);
		this.setSize(10 * 80, 10 * 80 + 20);
		this.setLocationRelativeTo(null);
		this.getModel().getMap().addObserver(panel);
	}

	public void keyTyped(final KeyEvent e) {

	}

	public void keyPressed(final KeyEvent e) {
	     long now = System.currentTimeMillis();
	     if (now - lastShoot > threshold) {
	    	 try {
				this.getController().orderPerform(View.keyCodeToControllerOrder(e.getKeyCode()));
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	    	 lastShoot = now;
	     }
	}

	public void keyReleased(final KeyEvent e) {
//		model.getMap().getPlayer().setMaxAnimations(0);
		getModel().getMap().getPlayer().restIndex = 1;
	}

	/**
	 * Send the key code to the controller
	 * 
	 * @param keyCode The key code
	 * @throws InterruptedException
	 */
	public void applyOrderPerform(int keyCode) throws InterruptedException {
		this.getController().orderPerform(View.keyCodeToControllerOrder(keyCode));
	}

}
