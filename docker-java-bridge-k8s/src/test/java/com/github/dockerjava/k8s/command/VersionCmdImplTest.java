package com.github.dockerjava.k8s.command;

import com.github.dockerjava.api.model.Version;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class VersionCmdImplTest {

    @Test
    void execLocalKubeConfigClient() throws IOException {
        String kubeConfigPath = System.getenv("HOME") + "/.kube/config";
        ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();

        VersionCmdImpl cmd = new VersionCmdImpl(client);
        Version version = cmd.exec();
        assertEquals(null, version.getGoVersion());
    }
}
