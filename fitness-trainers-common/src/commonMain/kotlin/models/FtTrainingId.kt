package models

import kotlin.jvm.JvmInline

@JvmInline
value class FtTrainingId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = FtTrainingId("")
    }
}