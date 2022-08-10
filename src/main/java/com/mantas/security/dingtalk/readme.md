
```
https://login.dingtalk.com/oauth2/auth?redirect_uri=http%3A%2F%2Fauth-dingtalk-demo.mantas.cn%3A11080%2Fapi%2Fauth%2Fdingtalk%2Fcallback&response_type=code&client_id=dinght8oiuzenkv4twpc&scope=openid&state=dddd&prompt=consent
```

# 测试
```shell
curl -i -X GET http://localhost:8080/api/auth/dingtalk/callback\?authCode\=123
```
