apply plugin: 'java'

jar {
    manifest {
        attributes 'Implementation-Title': '${project.common}', 'Implementation-Version': version
    }
}

dependencies {
    compile ( "org.mybatis:mybatis:3.2.2" )
}