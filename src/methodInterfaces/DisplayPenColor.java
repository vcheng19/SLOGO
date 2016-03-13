package methodInterfaces;

import frontend.Display;
import frontend.DisplayPreferences;
import frontend.EntryManager;
import frontend.turtle.SingleTurtle;
import javafx.scene.paint.Color;

public class DisplayPenColor implements DisplayInterface {

	@Override
	public double executeCommand(double[] args, SingleTurtle turtle, Display display,
			DisplayPreferences displayPreferences, EntryManager colorManager, EntryManager shapeManager) {
		return Double.parseDouble(colorManager.getString(turtle.getPreferences().getPenColor()));
	}

}
