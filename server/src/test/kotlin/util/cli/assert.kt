package util.cli

import dev.s7a.w3.server.cli.ErrorCode
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertIs

/**
 * CSV ファイルが出力されたかのアサートを行う
 */
fun assertExportCsv(block: () -> Unit) {
    ExecutionTestPlatform.lastExitStatus = null
    block()
    assertEquals(ExecutionTestPlatform.ExitStatus.ExportToCsv, ExecutionTestPlatform.lastExitStatus)
}

/**
 * メッセージが表示されたかのアサートを行う
 */
fun assertPrintMessage(block: () -> Unit) {
    ExecutionTestPlatform.lastExitStatus = null
    block()
    assertEquals(ExecutionTestPlatform.ExitStatus.PrintMessage, ExecutionTestPlatform.lastExitStatus)
}

/**
 * 成功メッセージが表示されたかのアサートを行う
 */
fun assertPrintSuccess(block: () -> Unit) {
    ExecutionTestPlatform.lastExitStatus = null
    block()
    assertEquals(ExecutionTestPlatform.ExitStatus.PrintSuccess, ExecutionTestPlatform.lastExitStatus)
}

/**
 * エラーメッセージが表示されたかのアサートを行う
 */
fun assertPrintError(expected: ErrorCode, block: () -> Unit): ExecutionTestPlatform.PrintErrorException {
    ExecutionTestPlatform.lastExitStatus = null
    val actual = assertFails(block)
    assertEquals(ExecutionTestPlatform.ExitStatus.PrintError, ExecutionTestPlatform.lastExitStatus)
    assertIs<ExecutionTestPlatform.PrintErrorException>(actual)
    assertEquals(expected, actual.errorCode)
    return actual
}
