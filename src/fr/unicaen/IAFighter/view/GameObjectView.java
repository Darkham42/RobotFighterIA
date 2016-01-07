package fr.unicaen.IAFighter.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import fr.unicaen.IAFighter.controller.ControllerFactory;
import fr.unicaen.IAFighter.model.Direction;
import fr.unicaen.IAFighter.model.GameBoard;
import fr.unicaen.IAFighter.model.GameObject;
import fr.unicaen.IAFighter.model.Robot;
import fr.unicaen.IAFighter.model.TypeObject;
import fr.unicaen.IAFighter.model.strategy.StrategyReader;

@SuppressWarnings("serial")
class GameObjectView extends BaseView {
	private GameObject gameObject;
	private JButton colorBtn, deleteBtn;
	private JComboBox<Direction> directionCbb;
	private JLabel sizeAll, image;
	GameObjectView(GameBoard model, GameObject gameObject, ControllerFactory controller) {
		super(model);
		this.gameObject = gameObject;
		this.setLayout(new GridLayout(0, 7, 5, 0));
		this.setSize(700, 50);
		
		
		//GameObject Image
		image = new JLabel();
		try {
			image.setIcon(new ImageIcon(this.gameObject.print()));
		} catch (IOException e) {
			image.setText("Aucune Image");
		}
		if(this.gameObject.getTypeObject() == TypeObject.robot)
			image.setText(Integer.toString(((Robot)this.gameObject).getEnergie()));
		this.add(image);
		
		//color
		colorBtn = new JButton();
		colorBtn.setBackground(this.gameObject.getColor());
		colorBtn.addActionListener(controller.getColorGameObjectController(this.model, this.gameObject));
		this.add(colorBtn);
		
		if(this.gameObject.getTypeObject() == TypeObject.robot){
			//strategy Inputs
			JComboBox<String> strategies = new JComboBox<String>(new StrategyReader().lireNomStrategies());
			strategies.setSelectedItem((String) this.gameObject.getStrategyName());
			strategies.addActionListener(controller.getStrategyGameObjectController(this.gameObject));
			this.add(strategies);
		}
		
		//Direction
		this.directionCbb = new JComboBox<Direction>(Direction.values());
		
		this.directionCbb.setSelectedItem(this.gameObject.getDirection());
		this.directionCbb.addActionListener(controller.getDirectionGameObjectController(this.gameObject));
		this.add(this.directionCbb);
		
		//row inputs
		JTextField row = new JTextField();
		row.setText(Integer.toString(this.gameObject.getRow()+1));
		row.addActionListener(controller.getRowGameObjectController(this.gameObject));
		this.add(row);
		
		//col inputs
		JTextField col = new JTextField();
		col.setText(Integer.toString(this.gameObject.getCol()+1));
		col.addActionListener(controller.getColGameObjectController(this.gameObject));
		this.add(col);
		
		//dell
		deleteBtn = new JButton("Delete");
		deleteBtn.setBackground(Color.RED);
		deleteBtn.addActionListener(controller.getDeleteGameObjectController(this.model, this.gameObject));
		this.add(deleteBtn);
		
		this.setVisible(true);
		sizeAll = new JLabel("SizeAll: " + this.model.listGameObjects().size());
	}

	@Override
	public void update(Observable o, Object arg) {
		if(!this.model.listGameObjects().contains(this.gameObject)){
			this.setVisible(false);
			return;
		}
		
		this.colorBtn.setBackground(this.gameObject.getColor());
		sizeAll.setText("SizeAll: " + this.model.listGameObjects().size());
		
		if(this.gameObject.getTypeObject() == TypeObject.robot)
			this.image.setText(Integer.toString(((Robot)this.gameObject).getEnergie()));
	}
}
