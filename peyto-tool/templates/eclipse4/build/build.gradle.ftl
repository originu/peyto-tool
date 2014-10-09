allprojects {

	project.ext.org_springframework_version		= '4.1.1.RELEASE'
	project.ext.org_springframework_security	= '3.2.5.RELEASE'
	project.ext.org_aspectj						= '1.6.9'
	project.ext.org_slf4j						= '1.7.7'
    
    project( ':${project.common}'	).version	= '1.0.0.RELEASE'
    project( ':${project.service}'	).version	= '1.0.0.RELEASE'
    project( ':${project.server}'	).version	= '1.0.0.RELEASE'
}

subprojects {

	apply plugin: 'java'
	apply plugin: 'eclipse'

    repositories {
       mavenCentral()
    }

	eclipse {
	    classpath {
	       downloadSources=true
	    }
	}
    
    sourceCompatibility = 1.7
    targetCompatibility = 1.7
    
    dependencies {
    	compile ( "org.springframework:spring-context:$org_springframework_version" ) {
    	 	exclude( group: "commons-logging", module: "commons-logging" )
    	}
    	compile ( "org.springframework:spring-core:$org_springframework_version" ) {
    	 	exclude( group: "commons-logging", module: "commons-logging" )
    	}
    	compile ( "org.springframework:spring-beans:$org_springframework_version" )
    	compile ( "org.springframework:spring-tx:$org_springframework_version" )
    	compile ( "org.springframework:spring-aop:$org_springframework_version" )
    	compile ( "org.springframework:spring-expression:$org_springframework_version" )
	    compile ( "org.springframework:spring-jdbc:$org_springframework_version" )
	    compile ( "org.springframework:spring-orm:$org_springframework_version" )
		compile ( "org.springframework:spring-context-support:$org_springframework_version" )
    	compile ( "org.springframework:spring-test:$org_springframework_version" )

    	compile ( "org.slf4j:slf4j-api:$org_slf4j" )
    	compile ( "ch.qos.logback:logback-classic:1.1.2" )
    	runtime ( "org.slf4j:jcl-over-slf4j:$org_slf4j" )
    	runtime ( "org.slf4j:slf4j-log4j12:$org_slf4j" )
    	compile ( "log4j:log4j:1.2.15" ) {
    		exclude( group: "javax.mail", module: "mail" )
    		exclude( group: "javax.jms", module: "jms" )
    		exclude( group: "com.sun.jdmk", module: "jmxtools" )
    		exclude( group: "com.sun.jmx", module: "jmxri" )
    	}

    	compile ( "commons-io:commons-io:2.4" )
    	compile ( "commons-lang:commons-lang:2.6" )
    	compile ( "commons-configuration:commons-configuration:1.9" )
    	compile ( "commons-collections:commons-collections:3.2.1" )
    	compile ( "commons-beanutils:commons-beanutils:1.8.3" )
    	compile ( "org.codehaus.jackson:jackson-core-asl:1.9.10" )
    	compile ( "org.codehaus.jackson:jackson-mapper-asl:1.9.10" )
    	compile ( "org.aspectj:aspectjrt:$org_aspectj" )

		testCompile ( "junit:junit:4.+" )
    }

    jar {
        manifest.attributes provider: 'kevin ryu'
    }

	uploadArchives {
	    repositories {
	       flatDir {
	           dirs 'repos'
	       }
	    }
	}
	
	test {
		systemProperties 'property': 'value'
	}
	
}

void process( String buildNumber, Project project ) {

	Project distProject	= project( ':${project.dist}' ) 
	Project homeProject	= project( ':${project.home}' )

	File	distBuildNumProject	= file( new File( distProject.projectDir, buildNumber ) )

	// copy a war
	copy {
		from file( new File( project.libsDir, project.finalName + ".war" ) )
		into file( new File( distBuildNumProject, project.finalName + '/war' ) )
	}

	// copy configuration files
	copy {
		from ( file( new File( homeProject.projectDir, project.finalName ) ) ) {
			exclude( 'logs/*' )
			exclude( 'deployment/**' )
		}
		into file( new File( distBuildNumProject, project.finalName + '/${project.home}' ) )
	}

	// override configuration files
	copy {
		from file( new File( homeProject.projectDir, project.finalName + '/deployment' ) )
		into file( new File( distBuildNumProject, project.finalName + '/${project.home}' ) )
	}
}

task deployToDist << {
	String	buildNumber		= new Date().format( "yyyyMMdd_HHmmss" )

	process( buildNumber, project( ':${project.server}' ) )
}

task triggerToJenkins << {
	ant.get( 
		src: 'http://127.0.0.1:8080/jenkins/job/${group}_trigger_build/build?token=${group}_trigger_build',
		dest: 'temp',
		username: 'kevin',
		password: '000000' )
	ant.delete( file: 'temp' )
}
