package util.factory

import dev.s7a.w3.server.database.entity.Desk
import util.random.randomString

/**
 * @see Desk
 */
object DeskFactory {
    /**
     * [Desk] を生成します
     * @param length [Desk.name] の文字数
     */
    fun create(length: Int = 16): Desk {
        return Desk.new {
            name = randomString(length)
        }
    }
}
