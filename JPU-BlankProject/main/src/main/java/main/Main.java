/**
 * @author Jean-Aymeric DIET jadiet@cesi.fr
 * @version 1.0
 */
package main;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import contract.ControllerOrder;
import controller.Controller;
import controller.Sound;
import model.Map;
import model.Model;
import model.Texture;
import model.Animation;
import model.bdd.BDDConnector;
import model.bdd.BDDGetData;
import view.View;

/**
 * The Class Main.
 *
 * @author Jean-Aymeric Diet
 */
public abstract class Main {

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        final Model model = new Model();
        final View view = new View(model);
        final Controller controller = new Controller(view, model);
        view.setController(controller);
        controller.control();
    }
}
