package model;

import java.util.Observable;

import entity.HelloWorld;

/**
 * The Interface IModel
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */
public interface IModel {

	/**
	 * Get the observable
	 *
	 * @return The observable
	 */
	Observable getObservable();

	/**
	 * Get the map
	 * 
	 * @return The map
	 */
	Map getMap();

	/**
	 * Update the map
	 * 
	 * @param map The map
	 */
	void updateMap(Map map);

	/**
	 * Reset current map
	 * @throws Exception 
	 */
	void resetMap() throws Exception;

	/**
	 * Load a new map
	 * 
	 * @param idTexture The id of the texture
	 * @param mapId     The id of the map
	 * @throws Exception 
	 */
	void loadMap(int idTexture, String mapId) throws Exception;

	/**
	 * Change the texture of the model
	 * 
	 * @param id The id of the texture
	 */
	void changeTexture(int id);

	/**
	 * Get the animated text
	 * 
	 * @return The animated text
	 */
	AnimatedText getAnimatedText();

	/**
	 * Set the animated text
	 * 
	 * @param animatedText The animated text
	 */
	void setAnimatedText(AnimatedText animatedText);

	/**
	 * Set the level id
	 * 
	 * @param level The level id
	 */
	void setLevelId(int level);

}
