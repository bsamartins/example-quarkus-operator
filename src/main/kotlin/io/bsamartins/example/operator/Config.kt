package io.bsamartins.example.operator

import io.fabric8.kubernetes.client.KubernetesClient
import io.javaoperatorsdk.operator.Operator
import io.quarkus.runtime.Startup
import javax.enterprise.context.ApplicationScoped
import javax.inject.Singleton

@ApplicationScoped
class Config {

    @Singleton
    @Startup
    fun operator(client: KubernetesClient): Operator {
        return Operator(client)
    }

}