package dev.s7a.w3.server.cli.api

/**
 * オプションが必要なコマンドであることを表す
 */
interface RequireOption {
    /**
     * オプション引数のインデックス
     */
    val optionArgumentIndex: Int
}
