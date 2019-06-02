package view;

import view.menu.MenuFrame;

/**
 * The Interface IView
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public interface IView {

	void changeView();

	MenuFrame getMenuFrame();

	void setCurrentFrame(int i);

	int getCurrentFrame();
}
