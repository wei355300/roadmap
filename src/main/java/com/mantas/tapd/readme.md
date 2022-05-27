
# nacos 配置支持

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

nacos配置示例
```yaml
{
  "auth": {
    "basicAuthId": "{basicAuthId of tapd open dev}",
    "basicAuthPwd": "{basicAuthPwd of tapd open dev}"
  },
  "projects": [
    {
      "id": "{id of tapd project}",
      "name": "{project name}"
    }
  ]
}

```

# 开发技巧

基于 tapd 开放平台返回的数据格式的特殊性, 

使用 `jsonpath` 解析数据并转化为 `Object` 对象,

具体用法可参考 `com.mantas.tapd.service.TapdClient#body2Obj()`