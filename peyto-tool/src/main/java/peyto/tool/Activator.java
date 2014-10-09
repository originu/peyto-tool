package peyto.tool;

import java.io.IOException;

import org.springframework.shell.Bootstrap;

public class Activator {
	
	public static void main(String[] args) throws IOException {
		Bootstrap.main( args );
		
		try {
			Thread.sleep( Integer.MAX_VALUE );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
