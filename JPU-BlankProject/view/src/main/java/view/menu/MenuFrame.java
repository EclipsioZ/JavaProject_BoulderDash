package view.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import view.IView;
import view.View;

public class MenuFrame {

	private int frame = 0;
	JFrame menu = new JFrame();
	LevelPanel levelpanel = new LevelPanel();

	IView view;

	public void MenuFrame() {
		MenuPanel menupanel = new MenuPanel();
		menu.add(menupanel);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setSize(960, 540);
		menu.setResizable(false);
		menu.setLocationRelativeTo(null);
		menu.setVisible(true);
		menu.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent ke) {

				if (ke.getKeyCode() == ke.VK_ENTER) {
					System.out.println("test");
					menu.setVisible(false);
					setFrame(1);
					getView().changeView();
				}
			}

			public void keyReleased(KeyEvent ke) {
			}

			public void keyTyped(KeyEvent ke) {
			}
		});
	}

	public JFrame getJframe() {
		return this.menu;
	}

	public void setJframe(JFrame jframe) {
		this.menu = jframe;
	}

	public int getFrame() {
		return this.frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public IView getView() {
		return view;
	}

	public void setView(IView view) {
		this.view = view;
	}

}
