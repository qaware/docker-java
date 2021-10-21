package com.github.dockerjava.k8s;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.AttachContainerCmd;
import com.github.dockerjava.api.command.AuthCmd;
import com.github.dockerjava.api.command.BuildImageCmd;
import com.github.dockerjava.api.command.CommitCmd;
import com.github.dockerjava.api.command.ConnectToNetworkCmd;
import com.github.dockerjava.api.command.ContainerDiffCmd;
import com.github.dockerjava.api.command.CopyArchiveFromContainerCmd;
import com.github.dockerjava.api.command.CopyArchiveToContainerCmd;
import com.github.dockerjava.api.command.CopyFileFromContainerCmd;
import com.github.dockerjava.api.command.CreateConfigCmd;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateImageCmd;
import com.github.dockerjava.api.command.CreateNetworkCmd;
import com.github.dockerjava.api.command.CreateSecretCmd;
import com.github.dockerjava.api.command.CreateServiceCmd;
import com.github.dockerjava.api.command.CreateVolumeCmd;
import com.github.dockerjava.api.command.DisconnectFromNetworkCmd;
import com.github.dockerjava.api.command.EventsCmd;
import com.github.dockerjava.api.command.ExecCreateCmd;
import com.github.dockerjava.api.command.ExecStartCmd;
import com.github.dockerjava.api.command.InfoCmd;
import com.github.dockerjava.api.command.InitializeSwarmCmd;
import com.github.dockerjava.api.command.InspectConfigCmd;
import com.github.dockerjava.api.command.InspectContainerCmd;
import com.github.dockerjava.api.command.InspectExecCmd;
import com.github.dockerjava.api.command.InspectImageCmd;
import com.github.dockerjava.api.command.InspectNetworkCmd;
import com.github.dockerjava.api.command.InspectServiceCmd;
import com.github.dockerjava.api.command.InspectSwarmCmd;
import com.github.dockerjava.api.command.InspectVolumeCmd;
import com.github.dockerjava.api.command.JoinSwarmCmd;
import com.github.dockerjava.api.command.KillContainerCmd;
import com.github.dockerjava.api.command.LeaveSwarmCmd;
import com.github.dockerjava.api.command.ListConfigsCmd;
import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.api.command.ListImagesCmd;
import com.github.dockerjava.api.command.ListNetworksCmd;
import com.github.dockerjava.api.command.ListSecretsCmd;
import com.github.dockerjava.api.command.ListServicesCmd;
import com.github.dockerjava.api.command.ListSwarmNodesCmd;
import com.github.dockerjava.api.command.ListTasksCmd;
import com.github.dockerjava.api.command.ListVolumesCmd;
import com.github.dockerjava.api.command.LoadImageCmd;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.command.LogSwarmObjectCmd;
import com.github.dockerjava.api.command.PauseContainerCmd;
import com.github.dockerjava.api.command.PingCmd;
import com.github.dockerjava.api.command.PruneCmd;
import com.github.dockerjava.api.command.PullImageCmd;
import com.github.dockerjava.api.command.PushImageCmd;
import com.github.dockerjava.api.command.RemoveConfigCmd;
import com.github.dockerjava.api.command.RemoveContainerCmd;
import com.github.dockerjava.api.command.RemoveImageCmd;
import com.github.dockerjava.api.command.RemoveNetworkCmd;
import com.github.dockerjava.api.command.RemoveSecretCmd;
import com.github.dockerjava.api.command.RemoveServiceCmd;
import com.github.dockerjava.api.command.RemoveVolumeCmd;
import com.github.dockerjava.api.command.RenameContainerCmd;
import com.github.dockerjava.api.command.ResizeContainerCmd;
import com.github.dockerjava.api.command.ResizeExecCmd;
import com.github.dockerjava.api.command.RestartContainerCmd;
import com.github.dockerjava.api.command.SaveImageCmd;
import com.github.dockerjava.api.command.SaveImagesCmd;
import com.github.dockerjava.api.command.SearchImagesCmd;
import com.github.dockerjava.api.command.StartContainerCmd;
import com.github.dockerjava.api.command.StatsCmd;
import com.github.dockerjava.api.command.StopContainerCmd;
import com.github.dockerjava.api.command.TagImageCmd;
import com.github.dockerjava.api.command.TopContainerCmd;
import com.github.dockerjava.api.command.UnpauseContainerCmd;
import com.github.dockerjava.api.command.UpdateContainerCmd;
import com.github.dockerjava.api.command.UpdateServiceCmd;
import com.github.dockerjava.api.command.UpdateSwarmCmd;
import com.github.dockerjava.api.command.UpdateSwarmNodeCmd;
import com.github.dockerjava.api.command.VersionCmd;
import com.github.dockerjava.api.command.WaitContainerCmd;
import com.github.dockerjava.api.exception.DockerException;
import com.github.dockerjava.api.model.AuthConfig;
import com.github.dockerjava.api.model.Identifier;
import com.github.dockerjava.api.model.PruneType;
import com.github.dockerjava.api.model.SecretSpec;
import com.github.dockerjava.api.model.ServiceSpec;
import com.github.dockerjava.api.model.SwarmSpec;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.Config;
import com.github.dockerjava.k8s.command.VersionCmdImpl;

public class K8sBridgeDockerClientImpl implements DockerClient, Closeable {

    private final ApiClient client;

    K8sBridgeDockerClientImpl(final ApiClient client) {
        this.client = client;
    }

    /**
     * Returns the default K8s Bridge client instance.
     *
     * @return the default instance
     * @throws IOException in case of I/O problems with K8s
     */
    public static DockerClient getInstance() throws IOException {
        ApiClient client = Config.defaultClient();
        Configuration.setDefaultApiClient(client);

        return new K8sBridgeDockerClientImpl(client);
    }

    @Override
    public AuthConfig authConfig() throws DockerException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AuthCmd authCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InfoCmd infoCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PingCmd pingCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public VersionCmd versionCmd() {
        return new VersionCmdImpl(client);
    }

    @Override
    public PullImageCmd pullImageCmd(String repository) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PushImageCmd pushImageCmd(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PushImageCmd pushImageCmd(Identifier identifier) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CreateImageCmd createImageCmd(String repository, InputStream imageStream) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LoadImageCmd loadImageCmd(InputStream imageStream) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SearchImagesCmd searchImagesCmd(String term) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RemoveImageCmd removeImageCmd(String imageId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListImagesCmd listImagesCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InspectImageCmd inspectImageCmd(String imageId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SaveImageCmd saveImageCmd(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SaveImagesCmd saveImagesCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListContainersCmd listContainersCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CreateContainerCmd createContainerCmd(String image) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StartContainerCmd startContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ExecCreateCmd execCreateCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResizeExecCmd resizeExecCmd(String execId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InspectContainerCmd inspectContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RemoveContainerCmd removeContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public WaitContainerCmd waitContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public AttachContainerCmd attachContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ExecStartCmd execStartCmd(String execId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InspectExecCmd inspectExecCmd(String execId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LogContainerCmd logContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CopyArchiveFromContainerCmd copyArchiveFromContainerCmd(String containerId, String resource) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CopyFileFromContainerCmd copyFileFromContainerCmd(String containerId, String resource) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CopyArchiveToContainerCmd copyArchiveToContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ContainerDiffCmd containerDiffCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StopContainerCmd stopContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public KillContainerCmd killContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UpdateContainerCmd updateContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RenameContainerCmd renameContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RestartContainerCmd restartContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResizeContainerCmd resizeContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CommitCmd commitCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BuildImageCmd buildImageCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BuildImageCmd buildImageCmd(File dockerFileOrFolder) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BuildImageCmd buildImageCmd(InputStream tarInputStream) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TopContainerCmd topContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TagImageCmd tagImageCmd(String imageId, String imageNameWithRepository, String tag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PauseContainerCmd pauseContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UnpauseContainerCmd unpauseContainerCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public EventsCmd eventsCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public StatsCmd statsCmd(String containerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CreateVolumeCmd createVolumeCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InspectVolumeCmd inspectVolumeCmd(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RemoveVolumeCmd removeVolumeCmd(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListVolumesCmd listVolumesCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListNetworksCmd listNetworksCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InspectNetworkCmd inspectNetworkCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CreateNetworkCmd createNetworkCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RemoveNetworkCmd removeNetworkCmd(String networkId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ConnectToNetworkCmd connectToNetworkCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public DisconnectFromNetworkCmd disconnectFromNetworkCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InitializeSwarmCmd initializeSwarmCmd(SwarmSpec swarmSpec) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InspectSwarmCmd inspectSwarmCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JoinSwarmCmd joinSwarmCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LeaveSwarmCmd leaveSwarmCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UpdateSwarmCmd updateSwarmCmd(SwarmSpec swarmSpec) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UpdateSwarmNodeCmd updateSwarmNodeCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListSwarmNodesCmd listSwarmNodesCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListServicesCmd listServicesCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CreateServiceCmd createServiceCmd(ServiceSpec serviceSpec) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InspectServiceCmd inspectServiceCmd(String serviceId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UpdateServiceCmd updateServiceCmd(String serviceId, ServiceSpec serviceSpec) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RemoveServiceCmd removeServiceCmd(String serviceId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListTasksCmd listTasksCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LogSwarmObjectCmd logServiceCmd(String serviceId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LogSwarmObjectCmd logTaskCmd(String taskId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PruneCmd pruneCmd(PruneType pruneType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListSecretsCmd listSecretsCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CreateSecretCmd createSecretCmd(SecretSpec secretSpec) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RemoveSecretCmd removeSecretCmd(String secretId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ListConfigsCmd listConfigsCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CreateConfigCmd createConfigCmd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public InspectConfigCmd inspectConfigCmd(String configId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public RemoveConfigCmd removeConfigCmd(String configId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void close() {
    }
}
