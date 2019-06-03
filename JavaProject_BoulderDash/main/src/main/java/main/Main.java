/**
 * @author Florian Rossi florian.rossi@viacesi.fr
 * @author Baptiste Miquel baptiste.miquel@viacesi.fr
 * 
 * @version 1.0
 */
package main;

import controller.Controller;
import model.Model;
import view.View;

/**
 * The Class Main
 * 
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public abstract class Main {

	/**
	 * The main method
	 *
	 * @param args The arguments
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {
		final Model model = new Model();
		final View view = new View(model);
		final Controller controller = new Controller(view, model);
		view.setController(controller);
	}
}
