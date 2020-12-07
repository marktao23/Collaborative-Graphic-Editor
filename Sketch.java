import java.awt.*;
import java.util.*;

/**
 * Does the actual sketching of the shapes in the graphical editor, description of each method in comments
 *
 * @author Mark Tao, Spring 2020
 */

public class Sketch {

    //instance variables that represent the maps the shapes will be added to
    private TreeMap<Integer, Shape> allShapes;
    private HashMap<Shape, Integer> shapeIDS;
    Shape shape = null;

    //constructor
    public Sketch() {

    }

    //creating the shape with its ID, and two string inputs and adding to map
    public synchronized void create(int shapeID, String firstInput, String secondInput) {

        if (firstInput.equals("polyline")) { //input for polyline
            shape = new Polyline(secondInput);
            allShapes.put(shapeID, shape);
            shapeIDS.put(shape, shapeID);
        }
        else if (firstInput.equals("ellipse")) { //input for ellipse
            shape = new Ellipse(secondInput);
            allShapes.put(shapeID, shape);
            shapeIDS.put(shape, shapeID);
        }
        else if (firstInput.equals("rectangle")) { //input for the rectangle
            shape = new Rectangle(secondInput);
            allShapes.put(shapeID, shape);
            shapeIDS.put(shape, shapeID);
        }
        else if (firstInput.equals("segment")) { //input for segment
            shape = new Segment(secondInput);
            allShapes.put(shapeID, shape);
            shapeIDS.put(shape, shapeID);
        }
    }

    //draw the shape
    public synchronized void draw(Graphics g) {
        for (int i = 0; i < allShapes.size(); i ++) {
            allShapes.get(i).draw(g);
        }
    }

    //getting the shape that is clicked on, but going backwards so that the newest ones are returned before the older ones
    public Shape getClickedShape(Point point) {
        for (Integer shapeID : allShapes.descendingKeySet()) {
            if (allShapes.get(shapeID).contains(point.x, point.y)) {
                return allShapes.get(shapeID);
            } else {
                return null;
            }
        }
        return null;
    }

    //moving shape by its ID, and x/y
    public synchronized void move(int ID, int x, int y) {
        allShapes.get(ID).moveBy(x, y);
    }

    //removes the shape
    public synchronized void remove(int ID) {
        allShapes.remove(ID);
    }

    //change the color of a shape to input
    public synchronized void recolor(int ID, int color) {
        allShapes.get(ID).setColor(new Color(color));
    }

    //getters for the shape, the IDs, and all shapes
    public int getshapeID(Shape shape) {
        return shapeIDS.get(shape);
    }
}
