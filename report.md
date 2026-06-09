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
| **糟糕指数** | **94.14/100** |
| 屎山等级 | 🌸 偶有异味 |

> 如沐春风，仿佛被天使亲吻过

### 📊 统计信息

| 指标 | 数值 |
|--------|-------|
| 总文件数 | 64 |
| 已跳过 | 16 |
| 耗时 | 327ms |

## 评分指标详情 {#metrics-details}

| 指标摘要 | 评分 | 状态 |
|:-----|------:|:------:|
| 循环复杂度 | 0.78% | ✓✓ |
| 认知复杂度 | 1.15% | ✓✓ |
| 嵌套深度 | 0.31% | ✓✓ |
| 函数长度 | 0.01% | ✓✓ |
| 文件长度 | 0.03% | ✓✓ |
| 参数数量 | 2.19% | ✓✓ |
| 代码重复 | 1.62% | ✓✓ |
| 结构分析 | 8.10% | ✓✓ |
| 错误处理 | 25.02% | ✓ |
| 注释比例 | 97.91% | ✗ |
| 命名规范 | 6.65% | ✓✓ |

## 最屎代码排行榜 {#problem-files}

### 1. src\main\java\cc\baka9\catseedlogin\common\config\YamlConfiguration.java

**糟糕指数: 10.83**

**问题**: ⚠️ 其他问题: 1, 📋 重复问题: 3, ❌ 错误处理问题: 4, 📝 注释问题: 1

- 📋 `load()` L36: 重复模式: load, loadFromResource
- 📋 `getBoolean()` L76: 重复模式: getBoolean, getNumeric
- 📋 `getInt()` L100: 重复模式: getInt, getLong, getDouble
- ❌ L186: 未处理的易出错调用
- ❌ L203: 未处理的易出错调用
- 🔍 ...还有 2 个问题实在太屎，列不完了

### 2. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandCatSeedLogin.java

**糟糕指数: 9.43**

**问题**: ⚠️ 其他问题: 2, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 📏 `toggle()` L52: 6 参数数量
- 🏗️ L1: 循环依赖: 10
- ❌ L54: 未处理的易出错调用

### 3. src\main\java\cc\baka9\catseedlogin\bukkit\database\SQL.java

**糟糕指数: 9.08**

**问题**: 📋 重复问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 📋 `getLocation()` L61: 重复模式: getLocation, get
- 🏗️ L1: 循环依赖: 1
- ❌ L72: 未处理的易出错调用

### 4. src\main\java\cc\baka9\catseedlogin\common\i18n\I18n.java

**糟糕指数: 8.17**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 6, 📝 注释问题: 1

- 🏗️ `flattenMap()` L115: 中等嵌套: 3
- ❌ L86: 未处理的易出错调用
- ❌ L122: 未处理的易出错调用
- ❌ L135: 未处理的易出错调用
- ❌ L140: 未处理的易出错调用
- 🔍 ...还有 2 个问题实在太屎，列不完了

### 5. src\main\java\cc\baka9\catseedlogin\velocity\Listeners.java

**糟糕指数: 7.97**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 2, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ `checkLoginAsync()` L88: 中等嵌套: 3
- 🏗️ L1: 循环依赖: 1
- ❌ L127: 未处理的易出错调用

### 6. src\main\java\cc\baka9\catseedlogin\bungee\Commands.java

**糟糕指数: 7.95**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 2
- ❌ L18: 未处理的易出错调用
- 🏷️ `Commands()` L12: "Commands" - camelCase

### 7. src\main\java\cc\baka9\catseedlogin\bukkit\Communication.java

**糟糕指数: 7.87**

**问题**: 🔄 复杂度问题: 1, 🏗️ 结构问题: 3, 📝 注释问题: 1

- 🔄 `handleKeepLoggedInRequest()` L77: 嵌套深度: 4
- 🏗️ `socketServerStart()` L39: 中等嵌套: 3
- 🏗️ `handleKeepLoggedInRequest()` L77: 中等嵌套: 4
- 🏗️ L1: 循环依赖: 4

### 8. src\main\java\cc\baka9\catseedlogin\bukkit\object\EmailCode.java

**糟糕指数: 7.82**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 5, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 2
- ❌ L29: 未处理的易出错调用
- ❌ L33: 未处理的易出错调用
- ❌ L35: 未处理的易出错调用
- ❌ L54: 未处理的易出错调用
- 🔍 ...还有 2 个问题实在太屎，列不完了

### 9. src\main\java\cc\baka9\catseedlogin\bukkit\Listeners.java

**糟糕指数: 7.80**

**问题**: 🏗️ 结构问题: 3, ❌ 错误处理问题: 2, 📝 注释问题: 1

- 🏗️ `teleportToLastLocation()` L198: 中等嵌套: 3
- 🏗️ L1: 导入过多: 26
- 🏗️ L1: 循环依赖: 4
- ❌ L165: 未处理的易出错调用
- ❌ L167: 未处理的易出错调用

### 10. src\main\java\cc\baka9\catseedlogin\bungee\Listeners.java

**糟糕指数: 7.67**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 1
- ❌ L89: 未处理的易出错调用
- 🏷️ `Listeners()` L26: "Listeners" - camelCase

## 诊断结论 {#conclusion}

🌸 **偶有异味** - 基本没事，但是有伤风化

👍 继续保持，你是编码界的一股清流，代码洁癖者的骄傲

---

*由 [fuck-u-code](https://github.com/Done-0/fuck-u-code) 生成*