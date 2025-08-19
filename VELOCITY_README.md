# CatSeedLogin-Velocity 适配说明

## 简介
CatSeedLogin-Velocity 是 CatSeedLogin 的 Velocity 代理适配版本，提供与原版 BungeeCord 适配完全一致的功能和行为。

## 功能特性
- ✅ 跨服登录验证
- ✅ 防止未登录玩家执行命令
- ✅ 防止未登录玩家切换服务器
- ✅ 自动重定向到登录服务器
- ✅ 保持登录状态同步
- ✅ 支持热重载配置
- ✅ 完整的命令管理系统

## 安装步骤

### 1. Velocity端安装
1. 下载 `CatSeedLogin-Velocity.jar` 并放入 Velocity 的 `plugins` 文件夹
2. 启动 Velocity 服务器，插件将自动生成配置文件
3. 编辑 `plugins/CatSeedLogin-Velocity/velocity.yml` 配置文件

### 2. Bukkit端配置
1. 确保 Bukkit 服务器已安装 CatSeedLogin 插件
2. 编辑 `plugins/CatSeedLogin/bungeecord.yml`：
   ```yaml
   Enable: true
   Host: 0.0.0.0  # 监听地址，0.0.0.0表示所有IP
   Port: 10086    # 监听端口
   AuthKey: your-secret-key  # 通信密钥，必须与Velocity端一致
   ```
3. 确保 `server.properties` 中 `bungeecord=true`

### 3. 配置文件说明

#### Velocity端配置 (velocity.yml)
```yaml
# 是否启用Velocity适配
Enable: true

# 登录服监听的主机地址
Host: 127.0.0.1

# 登录服监听的端口
Port: 10086

# 登录服务器名称（在velocity.toml中配置的服务器名）
LoginServerName: login

# 通信密钥，必须与Bukkit端配置一致
AuthKey: your-secret-key
```

## 命令系统
- `/cslv reload` - 重载配置文件
- `/cslv status` - 查看插件状态
- `/cslv list` - 查看已登录玩家列表

## 工作原理

### 登录流程
1. 玩家连接到 Velocity 代理
2. 插件检查玩家是否已登录
3. 如未登录，强制重定向到登录服务器
4. 玩家在登录服务器完成登录
5. 登录状态同步到 Velocity 端
6. 玩家可以自由切换服务器

### 状态同步
- 使用 TCP Socket 进行实时通信
- SHA-256 加密验证确保安全性
- 异步处理避免阻塞主线程

## 故障排除

### 常见问题

#### 1. 无法连接到登录服务器
- 检查 Bukkit 端的 `bungeecord.yml` 配置
- 确保防火墙允许指定端口通信
- 验证 `AuthKey` 是否一致

#### 2. 玩家无法登录
- 检查 Velocity 和 Bukkit 的日志输出
- 确认登录服务器名称配置正确
- 验证网络连接是否正常

#### 3. 命令无法执行
- 确认玩家是否有 `catseedlogin.admin` 权限
- 检查插件是否正确加载

### 调试模式
在配置文件中启用详细日志：
```yaml
# 在velocity.yml中添加
debug: true
```

## 技术支持
- GitHub Issues: https://github.com/shulng/CatSeedLogin-v2/issues
- QQ群: 839815243

## 更新日志
### v2.0.0
- 首次发布 Velocity 适配版本
- 完全兼容原版 BungeeCord 功能
- 优化性能和稳定性