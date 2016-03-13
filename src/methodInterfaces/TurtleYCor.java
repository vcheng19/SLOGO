package methodInterfaces;

import frontend.turtle.SingleTurtle;

public class TurtleYCor implements TurtleInterface {
	
	@Override
	public double executeCommand(double[] args, SingleTurtle display) {
		return display.getTurtleY();
	}

}