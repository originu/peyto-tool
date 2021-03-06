package peyto.tool.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

@Component
public class CreateProjectCommands implements CommandMarker {
	
	@CliAvailabilityIndicator({"create project"})
	public boolean isSimpleAvailable() {
		//always available
		return true;
	}
	
	@CliCommand(value = "create project", help = "create a project template")
	public String simple(
		@CliOption( key = { "group" }, mandatory = true, help = "first name" ) final String group,
		@CliOption( key = { "name" }, mandatory = true, help = "second name" ) final String name) {
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream( new File( "./templates/eclipse4/projects.yml" ) );
			Yaml yaml = new Yaml();
			Object project	= yaml.load( fis );
			LinkedHashMap< String, Object >	map		= ( LinkedHashMap< String, Object > ) project;
			ArrayList< String >	items	= ( ArrayList< String > ) map.get( "project" );
			
			
			Configuration cfg = new Configuration();

			// Specify the data source where the template files come from. Here I set a
			// plain directory for it, but non-file-system are possible too:
			//cfg.setDirectoryForTemplateLoading( new File( "./templates/eclipse4" ) );

			// Specify how templates will see the data-model. This is an advanced topic...
			// for now just use this:
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			// Set your preferred charset template files are stored in. UTF-8 is
			// a good choice in most applications:
			cfg.setDefaultEncoding("UTF-8");

			// Sets how errors will appear. Here we assume we are developing HTML pages.
			// For production systems TemplateExceptionHandler.RETHROW_HANDLER is better.
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);

			// At least in new projects, specify that you want the fixes that aren't
			// 100% backward compatible too (these are very low-risk changes as far as the
			// 1st and 2nd version number remains):
			cfg.setIncompatibleImprovements(new Version(2, 3, 20));  // FreeMarker 2.3.20
			
			
			
			HashMap< String, Object > root = new HashMap< String, Object >();
			root.put( "group", group );
			root.put( "name", name );
			
			Set<String> keySet = map.keySet();
			for ( String projectKey : keySet ) {
				ArrayList< String >	projectNames	= ( ArrayList< String > )map.get( projectKey );
				
				HashMap< String, String > sub = new HashMap< String, String >();
				for ( String projectName : projectNames ) {
					sub.put( projectName, group + "-" + name +"-" + projectName );
				}
				root.put( projectKey, sub );
			}

			File	outputDir	= new File( "./output" );
			outputDir.mkdir();
			
			File baseDir	= new File( "./templates/eclipse4/");
			Path basePath 	= Paths.get( baseDir.toURI() );	// ./templates/eclipse4
			
			
			
			keySet = map.keySet();
			for ( String projectKey : keySet ) {
				ArrayList< String >	projectNames	= ( ArrayList< String > )map.get( projectKey );
				
				// projectName: build
				for ( String projectName : projectNames ) {
					File	baseProjectDir		= new File( baseDir, projectName );
					Path	baseProjectPath 	= Paths.get(baseProjectDir.toURI() );			// ./templates/eclipse4/build
					
					File outputBaseProjectDir = new File( outputDir, getProjectName( group, name, projectName ) );	// ./output/{projectName} 
					
					Collection< File > listFiles = FileUtils.listFilesAndDirs( baseProjectDir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE );
//					Collection< File > listFiles = FileUtils.listFiles( baseProjectDir, null, true );
					for ( File file : listFiles ) {
						Path	sourcePath			= Paths.get( file.toURI() );								// {full file}
						Path	relativizedPath		= baseProjectPath.relativize( sourcePath );					// ./build/{file}
						File 	relativeTargetFile	= relativizedPath.toFile();	relativeTargetFile.getParent();
						if ( file.isDirectory() ) {
							File	targetFile	= new File( outputBaseProjectDir, relativeTargetFile.getParent() == null ? "" : relativeTargetFile.getParent() );
							targetFile.mkdirs();
						} else {
							if ( FilenameUtils.isExtension( file.getName(), "ftl" ) ) {
								File	targetFile			= new File( 
										new File( outputBaseProjectDir, relativeTargetFile.getParent() == null ? "" : relativeTargetFile.getParent() ), 
										FilenameUtils.getBaseName( file.getName() ) );
								targetFile.getParentFile().mkdirs();
								Template template = cfg.getTemplate( file.toString(), "UTF-8" );
								FileOutputStream	fos	= new FileOutputStream( targetFile );
								System.out.println( targetFile );
								Writer	out	= new OutputStreamWriter( fos );
								try {
									template.process( root, out );
								} catch (TemplateException e) {
									e.printStackTrace();
								} finally {
									IOUtils.closeQuietly( fos );
									IOUtils.closeQuietly( out );
								}
							} else {
								File	targetFile			= new File( 
										new File( outputBaseProjectDir, relativeTargetFile.getParent() == null ? "" : relativeTargetFile.getParent() ), 
										file.getName() );
								FileUtils.copyFile( file, targetFile );
							}
							
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			IOUtils.closeQuietly( fis );
		}
		
		return "Message = [" + group + "] Location = [" + name + "]";
	}
	
	private String getProjectName( String group, String name, String project ) {
		return group + "-" + name + "-" + project;
	}
	
}