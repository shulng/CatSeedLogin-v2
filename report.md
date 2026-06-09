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
| **糟糕指数** | **95.11/100** |
| 屎山等级 | 🌱 清新可人 |

> 如沐春风，仿佛被天使亲吻过

### 📊 统计信息

| 指标 | 数值 |
|--------|-------|
| 总文件数 | 64 |
| 已跳过 | 16 |
| 耗时 | 279ms |

## 评分指标详情 {#metrics-details}

| 指标摘要 | 评分 | 状态 |
|:-----|------:|:------:|
| 循环复杂度 | 0.97% | ✓✓ |
| 认知复杂度 | 1.18% | ✓✓ |
| 嵌套深度 | 0.00% | ✓✓ |
| 函数长度 | 0.01% | ✓✓ |
| 文件长度 | 0.04% | ✓✓ |
| 参数数量 | 1.80% | ✓✓ |
| 代码重复 | 0.62% | ✓✓ |
| 结构分析 | 7.69% | ✓✓ |
| 错误处理 | 16.20% | ✓✓ |
| 注释比例 | 97.85% | ✗ |
| 命名规范 | 6.63% | ✓✓ |

## 最屎代码排行榜 {#problem-files}

### 1. src\main\java\cc\baka9\catseedlogin\bukkit\database\SQL.java

**糟糕指数: 8.74**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 1
- ❌ L75: 未处理的易出错调用

### 2. src\main\java\cc\baka9\catseedlogin\bungee\BungeeCommands.java

**糟糕指数: 7.95**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 2
- ❌ L18: 未处理的易出错调用
- 🏷️ `BungeeCommands()` L12: "BungeeCommands" - camelCase

### 3. src\main\java\cc\baka9\catseedlogin\bukkit\object\LoginPlayerHelper.java

**糟糕指数: 7.73**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 5, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 5
- ❌ L38: 未处理的易出错调用
- ❌ L40: 未处理的易出错调用
- ❌ L44: 未处理的易出错调用
- ❌ L94: 未处理的易出错调用
- 🔍 ...还有 1 个问题实在太屎，列不完了

### 4. src\main\java\cc\baka9\catseedlogin\common\config\ConfigHelper.java

**糟糕指数: 7.67**

**问题**: ⚠️ 其他问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 2

- 📏 `LocationData()` L54: 6 参数数量
- 🏷️ `LocationData()` L50: "LocationData" - camelCase
- 🏷️ `LocationData()` L54: "LocationData" - camelCase

### 5. src\main\java\cc\baka9\catseedlogin\common\i18n\I18n.java

**糟糕指数: 7.59**

**问题**: ❌ 错误处理问题: 4, 📝 注释问题: 1

- ❌ L164: 未处理的易出错调用
- ❌ L170: 未处理的易出错调用
- ❌ L190: 未处理的易出错调用
- ❌ L195: 未处理的易出错调用

### 6. src\main\java\cc\baka9\catseedlogin\common\i18n\MessageKey.java

**糟糕指数: 6.95**

**问题**: ❌ 错误处理问题: 2, 📝 注释问题: 1, 🏷️ 命名问题: 1

- ❌ L58: 未处理的易出错调用
- ❌ L66: 未处理的易出错调用
- 🏷️ `MessageKey()` L50: "MessageKey" - camelCase

### 7. src\main\java\cc\baka9\catseedlogin\common\config\ConfigManager.java

**糟糕指数: 6.91**

**问题**: ❌ 错误处理问题: 1, 📝 注释问题: 1

- ❌ L59: 未处理的易出错调用

### 8. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandResetPassword.java

**糟糕指数: 6.91**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 9
- ❌ L121: 未处理的易出错调用

### 9. src\main\java\cc\baka9\catseedlogin\bukkit\database\SQLite.java

**糟糕指数: 6.26**

**问题**: ❌ 错误处理问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- ❌ L50: 未处理的易出错调用
- 🏷️ `SQLite()` L12: "SQLite" - camelCase

### 10. src\main\java\cc\baka9\catseedlogin\common\communication\BaseCommunication.java

**糟糕指数: 6.17**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 1
- ❌ L64: 未处理的易出错调用

## 诊断结论 {#conclusion}

🌸 **清新可人** - 代码洁净，令人赏心悦目

👍 继续保持，你是编码界的一股清流，代码洁癖者的骄傲

---

*由 [fuck-u-code](https://github.com/Done-0/fuck-u-code) 生成*