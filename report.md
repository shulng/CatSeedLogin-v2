# 🌸 屎山代码分析报告 🌸

## 📑 目录

- [糟糕指数](#overall-score)
- [评分指标详情](#metrics-details)
- [最屎代码排行榜](#problem-files)
- [诊断结论](#conclusion)

![Score](https://img.shields.io/badge/Score-89%25-brightgreen)

## 糟糕指数 {#overall-score}

| 指标摘要 | 评分 |
|------|-------|
| **糟糕指数** | **89.03/100** |
| 屎山等级 | 🌸 偶有异味 |

> 清新宜人，初闻像早晨的露珠

### 📊 统计信息

| 指标 | 数值 |
|--------|-------|
| 总文件数 | 63 |
| 已跳过 | 16 |
| 耗时 | 227ms |

## 评分指标详情 {#metrics-details}

| 指标摘要 | 评分 | 状态 |
|:-----|------:|:------:|
| 循环复杂度 | 3.95% | ✓✓ |
| 认知复杂度 | 5.66% | ✓✓ |
| 嵌套深度 | 7.57% | ✓✓ |
| 函数长度 | 0.32% | ✓✓ |
| 文件长度 | 0.03% | ✓✓ |
| 参数数量 | 1.83% | ✓✓ |
| 代码重复 | 5.61% | ✓✓ |
| 结构分析 | 8.94% | ✓✓ |
| 错误处理 | 25.66% | ✓ |
| 注释比例 | 98.82% | ✗ |
| 命名规范 | 6.82% | ✓✓ |

## 最屎代码排行榜 {#problem-files}

### 1. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandResetPassword.java

**糟糕指数: 52.39**

**问题**: 🔄 复杂度问题: 3, ⚠️ 其他问题: 1, 🏗️ 结构问题: 2, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🔄 `onCommand()` L20: 复杂度: 20
- 🔄 `onCommand()` L20: 认知复杂度: 38
- 🔄 `onCommand()` L20: 嵌套深度: 9
- 🏗️ `onCommand()` L20: 嵌套过深: 9
- 🏗️ L1: 循环依赖: 9
- 🔍 ...还有 1 个问题实在太屎，列不完了

### 2. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandCatSeedLogin.java

**糟糕指数: 37.22**

**问题**: 🔄 复杂度问题: 4, ⚠️ 其他问题: 2, 📋 重复问题: 1, 🏗️ 结构问题: 3, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🔄 `delPlayer()` L257: 认知复杂度: 17
- 🔄 `setPwd()` L292: 认知复杂度: 25
- 🔄 `delPlayer()` L257: 嵌套深度: 6
- 🔄 `setPwd()` L292: 嵌套深度: 8
- 📋 `deathStateQuitRecordLocation()` L48: 重复模式: deathStateQuitRecordLocation, autoKick, canTpSpawnLocation, afterLoginBack, setReenterInterval, beforeLoginNoDamage, limitChineseID, bedrockLoginBypass, LoginwiththesameIP, setIpCountLimit, setIpRegCountLimit
- 🔍 ...还有 4 个问题实在太屎，列不完了

### 3. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandChangePassword.java

**糟糕指数: 30.83**

**问题**: 🔄 复杂度问题: 3, ⚠️ 其他问题: 1, 🏗️ 结构问题: 2, 📝 注释问题: 1

- 🔄 `onCommand()` L19: 复杂度: 13
- 🔄 `onCommand()` L19: 认知复杂度: 25
- 🔄 `onCommand()` L19: 嵌套深度: 6
- 🏗️ `onCommand()` L19: 嵌套过深: 6
- 🏗️ L1: 循环依赖: 8

### 4. src\main\java\cc\baka9\catseedlogin\velocity\Listeners.java

**糟糕指数: 24.71**

**问题**: 🔄 复杂度问题: 3, ⚠️ 其他问题: 1, 📋 重复问题: 1, 🏗️ 结构问题: 3, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🔄 `onServerPreConnect()` L69: 认知复杂度: 18
- 🔄 `onServerPreConnect()` L69: 嵌套深度: 6
- 🔄 `handleLogin()` L141: 嵌套深度: 4
- 📋 `onChat()` L35: 重复模式: onChat, onServerConnected
- 🏗️ `onServerPreConnect()` L69: 嵌套过深: 6
- 🔍 ...还有 3 个问题实在太屎，列不完了

### 5. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandBindEmail.java

**糟糕指数: 18.64**

**问题**: 🔄 复杂度问题: 4, ⚠️ 其他问题: 1, 🏗️ 结构问题: 4, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🔄 `onCommand()` L20: 复杂度: 14
- 🔄 `onCommand()` L20: 认知复杂度: 22
- 🔄 `onCommand()` L20: 嵌套深度: 4
- 🔄 `bindEmail()` L107: 嵌套深度: 4
- 🏗️ `onCommand()` L20: 中等嵌套: 4
- 🔍 ...还有 4 个问题实在太屎，列不完了

### 6. src\main\java\cc\baka9\catseedlogin\bungee\Listeners.java

**糟糕指数: 18.33**

**问题**: 🔄 复杂度问题: 1, 🏗️ 结构问题: 4, ❌ 错误处理问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🔄 `onServerConnect()` L44: 嵌套深度: 5
- 🏗️ `onServerConnect()` L44: 嵌套过深: 5
- 🏗️ `onServerConnected()` L69: 中等嵌套: 3
- 🏗️ `handleLogin()` L100: 中等嵌套: 3
- 🏗️ L1: 循环依赖: 1
- 🔍 ...还有 2 个问题实在太屎，列不完了

### 7. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandRegister.java

**糟糕指数: 15.62**

**问题**: 🔄 复杂度问题: 2, ⚠️ 其他问题: 1, 🏗️ 结构问题: 2, 📝 注释问题: 1

- 🔄 `onCommand()` L19: 认知复杂度: 18
- 🔄 `onCommand()` L19: 嵌套深度: 4
- 🏗️ `onCommand()` L19: 中等嵌套: 4
- 🏗️ L1: 循环依赖: 8

### 8. src\main\java\cc\baka9\catseedlogin\common\config\ConfigHelper.java

**糟糕指数: 14.92**

**问题**: ⚠️ 其他问题: 1, 📋 重复问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 📏 `LocationData()` L49: 6 参数数量
- 📋 `parseIntOrDefault()` L66: 重复模式: parseIntOrDefault, parseLongOrDefault, parseBooleanOrDefault
- 🏷️ `LocationData()` L49: "LocationData" - camelCase

### 9. src\main\java\cc\baka9\catseedlogin\bukkit\task\TaskAutoKick.java

**糟糕指数: 13.47**

**问题**: 🔄 复杂度问题: 1, 🏗️ 结构问题: 2, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🔄 `run()` L14: 嵌套深度: 4
- 🏗️ `run()` L14: 中等嵌套: 4
- 🏗️ L1: 循环依赖: 3
- ❌ L28: 未处理的易出错调用

### 10. src\main\java\cc\baka9\catseedlogin\bukkit\database\SQL.java

**糟糕指数: 12.11**

**问题**: 📋 重复问题: 2, 🏗️ 结构问题: 1, ❌ 错误处理问题: 2, 📝 注释问题: 1

- 📋 `getLocation()` L60: 重复模式: getLocation, get
- 📋 `getAll()` L86: 重复模式: getAll, getLikeByIp
- 🏗️ L1: 循环依赖: 1
- ❌ L71: 未处理的易出错调用
- ❌ L125: 未处理的易出错调用

## 诊断结论 {#conclusion}

🌸 **偶有异味** - 基本没事，但是有伤风化

👍 继续保持，你是编码界的一股清流，代码洁癖者的骄傲

---

*由 [fuck-u-code](https://github.com/Done-0/fuck-u-code) 生成*