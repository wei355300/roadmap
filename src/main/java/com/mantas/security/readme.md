
- 鉴权/授权流程

[Spring Security Architecture](https://docs.spring.io/spring-security/reference/servlet/architecture.html)

http请求到达Spring Security Filter 的路径:
```shell
http request  
  |--> FilterChain    
  |--> DelegatingFilterProxy  
  |--> FilterChainProxy  
  |--> `SecurityFilterChain`
```
> SecurityFilterChain 为 Spring Security 的核心部分

> 参考用法: src/main/java/com/mantas/security/SecurityConfiguration.java

- AuthenticationFilter 与 AuthorizationFilter 的关系

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



