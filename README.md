# 🐱 CatSeedLogin - 猫种子登录系统

<div align="center">

[![Build Status](https://github.com/shulng/CatSeedLogin-v2/actions/workflows/maven.yml/badge.svg)](https://github.com/shulng/CatSeedLogin-v2/actions/workflows/maven.yml)
[![Release](https://img.shields.io/github/v/release/shulng/CatSeedLogin-v2)](https://github.com/shulng/CatSeedLogin-v2/releases/latest)
[![License](https://img.shields.io/github/license/shulng/CatSeedLogin-v2)](LICENSE)
[![Downloads](https://img.shields.io/github/downloads/shulng/CatSeedLogin-v2/total)](https://github.com/shulng/CatSeedLogin-v2/releases)

**🚀 高性能Minecraft登录插件 | 支持1.12-1.21版本 | 支持BungeeCord**

> 基于Spigot API 1.13.2开发，理论支持1.12 ~ 1.21版本，低版本向上兼容

</div>

## 📋 目录

- [✨ 核心功能](#-核心功能)
- [🏗️ 架构改进](#-架构改进)
- [📥 下载安装](#-下载安装)
- [🎯 快速开始](#-快速开始)
- [📖 指令大全](#-指令大全)
- [🔐 权限节点](#-权限节点)
- [⚙️ 配置文件](#️-配置文件)
- [🔗 BungeeCord配置](#-bungeecord配置)
- [👨‍💻 开发者API](#开发者api)
- [💬 社区支持](#-社区支持)

## ✨ 核心功能

### 🔐 安全认证
- ✅ **注册/登录/修改密码** - 完整的用户认证系统
- ✅ **密码加密存储** - 采用Crypt加密算法，保障数据安全
- ✅ **防止大小写登录Bug** - 解决英文ID大小写敏感问题
- ✅ **防止账号被顶替** - 登录后防止他人顶号下线

### 🛡️ 安全防护
- 🔒 **登录前限制** - 禁止移动、交互、攻击、发言、使用指令等
- 🎒 **背包保护** - 登录前隐藏背包，防止物品丢失
- 📍 **位置保护** - 登录前强制传送至安全出生点
- 🕐 **重入限制** - 下线后"可配置"秒内禁止重新进入服务器
- 🌐 **IP限制** - 限制同IP账号注册/登录数量

### 📧 邮箱功能
- 📨 **邮箱绑定** - 支持邮箱验证与绑定
- 🔑 **密码重置** - 通过邮箱验证码重置密码
- 📤 **邮件通知** - 完整的邮件系统支持

### 🌐 BungeeCord支持
- 🔄 **子服同步** - 支持BungeeCord跨服登录
- 🚪 **子服限制** - 未登录禁止切换子服
- 🔄 **状态保持** - 登录后子服切换保持登录状态

### 💾 数据存储
- 🗄️ **多数据库支持** - SQLite(默认) / MySQL
- 📍 **玩家位置存储** - 玩家离线位置存储在数据库中
- 🔄 **数据迁移** - 支持数据库切换和数据迁移
- 💾 **轻量级** - 占用资源少，性能优异

### 🌍 国际化支持
- 🌐 **多语言支持** - 内置中文、英文语言
- 🔧 **自定义语言** - 支持自定义语言文件覆盖
- 📝 **统一消息管理** - 使用MessageKey枚举统一管理所有消息

## 🏗️ 架构改进

### 📦 统一配置管理
- 📄 **单一配置文件** - 从多个分散配置文件合并为统一`config.yml`
- 🔧 **模块化配置** - 按功能模块组织配置项
- 🔄 **热重载支持** - 支持配置热重载，无需重启服务器

### 🌍 统一国际化系统
- 📝 **MessageKey枚举** - 所有消息键统一管理
- 🔌 **ResourceProvider接口** - 平台无关的资源加载
- 📄 **多语言文件** - `language.yml`和`language_en_US.yml`

### 🔗 统一API接口
- 🛠️ **PlatformAdapter接口** - 平台抽象层
- ⚙️ **CoreConfig接口** - 核心配置接口
- 🗄️ **DatabaseConfig接口** - 数据库配置接口
- 📧 **EmailConfig接口** - 邮箱配置接口
- 🌐 **BungeeCordConfig接口** - 代理配置接口

### 💾 改进的数据存储
- 📍 **玩家位置数据** - 离线位置从配置文件迁移到数据库
- 🔄 **异步操作** - 位置保存异步执行，提高性能
- 📊 **兼容性** - 自动升级现有数据库表结构

## 📥 下载安装

### 📦 下载地址
| 版本类型 | 下载链接 |
|---------|----------|
| 🔥 **最新稳定版** | [GitHub Releases](https://github.com/shulng/CatSeedLogin-v2/releases/latest) |
| 🔄 **自动构建版** | [GitHub Actions](https://github.com/shulng/CatSeedLogin-v2/actions/workflows/maven.yml) |
| 📚 **历史版本** | [旧版归档](https://github.com/CatSeed/CatSeedLogin/tags) |

### 🚀 安装步骤

#### 单服务器使用
1. 📁 下载插件JAR文件
2. 📂 将插件放入`plugins`文件夹
3. 🔄 重启服务器
4. ✅ 完成安装

#### BungeeCord网络使用
1. 🌐 **登录服务器** - 将插件放入登录服务器的`plugins`文件夹
2. 🔗 **BungeeCord端** - 将插件放入BungeeCord的`plugins`文件夹
3. ⚙️ 配置BungeeCord设置（详见[BungeeCord配置](#-bungeecord配置)）
4. 🔄 重启所有相关服务

## 🎯 快速开始

### 🔑 玩家指令

#### 注册账号
```
/register <密码> <重复密码>
/reg <密码> <重复密码>
```

#### 登录账号
```
/login <密码>
/l <密码>
```

#### 修改密码
```
/changepassword <旧密码> <新密码> <重复新密码>
/changepw <旧密码> <新密码> <重复新密码>
```

#### 邮箱相关
```
# 绑定邮箱
/bindemail set <邮箱地址>
/bdmail set <邮箱地址>

# 验证邮箱验证码
/bindemail verify <验证码>
/bdmail verify <验证码>

# 忘记密码重置
/resetpassword forget
/repw forget

# 使用验证码重置密码
/bindemail re <验证码> <新密码>
/bdmail re <验证码> <新密码>
```

## 📖 指令大全

### 🛠️ 管理员指令

| 指令 | 功能描述 |
|------|----------|
| `/catseedlogin reload` | 重载配置文件 |
| `/catseedlogin delPlayer <玩家名>` | 强制删除玩家账户 |
| `/catseedlogin setPwd <玩家名> <密码>` | 强制设置玩家密码 |

### ⚙️ 配置管理指令

| 指令 | 功能描述 | 默认值 |
|------|----------|--------|
| `/catseedlogin commandWhiteListAdd <指令>` | 添加登录前允许执行的指令 | 支持正则 |
| `/catseedlogin commandWhiteListDel <指令>` | 删除登录前允许执行的指令 | 支持正则 |
| `/catseedlogin commandWhiteListInfo` | 查看登录前允许执行的指令列表 | - |
| `/catseedlogin setIpRegCountLimit <数量>` | 设置同IP注册数量限制 | 2 |
| `/catseedlogin setIpCountLimit <数量>` | 设置同IP登录数量限制 | 2 |
| `/catseedlogin setIdLength <最短> <最长>` | 设置游戏名长度限制 | 2-15 |
| `/catseedlogin setReenterInterval <tick>` | 设置重入间隔限制 | 60tick(3秒) |
| `/catseedlogin setSpawnLocation` | 设置当前位置为登录出生点 | - |
| `/catseedlogin setAutoKick <秒数>` | 设置自动踢出时间 | 120秒 |
| `/catseedlogin limitChineseID` | 切换中文ID限制开关 | 开启 |
| `/catseedlogin beforeLoginNoDamage` | 切换登录前免伤开关 | 开启 |
| `/catseedlogin afterLoginBack` | 切换登录后返回开关 | 开启 |
| `/catseedlogin canTpSpawnLocation` | 切换强制登录点开关 | 开启 |
| `/catseedlogin deathStateQuitRecordLocation` | 切换死亡记录位置开关 | 开启 |

## 🔐 权限节点

| 权限节点 | 描述 |
|----------|------|
| `catseedlogin.command.catseedlogin` | 管理员指令使用权限 |

## ⚙️ 配置文件

### 📄 config.yml (统一配置文件)
```yaml
# CatSeedLogin 统一配置文件
# Version: 2.0.0

# 语言设置 (zh_CN, en_US, etc.)
language: "zh_CN"

# 核心设置
settings:
  # 相同IP注册数量限制
  ip-register-count-limit: 2
  # 相同IP登录数量限制
  ip-count-limit: 2
  # 是否限制中文ID
  limit-chinese-id: true
  # 游戏ID最小长度
  min-length-id: 2
  # 游戏ID最大长度
  max-length-id: 15
  # 登录前不受到伤害
  before-login-no-damage: true
  # 离开服务器重新进入的间隔限制 (单位: tick, 20tick = 1秒)
  reenter-interval: 60
  # 登录后是否返回退出地点
  after-login-back: true
  # 登录前是否强制在登录地点
  can-tp-spawn-location: true
  # 自动踢出未登录的玩家 (秒数, 小于1则关闭)
  auto-kick: 120
  # 死亡状态退出游戏是否记录退出位置
  death-state-quit-record-location: true
  # 游戏名正则表达式
  name-pattern: "^\\w+$"
  # 登录前允许执行的指令 (支持正则表达式)
  command-white-list:
    - "/(?i)l(ogin)?(\\z| .*)"
    - "/(?i)reg(ister)?(\\z| .*)"
    - "/(?i)resetpassword?(\\z| .*)"
    - "/(?i)repw?(\\z| .*)"
    - "/(?i)worldedit cui"

# 基岩版设置
bedrock:
  # 基岩版登录绕过
  login-bypass: true
  # Floodgate前缀保护
  floodgate-prefix-protect: true

# 同IP免登录设置
same-ip-login:
  # 是否启用同IP免登录
  enabled: false
  # IP超时时间 (分钟)
  timeout: 5

# 登录前隐藏背包 (需要ProtocolLib)
empty-backpack: true

# 登录点设置
spawn:
  # 格式: 世界名:x:y:z:yaw:pitch
  location: "world:0:64:0:0:0"

# 数据库设置
database:
  # 使用MySQL (false = 使用SQLite)
  mysql: false
  host: "127.0.0.1"
  port: 3306
  database: "catseedlogin"
  user: "root"
  password: "password"

# 邮箱验证设置
email:
  # 是否启用邮箱功能
  enabled: false
  account: ""
  password: ""
  smtp-host: "smtp.example.com"
  smtp-port: "465"
  ssl-auth: true
  from-name: "Server"

# BungeeCord/Velocity 代理设置
proxy:
  # 是否启用代理模式
  enabled: false
  # 通讯IP (建议使用内网)
  host: "127.0.0.1"
  # 通讯端口
  port: 2333
  # 验证密钥
  auth-key: ""
  # 登录服务器名称 (仅代理端需要)
  login-server-name: "lobby"
```

### 🌐 语言文件 (统一存放在 languages/ 文件夹)
> 语言文件统一存放在 `plugins/CatSeedLogin/languages/` 文件夹中
> - 中文：`zh-CN.yml`
> - 英文：`en-US.yml`
> 
> 配置文件中的 `language` 选项使用下划线格式（如 `zh_CN`），对应语言文件使用标准格式（如 `zh-CN.yml`）
> 
> 支持自定义语言覆盖：用户可以在 `plugins/CatSeedLogin/languages/` 文件夹中放置自定义语言文件

## 🔗 BungeeCord配置
velocity配置方法与bungeecord配置方法相同
### 🏗️ 架构说明
```
BungeeCord网络架构：
┌─────────────────┐
│   BungeeCord    │ ← 安装插件 + 配置config.yml
├─────────────────┤
│   登录服务器    │ ← 安装插件 + 配置config.yml
├─────────────────┤
│   游戏服务器1   │ ← 无需安装
│   游戏服务器2   │ ← 无需安装
└─────────────────┘
```

### 📋 子服配置 (config.yml)
```yaml
# 子服配置文件
proxy:
  enabled: false                    # 是否启用BungeeCord模式
  host: 127.0.0.1               # 通讯IP地址(建议使用内网)
  port: 2333                     # 通讯端口
  auth-key: ""                    # 验证密钥(内网可省略)
```

### 🔗 BungeeCord端配置 (config.yml)
```yaml
# BungeeCord端配置文件
proxy:
  host: 127.0.0.1               # 通讯IP地址(需与子服一致)
  port: 2333                     # 通讯端口(需与子服一致)
  login-server-name: "lobby"      # 登录服务器名称
  auth-key: ""                    # 验证密钥(需与子服一致)
```

### 🔄 BungeeCord指令
| 指令 | 功能 |
|------|------|
| `/CatSeedLoginBungee reload` | 重载BungeeCord端配置 |
| `/cslb reload` | 同上(简写) |

### 🔄 Velocity指令
- `/cslv reload` - 重载配置文件
- `/cslv status` - 查看插件状态
- `/cslv list` - 查看已登录玩家列表

## 👨‍💻 开发者API

### 🎯 事件监听
- `CatSeedPlayerLoginEvent` - 玩家登录事件
- `CatSeedPlayerRegisterEvent` - 玩家注册事件

### 🔌 API接口
- `CatSeedLoginAPI` - 主要API接口类

### 🏗️ 架构设计
插件采用分层架构：
- **API层** - PlatformAdapter、CoreConfig等接口
- **Common层** - 配置管理、国际化等公共模块
- **平台特定层** - Bukkit、BungeeCord、Velocity实现

> 📚 **开发者文档**：详细的API文档和示例代码将在后续版本中提供。

## 💬 社区支持

### 🏘️ 交流群组
[![QQ交流群](https://img.shields.io/badge/QQ%E4%BA%A4%E6%B5%81%E7%BE%A4-839815243-blue?style=flat-square&logo=tencent-qq)](http://shang.qq.com/wpa/qunwpa?idkey=91199801a9406f659c7add6fb87b03ca071b199b36687c62a3ac51bec2f258a3)

### 📊 项目统计
- ⭐ **给项目点星**：[GitHub仓库](https://github.com/shulng/CatSeedLogin-v2)
- 🐛 **提交Issue**：[问题反馈](https://github.com/shulng/CatSeedLogin-v2/issues)
- 📖 **贡献代码**：[Pull Request](https://github.com/shulng/CatSeedLogin-v2/pulls)

### 📝 更新日志
#### v2.0.0 (2026-05-05)
- 🎯 **统一架构重构** - 统一配置管理、统一国际化系统
- 📄 **单一配置文件** - 从多个分散配置合并为统一config.yml
- 🌍 **多语言支持** - 内置中文、英文，支持自定义语言
- 📍 **数据库改进** - 玩家离线位置存储从配置文件迁移到数据库
- 🔗 **统一API接口** - 平台无关的抽象接口层

---

<div align="center">

**Made with ❤️ by [CatSeed Team](https://github.com/shulng)**

*如果这个插件对你有帮助，请考虑给项目点星支持！*

</div>