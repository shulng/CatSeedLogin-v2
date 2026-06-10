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
| **糟糕指数** | **95.43/100** |
| 屎山等级 | 🌱 清新可人 |

> 如沐春风，仿佛被天使亲吻过

### 📊 统计信息

| 指标 | 数值 |
|--------|-------|
| 总文件数 | 63 |
| 已跳过 | 16 |
| 耗时 | 211ms |

## 评分指标详情 {#metrics-details}

| 指标摘要 | 评分 | 状态 |
|:-----|------:|:------:|
| 循环复杂度 | 0.95% | ✓✓ |
| 认知复杂度 | 1.07% | ✓✓ |
| 嵌套深度 | 0.00% | ✓✓ |
| 函数长度 | 0.01% | ✓✓ |
| 文件长度 | 0.04% | ✓✓ |
| 参数数量 | 1.83% | ✓✓ |
| 代码重复 | 0.63% | ✓✓ |
| 结构分析 | 7.67% | ✓✓ |
| 错误处理 | 9.83% | ✓✓ |
| 注释比例 | 97.99% | ✗ |
| 命名规范 | 6.71% | ✓✓ |

## 最屎代码排行榜 {#problem-files}

### 1. src\main\java\cc\baka9\catseedlogin\bungee\BungeeCommands.java

**糟糕指数: 7.95**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 2
- ❌ L18: 未处理的易出错调用
- 🏷️ `BungeeCommands()` L12: "BungeeCommands" - camelCase

### 2. src\main\java\cc\baka9\catseedlogin\common\config\ConfigHelper.java

**糟糕指数: 7.67**

**问题**: ⚠️ 其他问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 2

- 📏 `LocationData()` L54: 6 参数数量
- 🏷️ `LocationData()` L50: "LocationData" - camelCase
- 🏷️ `LocationData()` L54: "LocationData" - camelCase

### 3. src\main\java\cc\baka9\catseedlogin\bukkit\database\SQL.java

**糟糕指数: 7.55**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 1
- ❌ L131: 未处理的易出错调用

### 4. src\main\java\cc\baka9\catseedlogin\common\i18n\MessageKey.java

**糟糕指数: 6.95**

**问题**: ❌ 错误处理问题: 2, 📝 注释问题: 1, 🏷️ 命名问题: 1

- ❌ L58: 未处理的易出错调用
- ❌ L66: 未处理的易出错调用
- 🏷️ `MessageKey()` L50: "MessageKey" - camelCase

### 5. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandResetPassword.java

**糟糕指数: 6.91**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 9
- ❌ L121: 未处理的易出错调用

### 6. src\main\java\cc\baka9\catseedlogin\bukkit\task\TaskAutoKick.java

**糟糕指数: 6.71**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 2, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 3
- ❌ L35: 未处理的易出错调用
- ❌ L48: 未处理的易出错调用

### 7. src\main\java\cc\baka9\catseedlogin\bukkit\object\LoginPlayerHelper.java

**糟糕指数: 6.58**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 3, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 6
- ❌ L41: 未处理的易出错调用
- ❌ L50: 未处理的易出错调用
- ❌ L157: 未处理的易出错调用

### 8. src\main\java\cc\baka9\catseedlogin\common\i18n\I18n.java

**糟糕指数: 6.10**

**问题**: ❌ 错误处理问题: 2, 📝 注释问题: 1

- ❌ L164: 未处理的易出错调用
- ❌ L174: 未处理的易出错调用

### 9. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandChangePassword.java

**糟糕指数: 6.04**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 8

### 10. src\main\java\cc\baka9\catseedlogin\velocity\Commands.java

**糟糕指数: 5.94**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 2
- ❌ L30: 未处理的易出错调用
- 🏷️ `Commands()` L23: "Commands" - camelCase

### 11. src\main\java\cc\baka9\catseedlogin\bukkit\object\EmailCode.java

**糟糕指数: 5.60**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 3, ❌ 错误处理问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ `create()` L29: 中等嵌套: 3
- 🏗️ `removeByName()` L60: 中等嵌套: 3
- 🏗️ L1: 循环依赖: 2
- ❌ L29: 未处理的易出错调用
- 🏷️ `EmailCode()` L21: "EmailCode" - camelCase

### 12. src\main\java\cc\baka9\catseedlogin\bukkit\CatSeedLogin.java

**糟糕指数: 5.49**

**问题**: 🏗️ 结构问题: 2, 📝 注释问题: 1

- 🏗️ `registerBindEmailCommand()` L117: 中等嵌套: 3
- 🏗️ L1: 循环依赖: 8

### 13. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandBindEmail.java

**糟糕指数: 5.40**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 9

### 14. src\main\java\cc\baka9\catseedlogin\common\util\ValidationUtil.java

**糟糕指数: 5.37**

**问题**: 📋 重复问题: 1, 📝 注释问题: 1

- 📋 `isLoopbackAddress()` L46: 重复模式: isLoopbackAddress, isPrivateAddress

### 15. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandCatSeedLogin.java

**糟糕指数: 5.28**

**问题**: ⚠️ 其他问题: 2, 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 10

### 16. src\main\java\cc\baka9\catseedlogin\common\config\YamlConfiguration.java

**糟糕指数: 5.16**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 2, ❌ 错误处理问题: 2, 📝 注释问题: 1

- 🏗️ `get()` L158: 中等嵌套: 3
- 🏗️ `setNestedValue()` L176: 中等嵌套: 3
- ❌ L159: 未处理的易出错调用
- ❌ L190: 未处理的易出错调用

### 17. src\main\java\cc\baka9\catseedlogin\velocity\PluginMain.java

**糟糕指数: 4.66**

**问题**: 📋 重复问题: 1, 🏗️ 结构问题: 1, 📝 注释问题: 1

- 📋 `PluginMain()` L37: 重复模式: PluginMain, onProxyInitialization
- 🏗️ L1: 循环依赖: 4

### 18. src\main\java\cc\baka9\catseedlogin\bukkit\Listeners.java

**糟糕指数: 4.46**

**问题**: 🏗️ 结构问题: 3, 📝 注释问题: 1

- 🏗️ `onPlayerQuit()` L170: 中等嵌套: 3
- 🏗️ L1: 导入过多: 27
- 🏗️ L1: 循环依赖: 5

### 19. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandRegister.java

**糟糕指数: 4.39**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 8

### 20. src\main\java\cc\baka9\catseedlogin\bukkit\command\CommandLogin.java

**糟糕指数: 4.39**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 7

### 21. src\main\java\cc\baka9\catseedlogin\common\config\BaseConfigManager.java

**糟糕指数: 4.00**

**问题**: 🏗️ 结构问题: 2, 📝 注释问题: 1

- 🏗️ L1: 函数过多: 54
- 🏗️ L1: 循环依赖: 5

### 22. src\main\java\cc\baka9\catseedlogin\bukkit\PluginContext.java

**糟糕指数: 3.89**

**问题**: 🏗️ 结构问题: 1, ❌ 错误处理问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 2
- ❌ L24: 未处理的易出错调用

### 23. src\main\java\cc\baka9\catseedlogin\bukkit\config\BukkitConfigManager.java

**糟糕指数: 3.87**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 5

### 24. src\main\java\cc\baka9\catseedlogin\velocity\config\VelocityConfigManager.java

**糟糕指数: 3.85**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 3
- 🏷️ `VelocityConfigManager()` L14: "VelocityConfigManager" - camelCase

### 25. src\main\java\cc\baka9\catseedlogin\bungee\config\BungeeConfigManager.java

**糟糕指数: 3.85**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 3
- 🏷️ `BungeeConfigManager()` L14: "BungeeConfigManager" - camelCase

### 26. src\main\java\cc\baka9\catseedlogin\bungee\Listeners.java

**糟糕指数: 3.72**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 1
- 🏷️ `Listeners()` L26: "Listeners" - camelCase

### 27. src\main\java\cc\baka9\catseedlogin\velocity\Listeners.java

**糟糕指数: 3.60**

**问题**: ⚠️ 其他问题: 1, 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 1

### 28. src\main\java\cc\baka9\catseedlogin\velocity\config\VelocityPlatformAdapter.java

**糟糕指数: 3.53**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 4

### 29. src\main\java\cc\baka9\catseedlogin\bungee\config\BungeePlatformAdapter.java

**糟糕指数: 3.53**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 4

### 30. src\main\java\cc\baka9\catseedlogin\bukkit\config\BukkitPlatformAdapter.java

**糟糕指数: 3.53**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 5

### 31. src\main\java\cc\baka9\catseedlogin\common\config\ConfigManager.java

**糟糕指数: 3.51**

**问题**: 📝 注释问题: 1

✓ 代码质量良好，没有明显问题

### 32. src\main\java\cc\baka9\catseedlogin\bungee\PluginMain.java

**糟糕指数: 3.40**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 4

### 33. src\main\java\cc\baka9\catseedlogin\bukkit\Config.java

**糟糕指数: 3.40**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 5

### 34. src\main\java\cc\baka9\catseedlogin\bukkit\Communication.java

**糟糕指数: 3.40**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 5

### 35. src\main\java\cc\baka9\catseedlogin\velocity\VelocityCommunication.java

**糟糕指数: 3.40**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 2
- 🏷️ `VelocityCommunication()` L12: "VelocityCommunication" - camelCase

### 36. src\main\java\cc\baka9\catseedlogin\bungee\BungeeCommunication.java

**糟糕指数: 3.40**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 2
- 🏷️ `BungeeCommunication()` L11: "BungeeCommunication" - camelCase

### 37. src\main\java\cc\baka9\catseedlogin\bukkit\task\Task.java

**糟糕指数: 3.40**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 2
- 🏷️ `Task()` L15: "Task" - camelCase

### 38. src\main\java\cc\baka9\catseedlogin\bukkit\util\EmailSender.java

**糟糕指数: 3.39**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 1
- 🏷️ `EmailSender()` L9: "EmailSender" - camelCase

### 39. src\main\java\cc\baka9\catseedlogin\bukkit\CatScheduler.java

**糟糕指数: 3.37**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ `teleport()` L34: 中等嵌套: 3

### 40. src\main\java\cc\baka9\catseedlogin\bukkit\Cache.java

**糟糕指数: 3.35**

**问题**: 🏗️ 结构问题: 3, 📝 注释问题: 1

- 🏗️ `refreshAll()` L24: 中等嵌套: 3
- 🏗️ `refresh()` L42: 中等嵌套: 3
- 🏗️ L1: 循环依赖: 1

### 41. src\main\java\cc\baka9\catseedlogin\bukkit\event\CatSeedPlayerLoginEvent.java

**糟糕指数: 3.33**

**问题**: 📝 注释问题: 1, 🏷️ 命名问题: 2

- 🏷️ `CatSeedPlayerLoginEvent()` L16: "CatSeedPlayerLoginEvent" - camelCase
- 🏷️ `Result()` L36: "Result" - camelCase

### 42. src\main\java\cc\baka9\catseedlogin\bukkit\task\TaskSendLoginMessage.java

**糟糕指数: 3.31**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 3

### 43. src\main\java\cc\baka9\catseedlogin\bukkit\ProtocolLibListeners.java

**糟糕指数: 3.27**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 1
- 🏷️ `ProtocolLibListeners()` L19: "ProtocolLibListeners" - camelCase

### 44. src\main\java\cc\baka9\catseedlogin\bukkit\database\MySQL.java

**糟糕指数: 3.27**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 1
- 🏷️ `MySQL()` L12: "MySQL" - camelCase

### 45. src\main\java\cc\baka9\catseedlogin\common\model\LoginPlayer.java

**糟糕指数: 3.19**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏗️ L1: 循环依赖: 1
- 🏷️ `LoginPlayer()` L21: "LoginPlayer" - camelCase

### 46. src\main\java\cc\baka9\catseedlogin\bukkit\event\CatSeedPlayerRegisterEvent.java

**糟糕指数: 3.13**

**问题**: 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏷️ `CatSeedPlayerRegisterEvent()` L13: "CatSeedPlayerRegisterEvent" - camelCase

### 47. src\main\java\cc\baka9\catseedlogin\bukkit\database\BufferStatement.java

**糟糕指数: 3.00**

**问题**: 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏷️ `BufferStatement()` L13: "BufferStatement" - camelCase

### 48. src\main\java\cc\baka9\catseedlogin\bukkit\database\SQLite.java

**糟糕指数: 2.86**

**问题**: 📝 注释问题: 1, 🏷️ 命名问题: 1

- 🏷️ `SQLite()` L13: "SQLite" - camelCase

### 49. src\main\java\cc\baka9\catseedlogin\bukkit\CatSeedLoginAPI.java

**糟糕指数: 2.77**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 1

### 50. src\main\java\cc\baka9\catseedlogin\common\communication\BaseCommunication.java

**糟糕指数: 2.77**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 1

### 51. src\main\java\cc\baka9\catseedlogin\common\api\PlatformAdapter.java

**糟糕指数: 2.77**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 1

### 52. src\main\java\cc\baka9\catseedlogin\common\api\CoreConfig.java

**糟糕指数: 2.77**

**问题**: 🏗️ 结构问题: 1, 📝 注释问题: 1

- 🏗️ L1: 循环依赖: 1

### 53. src\main\java\cc\baka9\catseedlogin\common\Version.java

**糟糕指数: 2.50**

**问题**: 📝 注释问题: 1

✓ 代码质量良好，没有明显问题

### 54. src\main\java\cc\baka9\catseedlogin\common\util\DateUtil.java

**糟糕指数: 2.50**

**问题**: 📝 注释问题: 1

✓ 代码质量良好，没有明显问题

### 55. src\main\java\cc\baka9\catseedlogin\common\util\Crypt.java

**糟糕指数: 2.50**

**问题**: 📝 注释问题: 1

✓ 代码质量良好，没有明显问题

### 56. src\main\java\cc\baka9\catseedlogin\common\util\CommunicationAuth.java

**糟糕指数: 2.50**

**问题**: 📝 注释问题: 1

✓ 代码质量良好，没有明显问题

### 57. src\main\java\cc\baka9\catseedlogin\common\database\BaseDatabaseConnection.java

**糟糕指数: 2.50**

**问题**: 📝 注释问题: 1

✓ 代码质量良好，没有明显问题

### 58. src\main\java\cc\baka9\catseedlogin\common\config\ConfigurationSection.java

**糟糕指数: 2.50**

**问题**: 📝 注释问题: 1

✓ 代码质量良好，没有明显问题

### 59. src\main\java\cc\baka9\catseedlogin\common\config\Configuration.java

**糟糕指数: 2.50**

**问题**: 📝 注释问题: 1

✓ 代码质量良好，没有明显问题

### 60. src\main\java\cc\baka9\catseedlogin\common\api\EmailConfig.java

**糟糕指数: 2.50**

**问题**: 📝 注释问题: 1

✓ 代码质量良好，没有明显问题

### 61. src\main\java\cc\baka9\catseedlogin\common\api\DatabaseConfig.java

**糟糕指数: 2.50**

**问题**: 📝 注释问题: 1

✓ 代码质量良好，没有明显问题

### 62. src\main\java\cc\baka9\catseedlogin\common\api\BungeeCordConfig.java

**糟糕指数: 2.50**

**问题**: 📝 注释问题: 1

✓ 代码质量良好，没有明显问题

### 63. src\main\java\cc\baka9\catseedlogin\common\config\ConfigConstants.java

**糟糕指数: 2.17**

**问题**: 📝 注释问题: 1

✓ 代码质量良好，没有明显问题

## 诊断结论 {#conclusion}

🌸 **清新可人** - 代码洁净，令人赏心悦目

👍 继续保持，你是编码界的一股清流，代码洁癖者的骄傲

---

*由 [fuck-u-code](https://github.com/Done-0/fuck-u-code) 生成*