plugins {
    java
    application
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("eu.veldsoft.melsambria.Main")
}

tasks.test {
    useJUnitPlatform()
}
