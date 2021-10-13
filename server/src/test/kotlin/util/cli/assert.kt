package util.cli

import dev.s7a.w3.server.cli.ErrorCode
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertIs

/**
 * CSV ファイルが出力されたかのアサートを行う
 */
fun assertExportCsv(block: () -> Unit): ExecutionTestPlatform.ExportToCsvException {
    val actual = assertFails(block)
    assertIs<ExecutionTestPlatform.ExportToCsvException>(actual)
    return actual
}

/**
 * メッセージが表示されたかのアサートを行う
 */
fun assertPrintMessage(block: () -> Unit): ExecutionTestPlatform.PrintMessageException {
    val actual = assertFails(block)
    assertIs<ExecutionTestPlatform.PrintMessageException>(actual)
    return actual
}

/**
 * 成功メッセージが表示されたかのアサートを行う
 */
fun assertPrintSuccess(block: () -> Unit): ExecutionTestPlatform.PrintSuccessException {
    val actual = assertFails(block)
    assertIs<ExecutionTestPlatform.PrintSuccessException>(actual)
    return actual
}

/**
 * エラーメッセージが表示されたかのアサートを行う
 */
fun assertPrintError(expected: ErrorCode, block: () -> Unit): ExecutionTestPlatform.PrintErrorException {
    val actual = assertFails(block)
    assertIs<ExecutionTestPlatform.PrintErrorException>(actual)
    assertEquals(expected, actual.errorCode)
    return actual
}
