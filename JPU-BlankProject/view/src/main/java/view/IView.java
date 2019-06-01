package view;

import view.menu.MenuFrame;

/**
 * The Interface IView.
 *
 * @author Jean-Aymeric Diet
 */
public interface IView {

	/**
	 * Prints the message.
	 *
	 * @param message
	 *          the message
	 */
	void printMessage(final String message);

	void changeView();

	MenuFrame getMenuFrame();

	void setCurrentFrame(int i);

	int getCurrentFrame();
}
