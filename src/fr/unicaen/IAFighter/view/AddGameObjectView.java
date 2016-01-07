package fr.unicaen.IAFighter.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import fr.unicaen.IAFighter.controller.ControllerFactory;
import fr.unicaen.IAFighter.model.Direction;
import fr.unicaen.IAFighter.model.GameBoard;

@SuppressWarnings("serial")
public class AddGameObjectView extends BaseView {
	private JButton addRobotBtn;
	private JTextField rowTxt, colTxt;
	private JComboBox<Direction> directionCbb;
	
	public AddGameObjectView(GameBoard model, ControllerFactory controller) {
		super(model);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints constraint1 = new GridBagConstraints();
		constraint1.gridx = 1;
		constraint1.gridy = 0;
		
		this.addRobotBtn = new JButton("Ajouter un robot");
		this.addRobotBtn.addActionListener(controller.getAddGameObjectController(model, this));
		
		this.add(this.addRobotBtn, constraint1);
		
		GridBagConstraints constraint11 = new GridBagConstraints();
		constraint11.gridx = 2;
		constraint11.gridy = 0;
		
		this.add(new JLabel("Row: "), constraint11);
		
		GridBagConstraints constraint2 = new GridBagConstraints();
		constraint2.gridx = 3;
		constraint2.gridy = 0;
		
		this.rowTxt = new JTextField();
		this.rowTxt.setText("1");
		this.rowTxt.setPreferredSize(new Dimension(40, 20));
		
		this.add(this.rowTxt, constraint2);
		
		GridBagConstraints constraint21 = new GridBagConstraints();
		constraint21.gridx = 4;
		constraint21.gridy = 0;
		
		this.add(new JLabel("Column: "), constraint21);
		
		GridBagConstraints constraint3 = new GridBagConstraints();
		constraint3.gridx = 5;
		constraint3.gridy = 0;
		
		
		this.colTxt = new JTextField();
		this.colTxt.setText("1");
		this.colTxt.setPreferredSize(new Dimension(40, 20));
		this.add(this.colTxt, constraint3);
		
		GridBagConstraints constraint4 = new GridBagConstraints();
		constraint4.gridx = 6;
		constraint4.gridy = 0;
		
		this.directionCbb = new JComboBox<Direction>(Direction.values());
		this.directionCbb.setSelectedItem(Direction.top);
		this.add(this.directionCbb, constraint4);
		
		GridBagConstraints constraint5 = new GridBagConstraints();
		constraint5.gridx = 7;
		constraint5.gridy = 0;
		
		JButton randomPosition = new JButton("Positions aléatoire");
		randomPosition.addActionListener(controller.getRandomPositionController(this.model, this));
		this.add(randomPosition);
		this.setVisible(true);
	}
	
	public Direction getDirection(){
		return (Direction) this.directionCbb.getSelectedItem();
	}
	
	public int getRow(){
		int row = 0;
		try{
		 row = Integer.parseInt(this.rowTxt.getText());
		 if(row <= 0)
			 throw new NumberFormatException();
		}
		catch(NumberFormatException e){
			row = 1;
		}
		
		return --row;
	}
	
	public int getCol(){
		int col = 0;
		try{
		 col = Integer.parseInt(this.colTxt.getText());
		 if(col <= 0)
			 throw new NumberFormatException();
		}
		catch(NumberFormatException e){
			col = 1;
		}
		
		return --col;
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
	}

	public void setRow(int row) {
		this.rowTxt.setText(Integer.toString(row));
	}
	
	public void setCol(int col) {
		this.colTxt.setText(Integer.toString(col));
	}

}
