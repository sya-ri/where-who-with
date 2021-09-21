# サーバー

## 使い方

```shell
$ java -jar w3server.jar
```

```
Usage: java -jar w3server.jar <Option>
Options:
    start, s -> サーバーを起動します
    version, v -> バージョンを表示します
    help, h -> コマンドヘルプを表示します
```

## 開発

### コマンド

#### ビルド

```shell
# Windows
gradlew.bat build

# Mac, Linux
./gradlew build
```

#### 依存関係のバージョン確認

```shell
# Windows
gradlew.bat dependencyUpdates

# Mac, Linux
./gradlew dependencyUpdates
```

#### コードフォーマット

```shell
# Windows
gradlew.bat formatKotlin

# Mac, Linux
./gradlew formatKotlin
```

### 設計

#### データベース

[![](images/database.png)](https://dbdiagram.io/d/6149cd91825b5b01460c50de)