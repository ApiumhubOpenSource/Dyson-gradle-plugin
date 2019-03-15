package com.apiumhub

import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.GradleException

/**
 * dyson-ngrok plugin.
 *
 * @author Diego
 * @since 15.03.2019
 */
@CompileStatic
class DysonNgrokPlugin implements Plugin<Project> {

    @Override
    @CompileStatic(TypeCheckingMode.SKIP)
    void apply(Project project) {
        DysonNgrokExtension extension = project.extensions.create('dysonNgrok', DysonNgrokExtension)

        project.afterEvaluate {
            if (extension.bar == null) {
                throw new GradleException('dysonNgrok.bar configuration required')
            }
        }
    }
}
