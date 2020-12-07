import java.awt.*;

/**
 * A rectangle-shaped Shape
 * Defined by an upper-left corner (x1,y1) and a lower-right corner (x2,y2)
 * with x1<=x2 and y1<=y2
 *
 * @author Mark Tao, Spring 2020
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012
 * @author CBK, updated Fall 2016
 */
public class Rectangle implements Shape {
	private int x1, y1, x2, y2;
	private Color color;

	//building the constructor from the string input
	public Rectangle(String string) {
		String [] stringSplit = string.split(" ");

		x1 = Integer.parseInt(stringSplit[0]);
		y1 = Integer.parseInt(stringSplit[1]);
		x2 = Integer.parseInt(stringSplit[2]);
		y2 = Integer.parseInt(stringSplit[3]);
	}

	//constructing the rectangle with x, y coordinates and color
	public Rectangle(int x, int y, Color color) {
		this.x1 = x;
		this.x2 = x;
		this.y1 = y;
		this.y2 = y;
		this.color = color;
	}

	//method that sets the corners
	public void setCorners(int x1, int y1, int x2, int y2) {
		this.x1 = x1; this.x2 = x2; this.y1 = y1; this.y2 = y2;
	}

	@Override
	public void moveBy(int dx, int dy) { //move the rectangle
		x1 = x1 + dx;
		y1 = y1 + dx;
		x2 = x2 + dx;
		y2 = y2 + dx;
	}

	@Override
	public Color getColor() { //getting the color
		return color;
	}

	@Override
	public void setColor(Color color) { //setting the color
		this.color = color;
	}
		
	@Override
	public boolean contains(int x, int y) { //returns whether a point is inside the rectangle
		if (x >= x1 && y >= y1 && x<= x2 && y <= y2) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void draw(Graphics g) { //calling it to draw itself
		g.setColor(color);
		int width = x2 - x1;
		int height = y2 - y1;
		g.fillRect(x1, y1, width, height);
	}

	public String toString() { //returning where the rectangle is at
		return "rectangle is at:" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + ", color is: " + color.getRGB();
	}
}
