# CI/CD with Kubernetes and Jenkins Demo

## Pre-requisites
1. [Minikube](https://github.com/kubernetes/minikube)
2. [kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
    brew users: brew install kubectl
3. [helm](https://github.com/kubernetes/helm)
    brew users: brew install kubernetes-helm

Allow insecure docker registry in minikube
Add the minikube IP address + docker registry port to the list of insecure registries in this file
**~/.minikube/machines/minikube/config.json**

e.g. 192.168.99.100 is the minikube IP and 30400 is the port chosen for the registry service
```
"InsecureRegistry": [
    "10.0.0.0/24",
    "192.168.99.100:30400"
]
```

Start Minikube
```
minikube start
```

After installing the pre-requisites:
1. From infra/helm directory, build the helm docker image
```
docker build -t 192.168.99.100:30400/helm .
```

2. Deploy the docker registry chart from **infra/docker-registry** directory
```
helm install --name registry .
```

3. Deploy Jenkins chart from **infra/jenkins** directory
```
helm install --name jenkins .
```

4. Deploy gogs chart from **infra/gogs** directory
```
helm install --name gogs .
```

5. Push the helm docker image to the registry
```
docker push 192.168.99.100:30400/helm
```

6. Create a user in gogs, create a repo in gogs and push the contents of this directory

7. Log into jenkins and create a multibranch pipeline job
    1. Set the **Project Repository** to the gogs repo you created
    2. Set the **Credentials** to your gogs user
    3. Set the **Mode** in Build **Configuration** to **by Jenkinsfile**
    4. Check the **Periodically if not otherwise run** in **Scan Multibranch Pipeline Triggers**
    5. Save the job