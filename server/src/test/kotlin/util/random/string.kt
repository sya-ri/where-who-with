package util.random

/**
 * 文字一覧
 */
private val charList = ('a'..'z') + ('A'..'Z') + ('0'..'9')

/**
 * ランダムな文字列を生成します
 */
fun randomString(length: Int): String {
    return buildString {
        repeat(length) {
            append(charList.random())
        }
    }
}
