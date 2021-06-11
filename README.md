## 组件化部分划分-wanAndroid项目（2021/04/08）
### 功能模块组件划分
- 首页（m-home）
- 我的（m-my）
- 公众号(m-wx)
- 项目（m-project）
- 广场 (m-square)
### 公共组件划分
- library-base(抽取的Base)
### 其他模块组件
- 闪屏页 (m-splash)
- 登陆 (m-login)
- 网页 (m-webview)
- 搜索 (m-search)
- 收藏 (m-collect)
- 调试 (m-debug)
### 推送到远程Maven仓库
- library-base(抽取的Base)

目前依赖库已更新至1.3.0版本：
compile 'com.kc.component:library-base:1.3.0'
但要使用还需要额外配置，因为阿里云库是私有的Maven库。