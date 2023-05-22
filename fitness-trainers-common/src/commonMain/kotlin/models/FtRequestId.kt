package models

import kotlin.jvm.JvmInline

@JvmInline
value class FtRequestId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = FtRequestId("")
    }
}