package peyto.tool.meta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

//@RunWith( SpringJUnit4ClassRunner.class )
public class MetaLoaderTest {

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

//	@Test
//	public void loadProjects() {
//		FileInputStream fis = null;
//		try {
//			fis = new FileInputStream( new File( "./templates/eclipse4/projects.yml" ) );
//			Yaml yaml = new Yaml();
//			Object project	= yaml.load( fis );
//			LinkedHashMap< String, Object >	map	= (LinkedHashMap<String, Object>) project;
//			ArrayList< LinkedHashMap >	items	= (ArrayList<LinkedHashMap>) map.get( "projects" );
//			for ( LinkedHashMap< String, Object > item : items ) {
//				System.out.println( item.get( "project" ) );
//			}
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} finally {
//			IOUtils.closeQuietly( fis );
//		}
//	}
//	
//	
//	/**
//	 * projectName_common
//	 * projectName
//	 * 
//	 * @throws IOException
//	 */
//	@Test
//	public void loadFtl() throws IOException {
//		
//		Configuration cfg = new Configuration();
//
//		// Specify the data source where the template files come from. Here I set a
//		// plain directory for it, but non-file-system are possible too:
//		cfg.setDirectoryForTemplateLoading( new File( "./src/main/resources/templates/eclipse4/service" ) );
//
//		// Specify how templates will see the data-model. This is an advanced topic...
//		// for now just use this:
//		cfg.setObjectWrapper(new DefaultObjectWrapper());
//
//		// Set your preferred charset template files are stored in. UTF-8 is
//		// a good choice in most applications:
//		cfg.setDefaultEncoding("UTF-8");
//
//		// Sets how errors will appear. Here we assume we are developing HTML pages.
//		// For production systems TemplateExceptionHandler.RETHROW_HANDLER is better.
//		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
//
//		// At least in new projects, specify that you want the fixes that aren't
//		// 100% backward compatible too (these are very low-risk changes as far as the
//		// 1st and 2nd version number remains):
//		cfg.setIncompatibleImprovements(new Version(2, 3, 20));  // FreeMarker 2.3.20
//		
//		Map root = new HashMap();
//        root.put("project", "peyto-round-service");
//		
//		Template template = cfg.getTemplate( ".project.ftl", "UTF-8" );
//		
//		Writer	out	= new OutputStreamWriter( System.out );
//		try {
//			template.process( root, out );
//		} catch (TemplateException e) {
//			e.printStackTrace();
//		}
//	}

}
