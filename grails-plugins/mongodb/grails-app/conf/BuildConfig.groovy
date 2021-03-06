grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {

    inherits( "global" ) {
        excludes 'xml-apis', 'netty'
    }

    log "warn"

    def version = "1.0.0.M6"

    repositories {
        mavenCentral()
        grailsCentral()
        mavenRepo "http://repo.grails.org/grails/core"
        if (version.endsWith("-SNAPSHOT")) {
            mavenRepo "http://maven.springframework.org/snapshot"
        }
		else {
			mavenRepo 'http://maven.springframework.org/milestone'
		}
    }

    dependencies {

        def excludes = {
            excludes "slf4j-simple", "persistence-api", "commons-logging", "jcl-over-slf4j", "slf4j-api", "jta"
            excludes "spring-core", "spring-beans", "spring-aop", "spring-asm","spring-webmvc","spring-tx", "spring-context", "spring-web", "log4j", "slf4j-log4j12"
        }

        compile("org.mongodb:mongo-java-driver:2.5.3")
        compile("org.springframework.data:spring-data-mongodb:1.0.0.M3", excludes)
        runtime("com.gmongo:gmongo:0.8", excludes)
        compile("org.grails:grails-datastore-gorm-mongo:$version",
                "org.grails:grails-datastore-gorm:$version",
                "org.springframework:spring-datastore-core:$version",
                "org.springframework:spring-datastore-mongo:$version",
                "org.springframework:spring-datastore-web:$version") {
            transitive = false
        }

        test("org.grails:grails-datastore-gorm-test:$version",
             "org.springframework:spring-datastore-simple:$version"){
            transitive = false
        }
    }

    plugins {
        build( ":release:1.0.0.M2" ) {
            export = false
        }
    }
}
