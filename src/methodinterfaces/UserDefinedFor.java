package methodinterfaces;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import backend.CommandParser;
import frontend.Entry;
import frontend.EntryManager;
import frontend.StringNumEntry;

public class UserDefinedFor implements UserDefinedInterface {

	@Override
	public void executeCommand(String command, CommandParser parser, List<String> userDefinedCommands,
			EntryManager terminal, EntryManager commandManager, EntryManager workspace, boolean read) {
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher(command);
		String loopInfo = "";
		if (m.find())
			loopInfo = m.group(1);
		else {
			parser.throwError("Not a Valid Command!");
			return;
		}
		String newCommand = "";
		if (m.find()) {
			newCommand = m.group(1);
			if ( userDefinedCommands.contains(parser.parseCommand(newCommand.split("\\s+")[0])))
				newCommand = newCommand + "]";
		}
		else {
			parser.throwError("Not a Valid Command!");
			return;
		}
		String[] loopStuff = loopInfo.split("\\s+");
		if ( loopStuff.length != 4) {
			parser.throwError("Not a Valid Command!");
			return;
		}
		try {
			int startNum = Integer.parseInt(loopStuff[1]);
			int endNum = Integer.parseInt(loopStuff[2]);
			int increment = Integer.parseInt(loopStuff[3]);
			Entry repcount = new StringNumEntry(loopStuff[0],startNum);
			workspace.addEntry(repcount, true);
			for ( Integer i = startNum; i < endNum; i+=increment ) {
				boolean add = false;
				if(i == endNum - 1){
					add = true;
				}
				workspace.removeEntry(repcount);
				repcount.setSecondValue((double)i);
				workspace.addEntry(repcount, true);
				parser.parse(newCommand, terminal, commandManager, workspace, false, read, add);
			}
		} catch (NumberFormatException e) {
			parser.throwError("Not a Valid Command!");
		}
	}

}
