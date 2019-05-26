package view;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * The Class ViewPanel.
 *
 * @author Jean-Aymeric Diet
 */
class ViewPanel extends JPanel implements Observer {

		
	private final GraphicBuilder graphicBuilder;
	
	private static final long	serialVersionUID	= -998294702363713521L;

	public ViewPanel(final GraphicBuilder graphicBuilder) {
		this.graphicBuilder = graphicBuilder;
	}

	public void update(final Observable arg0, final Object arg1) {
		this.repaint();
	}

	@Override
	protected void paintComponent(final Graphics graphics) {
		this.graphicBuilder.applyModelToGraphic(graphics);
	}
}
