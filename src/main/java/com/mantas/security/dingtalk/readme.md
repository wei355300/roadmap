
功能说明

# DingtalkAuthenticationCallbackFilter

前端扫描钉钉二维码进行登录后, 由钉钉服务器回调的接口入口.  

参考文档: [获取登录用户的访问凭证](https://open.dingtalk.com/document/isvapp-server/obtain-identity-credentials)

> Filter 拦截地址: "/api/auth/dingtalk/callback"

## 拦截过程说明
 
 ```
 DingtalkAuthenticationCallbackFilter  #拦截器入口
 | --> DingtalkAuthenticationManager #鉴权入口
 | --> DingtalkAuthenticationProvider  #鉴权具体判断入口
 | --> DingtalkDetailServices #获取鉴权信息/钉钉用户信息入口
 | --> DingtalkAuthenticationSuccessHandler #鉴权成功后返回数据操作
 | --> AuthenticationEntryPointFailureHandler(HttpStatusEntryPoint) #鉴权失败后返回数据操作
 ```
 
# DingtalkProperties

钉钉的相关配置, 

```
auth:
  dingtalk:
    client-id: "" # 钉钉应用配置
    client-secret: "" # 钉钉应用配置
    grant-type: "authorization_code" # 钉钉应用配置, 固定值
    corp-id: "" # 钉钉应用配置
    authentication-callback-url: "/api/auth/dingtalk/callback" #回调地址, 与钉钉管理后台的应用对应, 的用于 DingtalkAuthenticationCallbackFilter 
``` 

# DingtalkController

提供获取钉钉数据接口

1. 构造扫码登录的参数的接口 : "/base/auth/dingtalk/metainfo"

> 登录请求的参数的获取接口, 需要绕开 security 的 鉴权/授权


# 参考

## 钉钉文档 & 应用
[获取登录用户的访问凭证](https://open.dingtalk.com/document/isvapp-server/obtain-identity-credentials)


## 测试
```shell
curl -i -X GET http://localhost:8080/api/auth/dingtalk/callback\?authCode\=123
```
