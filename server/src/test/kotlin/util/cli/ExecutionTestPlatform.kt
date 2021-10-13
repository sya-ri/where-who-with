package util.cli

import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.api.ExecutionPlatform

/**
 * テスト用の環境
 */
object ExecutionTestPlatform : ExecutionPlatform {
    /**
     * [printMessage] を実行したことを表す例外
     */
    class PrintMessageException(message: String) : Exception(message)

    /**
     * [printSuccess] を実行したことを表す例外
     */
    class PrintSuccessException(message: String) : Exception(message)

    /**
     * エラーで終了したことを表す例外
     */
    class PrintErrorException(val errorCode: ErrorCode, message: String) : Exception(message)

    override fun printMessage(message: String) {
        throw PrintMessageException(message)
    }

    override fun printSuccess(message: String) {
        throw PrintSuccessException(message)
    }

    override fun printError(errorCode: ErrorCode, message: String) {
        throw PrintErrorException(errorCode, message)
    }
}
