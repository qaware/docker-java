package com.github.dockerjava.k8s.command;

import com.github.dockerjava.api.model.Container;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListContainersCmdImplTest {


    @Test
    @DisplayName("check if containers are available")
    void execLocalKubeConfigClient() throws Exception {
        String kubeConfigPath = Paths.get(System.getProperty("user.home"), ".kube", "config").toString();
        ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
        Configuration.setDefaultApiClient(client);

        final ListContainersCmdImpl cmd = new ListContainersCmdImpl(client);
        final List<Container> containers = cmd.exec();

        assertNotNull(containers);
        assertTrue(containers.size() > 0);
        final Container container = containers.get(0);
        assertNotNull(container.getNames());
        assertTrue(container.getNames().length > 0);
    }

    @Test
    @DisplayName("compare container list with docker client api result")
    void compareWithDockerClientAPI() throws Exception {
        // compute expected result
        final AtomicReference<V1PodList> atomicPodList = new AtomicReference<>();

        assertDoesNotThrow(() -> {
            final ApiClient clientK8s = Config.defaultClient();
            Configuration.setDefaultApiClient(clientK8s);
            final CoreV1Api api = new CoreV1Api(clientK8s);
            atomicPodList.set(api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null, null));
        });

        final V1PodList expectedPodList = atomicPodList.get();
        final List<V1Pod> expectedPods = expectedPodList.getItems();

        // prepare test
        final String kubeConfigPath = Paths.get(System.getProperty("user.home"), ".kube", "config").toString();
        final ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();
        Configuration.setDefaultApiClient(client);

        final ListContainersCmdImpl cmd = new ListContainersCmdImpl(client);
        final List<Container> containers = cmd.exec();

        assertNotNull(containers);

        assertEquals(expectedPods.size(),containers.size());
        final Container container = containers.get(0);
        final V1Pod pod = expectedPods.get(0);
        assertTrue(container.getNames().length > 0);
        assertEquals(pod.getMetadata().getName(),container.getNames()[0]);
        assertEquals(pod.getMetadata().getCreationTimestamp().toEpochSecond(),container.getCreated());
        assertEquals(pod.getMetadata().getUid(),container.getId());
        assertEquals(pod.getMetadata().getLabels(),container.getLabels());
        assertEquals(pod.getStatus().toString(),container.getStatus());
        assertEquals(pod.getStatus().toString(),container.getState());
    }

}
