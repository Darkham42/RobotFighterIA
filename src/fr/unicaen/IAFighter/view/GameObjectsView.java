package fr.unicaen.IAFighter.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;

import fr.unicaen.IAFighter.controller.ControllerFactory;
import fr.unicaen.IAFighter.model.*;

@SuppressWarnings("serial")
class GameObjectsView extends BaseView {
	private ControllerFactory controller;
	private int numberGameObjects;
	
	GameObjectsView(GameBoard model, ControllerFactory controller) {
		super(model);
		this.controller = controller;
		this.setLayout(new GridBagLayout());
		this.constructView();
		this.setVisible(true);
		
		this.numberGameObjects = this.model.listGameObjects().size();
	}
	
	private void constructView(){
		for(int i=0 ; i < this.model.listGameObjects().size() ; i++){
			GridBagConstraints constraint = new GridBagConstraints();
			constraint.gridx = 0;
			constraint.gridy = i+1;
			constraint.insets = new Insets(10,10,10,10);
			this.add(new GameObjectView(this.model, this.model.listGameObjects().get(i), controller), constraint);
		}
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		if(this.numberGameObjects != this.model.listGameObjects().size()){
			this.removeAll();
			this.constructView();
			this.repaint();
			this.numberGameObjects = this.model.listGameObjects().size();
		}
	}

}
