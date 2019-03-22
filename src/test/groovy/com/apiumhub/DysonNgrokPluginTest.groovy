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

    def "Assert mandatory data is provided"() {

        when: "plugin configured"
        Project project = project {
            apply plugin: "com.apiumhub.dyson-ngrok"

            dysonNgrok {
                dysonFolder '~/someFolder'
                subdomain 'myapplication'
                port 8080
            }
        }

        then: "validation pass"
        def dysonNgrok = project.extensions.dysonNgrok
        dysonNgrok.dysonFolder == '~/someFolder'
        dysonNgrok.subdomain == 'myapplication'
        dysonNgrok.port == 8080
    }

    def "Check extension validation failure"() {

         when: "plugin configured without dysonFolder"
        project {
            apply plugin: "com.apiumhub.dyson-ngrok"

            dysonNgrok {
                subdomain 'myapplication'
                port 8080
            }
        }

        then: "missing dyson folder exception is throws"
        def folderException = thrown(ProjectConfigurationException)
        folderException.cause.message == 'dysonNgrok.dysonFolder is mandatory'

        when: "plugin configured without dysonFolder"
        project {
            apply plugin: "com.apiumhub.dyson-ngrok"

            dysonNgrok {
                dysonFolder '~/someFolder'
                port 8080
            }
        }

        then: "missing subdomain exception is throws"
        def subdomainException = thrown(ProjectConfigurationException)
        subdomainException.cause.message == 'dysonNgrok.subdomain is mandatory'

        when: "plugin configured without port"
        project {
            apply plugin: "com.apiumhub.dyson-ngrok"

            dysonNgrok {
                dysonFolder '~/someFolder'
                subdomain 'myapplication'
            }
        }

        then: "missing subdomain exception is throws"
        def portException = thrown(ProjectConfigurationException)
        portException.cause.message == 'dysonNgrok.port is mandatory'
    }

}