package dev.s7a.w3.server.cli.flag

import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.joran.JoranConfigurator
import dev.s7a.w3.server.cli.api.Flag
import org.slf4j.LoggerFactory

/**
 * デバッグフラグ
 */
object DebugFlag : Flag("--debug", "-D", "デバッグログを表示します") {
    /**
     * デフォルトのログ設定ファイル
     */
    private const val defaultLogbackXml = "logback-default.xml"

    /**
     * デバッグ表示のログ設定ファイル
     */
    private const val debugLogbackXml = "logback-debug.xml"

    override fun execute(isSet: Boolean) {
        val context = LoggerFactory.getILoggerFactory() as LoggerContext
        context.reset()
        val configurator = JoranConfigurator()
        configurator.context = context
        val configUrl = ClassLoader.getSystemResource(if (isSet) debugLogbackXml else defaultLogbackXml)
        configurator.doConfigure(configUrl)
    }
}
