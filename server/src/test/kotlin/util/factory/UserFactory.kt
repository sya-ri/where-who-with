package util.factory

import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.database.entity.User

/**
 * @see User
 */
object UserFactory {
    /**
     * [User] を生成します
     * @param length [Desk.name] の文字数
     */
    fun create(length: Int = 16): User {
        return User.new {
            desk = DeskFactory.create(length)
        }
    }
}
