#### 组件化部分划分-wanAndroid项目（2021/04/08）
##### 功能模块组件划分
- 首页（m-home）
- 我的（m-my）
- 公众号(m-wx)
- 项目（m-project）
- 广场 (m-square)
- 收藏 (m-collect)
##### 公共组件划分
- library-base(抽取的Base)
- library-network(网络层)
- library-resource（公用的资源文件）
- library-service(组件间存在调用关系，为其提供调用接口)