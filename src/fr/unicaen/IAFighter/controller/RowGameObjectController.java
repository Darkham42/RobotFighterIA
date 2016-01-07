package fr.unicaen.IAFighter.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

import fr.unicaen.IAFighter.model.Cell;
import fr.unicaen.IAFighter.model.GameObject;
import fr.unicaen.IAFighter.model.TypeObject;

/**
 * Controle la ligne du GameObject selectionne
 *
 */
class RowGameObjectController implements ActionListener {
	private GameObject gameObject;
	
	/**
	 * constructeur logique
	 * @param gameObject
	 */
	public RowGameObjectController(GameObject gameObject) {
		this.gameObject = gameObject;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField textField = (JTextField) e.getSource();
		int row = 0;
		int col = this.gameObject.getCol();
		
		try{
			row = Integer.parseInt(textField.getText()) - 1;
			if(!this.gameObject.getModel().inGrid(row, col)){
				throw new NumberFormatException();
			}
				
		}
		catch(NumberFormatException exc){
			textField.setText(Integer.toString(this.gameObject.getRow() + 1));
			return;
		}
		
		Cell cell = this.gameObject.getModel().getBoard()[row][col];
			if(cell.numberOf(TypeObject.robot) != 0){
				textField.setText(Integer.toString(this.gameObject.getRow() + 1));
				return;
			}
			
			this.gameObject.getModel().getBoard()[this.gameObject.getRow()][col].removeContent(this.gameObject);
		this.gameObject.updatePosition(row, col);
		cell.setContent(this.gameObject);
		
		textField.setText(Integer.toString(row+1));
	}
}
