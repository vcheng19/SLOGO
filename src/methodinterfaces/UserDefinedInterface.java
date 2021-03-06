package methodinterfaces;

import java.util.List;

import backend.CommandParser;
import frontend.EntryManager;

public interface UserDefinedInterface {
	public void executeCommand(String command, CommandParser parser, List<String> userDefinedCommands,
			EntryManager terminal, EntryManager commandManager, EntryManager workspace, boolean read);
}
