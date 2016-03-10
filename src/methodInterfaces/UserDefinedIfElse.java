package methodInterfaces;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import backend.CommandParser;
import frontend.EntryManager;

public class UserDefinedIfElse implements UserDefinedInterface {

	@Override
	public void executeCommand(String command, CommandParser parser, ArrayList<String> userDefinedCommands,
			EntryManager terminal, EntryManager commandManager, EntryManager workspace) {
		String[] commandPieces = command.split("\\s+");
		try {
			int expr = Integer.parseInt(commandPieces[1]);
			Pattern p = Pattern.compile("\\[(.*?)\\]");
			Matcher m = p.matcher(command);
			String newTrueCommand = "";
			if (m.find()) {
				newTrueCommand = m.group(1);
				if ( userDefinedCommands.contains(parser.parseCommand(newTrueCommand.split("\\s+")[0])))
					newTrueCommand = newTrueCommand + "]";
			}
			else {
				parser.throwError("Not a Valid Command!");
				return;
			}
			String newFalseCommand = "";
			if (m.find()) {
				newFalseCommand = m.group(1);
				if ( userDefinedCommands.contains(parser.parseCommand(newFalseCommand.split("\\s+")[0])))
					newFalseCommand = newFalseCommand + "]";
			}
			else {
				parser.throwError("Not a Valid Command!");
				return;
			}
			if ( expr != 0 )
				parser.parse(newTrueCommand, terminal, commandManager, workspace);
			else
				parser.parse(newFalseCommand, terminal, commandManager, workspace);
		} catch (NumberFormatException e) {
			parser.throwError("Not a Valid Command!");
		}
	}

}