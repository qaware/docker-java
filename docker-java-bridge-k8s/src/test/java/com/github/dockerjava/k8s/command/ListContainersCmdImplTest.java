package com.github.dockerjava.k8s.command;

import com.github.dockerjava.api.model.Container;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListContainersCmdImplTest {


    @Test
    @DisplayName("check if version is not null")
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


}
