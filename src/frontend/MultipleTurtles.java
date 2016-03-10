package frontend;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import methodInterfaces.DisplayPenColor;
import methodInterfaces.DisplaySetBackground;
import methodInterfaces.DisplaySetPalette;
import methodInterfaces.DisplaySetPenColor;
import methodInterfaces.DisplaySetPenSize;
import methodInterfaces.DisplaySetShape;
import methodInterfaces.DisplayShape;
import methodInterfaces.DisplayStamp;
import methodInterfaces.TurtleBackward;
import methodInterfaces.TurtleClearScreen;
import methodInterfaces.TurtleForward;
import methodInterfaces.TurtleHeading;
import methodInterfaces.TurtleHideTurtle;
import methodInterfaces.TurtleHome;
import methodInterfaces.TurtleInterface;
import methodInterfaces.TurtleIsPenDown;
import methodInterfaces.TurtleIsShowing;
import methodInterfaces.TurtleLeft;
import methodInterfaces.TurtlePenDown;
import methodInterfaces.TurtlePenUp;
import methodInterfaces.TurtleRight;
import methodInterfaces.TurtleSetHeading;
import methodInterfaces.TurtleSetPosition;
import methodInterfaces.TurtleSetTowards;
import methodInterfaces.TurtleShowTurtle;
import methodInterfaces.TurtleXCor;
import methodInterfaces.TurtleYCor;


public class MultipleTurtles {
    private EntryManager turtleManager;
    private Pane myDisplayPane;
	private Map<String, TurtleInterface> turtleInstructions = new HashMap<String, TurtleInterface>();
    
    public MultipleTurtles (ObjectProperty<Image> imageProperty, EntryManager turtleManager, Pane displayPane) {
        this.turtleManager = turtleManager;
        this.myDisplayPane = displayPane;
        Turtle turtleOne = new SingleTurtle(imageProperty);
        turtleManager.addEntry(new StringObjectEntry("Turtle 1", turtleOne), false);
        
        
        
        
        createTurtleMap();
    }
    
	
	private void createTurtleMap() { //could make this a loop with a resource file and reflection <<< do if there is time
		turtleInstructions.put("Forward", new TurtleForward());
		turtleInstructions.put("Backward", new TurtleBackward());
		turtleInstructions.put("Right", new TurtleRight());
		turtleInstructions.put("Left", new TurtleLeft());
		turtleInstructions.put("SetHeading", new TurtleSetHeading());
		turtleInstructions.put("SetTowards", new TurtleSetTowards());
		turtleInstructions.put("SetPosition", new TurtleSetPosition());
		turtleInstructions.put("PenUp", new TurtlePenUp());
		turtleInstructions.put("PenDown", new TurtlePenDown());
		turtleInstructions.put("ShowTurtle", new TurtleShowTurtle());
		turtleInstructions.put("HideTurtle", new TurtleHideTurtle());
		turtleInstructions.put("Home", new TurtleHome());
		turtleInstructions.put("ClearScreen", new TurtleClearScreen());
		turtleInstructions.put("XCoordinate", new TurtleXCor());
		turtleInstructions.put("YCoordinate", new TurtleYCor());
		turtleInstructions.put("Heading", new TurtleHeading());
		turtleInstructions.put("IsPenDown", new TurtleIsPenDown());
		turtleInstructions.put("IsShowing", new TurtleIsShowing());
		turtleInstructions.put("SetBackground", new DisplaySetBackground());
		turtleInstructions.put("SetPenColor", new DisplaySetPenColor());
		turtleInstructions.put("SetPenSize", new DisplaySetPenSize());
		turtleInstructions.put("SetShape", new DisplaySetShape());
		turtleInstructions.put("SetPalette", new DisplaySetPalette());
		turtleInstructions.put("PenColor", new DisplayPenColor());
		turtleInstructions.put("Shape", new DisplayShape());
		turtleInstructions.put("Stamp", new DisplayStamp());
//		turtleInstructions.put("Id", new TurtleID());
//		turtleInstructions.put("Turtles", new TurtleTurtles());
//		turtleInstructions.put("Tell", new TurtleTell());
//		turtleInstructions.put("Ask", new TurtleAsk());
//		turtleInstructions.put("AskWith", new TurtleAskWith());
	}
	
	public double executeCommand(String s, double[] args, Display display){
		TurtleInterface turtleCommand = turtleInstructions.get(s);
		for(	: turtleManager.getEntryList()){

			turtleCommand.executeCommand(args, display, );
		}
		return turtleCommand.executeCommand(args, display);
	}
}