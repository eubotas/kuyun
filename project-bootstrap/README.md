# project-bootstrap

本目录为项目介绍资源目录，包含模块依赖图、拓扑图等


### 组织结构

``` lua
kuyun
├── kuyun-common -- SSM框架公共模块
├── kuyun-admin -- 后台管理模板
├── kuyun-ui -- 前台thymeleaf模板[端口:1000]
├── kuyun-upms -- 用户权限管理系统
|    ├── kuyun-upms-common -- upms系统公共模块
|    ├── kuyun-upms-dao -- 代码生成模块，无需开发
|    ├── kuyun-upms-client -- 集成upms依赖包，提供单点认证、授权、统一会话管理
|    ├── kuyun-upms-rpc-api -- rpc接口包
|    ├── kuyun-upms-rpc-service -- rpc服务提供者
|    └── kuyun-upms-server -- 用户权限系统及SSO服务端[端口:1111]
├── kuyun-cms -- 内容管理系统
|    ├── kuyun-cms-common -- cms系统公共模块
|    ├── kuyun-cms-dao -- 代码生成模块，无需开发
|    ├── kuyun-cms-rpc-api -- rpc接口包
|    ├── kuyun-cms-rpc-service -- rpc服务提供者
|    ├── kuyun-cms-search -- 搜索服务[端口:2221]
|    ├── kuyun-cms-admin -- 后台管理[端口:2222]
|    ├── kuyun-cms-job -- 消息队列、任务调度等[端口:2223]
|    └── kuyun-cms-web -- 网站前台[端口:2224]
├── kuyun-pay -- 支付系统
|    ├── kuyun-pay-common -- pay系统公共模块
|    ├── kuyun-pay-dao -- 代码生成模块，无需开发
|    ├── kuyun-pay-rpc-api -- rpc接口包
|    ├── kuyun-pay-rpc-service -- rpc服务提供者
|    ├── kuyun-pay-sdk -- 开发工具包
|    ├── kuyun-pay-admin -- 后台管理[端口:3331]
|    └── kuyun-pay-web -- 演示示例[端口:3332]
├── kuyun-ucenter -- 用户系统(包括第三方登录)
|    ├── kuyun-ucenter-dao -- 代码生成模块，无需开发
|    ├── kuyun-ucenter-service -- 业务逻辑
|    └── kuyun-ucenter-home -- 网站前台[端口:4441]
├── kuyun-wechat -- 微信系统
|    ├── kuyun-wechat-mp -- 微信公众号管理系统
|    |    ├── kuyun-wechat-mp-dao -- 代码生成模块，无需开发
|    |    ├── kuyun-wechat-mp-service -- 业务逻辑
|    |    └── kuyun-wechat-mp-admin -- 后台管理[端口:5551]
|    └── kuyun-ucenter-app -- 微信小程序后台
├── kuyun-api -- API接口总线系统
|    ├── kuyun-api-common -- api系统公共模块
|    ├── kuyun-api-rpc-api -- rpc接口包
|    ├── kuyun-api-rpc-service -- rpc服务提供者
|    └── kuyun-api-server -- api系统服务端[端口:6666]
├── kuyun-oss -- 对象存储系统
|    ├── kuyun-oss-sdk -- 开发工具包
|    └── kuyun-oss-web -- 管理界面[端口:7771]
├── kuyun-shop -- 电子商务系统
├── kuyun-im -- 即时通讯系统
├── kuyun-oa -- 办公自动化系统
├── kuyun-eoms -- 运维系统
└── kuyun-demo -- 示例模块(包含一些示例代码等)
     ├── kuyun-demo-rpc-api -- rpc接口包
     ├── kuyun-demo-rpc-service -- rpc服务提供者
     └── kuyun-demo-web -- 演示示例[端口:8888]
```