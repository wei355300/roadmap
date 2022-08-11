
工具大杂烩

# 功能

## 鉴权与授权

核心代码: (package) com.mantas.security

1. 账号相关: com.mantas.security.account
2. 接口调用鉴权: com.mantas.security.token

用法: 

前端在调用接口时, 会携带 token 参数,  
通过 token 的校验(鉴权), (参考: TokenAuthenticationFilter)   
如果token合法, 则进行授权 (参考: AuthorityUrlCheckerAuthorizationManager)   

授权通过后, 调用 spring-mvc的controller定义的接口

流程说明: 查看 `com.mantas.security.readme.md` 文档


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


### 自定义json解析

jackson的配置查看: GlobalConfiguration::jsonCustomizer()

## `jsonpath` 的使用

基于tapd开放平台响应数据格式的特殊性,  

jsonpath的配置查看: GlobalConfiguration.java

> 使用 `jsonpath` 解析响应结果, 用法可查看 `tapd-package`下的`readme`文档