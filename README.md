
工具大杂烩

# 功能

## tapd

对接腾讯的TAPD的开放接口, 集中查看TAPD上的内容: 

- 跨项目的迭代

- 人员的工作内容

## ali-log查询

对接阿里云的SLS(日志服务), 查看整理过的日志数据(日志分析)

## roadmap 站点地图(gitlab)



# 配置限制

- nacos-config

程序启动后, 从 nacos 获取各功能的配置  
各具体配置可查看对应package下的readme文档



# 编程技巧

## 接收json格式的Get请求

基于 `PropertyEditorSupport` 及 `@InitBinder` 将 `json` 格式的`Get`请求的参数转化为`Object`

具体做法参考 `com.mantas.alilog.controller.AlilogQueryController` 等实现

## 响应Json格式

响应仅支持json格式,

仅采用spring默认的jackson类库,

通过 `@JsonView`, `@JsonIgnore`, `@JsonProperty` 注解, 格式化响应结果

## `jsonpath` 的使用

基于tapd开放平台响应数据格式的特殊性,  

使用 `jsonpath` 解析响应结果, 具体可查看 `tapd-package`下的`readme`文档