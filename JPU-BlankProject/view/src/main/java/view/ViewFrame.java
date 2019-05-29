package view;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import contract.IController;
import model.IModel;

/**
 * The Class ViewFrame.
 *
 * @author Jean-Aymeric Diet
 */
class ViewFrame extends JFrame implements KeyListener {

	private IModel model;

	private IController controller;
	
	long lastShoot = System.currentTimeMillis();
	final long threshold = 200;

	private static final long serialVersionUID = -697358409737458175L;

	public ViewFrame(final IModel model, final String title) throws HeadlessException {
		super(title);
		this.buildViewFrame(model);
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

	private void buildViewFrame(final IModel model) {
		this.setModel(model);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.addKeyListener(this);
		final ViewPanel panel = new ViewPanel(new GraphicBuilder(), model);
		this.setContentPane(panel);
		this.setSize(20 * 80, 20 * 80);
		this.setLocationRelativeTo(null);
		model.getMap().addObserver(panel);
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
		
	}

	public void applyOrderPerform(int keyCode) throws InterruptedException {
		this.getController().orderPerform(View.keyCodeToControllerOrder(keyCode));
	}

}
