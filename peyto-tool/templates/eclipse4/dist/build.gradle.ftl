apply plugin: 'java'

jar {
    manifest {
        attributes 'Implementation-Title': '${project.dist}', 'Implementation-Version': version
    }
}

dependencies {
}