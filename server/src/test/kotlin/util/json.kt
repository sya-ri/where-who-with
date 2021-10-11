package util

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.assertEquals

/**
 * Json のエンコードをアサートする
 *
 * @param expected 期待されるJson
 * @param actual 実際の値
 */
@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T> assertJsonEncode(expected: String, actual: T) {
    assertEquals(expected, Json.encodeToString(actual))
}

/**
 * Json のデコードをアサートする
 *
 * @param expected 期待される値
 * @param actual 実際のJson
 */
@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T> assertJsonDecode(expected: T, actual: String) {
    assertEquals(expected, Json.decodeFromString(actual))
}
