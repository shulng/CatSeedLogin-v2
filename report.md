# 🌸 屎山代码分析报告 🌸

## 📑 目录

- [糟糕指数](#overall-score)
- [评分指标详情](#metrics-details)
- [最屎代码排行榜](#problem-files)
- [诊断结论](#conclusion)

![Score](https://img.shields.io/badge/Score-95%25-brightgreen)

## 糟糕指数 {#overall-score}

| 指标摘要 | 评分 |
|------|-------|
| **糟糕指数** | **95.09/100** |
| 屎山等级 | 🌱 清新可人 |

> 如沐春风，仿佛被天使亲吻过

### 📊 统计信息

| 指标 | 数值 |
|--------|-------|
| 总文件数 | 64 |
| 已跳过 | 16 |
| 耗时 | 202ms |

## 评分指标详情 {#metrics-details}

| 指标摘要 | 评分 | 状态 |
|:-----|------:|:------:|
| 循环复杂度 | 0.84% | ✓✓ |
| 认知复杂度 | 1.12% | ✓✓ |
| 嵌套深度 | 0.00% | ✓✓ |
| 函数长度 | 0.01% | ✓✓ |
| 文件长度 | 0.04% | ✓✓ |
| 参数数量 | 1.80% | ✓✓ |
| 代码重复 | 0.62% | ✓✓ |
| 结构分析 | 7.80% | ✓✓ |
| 错误处理 | 18.63% | ✓✓ |
| 注释比例 | 97.84% | ✗ |
| 命名规范 | 6.62% | ✓✓ |

## 最屎代码排行榜 {#problem-files}

### 1. src\main\java\cc\baka9\catseedlogin\bukkit\database\SQL.java

**糟糕指数: 8.20**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 1
- ❌ L65: 未处理的易出错调用

### 2. src\main\java\cc\baka9\catseedlogin\bungee\BungeeCommands.java

**糟糕指数: 7.95**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 2
- ❌ L18: 未处理的易出错调用
- 🏷️ `BungeeCommands()` L12: "BungeeCommands" - camelCase

### 3. src\main\java\cc\baka9\catseedlogin\common\config\ConfigHelper.java

**糟糕指数: 7.67**

**问题**: ⚠️ 其他问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 2

- 📏 `LocationData()` L54: 6 参数数量
- 🏷️ `LocationData()` L50: "LocationData" - camelCase
- 🏷️ `LocationData()` L54: "LocationData" - camelCase

### 4. src\main\java\cc\baka9\catseedlogin\common\i18n\I18n.java

**糟糕指数: 7.59**

**问题**: ❌ 错误处理问题: 4, 📝 注释问题: 1

- ❌ L160: 未处理的易出错调用
- ❌ L165: 未处理的易出错调用
- ❌ L184: 未处理的易出错调用
- ❌ L189: 未处理的易出错调用

### 5. src\main\java\cc\baka9\catseedlogin\bukkit\object\LoginPlayerHelper.java

**糟糕指数: 7.58**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 4, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 5
- ❌ L34: 未处理的易出错调用
- ❌ L36: 未处理的易出错调用
- ❌ L83: 未处理的易出错调用
- ❌ L129: 未处理的易出错调用

### 6. src\main\java\cc\baka9\catseedlogin\util\EmailSender.java

**糟糕指数: 7.35**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 1
- ❌ L29: 未处理的易出错调用
- 🏷️ `EmailSender()` L9: "EmailSender" - camelCase

### 7. src\main\java\cc\baka9\catseedlogin\common\i18n\MessageKey.java

**糟糕指数: 6.95**

**问题**: ❌ 错误处理问题: 2, 📝 注释问题: 1, 🏷️ 命名问题: 1

- ❌ L58: 未处理的易出错调用
- ❌ L62: 未处理的易出错调用
- 🏷️ `MessageKey()` L50: "MessageKey" - camelCase

### 8. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandResetPassword.java

**糟糕指数: 6.91**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 9
- ❌ L121: 未处理的易出错调用

### 9. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandBindEmail.java

**糟糕指数: 6.70**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 9
- ❌ L76: 未处理的易出错调用

### 10. src\main\java\cc\baka9\catseedlogin\common\config\ConfigManager.java

**糟糕指数: 6.33**

**问题**: ❌ 错误处理问题: 1, 📝 注释问题: 1

- ❌ L75: 未处理的易出错调用

## 诊断结论 {#conclusion}

🌸 **清新可人** - 代码洁净，令人赏心悦目

👍 继续保持，你是编码界的一股清流，代码洁癖者的骄傲

---

*由 [fuck-u-code](https://github.com/Done-0/fuck-u-code) 生成*