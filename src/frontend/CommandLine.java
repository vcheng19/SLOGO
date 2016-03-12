package frontend;

import java.util.ResourceBundle;

import backend.CommandParser;
import javafx.geometry.Pos;
//import backend.CommandParser;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class CommandLine {    
    private ResourceBundle sceneResources =
            ResourceBundle.getBundle(UserInterface.DEFAULT_RESOURCE_PACKAGE + UserInterface.SCENE);
    private static final double WIDTH = 500;
    private static final double HEIGHT = 35;
    private TextArea myTextField;
    private Button myGoButton;
    
    // Submitting code pressing SHIFT+ENTER
    private boolean shiftPressed = false;
    private boolean enterPressed = false;
    

    public CommandLine(CommandParser parser, EntryManager terminal, EntryManager command, EntryManager workspace ){
        initCommandLine(parser, terminal, command, workspace);
        
    }
    
    private void initCommandLine(CommandParser parser, EntryManager terminal, EntryManager command, EntryManager workspace){
        myTextField = new TextArea();       
        myTextField.getStyleClass().add(sceneResources.getString("COMMANDLINEID"));
        myTextField.setPrefSize(WIDTH, HEIGHT);
        myGoButton = new Button(sceneResources.getString("GOBUTTON")); 
        myGoButton.getStyleClass().add(sceneResources.getString("BUTTONID"));
        myGoButton.setOnAction(e -> enterCommand(parser, terminal, command, workspace));           
        myTextField.setOnKeyPressed(e -> keyPressed(e.getCode(), parser, terminal, command, workspace, true));
        myTextField.setOnKeyReleased(e -> keyPressed(e.getCode(), parser, terminal, command, workspace, false));
    }
    
    private void enterCommand(CommandParser parser, EntryManager terminal, EntryManager command, EntryManager workspace)
    {
    	shiftPressed = false;
    	enterPressed = false;
    	
    	if ( !myTextField.getText().isEmpty() )
        {
                parser.parse(myTextField.getText(), terminal, command, workspace, true, true);
                myTextField.clear();
        }

    }
    
    private void keyPressed(KeyCode code, CommandParser parser, EntryManager terminal, EntryManager command, EntryManager workspace, boolean beingPressed)
    {
        if ( code == KeyCode.SHIFT )
                shiftPressed = beingPressed;
        
        else if ( code ==  KeyCode.ENTER)
                enterPressed = beingPressed;

        if ( enterPressed && shiftPressed )
                enterCommand(parser, terminal, command, workspace);
        
    }
    
  
    
    public TextArea getTextField(){
        return myTextField;
    }
    public Node getButton(){
        return myGoButton;
    }    

}
