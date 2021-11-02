package com.github.dockerjava.k8s.command;

import com.github.dockerjava.api.command.ListContainersCmd;
import com.github.dockerjava.api.exception.DockerException;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.exception.NotModifiedException;
import com.github.dockerjava.api.model.Container;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang.reflect.FieldUtils.writeField;

public class ListContainersCmdImpl implements ListContainersCmd {

    private static final Logger LOGGER = LoggerFactory.getLogger(ListContainersCmdImpl.class);


    private final ApiClient client;


    public ListContainersCmdImpl(final ApiClient client) {
        this.client = client;
    }

    @Override
    public List<Container> exec() throws NotFoundException, NotModifiedException {
        final CoreV1Api api = new CoreV1Api(this.client);
        final V1PodList podList;
        try {
             podList = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null, null);
        } catch (ApiException e) {
            throw new DockerException("Could not get K8s pod list.", 500, e);
        }

        final List<Container> containers=new ArrayList<>();
        for (V1Pod pod : podList.getItems()) {
            final Container container=createContainerOfPod(pod);
            containers.add(container);
        }

       return containers;
    }

    protected Container createContainerOfPod(V1Pod pod){
        final V1ObjectMeta meta = pod.getMetadata();
        final Container container = new Container();
        try {
            final String podName = meta.getName();
            writeField(container, "names", new String[]{podName}, true);

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to set bean field.", e);
        }
        return container;
    }



    @Override
    public void close() {
        // apiClient schliessen?
    }

    @Nullable
    @Override
    public String getBeforeId() {
        return null;
    }

    @Nullable
    @Override
    public Map<String, List<String>> getFilters() {
        return null;
    }

    @Nullable
    @Override
    public Integer getLimit() {
        return null;
    }

    @Nullable
    @Override
    public String getSinceId() {
        return null;
    }

    @Nullable
    @Override
    public Boolean hasShowAllEnabled() {
        return null;
    }

    @Nullable
    @Override
    public Boolean hasShowSizeEnabled() {
        return null;
    }

    @Override
    public ListContainersCmd withBefore(String before) {
        return null;
    }

    @Override
    public ListContainersCmd withNameFilter(Collection<String> name) {
        return null;
    }

    @Override
    public ListContainersCmd withIdFilter(Collection<String> id) {
        return null;
    }

    @Override
    public ListContainersCmd withAncestorFilter(Collection<String> ancestor) {
        return null;
    }

    @Override
    public ListContainersCmd withVolumeFilter(Collection<String> volume) {
        return null;
    }

    @Override
    public ListContainersCmd withNetworkFilter(Collection<String> network) {
        return null;
    }

    @Override
    public ListContainersCmd withExitedFilter(Integer exited) {
        return null;
    }

    @Override
    public ListContainersCmd withStatusFilter(Collection<String> status) {
        return null;
    }

    @Override
    public ListContainersCmd withLabelFilter(Collection<String> labels) {
        return null;
    }

    @Override
    public ListContainersCmd withLabelFilter(Map<String, String> labels) {
        return null;
    }

    @Override
    public ListContainersCmd withLimit(Integer limit) {
        return null;
    }

    @Override
    public ListContainersCmd withShowAll(Boolean showAll) {
        return null;
    }

    @Override
    public ListContainersCmd withShowSize(Boolean showSize) {
        return null;
    }

    @Override
    public ListContainersCmd withSince(String since) {
        return null;
    }

    @Override
    public ListContainersCmd withFilter(String filterName, Collection<String> filterValues) {
        return null;
    }
}
