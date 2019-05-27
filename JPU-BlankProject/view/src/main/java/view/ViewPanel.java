package view;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.IModel;
import model.Map;

/**
 * The Class ViewPanel.
 *
 * @author Jean-Aymeric Diet
 */
class ViewPanel extends JPanel implements Observer {

		
	private final GraphicBuilder graphicBuilder;
	
	private static final long	serialVersionUID	= -998294702363713521L;

	public ViewPanel(final GraphicBuilder graphicBuilder, IModel iModel) {
		this.graphicBuilder = graphicBuilder;
		this.graphicBuilder.setMap(iModel);
	}

	public void update(final Observable arg0, final Object arg1) {
		this.repaint();
	}

	@Override
	protected void paintComponent(final Graphics graphics) {
		this.graphicBuilder.applyModelToGraphic(graphics);
	}
}
