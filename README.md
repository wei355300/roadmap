<<<<<<< HEAD
# tapd-java

## 增加 spring-cloud 支持

- 增加 nacos 配置支持

在 nacos server 中增加 tapd 的配置

```
namespace: "?"
data_id: "?"
group_id: "?"
```
配置内容
```
{
  "auth": {
    "basicAuthId": "${tapd 开放平台的 auth id}",
    "basicAuthPwd": "${tapd 开放平台的 auth pwd}"
  },
  "projects": [
    {
      "id": "tapd 开放平台的 项目 ID",
      "name": "tapd 开放平台的 项目 名称"
    }
  ]
}
```

在 java 项目的application.yml 中设置 nacos 的配置信息
```yaml
nacos:
  config:
    tapdx:
      server_addr: "${nacos_server_addr}"
      namespace: "{nacos_namespace}"
      data_id: "{nacos_data_id}"
      group_id: "{nacos_group_id}"
```

java项目配置示例
```yaml
nacos:
  config:
    tapdx:
      server_addr: "127.0.0.1:8848"
      namespace: "17013706-c074-4416-af54-xxx"
      data_id: "tapdx"
      group_id: "projects"
```


> http://127.0.0.1:8848/nacos/  
> nacos / Petkit123

> curl -X GET "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=tapdx&group=projects&tenant=17013706-c074-4416-af54-b002b34157a2"

curl -X GET "http://192.168.220.13:8848/nacos/v1/cs/configs?dataId=tapdx&group=projects&tenant=17013706-c074-4416-af54-b002b34157a2"


curl -X GET "http://172.31.13.180:8848/nacos/v1/cs/configs?dataId=tapdx&group=projects&tenant=17013706-c074-4416-af54-b002b34157a2"


=======
# roadmap
>>>>>>> 023226b8b32aff28f31b587a226083dd275bffed
