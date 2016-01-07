package fr.unicaen.IAFighter.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;

import javax.swing.JScrollPane;

import fr.unicaen.IAFighter.controller.ControllerFactory;
import fr.unicaen.IAFighter.controller.InitializeGameFactory;
import fr.unicaen.IAFighter.model.GameBoard;

@SuppressWarnings("serial")
class InitializeGameView extends BaseView {
	private final static ControllerFactory controller = new InitializeGameFactory();
	private JScrollPane gameObjectsView;
	private int numberGameObjects;
	InitializeGameView(GameBoard model) {
		super(model);
		this.setMinimumSize(new Dimension(700, 300));
		
		this.setLayout(new GridBagLayout());
		
		this.addChangeViewBtn(1, "Jouer", new GameState());
		this.addChangeViewBtn(2, "Retour Initialisation de la Grille", new InitializeGridState());
		this.addChangeViewBtn(3, "Menu", new MainState());
		
		GridBagConstraints constraint1 = new GridBagConstraints();
		constraint1.gridx = 0;
		constraint1.gridy = 4;
		//constraint1.gridheight = 3;
		GameObjectsView view = new GameObjectsView(this.model, InitializeGameView.controller);
		gameObjectsView = new JScrollPane(view);
		gameObjectsView.setPreferredSize(this.getMinimumSize());
		this.add(gameObjectsView, constraint1);
		
		GridBagConstraints constraint2 = new GridBagConstraints();
		constraint2.gridx = 0;
		constraint2.gridy = 5;
		AddGameObjectView addGameObjectView = new AddGameObjectView(model, controller);
		this.add(addGameObjectView, constraint2);
		
		this.setVisible(true);
		this.numberGameObjects = this.model.listGameObjects().size();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(this.numberGameObjects != this.model.listGameObjects().size()){
			this.numberGameObjects = this.model.listGameObjects().size();
			this.gameObjectsView.revalidate();
		}
	}

}
