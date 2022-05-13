#!/usr/bin/env bash

echo "切换到 minikube-env 环境, 制作minikube-docker镜像"
eval $(minikube docker-env)

#
echo "清理docker镜像"
docker images|grep -v 'grep'|grep '<none>'|awk '$1~/none/ {print $3}'|xargs docker rmi


echo "删除k8s-Deployment"
kubectl delete Service/roadmap-java-svc Deployment/roadmap-java ConfigMap/roadmap-cm