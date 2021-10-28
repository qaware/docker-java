# Docker API to K8s Bridge

This implementation of the Docker API tries to bridge all calls to K8s. For example, a CreateContainer command should roughly translate to
running a Pod in Kubernetes. The idea is kind of inspired by kubedock (https://github.com/joyrex2001/kubedock), a minimal implementation of
the Docker API to run containers in a K8s cluster. Also, we take some inspiration from BuildKit CLI for
Kubernetes (https://github.com/vmware-tanzu/buildkit-cli-for-kubectl).

