# Latte Core Module

### Core库 文件夹说明

folder  | function
---|---
activity | 基类活动
app| Latte主架构
delegate| Fragment页面
net | 网络核心库
ui |  控件及视图
util | 工具类

1. Latte主架构

folder | file | function
---|---|---
app|Latte|模块主架构
app|ConfigKeys|全局静态不变的数据(枚举类型)
app|Configurator|全局配置生成器

2. UI架构
- 本模块UI采用[**Fragmentation**](https://github.com/YoKeyword/Fragmentation)的**单Activity+多Fragment**架构

**2.1** 内容分为**activity**、**delegate**两个文件夹

folder | file | function
---|---|---
activity|ProxyActivity|单activity的基类，用于进行初始配置
delegate|BaseDelegate|基类Fragment继承自**SwipeBackFragment**
delegate|PermissionDelegate|权限检查基类，继承自BaseDelegate
delegate|LatteDelegate|UI基类，继承自PermissionDelegate

**2.2** delegate又分为 **bottom** 和 **web** 基页面

