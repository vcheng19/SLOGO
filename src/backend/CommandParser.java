package backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import frontend.UserInterface;

public class CommandParser {
	
	private String myCommand;
	private String myLanguage;
	private Parameters myParameters;

	public CommandParser() {
		// TODO Auto-generated constructor stub
	}
	
	public void parse(String command) {
		// TODO put it all together
		if ( command.equals("") )
			return;
		String[] commandPieces = command.split(" ");
		ParseNode commandTree = makeTree(commandPieces);
		
	}
	
	private void inputCommand(String command) {
		myCommand = command;
	}
	
	public void setLanguage(String language) {
		myLanguage = language;
	}
	
	private String parseCommand(String command) {
		try {
			FileInputStream fileInput = new FileInputStream(new File(myLanguage + ".properties"));
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			Enumeration commands = properties.keys();
			String desiredCommand = getDesiredCommand(properties,commands,command);
			if (desiredCommand.equals(""))
				UserInterface.displayError("That is not a command!");
			return desiredCommand;
		} catch (FileNotFoundException e) {
			throwError(e);
		} catch (IOException e) {
			throwError(e);
		}
	}
	
	private String getDesiredCommand(Properties properties, Enumeration commands, String command) {
		while ( commands.hasMoreElements() ) {
			String key = (String) commands.nextElement();
			String[] values = properties.getProperty(key).split("|");
			for ( String value: values) {
				if (value.equals(command)) {
					return key;
				}
			}
		}
		return "";
	}
	
	public Parameters getParameters() {
		return myParameters;
	}
	
	private void throwError(Exception e) {
		// CHANGE THIS LATER!!!!!!!!!!!!!!!!!!
		e.printStackTrace();
	}

}
