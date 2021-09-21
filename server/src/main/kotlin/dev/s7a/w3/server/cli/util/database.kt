package dev.s7a.w3.server.cli.util

import dev.s7a.w3.server.database.setupDatabase
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * データベースを一度だけ使用する
 * - [setupDatabase] を実行
 * - [transaction] でデータベースにアクセス
 */
fun <T> useDatabaseOnce(db: Database? = null, statement: Transaction.() -> T): T {
    setupDatabase()
    return transaction(db, statement)
}
