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

- nacos存储配置相关信息
  1. aliyun-sls(日志服务)的连接信息
    > "accessId": "",  // aliyun账号的accessId    
      "accessKey": "", // aliyun账号的accessKey    
      "host": "",      // aliyun-sls的访问地址  
   
  1. 内置的查询条件
    > 查询条件可存放在Git上或数据库等介质中

- nacos配置数据格式  
```json
{
  "entities": [
    {
      "entity": "",            
      "desc": "",                 
      "accessId": "",
      "accessKey": "",
      "host": "",
      "git": {                 
        "projectId": "",
        "projectName": "",
        "url": "",
        "accessToken": "",
        "file": "b2b/query_b2b_buyer.json"
      }
    }
  ]
}
```

> "entity":   标明查询的数据定义, 如: xx数据分析  
> "desc":     简短的目的描述  
> "git":      存放在git上的配置  
> "git.file": 存放在git上的文件路径  

- 存放在git的内置查询条件

```json
[
    {
      "name": "",
      "projectName": "",
      "logStoreName": "",
      "queries": [
        {
          "name": "搜索热词",
          "topic": "",
          "filter": "list/by/menu  | select replace(split_part(split_part(REQUEST_PARAMS, '\"search\":', 2), ',', 1), '}', '') as search,count(1)  as times where split_part(split_part(REQUEST_PARAMS, '\"search\":', 2), ',', 1) !='null' group by search order by times desc limit 10000"
        }
      ]
    }
]
```