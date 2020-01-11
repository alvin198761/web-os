# V 1.2 描述
 1. 增加浏览器功能
 2. 增加手绘风格的便签展示
 3. 增加打包脚本
 4. 开始菜单功能布局定型
 5. 前端架构调整为 vue-cli3 这个很关键，对以后的前端开发效率会更高
 <br/>效果图：
 ![image](https://github.com/alvin198761/web-os/blob/master/web-os-v1.2/pics/browser.png?raw=true)
 ![image](https://github.com/alvin198761/web-os/blob/master/web-os-v1.2/pics/notes.png?raw=true)
 ![image](https://github.com/alvin198761/web-os/blob/master/web-os-v1.2/pics/startMenu.png?raw=true)

# V 1.1 描述
在 V 0.3 的基础上将app死数据动态化，
web-os-V1.1: 1.0 版本的根目录
    WebOS 项目跟目录
        WebOSApp java 项目位置
            src/main/webos-win10ui/  web项目代码位置 
1. 改为window 10 风格
    > 界面没有别人的好看，就抄了一个
    win10 ui 地址：https://github.com/yuri2peter/win10-ui
    metro 风格：https://github.com/olton/Metro-UI-CSS
2. 界面和设计改变了
3. 实现 应用的添加和管理
4. 协议的添加和管理
5. 加入动态背景
5. 搭建阿里云测试 服务 ： [http://www.webos.online](http://www.webos.online) 
    > 用户名随便一个邮箱，密码随便输入
6. 1.0 版本的访问地址作废了
<br/>.效果图：
 ![image](https://github.com/alvin198761/web-os/blob/master/web-os-V1.1/pics/login.png?raw=true)
 ![image](https://github.com/alvin198761/web-os/blob/master/web-os-V1.1/pics/windows.png?raw=true)
 ![image](https://github.com/alvin198761/web-os/blob/master/web-os-V1.1/pics/bg.png?raw=true)

 # V 1.0
> 简介文档

## 1.说明
    webos 开发了一个测试简单版本以后，因为本人没有时间，所有一直没有继续升级，到现在也没有出现一个可用版本，
     所以我准备开发一个可用简单实用的版本，作为1.0 版本
     
## 2.计划开发的功能
### 2.1 功能上的改造
    1. APP 图标是动态增加的，只有顶部分类是固定的
    2. APP 也是动态显示的，我会写死一些图标，其他都可以动态添加，因为目前没有后台管理
    3. 完成便签功能
    4. rdp ssh vnc 功能只是测试可以使用了，但是并没有做一个完整的功能，这里准备完善掉
    5. 拍照功能 需要改成一个完整的功能
    
### 2.2 技术上面的升级
    1. vue ,vuex ,element 都升级到最新版本
    2. jdk 准备用到 10 ，springboot 用到最新版本
    3. jpa 改为 jdbctemplate 因为现在有一个生成工具，效率更高
    4. 改为需要登录才能进入系统
    
## 3.目录结构 
    1. web-os-V1.0 以外的目录，是之前的，因为有人fork 了，我就保留上面的目录，开了 这个目录开重新做新功能
    2. web-os-app : 后端项目 jdk 10 ,spring boot 2.x ,后端开发逻辑，目前只为 web-os-ui 提供接口
    3. web-os-ui ： 前端项目， vue vuex element 2.x 开发，

# V0.3
web-os-app: 项目后端，用来做真是数据处理，提供gucamole 连接等
			部署见内部 readme.md

web-os-ui: 项目前端，可以独立运行，具体操作见内部 readme.md

新增照相机：
![image](https://github.com/alvin198761/web-os/blob/master/web-os-app/pics/capture.png?raw=true)

实例图：
![image](https://github.com/alvin198761/web-os/blob/master/web-os-app/pics/rdp.png?raw=true)
![image](https://github.com/alvin198761/web-os/blob/master/web-os-app/pics/vnc.png?raw=true)
![image](https://github.com/alvin198761/web-os/blob/master/web-os-app/pics/ssh.png?raw=true)

![image](https://github.com/alvin198761/web-os/blob/master/web-os-app/pics/111.png?raw=true)

![image](https://github.com/alvin198761/web-os/blob/master/web-os-app/pics/222.png?raw=true)
