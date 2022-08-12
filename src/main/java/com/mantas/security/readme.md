鉴权&授权

功能说明:  

1. 所有以 "/api/**" 前缀的请求, 都将进入 `TokenAuthenticationFilter` 进行 token 校验
2. 所有以 "/base/**" 前缀的请求, 默认放行, 可以直接调用 `Controller` 定义的接口
3. 其它特殊请求地址, 可通过自定义 `Filter` 等方式处理(如: DingtalkAuthenticationCallbackFilter)

# package: token

token拦截器,   
拦截所有 `/api/**`  的请求, 提取请求的 `token` 参数, 并验证.   
验证通过后, 进入 授权流程,   
验证失败则返回 401 状态码.

> 可自定义 TokenAuthenticationFilter::AuthenticationFailureHandler 调整默认返回 401 状态码的行为

# package: authority

权限校验(授权)相关.

## AuthorityUrlCheckerAuthorizationManager

通过request的url与token对应的账号的已被赋予的权限进行比较,  

> 具体的匹配可参考:  AntPathMatcher 的用法  
> 账号的权限分配等数据, 查看 package: account 的说明

# package: account

账号相关




# 鉴权/授权流程

参考文档: [Spring Security Architecture](https://docs.spring.io/spring-security/reference/servlet/architecture.html)

http请求到达Spring Security Filter 的流程:

```
http request  
  |--> FilterChain    
  |--> DelegatingFilterProxy  
  |--> FilterChainProxy  
  |--> SecurityFilterChain
```

SecurityFilterChain 为 Spring Security 的核心部分

> 参考用法: src/main/java/com/mantas/security/SecurityConfiguration.java

## AuthenticationFilter 与 AuthorizationFilter 的关系

基于 `HttpSecurity` 的配置,   
通常情况下, 鉴权通过后(authenticated) 将进入授权流程.  

> 具体可查看 Authentication的isAuthenticated() 的用法




## 鉴权

- 登录流程

    登录时, 如果能通过 账号/密码 获取到用户信息, 说明登录成功, filter直接返回

- 鉴权流程(例: token校验)
    > 参考 TokenAuthenticationFilter  

    取得http request 中的 token 参数, 通过 token 参数取到账号信息,  
    将账号信息设置到 Authentication 实例中, 并设置为 authenticated 状态.  
    鉴权filter继续(chainFilter.doFilter())
    > 参考 TokenAuthenticationFilter::doFilterInternal()

    鉴权流程结束

流程概要

TokenAuthenticationFilter:

```shell
TokenAuthenticationFilter
    |
TokenAuthenticationManager
    | 
TokenAuthenticationProvider
    |
    |--> (Failure)AuthenticationFailureHandler
    |--> (Success)AuthorizeFilter

AuthorizeFilter
    |
AuthorityUrlCheckerAuthorizationManager
```


DingtalkAuthenticationFilter:
登录流程, 从钉钉服务器获取用户信息, 并验证通过后, 
生成token后, 携带账号信息, 返回至前端 

```shell
DingtalkAuthenticationFilter
    |
DingtalkAuthenticationManager
    | 
DingtalkAuthenticationProvider
    |
    |--> (Failure)AuthenticationFailureHandler(Default: HttpStatusEntryPoint(401))
    |--> (Success)DingtalkAuthenticationSuccessHandler
```

## 授权

> 参考 Authorization Architecture:  
> https://docs.spring.io/spring-security/reference/servlet/authorization/architecture.html

- 授权流程
    授权流程由 `AuthorizationFilter` 负责  
    通过 `HttpSecurity` 配置 `authorizeHttpRequests` 的 `access ()`   
    可以自定义授权方式
  
    > 参考用法: src/main/java/com/mantas/security/SecurityConfiguration.java



# GrantedAuthority

在鉴权阶段, 通常由 `AuthenticationProvider` 去获取账号已经被赋予的权限  
比如: 权限码, 角色 等

在授权阶段, 将 GrantedAuthority 的数据 与 资源需要具备的 Authority 进行比较,  
如果包含, 则表示授权通过. 
> 权限的授权方式可以有多种形式, 具体可参考spring的文档



