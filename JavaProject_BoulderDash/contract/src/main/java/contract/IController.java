package contract;

/**
 * The Interface IController.
 * 
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public interface IController {

	/**
	 * Order perform.
	 *
	 * @param controllerOrder
	 *          the controller order
	 * @throws InterruptedException Throw an exception
	 */
	public void orderPerform(ControllerOrder controllerOrder) throws InterruptedException;

	public void setLevel(int level);

	public void returnToMenu();
}
