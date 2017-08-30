FROM alpine:3.6

ADD https://storage.googleapis.com/kubernetes-release/release/v1.7.3/bin/linux/amd64/kubectl /usr/local/bin/kubectl

ENV HOME=/config
ENV HELM_VERSION v2.5.0
ENV HELM_FILENAME "helm-${HELM_VERSION}-linux-amd64.tar.gz"
ENV HELM_URL "https://storage.googleapis.com/kubernetes-helm/${HELM_FILENAME}"

RUN set -x && \
    apk add --no-cache curl ca-certificates && \
    chmod +x /usr/local/bin/kubectl && \
    kubectl version --client && \
    curl -o "/tmp/${HELM_FILENAME}" "${HELM_URL}" && \
    tar -zxvf "/tmp/${HELM_FILENAME}" -C /tmp && \
    mv /tmp/linux-amd64/helm /bin/helm && \
    chmod +x /bin/helm && \
    rm -rf /tmp

ENTRYPOINT ["/bin/helm"]
