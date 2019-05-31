package view.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class LevelFrame {

	MenuFrame menuframe;
	MenuFrame menuframe2 = new MenuFrame();
	JFrame menu;
	LevelPanel levelpanel;

	public MenuFrame getMenuframe() {
		return menuframe;
	}

	public LevelPanel getLevelpanel() {
		return levelpanel;
	}

	public void setMenuframe(MenuFrame menuframe) {
		this.menuframe = menuframe;
	}

	public void LevelFrame() {

		menu = menuframe2.getJframe();
		levelpanel = new LevelPanel();

		menu.add(levelpanel);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(12 * 80 - 12, 12 * 80);
		menu.setResizable(false);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
		menu.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent ke) {

				if (ke.getKeyCode() == ke.VK_ENTER) {
					getMenuframe().setFrame(2);
					menu.setVisible(false);
					getMenuframe().getView().changeView();
				}

				if (ke.getKeyCode() == ke.VK_RIGHT && levelpanel.getLevel() < 13) {
					levelpanel.setLevel(levelpanel.getLevel() + 1);
					levelpanel.repaint();
				}
				if (ke.getKeyCode() == ke.VK_LEFT && levelpanel.getLevel() > 1) {
					levelpanel.setLevel(levelpanel.getLevel() - 1);
					levelpanel.repaint();
				}

			}

			public void keyReleased(KeyEvent ke) {
			}

			public void keyTyped(KeyEvent ke) {
			}
		});
	}
}
