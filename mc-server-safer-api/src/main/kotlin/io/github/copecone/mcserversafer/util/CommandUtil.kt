package io.github.copecone.mcserversafer.util

object CommandUtil {

    fun commandEquals(targetCommand: String, command: String, prefix: String = "minecraft"): Boolean {
        val targetComm = arrayOf(dropSlash(targetCommand), "${prefix}:${targetCommand}")
        val comm = dropSlash(command)

        return targetComm.contains(comm)
    }

    fun dropSlash(command: String): String {
        if (command.isNotEmpty()) {
            if (command.startsWith('/')) {
                return command.drop(1)
            }
        }

        return command
    }

}