package peyto.tool;

import java.io.File;

public class Peyto {

	public static File APP_HOME() throws RuntimeException {
		String appHome	= System.getenv( "APP_HOME" );
		if ( "".equals( appHome ) || ( appHome == null ) ) {
			System.out.println( "It should configure APP_HOME first" );
			throw new RuntimeException( "It should configure APP_HOME first. APP_HOME=" + appHome );
		} else {
			return new File( appHome );
		}
	}
}
