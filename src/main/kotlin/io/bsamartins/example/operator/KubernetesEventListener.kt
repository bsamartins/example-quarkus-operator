package io.bsamartins.example.operator

import io.fabric8.kubernetes.api.model.Pod
import io.fabric8.kubernetes.client.KubernetesClient
import io.fabric8.kubernetes.client.KubernetesClientException
import io.fabric8.kubernetes.client.Watch
import io.fabric8.kubernetes.client.Watcher
import io.quarkus.runtime.ShutdownEvent
import io.quarkus.runtime.StartupEvent
import org.slf4j.LoggerFactory
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes

@ApplicationScoped
class KubernetesEventListener(private val client: KubernetesClient) {

    private val logger = LoggerFactory.getLogger(KubernetesEventListener::class.java)
    private var watch: Watch? = null

    fun onStart(@Observes ev: StartupEvent) {
        this.watch = client.pods().watch(object: Watcher<Pod> {
            override fun eventReceived(action: Watcher.Action, resource: Pod) {
                logger.info("action=$action, resource=$resource")
            }

            override fun onClose(cause: KubernetesClientException) {}
        })
    }

    fun onStop(@Observes ev: ShutdownEvent) {
        this.watch?.close()
    }
}