package fr.unicaen.IAFighter.controller;

import java.awt.event.ActionListener;

import javax.swing.event.ChangeListener;

import fr.unicaen.IAFighter.model.GameBoard;
import fr.unicaen.IAFighter.model.GameObject;
import fr.unicaen.IAFighter.view.AddGameObjectView;
/**
 * Delegue a la vue les controllers qui rend les modifications du modele possible
 * (acces au modele en lecture et ecriture)
 *
 */
public class InitializeGameFactory extends ControllerFactory {

	@Override
	public ActionListener getStartGameController(GameBoard model) {
		return new StartGameController(model);
	}

	@Override
	public ActionListener getDeleteGameObjectController(GameBoard model, GameObject gameObject) {
		// TODO Auto-generated method stub
		return new DeleteGameObjectController(model, gameObject);
	}

	@Override
	public ActionListener getColorGameObjectController(GameBoard model, GameObject gameObject) {
		return new ColorGameObjectController(model, gameObject);
	}

	@Override
	public ActionListener getAddGameObjectController(GameBoard model, AddGameObjectView view) {
		return new AddGameObjectController(model, view);
	}

	@Override
	public ActionListener getDirectionGameObjectController(GameObject gameObject) {
		// TODO Auto-generated method stub
		return new DirectionGameObjectController(gameObject);
	}

	@Override
	public ActionListener getRandomPositionController(GameBoard model, AddGameObjectView view) {
		return new RandomPositionController(model, view);
	}
	
	@Override
	public ChangeListener getRowSizeController(GameBoard model) {
		return new RowSizeController(model);
	}
	
	@Override
	public ChangeListener getColSizeController(GameBoard model) {
		return new ColSizeController(model);
	}
	
	@Override
	public ActionListener getRowGameObjectController(GameObject gameObject) {
		// TODO Auto-generated method stub
		return new RowGameObjectController(gameObject);
	}
	
	@Override
	public ActionListener getColGameObjectController(GameObject gameObject) {
		// TODO Auto-generated method stub
		return new ColGameObjectController(gameObject);
	}
	
	@Override
	public ActionListener getStrategyGameObjectController(GameObject gameObject) {
		// TODO Auto-generated method stub
		return new StrategyGameObjectController(gameObject);
	}

}
