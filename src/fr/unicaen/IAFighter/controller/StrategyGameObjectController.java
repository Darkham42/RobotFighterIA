package fr.unicaen.IAFighter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import fr.unicaen.IAFighter.model.GameObject;
import fr.unicaen.IAFighter.model.Robot;
import fr.unicaen.IAFighter.model.strategy.RandomStrategy;
import fr.unicaen.IAFighter.model.strategy.Strategy;

/**
 * Classe permettant de controler les strategies du robot selectionne
 *
 */
class StrategyGameObjectController implements ActionListener {
	private GameObject gameObject;
	
	/**
	 * Constructeur logique
	 * @param gameObject
	 */
	public StrategyGameObjectController(GameObject gameObject) {
		this.gameObject = gameObject;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> strategies = (JComboBox<String>) e.getSource();
		if(strategies.getSelectedItem() == null){
			((Robot)this.gameObject).setStrategy(new RandomStrategy());
		}
			
		if(strategies.getSelectedItem().equals(new RandomStrategy().getName()))
			((Robot)this.gameObject).setStrategy(new RandomStrategy());
		else
			((Robot)this.gameObject).setStrategy(new Strategy((String)strategies.getSelectedItem()));
	
		strategies.setSelectedItem(this.gameObject.getStrategyName());
	}

}
