package contract;

/**
 * The Interface IController.
 *
 * @author Jean-Aymeric Diet
 */
public interface IController {

	/**
	 * Order perform.
	 *
	 * @param controllerOrder
	 *          the controller order
	 * @throws InterruptedException 
	 */
	public void orderPerform(ControllerOrder controllerOrder) throws InterruptedException;

	public void setLevel(int level);

	public void returnToMenu();
}
