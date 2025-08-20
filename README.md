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
- 🔄 **数据迁移** - 支持数据库切换和数据迁移
- 💾 **轻量级** - 占用资源少，性能优异

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

### 🔧 settings.yml
```yaml
# 基础设置
IpRegisterCountLimit: 2          # 同IP注册数量限制
IpCountLimit: 2                  # 同IP登录数量限制
SpawnLocation: "world:0:64:0:0:0" # 登录出生点坐标
LimitChineseID: true             # 是否限制中文ID
MinLengthID: 2                   # 游戏ID最小长度
MaxLengthID: 15                  # 游戏ID最大长度
BeforeLoginNoDamage: true        # 登录前免伤
ReenterInterval: 60              # 重入间隔(tick)
AfterLoginBack: true             # 登录后返回退出点
CanTpSpawnLocation: true        # 强制登录点
AutoKick: 120                    # 自动踢出时间(秒)
DeathStateQuitRecordLocation: true # 死亡记录位置

# 高级功能
BedrockLoginBypass: true         # 基岩版登录绕过
LoginwiththesameIP: false        # 同IP免登录
IPTimeout: 5                     # IP超时时间
FloodgatePrefixProtect: true     # Floodgate前缀保护
Emptybackpack: true              # 空背包保护

# 指令白名单(支持正则表达式)
CommandWhiteList:
  - "/(?i)l(ogin)?(\z| .*)"
  - "/(?i)reg(ister)?(\z| .*)"
  - "/(?i)resetpassword?(\z| .*)"
  - "/(?i)repw?(\z| .*)"
  - "/(?i)worldedit cui"
```

### 🗄️ sql.yml (MySQL配置)
```yaml
MySQL:
  Enable: false           # 是否启用MySQL
  Host: 127.0.0.1        # 数据库地址
  Port: '3306'           # 数据库端口
  Database: databaseName  # 数据库名称
  User: root             # 数据库用户
  Password: root         # 数据库密码
```

### 📧 emailVerify.yml (邮箱配置)
```yaml
# 邮箱功能开关
Enable: false
EmailAccount: "763737569@qq.com"    # 发件邮箱
EmailPassword: "123456"              # 邮箱密码/授权码
EmailSmtpHost: "smtp.qq.com"        # SMTP服务器
EmailSmtpPort: "465"                # SMTP端口
SSLAuthVerify: true                 # SSL验证
FromPersonal: "xxx服务器"           # 发件人名称
```

### 🌐 language.yml (语言配置)
> 语言文件内容较多，此处省略。插件会自动生成默认语言文件。

## 🔗 BungeeCord配置

### 🏗️ 架构说明
```
BungeeCord网络架构：
┌─────────────────┐
│   BungeeCord    │ ← 安装插件 + 配置bungeecord.yml
├─────────────────┤
│   登录服务器    │ ← 安装插件 + 配置bungeecord.yml
├─────────────────┤
│   游戏服务器1   │ ← 无需安装
│   游戏服务器2   │ ← 无需安装
└─────────────────┘
```

### 📋 子服配置 (bungeecord.yml)
```yaml
# 子服配置文件
Enable: false                    # 是否启用BungeeCord模式
Host: 127.0.0.1               # 通讯IP地址(建议使用内网)
Port: 2333                     # 通讯端口
AuthKey: ""                    # 验证密钥(内网可省略)
```

### 🔗 BungeeCord端配置 (bungeecord.yml)
```yaml
# BungeeCord端配置文件
Host: 127.0.0.1               # 通讯IP地址(需与子服一致)
Port: 2333                     # 通讯端口(需与子服一致)
LoginServerName: "lobby"      # 登录服务器名称
AuthKey: ""                    # 验证密钥(需与子服一致)
```

### 🔄 BungeeCord指令
| 指令 | 功能 |
|------|------|
| `/CatSeedLoginBungee reload` | 重载BungeeCord端配置 |
| `/cslb reload` | 同上(简写) |

## 👨‍💻 开发者API

### 🎯 事件监听
- `CatSeedPlayerLoginEvent` - 玩家登录事件
- `CatSeedPlayerRegisterEvent` - 玩家注册事件

### 🔌 API接口
- `CatSeedLoginAPI` - 主要API接口类

> 📚 **开发者文档**：详细的API文档和示例代码将在后续版本中提供。

## 💬 社区支持

### 🏘️ 交流群组
[![QQ交流群](https://img.shields.io/badge/QQ%E4%BA%A4%E6%B5%81%E7%BE%A4-839815243-blue?style=flat-square&logo=tencent-qq)](http://shang.qq.com/wpa/qunwpa?idkey=91199801a9406f659c7add6fb87b03ca071b199b36687c62a3ac51bec2f258a3)

### 📊 项目统计
- ⭐ **给项目点星**：[GitHub仓库](https://github.com/shulng/CatSeedLogin-v2)
- 🐛 **提交Issue**：[问题反馈](https://github.com/shulng/CatSeedLogin-v2/issues)
- 📖 **贡献代码**：[Pull Request](https://github.com/shulng/CatSeedLogin-v2/pulls)

---

<div align="center">

**Made with ❤️ by [CatSeed Team](https://github.com/shulng)**

*如果这个插件对你有帮助，请考虑给项目点星支持！*

</div>
