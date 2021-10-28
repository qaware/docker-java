package com.github.dockerjava.k8s.command;

import com.github.dockerjava.api.model.Version;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class VersionCmdImplTest {

    @Test
    void execLocalKubeConfigClient() throws IOException {

        final String kubeConfigPath = Paths.get(System.getProperty("user.home")+ File.separator + ".kube"+File.separator+"config").toString();

        ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();

        VersionCmdImpl cmd = new VersionCmdImpl(client);
        Version version = cmd.exec();
        assertEquals(null, version.getGoVersion());
    }
}
