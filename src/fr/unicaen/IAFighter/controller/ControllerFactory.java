package fr.unicaen.IAFighter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.unicaen.IAFighter.model.GameBoard;
import fr.unicaen.IAFighter.model.GameObject;
import fr.unicaen.IAFighter.view.AddGameObjectView;
/**
 * Factory (abstraite) qui ne donne aucun droit a la vue
 * delegue a la vue un controller qui ne fait rien
 *
 */
public class ControllerFactory {
	
	public ActionListener getStartGameController(GameBoard model) {
		return new StartGameController(model);
	}

	public ActionListener getDeleteGameObjectController(GameBoard model, GameObject gameObject) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		};
	}

	public ActionListener getColorGameObjectController(GameBoard model, GameObject gameObject) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	public ActionListener getStrategyGameObjectController(GameObject gameObject) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	public ActionListener getAddGameObjectController(GameBoard model, AddGameObjectView addGameObjectView) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	public ActionListener getDirectionGameObjectController(GameObject gameObject) {
		// TODO Auto-generated method stub
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	public ActionListener getRandomPositionController(GameBoard model, AddGameObjectView view) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public ChangeListener getRowSizeController(GameBoard model){
		return new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public ChangeListener getColSizeController(GameBoard model){
		return new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}

	public ActionListener getRowGameObjectController(GameObject gameObject) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	public ActionListener getColGameObjectController(GameObject gameObject) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
