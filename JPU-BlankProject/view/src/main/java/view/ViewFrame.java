package view;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import contract.IController;
import model.IModel;
import model.Map;

/**
 * The Class ViewFrame.
 *
 * @author Jean-Aymeric Diet
 */
class ViewFrame extends JFrame implements KeyListener {

	private IModel model;

	private IController controller;

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
		this.setContentPane(new ViewPanel(new GraphicBuilder(), model));
		this.setSize(48*80, 24*80);
		this.setLocationRelativeTo(null);
		
	}


	public void keyTyped(final KeyEvent e) {

	}

	public void keyPressed(final KeyEvent e) {
		this.getController().orderPerform(View.keyCodeToControllerOrder(e.getKeyCode()));
	}

	public void keyReleased(final KeyEvent e) {

	}
}
