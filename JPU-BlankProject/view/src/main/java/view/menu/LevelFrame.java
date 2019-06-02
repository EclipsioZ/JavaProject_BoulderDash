package view.menu;

import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;

import model.db.DBGetData;

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

	public void loadLevelFrame() {
		menu.add(levelpanel);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(12 * 80 - 12, 12 * 80);
		menu.setResizable(false);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
	}

	public LevelFrame() {
		menu = menuframe2.getJframe();
		levelpanel = new LevelPanel();
		levelpanel.setLevelName(getLevelName(levelpanel.getLevel()));

		menu.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent ke) {

				if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
					if (levelpanel.getLevel() == 1) {
						try {
							openEditor();
						} catch (IOException | URISyntaxException e) {
							e.printStackTrace();
						}
					} else {
						if (levelpanel.getLevelName() != "-- No map found --") {
							getMenuframe().getView().setCurrentFrame(2);
							menu.setVisible(false);
							getMenuframe().getView().changeView();
						}
					}
				}

				if (ke.getKeyCode() == KeyEvent.VK_RIGHT && levelpanel.getLevel() < 14) {
					levelpanel.setLevel(levelpanel.getLevel() + 1);
					levelpanel.repaint();
				}
				if (ke.getKeyCode() == KeyEvent.VK_LEFT && levelpanel.getLevel() > 1) {
					levelpanel.setLevel(levelpanel.getLevel() - 1);
					levelpanel.repaint();
				}

				levelpanel.setLevelName(getLevelName(levelpanel.getLevel() - 1));

			}

			public void keyReleased(KeyEvent ke) {
			}

			public void keyTyped(KeyEvent ke) {
			}
		});
	}

	public String getLevelName(int id) {
		DBGetData bdd = new DBGetData();
		return bdd.getMapNameFromId(id);
	}
	
	public void openEditor() throws IOException, URISyntaxException {
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
		    Desktop.getDesktop().browse(new URI("http://localhost/mapEditor/"));
		}
	}

}
