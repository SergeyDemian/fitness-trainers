package models

import kotlin.jvm.JvmInline

@JvmInline
value class FtClientId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = FtClientId("")
    }
}