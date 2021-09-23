package dev.s7a.w3.server.cli

/**
 * エラーコードの一覧
 */
enum class ErrorCode {
    /**
     * コマンドが見つからなかった
     */
    NotFoundCommand,

    /**
     * オプションを入力していない
     */
    OptionNotEnter,

    /**
     * オプションのフォーマットが一致しない
     */
    OptionIllegalFormat,

    /**
     * 既に存在するオプションを指定している
     */
    OptionExist,

    /**
     * 存在しないオプションを指定している
     */
    OptionNotFound,

    /**
     * 既に対話モードで実行されている
     */
    PlatformAlreadyInteract,
}
