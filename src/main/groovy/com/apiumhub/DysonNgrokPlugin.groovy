package com.apiumhub

import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 1.- Carpeta para meter como volumen al docker de Dyson
 * 2.- Subdomain para serveo
 * 3.- Puerto
 */
@CompileStatic
class DysonNgrokPlugin implements Plugin<Project> {

    @Override
    @CompileStatic(TypeCheckingMode.SKIP)
    void apply(Project project) {
        DysonNgrokExtension extension = project.extensions.create('dysonNgrok', DysonNgrokExtension)

        project.afterEvaluate {
            if (extension.dysonFolder == null) {
                throw new GradleException('dysonNgrok.dysonFolder is mandatory')
            }
            if (extension.subdomain == null) {
                throw new GradleException('dysonNgrok.subdomain is mandatory')
            }
            if (extension.port == null) {
                throw new GradleException('dysonNgrok.port is mandatory')
            }
        }
    }
}
