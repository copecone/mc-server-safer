package io.github.copecone.mcserversafer.util

import io.github.copecone.mcserversafer.error.PositionFormatException
import org.bukkit.Location
import org.bukkit.entity.Player

object LocationUtil {

    fun formatLocation(loc: Location, blockLocation: Boolean = false): String {
        return if (blockLocation) {
            "${loc.blockX}, ${loc.blockY}, ${loc.blockZ}"
        } else {
            "${loc.x}, ${loc.y}, ${loc.z}"
        }
    }

    @Suppress("unused", "CanBeVal", "ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE", "UNUSED_VALUE")
    fun parsePlayerPosition(player: Player, x: String, y: String, z: String): Location {
        var finalX = x
        var finalY = y
        var finalZ = z
        var caretMode: Boolean? = null

        //TODO mixed mode error check

        try {
            if (finalX == "~" || finalX == "^") {
                finalX = player.location.x.toString()
                caretMode = finalX == "^"
            } else if (finalX.isNotEmpty()) {
                caretMode = finalX.startsWith('^')
                if (finalX.startsWith('~')) {
                    finalX = (player.location.x + finalX.drop(1).toDouble()).toString()
                } else if (finalX.startsWith('^')) { // TODO
                    finalX = (player.location.x).toString()
                }
            }

            //TODO(Y, Z)


        } catch (error: Exception) {
            error.printStackTrace()
        }

        try {
            return Location(player.world, finalX.toDouble(), finalY.toDouble(), finalZ.toDouble())
        } catch (error: NumberFormatException) {
            throw PositionFormatException(x, y, z)
        }
    }

}

