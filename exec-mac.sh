#!/usr/bin/env bash

#JDK_IMAGE="arm64v8/openjdk:11.0.15-jdk-oraclelinux8"
##r='${ROADMAP_JAVA_IMAGE_TAG}'
#sed -i "" -e 's|'"${r}"'|'"${image}"'|g' "Dockerfile"

image_name='mantas/roadmap-java'
image_version=`mvn org.apache.maven.plugins:maven-help-plugin:3.2.0:evaluate -Dexpression=project.version -q -DforceStdout`

image="${image_name}:${image_version}"
export image

k8s_yaml="k8s-roadmap-java-mac.yaml"

#
#
#
echo "开始生成可执行jar文件"
mvn clean && mvn package -Dmaven.test.skip=true

echo "切换到 minikube-env 环境, 制作minikube-docker镜像"
eval $(minikube docker-env)

# todo
# 判断是否已存在相同tag的镜像
# 如果已存在, 则先删除再重新生成
#
echo "开始制作镜像 ${image}"
docker build -f Dockerfile -t "${image}" .
echo "镜像制作完成."
echo ""

#echo "push image"
#echo "---"
#echo "push done."

#
# ${image} 名称中存在 / 字符,
# sed 的替换将定界符调整为 |, 以避开 /
#
# 因mac系统与linux系统的区别,
# mac系统下, (sed -i "")用于不备份
#

echo "替换k8s容器镜像为: ${image}"
#r='${ROADMAP_JAVA_IMAGE_TAG}'
#sed -i "" -e 's|'"${r}"'|'"${image}"'|g' ${k8s_yaml}
yq e -i 'select(.kind == "Deployment").spec.template.spec.containers[0].image = env(image)' ${k8s_yaml}
echo "k8s容器镜像替换完成."
echo ""

echo "应用最新镜像, 部署到环境"
kubectl apply -f ${k8s_yaml}
echo "部署完成."

