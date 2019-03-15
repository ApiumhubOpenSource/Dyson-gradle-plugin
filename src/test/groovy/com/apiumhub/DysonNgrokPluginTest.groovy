package com.apiumhub

import org.gradle.api.Project
import org.gradle.api.ProjectConfigurationException
import org.gradle.testfixtures.ProjectBuilder

/**
 * @author Diego
 * @since 15.03.2019
 */
class DysonNgrokPluginTest extends AbstractTest {

    def "Check extension registration"() {

        when: "plugin applied"
        Project project = ProjectBuilder.builder().build()
        project.plugins.apply "com.apiumhub.dyson-ngrok"

        then: "extension registered"
        project.extensions.findByType(DysonNgrokExtension)

    }

    def "Check extension validation"() {

        when: "plugin configured"
        Project project = project {
            apply plugin: "com.apiumhub.dyson-ngrok"

            dysonNgrok {
                foo '1'
                bar '2'
            }
        }

        then: "validation pass"
        def dysonNgrok = project.extensions.dysonNgrok;
        dysonNgrok.foo == '1'
        dysonNgrok.bar == '2'
    }


    def "Check extension validation failure"() {

        when: "plugin configured"
        Project project = project {
            apply plugin: "com.apiumhub.dyson-ngrok"

            dysonNgrok {
                foo '1'
            }
        }

        then: "validation failed"
        def ex = thrown(ProjectConfigurationException)
        ex.cause.message == 'dysonNgrok.bar configuration required'
    }

}