package view;

import view.menu.MenuFrame;

/**
 * The Interface IView
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public interface IView {

	/**
	 * Change the view (menu, map or game)
	 */
	void changeView();

	/**
	 * Get the menu frame
	 * 
	 * @return The menu frame
	 */
	MenuFrame getMenuFrame();

	/**
	 * Set the current frame
	 * 
	 * @param id The id of the frame
	 */
	void setCurrentFrame(int id);

	/**
	 * Get the current frame id
	 * 
	 * @return The frame id
	 */
	int getCurrentFrame();
}
