apply plugin: 'java'

jar {
    manifest {
        attributes 'Implementation-Title': '${project.service}', 'Implementation-Version': version
    }
}

dependencies {

    compile ( "org.mybatis:mybatis-spring:1.2.0" )
    compile ( "org.mybatis:mybatis:3.2.2" )

    //compile ( "org.hibernate:hibernate-core:4.3.5.Final" )
	// for JPA, use hibernate-entitymanager instead of hibernate-core
    //compile ( "org.hibernate:hibernate-entitymanager:4.3.5.Final" )

    compile ( "net.sf.ehcache:ehcache:2.7.2" )

    compile project ( ":${project.common}" )
    
    // DB::mssql
    //runtime files( "../${project.home}/lib/sqljdbc4-4.0.jar" ) 
    
    // DB::oracle
    runtime files( "../${project.home}/lib/ojdbc14-10.2.0.1.0.jar" )
    
    // DB::mariadb
    //runtime files( "../${project.home}/lib/mariadb-java-client-1.1.7.jar" )
}
