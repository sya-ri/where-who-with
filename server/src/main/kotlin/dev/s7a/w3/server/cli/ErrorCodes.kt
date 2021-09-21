package dev.s7a.w3.server.cli

/**
 * エラーコードの一覧
 */
object ErrorCodes {
    /**
     * コマンドが見つからなかった
     */
    const val NotFoundCommand = 1

    /**
     * オプションに関するエラー
     */
    object Option {
        /**
         * オプションを入力していない
         */
        const val NotEnter = 2

        /**
         * オプションのフォーマットが一致しない
         */
        const val IllegalFormat = 3

        /**
         * 既に存在するオプションを指定している
         */
        const val Exist = 4

        /**
         * 存在しないオプションを指定している
         */
        const val NotFound = 5
    }
}
