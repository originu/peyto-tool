package peyto.tool.commands;

import java.sql.SQLException;

import groovy.sql.Sql;

import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;

@Component
public class ExecuteSQLCommands implements CommandMarker {
	
	@CliAvailabilityIndicator({"execute sql"})
	public boolean isSimpleAvailable() {
		//always available
		return true;
	}
	
	@CliCommand(value = "execute sql", help = "execute sql files")
	public String execute( @CliOption( key = { "num" }, mandatory = true, help = "first name" ) final String num ) {
		String url = null;
		String user = null;
		String password = null;
		String driverClassName = null;
		try {
			Sql newInstance = Sql.newInstance( url, user, password, driverClassName );
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}