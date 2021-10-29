package com.github.dockerjava.k8s.command;

import com.github.dockerjava.api.model.Version;
import com.github.dockerjava.api.model.VersionPlatform;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.models.VersionInfo;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.KubeConfig;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class VersionCmdImplTest {

    @Test
    @DisplayName("check if version is not null")
    void execLocalKubeConfigClient() throws IOException {
        String kubeConfigPath = Paths.get(System.getProperty("user.home"), ".kube", "config").toString();
        ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();

        VersionCmdImpl cmd = new VersionCmdImpl(client);
        Version version = cmd.exec();

        assertNotNull(version.getGoVersion());
        assertNotNull(version.getGitCommit());
        assertNotNull(version.getBuildTime());
        assertNotNull(version.getApiVersion());
        assertNotNull(version.getVersion());
        assertNotNull(version.getArch());
        assertNotNull(version.getOperatingSystem());
        assertNotNull(version.getPlatform());
        assertFalse(version.getExperimental());
    }

    @Test
    @DisplayName("compare result with original k8s client")
    void compatibleWithK8sClient() throws IOException, ApiException {

        final ApiClient clientK8s = Config.defaultClient();
        Configuration.setDefaultApiClient(clientK8s);
        final io.kubernetes.client.util.version.Version versionK8s = new io.kubernetes.client.util.version.Version(clientK8s);
        final VersionInfo expectation = versionK8s.getVersion();

        String kubeConfigPath = Paths.get(System.getProperty("user.home"), ".kube", "config").toString();
        ApiClient client = ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeConfigPath))).build();

        VersionCmdImpl cmd = new VersionCmdImpl(client);
        Version version = cmd.exec();

        assertEquals(expectation.getGoVersion(), version.getGoVersion());
        assertEquals(expectation.getGitCommit(), version.getGitCommit());
        assertEquals(expectation.getBuildDate(), version.getBuildTime());
        assertEquals(expectation.getMajor() + "." + expectation.getMinor(), version.getApiVersion());
        assertEquals(expectation.getMajor() + "." + expectation.getMinor(), version.getVersion());
        assertEquals(expectation.getPlatform().split("/")[1], version.getArch());
        assertEquals(expectation.getPlatform().split("/")[0], version.getOperatingSystem());
        assertEquals(new VersionPlatform().withName(expectation.getPlatform()), version.getPlatform());
        assertFalse(version.getExperimental());
    }
}
