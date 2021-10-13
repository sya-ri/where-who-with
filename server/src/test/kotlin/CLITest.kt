import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.command.action.area.areaCreate
import dev.s7a.w3.server.cli.command.action.area.areaDelete
import dev.s7a.w3.server.cli.command.action.area.areaExport
import dev.s7a.w3.server.cli.command.action.area.areaList
import dev.s7a.w3.server.cli.command.action.desk.deskCreate
import dev.s7a.w3.server.cli.command.action.desk.deskDelete
import dev.s7a.w3.server.cli.command.action.desk.deskExport
import dev.s7a.w3.server.cli.command.action.desk.deskList
import dev.s7a.w3.server.cli.command.action.version
import dev.s7a.w3.server.database.entity.Area
import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.database.table.Areas
import dev.s7a.w3.server.database.table.Desks
import org.jetbrains.exposed.sql.transactions.transaction
import util.cli.ExecutionTestPlatform
import util.cli.assertExportCsv
import util.cli.assertPrintError
import util.cli.assertPrintMessage
import util.cli.assertPrintSuccess
import util.random.randomString
import util.setupTestDatabase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class CLITest {
    @BeforeTest
    fun setupDatabase() {
        setupTestDatabase()
    }

    @Test
    fun `desk can be created`() {
        val name = randomString(16)
        val action = {
            ExecutionTestPlatform.deskCreate(name)
        }
        assertPrintSuccess(action)
        transaction {
            Desk.find { Desks.name eq name }.limit(1).firstOrNull()
        }.run(::assertNotNull)
        assertPrintError(ErrorCode.OptionExist, action)
    }

    @Test
    fun `desk can be deleted`() {
        val name = randomString(16)
        val action = {
            ExecutionTestPlatform.deskDelete(name)
        }
        val desk = transaction {
            Desk.new {
                this.name = name
            }
        }
        assertPrintSuccess(action)
        transaction {
            Desk.findById(desk.id)
        }.run(::assertNull)
        assertPrintError(ErrorCode.OptionNotFound, action)
    }

    @Test
    fun `desk list can be printed`() {
        assertPrintMessage {
            ExecutionTestPlatform.deskList()
        }
    }

    @Test
    fun `desk list can be exported`() {
        assertExportCsv {
            ExecutionTestPlatform.deskExport(null)
        }
    }

    @Test
    fun `area can be created`() {
        val name = randomString(16)
        val action = {
            ExecutionTestPlatform.areaCreate(name)
        }
        assertPrintSuccess(action)
        transaction {
            Area.find { Areas.name eq name }.limit(1).firstOrNull()
        }.run(::assertNotNull)
        assertPrintError(ErrorCode.OptionExist, action)
    }

    @Test
    fun `area can be deleted`() {
        val name = randomString(16)
        val action = {
            ExecutionTestPlatform.areaDelete(name)
        }
        val desk = transaction {
            Area.new {
                this.name = name
            }
        }
        assertPrintSuccess(action)
        transaction {
            Area.findById(desk.id)
        }.run(::assertNull)
        assertPrintError(ErrorCode.OptionNotFound, action)
    }

    @Test
    fun `area list can be printed`() {
        assertPrintMessage {
            ExecutionTestPlatform.areaList()
        }
    }

    @Test
    fun `area list can be exported`() {
        assertExportCsv {
            ExecutionTestPlatform.areaExport(null)
        }
    }

    @Test
    fun `version can be printed`() {
        val exception = assertPrintMessage {
            ExecutionTestPlatform.version()
        }
        assertNotNull(exception.message)
        assertFalse(exception.message.endsWith("null"))
    }
}
