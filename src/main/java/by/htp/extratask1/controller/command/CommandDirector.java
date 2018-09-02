package by.htp.extratask1.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandDirector {
	private Map<String, Command> commands = new HashMap<>();

	
	public CommandDirector() {
		commands.put("createEntry", new CreationEntryCommand());
		commands.put("searchEntry", new SearchingEntryCommand());
	}
	
	public Command getCommand(String commandName) {
		return commands.get(commandName);
	}
}
