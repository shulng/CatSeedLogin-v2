# 🌸 屎山代码分析报告 🌸

## 📑 目录

- [糟糕指数](#overall-score)
- [评分指标详情](#metrics-details)
- [最屎代码排行榜](#problem-files)
- [诊断结论](#conclusion)

![Score](https://img.shields.io/badge/Score-94%25-brightgreen)

## 糟糕指数 {#overall-score}

| 指标摘要 | 评分 |
|------|-------|
| **糟糕指数** | **93.83/100** |
| 屎山等级 | 🌸 偶有异味 |

> 如沐春风，仿佛被天使亲吻过

### 📊 统计信息

| 指标 | 数值 |
|--------|-------|
| 总文件数 | 63 |
| 已跳过 | 16 |
| 耗时 | 249ms |

## 评分指标详情 {#metrics-details}

| 指标摘要 | 评分 | 状态 |
|:-----|------:|:------:|
| 循环复杂度 | 1.05% | ✓✓ |
| 认知复杂度 | 1.47% | ✓✓ |
| 嵌套深度 | 0.32% | ✓✓ |
| 函数长度 | 0.00% | ✓✓ |
| 文件长度 | 0.02% | ✓✓ |
| 参数数量 | 2.74% | ✓✓ |
| 代码重复 | 2.34% | ✓✓ |
| 结构分析 | 8.09% | ✓✓ |
| 错误处理 | 26.72% | ✓ |
| 注释比例 | 97.86% | ✗ |
| 命名规范 | 6.62% | ✓✓ |

## 最屎代码排行榜 {#problem-files}

### 1. src\main\java\cc\baka9\catseedlogin\velocity\Commands.java

**糟糕指数: 11.27**

**问题**: ⚠️ 其他问题: 1, 📋 重复问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 2, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 📋 `Commands()` L24: 重复模式: Commands, handleStatus
- 🏗️ L1: 循环依赖: 2
- ❌ L32: 未处理的易出错调用
- ❌ L37: 未处理的易出错调用
- 🏷️ `Commands()` L24: "Commands" - camelCase

### 2. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandResetPassword.java

**糟糕指数: 10.95**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 3, ❌ 错误处理问题: 2, 📝 注释问题: 1

- 📏 `handleReset()` L93: 6 参数数量
- 🏗️ `sendResetEmailAsync()` L73: 中等嵌套: 3
- 🏗️ `processPasswordResetAsync()` L118: 中等嵌套: 3
- 🏗️ L1: 循环依赖: 9
- ❌ L61: 未处理的易出错调用
- 🔍 ...还有 1 个问题实在太屎，列不完了

### 3. src\main\java\cc\baka9\catseedlogin\common\i18n\I18n.java

**糟糕指数: 9.47**

**问题**: 📋 重复问题: 2, 🏗️ 结构问题: 1, ❌ 错误处理问题: 6, 📝 注释问题: 1

- 📋 `loadFromFile()` L88: 重复模式: loadFromFile, loadFromStream
- 📋 `get()` L131: 重复模式: get, getOrDefault
- 🏗️ `flattenMap()` L119: 中等嵌套: 3
- ❌ L85: 未处理的易出错调用
- ❌ L126: 未处理的易出错调用
- 🔍 ...还有 4 个问题实在太屎，列不完了

### 4. src\main\java\cc\baka9\catseedlogin\bukkit\database\SQL.java

**糟糕指数: 9.31**

**问题**: 📋 重复问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 📋 `getLocation()` L61: 重复模式: getLocation, get
- 🏗️ L1: 循环依赖: 1
- ❌ L72: 未处理的易出错调用

### 5. src\main\java\cc\baka9\catseedlogin\common\config\YamlConfiguration.java

**糟糕指数: 9.15**

**问题**: ⚠️ 其他问题: 1, 📋 重复问题: 2, ❌ 错误处理问题: 4, 📝 注释问题: 1

- 📋 `load()` L36: 重复模式: load, loadFromResource
- 📋 `getInt()` L89: 重复模式: getInt, getLong, getDouble
- ❌ L175: 未处理的易出错调用
- ❌ L192: 未处理的易出错调用
- ❌ L194: 未处理的易出错调用
- 🔍 ...还有 1 个问题实在太屎，列不完了

### 6. src\main\java\cc\baka9\catseedlogin\velocity\Listeners.java

**糟糕指数: 9.06**

**问题**: ⚠️ 其他问题: 1, 📋 重复问题: 1, 🏗️ 结构问题: 2, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 📋 `onChat()` L35: 重复模式: onChat, onServerConnected
- 🏗️ `checkLoginAsync()` L90: 中等嵌套: 3
- 🏗️ L1: 循环依赖: 1
- ❌ L129: 未处理的易出错调用

### 7. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandCatSeedLogin.java

**糟糕指数: 9.04**

**问题**: ⚠️ 其他问题: 2, 🏗️ 结构问题: 1, ❌ 错误处理问题: 2, 📝 注释问题: 1

- 📏 `toggle()` L52: 6 参数数量
- 🏗️ L1: 循环依赖: 10
- ❌ L54: 未处理的易出错调用
- ❌ L226: 未处理的易出错调用

### 8. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandLogin.java

**糟糕指数: 8.91**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 2, 📝 注释问题: 1

- 🏗️ `onCommand()` L18: 中等嵌套: 3
- 🏗️ L1: 循环依赖: 7

### 9. src\main\java\cc\baka9\catseedlogin\bukkit\CatSeedLogin.java

**糟糕指数: 8.60**

**问题**: 🔄 复杂度问题: 2, 🏗️ 结构问题: 2, 📝 注释问题: 1

- 🔄 `registerCommands()` L89: 复杂度: 12
- 🔄 `registerCommands()` L89: 认知复杂度: 18
- 🏗️ `registerCommands()` L89: 中等嵌套: 3
- 🏗️ L1: 循环依赖: 8

### 10. src\main\java\cc\baka9\catseedlogin\bungee\Commands.java

**糟糕指数: 8.50**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 2, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 2
- ❌ L18: 未处理的易出错调用
- ❌ L21: 未处理的易出错调用
- 🏷️ `Commands()` L12: "Commands" - camelCase

## 诊断结论 {#conclusion}

🌸 **偶有异味** - 基本没事，但是有伤风化

👍 继续保持，你是编码界的一股清流，代码洁癖者的骄傲

---

*由 [fuck-u-code](https://github.com/Done-0/fuck-u-code) 生成*