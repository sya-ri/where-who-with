package util.factory

import dev.s7a.w3.server.database.entity.Area
import util.random.randomString

/**
 * @see Area
 */
object AreaFactory {
    /**
     * [Area] を生成します
     * @param length [Area.name] の文字数
     */
    fun create(length: Int = 16): Area {
        return Area.new {
            name = randomString(length)
        }
    }
}
