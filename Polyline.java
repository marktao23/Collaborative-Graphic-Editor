import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A multi-segment Shape, with straight lines connecting "joint" points -- (x1,y1) to (x2,y2) to (x3,y3) ...
 *
 * @author Mark Tao, Spring 2020
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Spring 2016
 * @author CBK, updated Fall 2016
 */
public class Polyline implements Shape {

	private final static double minDistance = 5;
	private Color color;

	private ArrayList<Point> listOfPoints = new ArrayList<>(); //list of points

	public Polyline(int x, int y, Color c) { //calling the constructor of regular polyline
		listOfPoints.add(new Point(x, y));
		this.color = c;
	}

	public Polyline(String input) { //polyline constructed from a string input
		String [] splitString = input.split(" ");

		Color newColor = new Color(Integer.parseInt(splitString[splitString.length - 1])); //declaring new color
		color = newColor;

		for (int i = 0; i < splitString.length - 2; i += 2) { //adding all points to the list of points from the input
			Point addThisPoint = new Point(Integer.parseInt(splitString[i]), Integer.parseInt(splitString[i + 1]));
			listOfPoints.add(addThisPoint);
		}
	}

	//method to add a point
	public void addPoint(Point point) {
		listOfPoints.add(point);
	}

	@Override
	public void moveBy(int dx, int dy) { //method that moves the object
		for (int i = 0; i < listOfPoints.size() - 1; i ++) {
			listOfPoints.get(i).x = listOfPoints.get(i).x + dx;
			listOfPoints.get(i).y = listOfPoints.get(i).y + dy;
		}
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public boolean contains(int x, int y) { //returns whether or not a coordinate is inside the shape
		for (int i = 0; i < listOfPoints.size() - 1; i ++) {
			return Segment.pointToSegmentDistance(x, y, listOfPoints.get(i).x,
					listOfPoints.get(i).y, listOfPoints.get(i + 1).x, listOfPoints.get(i + 1).y) < minDistance;
		}
		return false;
	}

	@Override
	public void draw(Graphics g) { //looping through all of the elements in the list of points and drawing them
		g.setColor(color);
		for (int i = 0; i < listOfPoints.size() - 1; i ++) {
			g.drawLine(listOfPoints.get(i).x, listOfPoints.get(i).y, listOfPoints.get(i+1).x, listOfPoints.get(i+1).y);
		}
	}

	@Override
	public String toString() { //returns the string
		StringBuilder res = new StringBuilder("the polyline is: ");
		for(Point point: listOfPoints) {
			if (point != null) {
				res.append(" ");
				}
			assert point != null;
			res.append(point.x).append(" ").append(point.y);
		}
		res.append(" ").append(color.getRGB());
		return res.toString();
	}
}
