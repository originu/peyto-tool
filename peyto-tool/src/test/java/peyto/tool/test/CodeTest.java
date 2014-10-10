package peyto.tool.test;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CodeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Collection< File > listFiles = FileUtils.listFiles( new File( "./templates/eclipse4/" ), new String[]{ "ftl" }, true );
		for ( File file : listFiles ) {
			String baseName = FilenameUtils.getBaseName( file.getName() );
			System.out.println( file.getName() + " => " + baseName );
		}
	}
	
	@Test
	public void test1() {
		String	m = "D:\\development\\git_repository\\peyto-tool\\peyto-tool\\.\\templates\\eclipse4\\build\\.classpath.ftl";
		File	file	= new File( m );
		System.out.println( file.exists() );
	}	

}
