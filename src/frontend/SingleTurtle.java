package frontend;

import methodInterfaces.*;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;


public class SingleTurtle implements Turtle {
    private ImageView body;
    private double x;
    private double y;
    private boolean pen;
    private Pane myPane;
    private static final double WIDTH = 30;
    private static final double HEIGHT = 30;
    private static final double DEFAULT_TURTLE_SIZE = 30;
    private static final double DISPLAY_WIDTH = Display.WIDTH;
    private static final double DISPLAY_HEIGHT = Display.HEIGHT;
    private boolean isActive;

    private ColorPicker penCol;

    // List of Lines that are being drawn by the turtle
    private List<Line> lines = new ArrayList<Line>();

    public SingleTurtle (ObjectProperty<Image> imageProperty, Pane myPane) {
        this.myPane = myPane;
        body = new ImageView();
        Bindings.bindBidirectional(this.body.imageProperty(), imageProperty);
        body.setFitWidth(WIDTH);
        body.setFitHeight(HEIGHT);
        x = 0;
        y = 0;
        pen = false;
        isActive = true;
        updateTurtleVisualPosition(false);
    }
    
    public ImageView getBody () {
        return body;
    }

    // Both visual getters return the center of the turtle's ImageView body
    private double getVisualX () {
        return body.getX() + DEFAULT_TURTLE_SIZE / 2;
    }

    private double getVisualY () {
        return body.getY() + DEFAULT_TURTLE_SIZE / 2;
    }

    private void setVisualCoordinates (double newX, double newY) {
        body.setX(newX - DEFAULT_TURTLE_SIZE / 2);
        body.setY(newY - DEFAULT_TURTLE_SIZE / 2);
    }

    private void toggleVisibility (double newOpacity) {
        body.setOpacity(newOpacity);
    }
    
    private void setCoordinates (double x, double y) {        
        this.x = x;
        this.y = y;
    }

    public void moveTurtleForward (double length) {
        double deltaX = Math.sin(getTurtleAngle() * Math.PI / 180) * length;
        double deltaY = Math.cos(getTurtleAngle() * Math.PI / 180) * length;

        // TODO:BUG: Large inputs crash program
        boolean flag = false;
        do {
            flag = false;

            for (int multiplier : new int[] { -1, 1 }) {
                if (multiplier * (getTurtleX() + deltaX) > DISPLAY_WIDTH / 2) {
                    double amountWalked = multiplier * DISPLAY_WIDTH / 2 - getTurtleX();
                    double deltaPrime = amountWalked / Math.tan(getTurtleAngle() * Math.PI / 180);

                    setCoordinates(multiplier * DISPLAY_WIDTH / 2, getTurtleY() + deltaPrime);
                    updateTurtleVisualPosition(false);
                    deltaX -= amountWalked;
                    deltaY -= deltaPrime;

                    setCoordinates(-multiplier * DISPLAY_WIDTH / 2, getTurtleY() + deltaPrime);

                    updateTurtleVisualPosition(true);

                    flag = true;
                }

                if (multiplier * (getTurtleY() + deltaY) > DISPLAY_HEIGHT / 2) {
                    double amountWalked = multiplier * DISPLAY_HEIGHT / 2 - getTurtleY();
                    double deltaPrime = amountWalked * Math.tan(getTurtleAngle() * Math.PI / 180);

                    setCoordinates(getTurtleX() + deltaPrime, multiplier * DISPLAY_HEIGHT / 2);
                    updateTurtleVisualPosition(false);
                    deltaY -= amountWalked;
                    deltaX -= deltaPrime;

                    setCoordinates(getTurtleX() + deltaPrime, -multiplier * DISPLAY_HEIGHT / 2);
                    updateTurtleVisualPosition(true);

                    flag = true;
                }
            }
        } while (flag);

        setCoordinates(getTurtleX() + deltaX, getTurtleY() + deltaY);
        updateTurtleVisualPosition(false);
    }

    // Takes current Turtle's Logo's (x,y) position and update the ImageView's javafx (x,y)
    public void updateTurtleVisualPosition (boolean overridePen) {
        double newX = DISPLAY_WIDTH / 2 + getTurtleX();
        double newY = DISPLAY_HEIGHT / 2 - getTurtleY();

        if (isTurtlePenDown() && !overridePen) {
            Line newLine = new Line(getVisualX(), getVisualY(), newX, newY);
            // newLine.setStroke(penCol.getValue());
            lines.add(newLine);
            myPane.getChildren().add(newLine);
        }

        // When updating coordinates, compensate the X and Y because they reference the edge of the
        // image, not the center
        setVisualCoordinates(newX, newY);
    }

    public void clearDisplay () {
        // Deletes all lines
        for (Line toBeDeleted : lines) {
            myPane.getChildren().remove(toBeDeleted);
        }

        lines.clear();
    }
    
    public double getTurtleX () {
        return x;
    }

    public double getTurtleY () {
        return y;
    }
    
    public boolean isActive () {
        return this.isActive;
    }

    public void setActive (boolean isActive) {
        this.isActive = isActive;
    }

    public double getTurtleAngle () {
        return body.getRotate();
    }

    public void turnTurtle (double angle) {
        body.setRotate(body.getRotate() + angle);
    }

    public void setTurtleAngle (double angle) {
        body.setRotate(angle);
    }

    public void turtlePenDown () {
        pen = true;
    }

    public void turtlePenUp () {
        pen = false;
    }

    public boolean isTurtlePenDown () {
        return pen;
    }

    public void hideTurtle () {
        toggleVisibility(0);
    }

    public void showTurtle () {
        toggleVisibility(1);
    }

    public boolean getTurtleVisibility () {
        return (body.getOpacity() == 1);
    }
    
    public void setTurtleCoordinates(double x, double y){
        setCoordinates(x,y);
        updateTurtleVisualPosition(false);
    }
 
}
