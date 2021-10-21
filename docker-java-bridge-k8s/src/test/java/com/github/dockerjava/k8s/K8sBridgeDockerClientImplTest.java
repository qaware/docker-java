package com.github.dockerjava.k8s;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.VersionCmd;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class K8sBridgeDockerClientImplTest {

    @Test
    void versionCmd() throws Exception {
        DockerClient client = K8sBridgeDockerClientImpl.getInstance();
        VersionCmd versionCmd = client.versionCmd();
        assertNotNull(versionCmd);
    }

    @Test
    void close() throws Exception {
        DockerClient client = K8sBridgeDockerClientImpl.getInstance();
        client.close();
    }
}
