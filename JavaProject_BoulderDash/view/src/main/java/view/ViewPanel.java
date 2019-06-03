package view;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.IModel;

/**
 * The Class ViewPanel
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
class ViewPanel extends JPanel implements Observer {

	private final GraphicBuilder graphicBuilder;

	private static final long serialVersionUID = -998294702363713521L;

	/**
	 * Instantiates a new view panel
	 * 
	 * @param graphicBuilder The graphic builder
	 * @param iModel         The model interface
	 */
	public ViewPanel(final GraphicBuilder graphicBuilder, IModel iModel) {
		this.graphicBuilder = graphicBuilder;
		this.graphicBuilder.setMap(iModel);
	}

	/**
	 * Update from observable
	 */
	public void update(final Observable arg0, final Object arg1) {
		this.repaint();
	}

	/**
	 * Paint in graphic builder
	 */
	@Override
	protected void paintComponent(final Graphics graphics) {
		this.graphicBuilder.applyModelToGraphic(graphics);
	}
}
