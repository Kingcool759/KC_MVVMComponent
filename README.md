## 组件化部分划分-wanAndroid项目（2021/04/08）

### 界面截图
<img src="https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img1.jpg" width="257" height="540"><img src="https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img2.jpg" width="257" height="540"><img src="https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img3.jpg" width="257" height="540"/>
![截图4](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img4.jpg)
![截图5](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img5.jpg)
![截图6](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img6.jpg)
![截图7](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img7.jpg)
![截图8](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img8.jpg)
![截图9](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img9.jpg)
![截图10](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img10.jpg)
![截图11](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img11.jpg)
![截图12](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img12.jpg)
![截图13](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img13.jpg)
![截图14](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img14.jpg)
![截图15](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img15.jpg)
![截图16](https://github.com/Kingcool759/Project_Images/blob/main/wanAndroid/img16.jpg)

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
目前依赖库更新至1.0.1版本：
implementation 'io.github.Kingcool759:lib-base:1.0.1-SNAPSHOT'

### didi-dokit工具
项目中通过引入滴滴的Dokit工具进行快捷测试，可抓取网络请求数据，以及控件检测、布局层级预览、当前代码定位Activity、Fragment。