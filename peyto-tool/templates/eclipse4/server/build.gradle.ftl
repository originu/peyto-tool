apply plugin: 'java'
apply plugin: 'war'
apply plugin: 'eclipse-wtp'

jar {
    manifest {
        attributes 'Implementation-Title': '${project.server}', 'Implementation-Version': version
    }
}

dependencies {
	compile ( "javax.servlet:javax.servlet-api:3.0.1" )
	compile ( "javax.servlet:jstl:1.2" )
	compile ( "taglibs:standard:1.1.2" )

	compile ( "org.springframework:spring-web:$org_springframework_version" )
	compile ( "org.springframework:spring-webmvc:$org_springframework_version" )
	
	compile ( "org.apache.tiles:tiles-extras:3.0.1" )

	compile ( "commons-fileupload:commons-fileupload:1.3" )

    compile ( "net.sf.ehcache:ehcache:2.7.2" )

	compile project( ":${project.service}" )
}

project.ext.finalName	= "${project.server}"

war {
	archiveName = project.ext.finalName + '.war'
}