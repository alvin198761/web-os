名称： opsdev-desktop
作者： alvin198761@163.com || QQ 群：481434622
作用： 用户web操作桌面，用户在出差或者没有带有环境的地方，只需要用浏览器，就可以实现远程运维

前端环境：vue2 + vuex + mockjs + puer + jquery + jquery ui + smartMenu + interface + guacamole
后端环境：jdk1.8 + spring boot 1.4  + guacamole 0.9.11

更新日志：
2017/3/7状态：guacamole 前端和后端集成代码写完，苦于没有测试环境，具体测试还需要等我环境搭好。
                后端数据都是模拟的，需要与真个平台集成材能有真实数据，但这工作量还比较大
2017/3/6状态：基本功能已经完成，浏览器改变大小问题解决，目前guacamole 处于研究之中，
2017/3/6状态：弹出框，浏览器等，不能改变大小，其他细化功能还没有做

部署：
1.前端编译： npm install
             npm run build

2.前端编译： mvn install
3.安装jdk 1.8.x
4.复制 1.0-SNAPSHOT\opsdev-desktop-1.0-SNAPSHOT-bin.zip 到目标机器，解压
5.运行jar
6.访问： http://yourip:4005

样例图片：
![image](https://github.com/alvin198761/devops_v1/blob/master/source/opsdev-desktop/pics/111.png?raw=true)
![image](https://github.com/alvin198761/devops_v1/blob/master/source/opsdev-desktop/pics/222.png?raw=true)
