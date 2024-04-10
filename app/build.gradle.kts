plugins {
    application
    checkstyle
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories { mavenCentral() }

application { mainClass.set("hexlet.code.App") }

dependencies {
    implementation("info.picocli:picocli:4.6.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

jacoco {
    toolVersion = "0.8.11"
    reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}


