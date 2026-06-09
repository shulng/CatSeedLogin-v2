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
| **糟糕指数** | **94.87/100** |
| 屎山等级 | 🌸 偶有异味 |

> 如沐春风，仿佛被天使亲吻过

### 📊 统计信息

| 指标 | 数值 |
|--------|-------|
| 总文件数 | 64 |
| 已跳过 | 16 |
| 耗时 | 212ms |

## 评分指标详情 {#metrics-details}

| 指标摘要 | 评分 | 状态 |
|:-----|------:|:------:|
| 循环复杂度 | 0.78% | ✓✓ |
| 认知复杂度 | 1.10% | ✓✓ |
| 嵌套深度 | 0.00% | ✓✓ |
| 函数长度 | 0.01% | ✓✓ |
| 文件长度 | 0.04% | ✓✓ |
| 参数数量 | 1.80% | ✓✓ |
| 代码重复 | 0.83% | ✓✓ |
| 结构分析 | 8.13% | ✓✓ |
| 错误处理 | 21.35% | ✓ |
| 注释比例 | 97.81% | ✗ |
| 命名规范 | 6.68% | ✓✓ |

## 最屎代码排行榜 {#problem-files}

### 1. src\main\java\cc\baka9\catseedlogin\bukkit\database\SQL.java

**糟糕指数: 8.88**

**问题**: 📋 重复问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 📋 `getLocation()` L61: 重复模式: getLocation, get
- 🏗️ L1: 循环依赖: 1
- ❌ L69: 未处理的易出错调用

### 2. src\main\java\cc\baka9\catseedlogin\common\i18n\I18n.java

**糟糕指数: 8.17**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 6, 📝 注释问题: 1

- 🏗️ `flattenMap()` L115: 中等嵌套: 3
- ❌ L86: 未处理的易出错调用
- ❌ L123: 未处理的易出错调用
- ❌ L136: 未处理的易出错调用
- ❌ L141: 未处理的易出错调用
- 🔍 ...还有 2 个问题实在太屎，列不完了

### 3. src\main\java\cc\baka9\catseedlogin\velocity\Listeners.java

**糟糕指数: 7.97**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 2, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ `checkLoginAsync()` L88: 中等嵌套: 3
- 🏗️ L1: 循环依赖: 1
- ❌ L127: 未处理的易出错调用

### 4. src\main\java\cc\baka9\catseedlogin\bungee\Commands.java

**糟糕指数: 7.95**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 2
- ❌ L18: 未处理的易出错调用
- 🏷️ `Commands()` L12: "Commands" - camelCase

### 5. src\main\java\cc\baka9\catseedlogin\bukkit\Listeners.java

**糟糕指数: 7.61**

**问题**: 🏗️ 结构问题: 2, ❌ 错误处理问题: 2, 📝 注释问题: 1

- 🏗️ L1: 导入过多: 26
- 🏗️ L1: 循环依赖: 4
- ❌ L165: 未处理的易出错调用
- ❌ L167: 未处理的易出错调用

### 6. src\main\java\cc\baka9\catseedlogin\util\Mail.java

**糟糕指数: 7.55**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 1
- ❌ L27: 未处理的易出错调用
- 🏷️ `Mail()` L9: "Mail" - camelCase

### 7. src\main\java\cc\baka9\catseedlogin\common\config\ConfigHelper.java

**糟糕指数: 7.54**

**问题**: ⚠️ 其他问题: 1, 📝 注释问题: 1

- 📏 `LocationData()` L49: 6 参数数量

### 8. src\main\java\cc\baka9\catseedlogin\bukkit\object\LoginPlayerHelper.java

**糟糕指数: 7.53**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 7, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 5
- ❌ L34: 未处理的易出错调用
- ❌ L36: 未处理的易出错调用
- ❌ L74: 未处理的易出错调用
- ❌ L105: 未处理的易出错调用
- 🔍 ...还有 3 个问题实在太屎，列不完了

### 9. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandResetPassword.java

**糟糕指数: 7.30**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 3, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ `sendResetEmailAsync()` L77: 中等嵌套: 3
- 🏗️ `processPasswordResetAsync()` L130: 中等嵌套: 3
- 🏗️ L1: 循环依赖: 9
- ❌ L110: 未处理的易出错调用

### 10. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandBindEmail.java

**糟糕指数: 7.06**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 3, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ `sendEmailCode()` L111: 中等嵌套: 3
- 🏗️ `bindEmail()` L129: 中等嵌套: 3
- 🏗️ L1: 循环依赖: 9
- ❌ L76: 未处理的易出错调用

## 诊断结论 {#conclusion}

🌸 **偶有异味** - 基本没事，但是有伤风化

👍 继续保持，你是编码界的一股清流，代码洁癖者的骄傲

---

*由 [fuck-u-code](https://github.com/Done-0/fuck-u-code) 生成*