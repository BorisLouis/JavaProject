package player;
import miscellaneousItem.Item;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
public class Player {
	private String name;
	private Point2D currentPosition;
	public List<Point2D> positions = new  ArrayList<Point2D>() ;
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private int lineOfSight=2;//Only used in hard mode



	public Player(String playerName, Point2D playerPosition)
	{
		this.name = playerName;
		this.currentPosition = playerPosition;

		this.positions.add(this.currentPosition);
	}

	public String getPlayerName() {
		return (this.name);
	}

	public List<Point2D> getPlayerPosition() {
		return(this.positions);
	}

	public Point2D getPlayerPosition(int index) {
		return (this.positions.get(index));
	}


	public void setPlayerPosition(Point2D newPosition) {
		this.currentPosition = newPosition;
	}

	public void movePlayer(String direction, ArrayList <ArrayList<String>> mazeDescription) {

		switch (direction) {

		case "Up": {
			List <Point2D> newPos = new ArrayList <Point2D>();
			for (int i = 0; i<this.positions.size();i++)
			{
				newPos.add(new Point2D.Double(this.positions.get(i).getX(),this.positions.get(i).getY()));
			}
			double yPos = this.currentPosition.getY();
			double xPos = this.currentPosition.getX();
			yPos -=1;


			this.currentPosition.setLocation(xPos, yPos);
			newPos.add(new Point2D.Double(xPos,yPos));
			this.positions = newPos;
			break;}

		case "Left":{ 
			List <Point2D> newPos = new ArrayList <Point2D>();
			for (int i = 0; i<this.positions.size();i++)
			{
				newPos.add(new Point2D.Double(this.positions.get(i).getX(),this.positions.get(i).getY()));
			}
			double yPos = this.currentPosition.getY();
			double xPos = this.currentPosition.getX();
			xPos -=1;

			this.currentPosition.setLocation(xPos, yPos);
			newPos.add(new Point2D.Double(xPos,yPos));
			this.positions = newPos;
			break;}

		case "Right":{ 
			List <Point2D> newPos = new ArrayList <Point2D>();
			for (int i = 0; i<this.positions.size();i++)
			{
				newPos.add(new Point2D.Double(this.positions.get(i).getX(),this.positions.get(i).getY()));
			}
			double yPos = this.currentPosition.getY();
			double xPos = this.currentPosition.getX();

			xPos +=1;
			this.currentPosition.setLocation(xPos, yPos);
			newPos.add(new Point2D.Double(xPos,yPos));
			this.positions = newPos;
			break;}

		case "Down":{
			List <Point2D> newPos = new ArrayList <Point2D>();
			for (int i = 0; i<this.positions.size();i++)
			{
				newPos.add(new Point2D.Double(this.positions.get(i).getX(),this.positions.get(i).getY()));
			}
			double yPos = this.currentPosition.getY();
			double xPos = this.currentPosition.getX();

			yPos +=1;
			this.currentPosition.setLocation(xPos, yPos);
			newPos.add(new Point2D.Double(xPos,yPos));
			this.positions = newPos;
			break;}
		}
	}

	public ArrayList<Item> getPlayerInventory(){
		return (this.inventory);

	}
	public int getPlayerSight() {
		return lineOfSight;
	}
}
