package com.apiumhub

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.TaskOutcome

/**
 * @author Diego
 * @since 15.03.2019
 */
class DysonNgrokPluginKitTest extends AbstractKitTest {

    def "Check plugin execution"() {
        setup:
        build """
            plugins {
                id 'com.apiumhub.dyson-ngrok'
            }

            dysonNgrok {
                foo '1'
                bar '2'
            }

            task printFoo() << {
                println "fooo: \$dysonNgrok.foo"
            }

        """

        when: "run task"
        BuildResult result = run('printFoo')

        then: "task successful"
        result.task(':printFoo').outcome == TaskOutcome.SUCCESS
        result.output.contains('fooo: 1')
    }
}