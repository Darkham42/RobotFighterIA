package fr.unicaen.IAFighter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.unicaen.IAFighter.model.GameBoard;
import fr.unicaen.IAFighter.model.Robot;
import fr.unicaen.IAFighter.view.AddGameObjectView;

/**
 * Controle les actions de l'utilisateur sur la vue et ajoute un Robot dans la liste
 * des joueurs du modele
 *
 */
class AddGameObjectController extends BaseController implements ActionListener{
	
	private AddGameObjectView view;
	
	/**
	 * instancie la classe AddGameObjectController
	 * le controleur a besoin du modele pour repercuter les actions sur la vue
	 * @param model 
	 * @param view 
	 */
	public AddGameObjectController(GameBoard model, AddGameObjectView view) {
		super(model);
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Robot robot = new Robot(this.model);
		robot.updatePosition(this.view.getRow(), this.view.getCol());
		
		if(!this.model.inGrid(robot.getRow(), robot.getCol())){
			return;
		}
		
		if(this.model.getBoard()[robot.getRow()][robot.getCol()].getContent().length == 0){
			robot.setDirection(this.view.getDirection());
			this.model.addGameObject(robot);
		}
	}

}
