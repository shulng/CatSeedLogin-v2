# AGENTS.md

## Build & Verify

```bash
# Build (produces shaded JAR at target/CatSeedLogin-2.0.0.jar)
mvn -B clean package --no-transfer-progress

# Build with verbose output
mvn -B clean package
```

No lint/typecheck/test commands exist. The repo has no test source (`src/test/` is gitignored). CI only runs `mvn clean package`.

## Architecture

Single-module Maven project. Three platform entry points share a `common/` layer:

```
common/        → Shared API interfaces, config, i18n, database abstractions
bukkit/        → Bukkit/Spigot/Paper/Folia server plugin (main plugin)
bungee/        → BungeeCord proxy plugin
velocity/      → Velocity proxy plugin
```

**Entry points** (each platform's `onEnable`/initialization):
- `bukkit/CatSeedLogin.java` — Bukkit plugin main class (`JavaPlugin`)
- `bungee/PluginMain.java` — BungeeCord plugin main class
- `velocity/PluginMain.java` — Velocity plugin main class

**API interfaces** (`common/api/`):
- `PlatformAdapter` — platform abstraction
- `CoreConfig`, `DatabaseConfig`, `EmailConfig`, `BungeeCordConfig` — config interfaces

**Config flow**: Each platform has its own `ConfigManager` (e.g., `BukkitConfigManager`) implementing `BaseConfigManager`. All platforms read from a single `config.yml` at runtime.

**Database**: `common/database/BaseDatabaseConnection` → `bukkit/database/SQLite.java` or `MySQL.java`. Uses raw JDBC, no ORM.

## Key Conventions

- **Java 8 target** (`maven.compiler.source/target=8`). Do not use Java 9+ APIs.
- **Lombok** is used (`provided` scope). Annotations like `@Getter`, `@Setter`, `@Data` are expected.
- **Version filtering**: `${version}` in `pom.xml` is filtered into `Version.java` and plugin descriptors (`plugin.yml`, `bungee.yml`, `velocity-plugin.json`).
- **i18n**: All user-facing strings go through `I18n` + `MessageKey` enum. Language files are `languages/zh-CN.yml` and `languages/en-US.yml`. The config key `language` uses underscore format (`zh_CN`) mapping to dash-format filenames (`zh-CN.yml`).
- **Dependencies compiled into JAR** (shaded): sqlite-jdbc, mysql-connector-java, snakeyaml, javax.mail, commons-email, commons-lang3, commons-net, FoliaLib, MorePaperLib.
- **Dependencies NOT shaded** (provided by server): bukkit, spigot-api, paper-api, folia-api, bungeecord-api, velocity-api, ProtocolLib, Lombok, Floodgate.

## Gotchas

- `plugin.yml` lists `main: cc.baka9.catseedlogin.bukkit.CatSeedLogin`. The Bukkit entry point is the only platform that registers database, commands, and event listeners directly.
- ProtocolLib integration is optional — detected at runtime via `Class.forName`. If absent, backpack hiding is disabled.
- BungeeCord/Velocity plugins communicate with the Bukkit plugin over a socket (see `Communication` / `BungeeCommunication` / `VelocityCommunication` on port 2333).
- The `proxy.enabled` config flag controls whether the Bukkit plugin opens a socket server for cross-proxy auth.
- `src/test/` is gitignored — no automated tests exist.
