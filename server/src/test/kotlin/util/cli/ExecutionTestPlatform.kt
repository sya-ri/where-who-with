package util.cli

import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.api.ExecutionPlatform

/**
 * テスト用の環境
 */
object ExecutionTestPlatform : ExecutionPlatform {
    /**
     * 終了状態
     */
    enum class ExitStatus {
        ImportFromCsv,
        ExportToCsv,
        PrintMessage,
        PrintSuccess,
        PrintError,
    }

    /**
     * エラーで終了したことを表す例外
     */
    class PrintErrorException(val errorCode: ErrorCode, message: String) : Exception(message)

    /**
     * 最後の終了状態
     */
    var lastExitStatus: ExitStatus? = null

    /**
     * インポートに使うデータ
     */
    var importData: List<Map<String, String>>? = null

    override fun importFromCsv(fileName: String): List<Map<String, String>> {
        lastExitStatus = ExitStatus.ImportFromCsv
        return importData.orEmpty()
    }

    override fun exportToCsv(fileName: String, data: List<List<Any?>>) {
        lastExitStatus = ExitStatus.ExportToCsv
    }

    override fun printMessage(message: String) {
        lastExitStatus = ExitStatus.PrintMessage
    }

    override fun printSuccess(message: String) {
        lastExitStatus = ExitStatus.PrintSuccess
    }

    override fun printError(errorCode: ErrorCode, message: String) {
        lastExitStatus = ExitStatus.PrintError
        throw PrintErrorException(errorCode, message)
    }
}
