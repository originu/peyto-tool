package peyto.tool;

import java.io.File;
import java.io.IOException;

import org.springframework.shell.Bootstrap;

/**
 * Activator
 * @author kevin
 *
 */
public class Activator {
	
	public static void main(String[] args) throws IOException {
		try {
			File APP_HOME = Peyto.APP_HOME();
			Bootstrap.main( args );
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
}
