
工具大杂烩

# 功能

## tapd-ext

对接腾讯的TAPD的开放接口, 集中参看TAPD上的内容: 

- 跨项目的迭代


## ali-log查询

对接阿里云的SLS(日志服务), 查看整理过的日志数据(日志分析)

# 代码层面的限制

## http限制

响应仅支持json格式,  
采用spring默认的jackson类库,  
通过 `@JsonView`, `@JsonIgnore`, `@JsonProperty` 注解, 格式化响应结果

## 配置现在

- nacos-config

程序启动后, 从 nacos 获取各功能的配置  
各具体配置可查看对应package下的readme文档


