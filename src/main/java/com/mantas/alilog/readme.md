Aliyun log 

通过aliyun-log接口查询数据.

将查询结果自动显示的web页面上

根据ali log的查询结果的特性, 已表格的形式展示结果

功能包括:

- web提供自定义查询脚本, 实时显示查询结果, 以表格的形式展示
- 提供内置查询脚本, 实时显示查询结果
- 脚本存放在git上



# 配置格式

## nacos

```json
{
  "entities": [
    {
      "entity": "B2B订货平台",
      "desc": "",
      "accessId": "",
      "accessKey": "",
      "host": "",
      "query": "b2b/query_buyer.json"
    }
  ]
}
```

存放在git的内置查询条件


```json
{
  "name": "B2B订货行为分析",
  "projectName": "",
  "logstoreName": "",
  "queries": [
    {
      "name": "搜索热词",
      "topic": "",
      "filter": ""
    }
  ]
}
```