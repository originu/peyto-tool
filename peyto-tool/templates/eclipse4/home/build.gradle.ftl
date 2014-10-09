apply plugin: 'java'

jar {
    manifest {
        attributes 'Implementation-Title': '${project.home}', 'Implementation-Version': version
    }
}

dependencies {
}