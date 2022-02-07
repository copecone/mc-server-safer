package io.github.copecone.mcserversafer.event

import io.github.copecone.mcserversafer.PluginManager
import io.github.copecone.mcserversafer.util.CommandUtil
import io.github.copecone.mcserversafer.util.LocationUtil
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class MCEventListener: Listener {

    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        val command = event.message
        val commands = command.split(" ")

        val plugin = PluginManager.getPlugin()
        val blockedBlocks = arrayOf("tnt", "minecraft:tnt")

        if (commands.size >= 5) {
            if (CommandUtil.commandEquals("setblock", commands[0])) {
                if (blockedBlocks.contains(commands[4])) {

                    event.isCancelled = true
                    plugin.logger.info(
                        "${ChatColor.RED}${ChatColor.BOLD}[${event.player.name}]${ChatColor.WHITE}${ChatColor.BOLD}이 ${ChatColor.RED}${ChatColor.BOLD}T${ChatColor.WHITE}${ChatColor.BOLD}N${ChatColor.RED}${ChatColor.BOLD}T${ChatColor.WHITE}${ChatColor.BOLD}를 ${ChatColor.GREEN}${ChatColor.BOLD}setblock ${ChatColor.WHITE}${ChatColor.BOLD}커맨드로 () ${ChatColor.WHITE}${ChatColor.BOLD}에 설치하려고 시도했습니다"
                    )
                }
            }
        }

        if (commands.size >= 8) {
            if (CommandUtil.commandEquals("fill", commands[0])) {
                if (blockedBlocks.contains(commands[7])) {
                    event.isCancelled = true
                    plugin.logger.info(
                        "${ChatColor.RED}${ChatColor.BOLD}[${event.player.name}]${ChatColor.WHITE}${ChatColor.BOLD}이 ${ChatColor.RED}${ChatColor.BOLD}T${ChatColor.WHITE}${ChatColor.BOLD}N${ChatColor.RED}${ChatColor.BOLD}T${ChatColor.WHITE}${ChatColor.BOLD}를 ${ChatColor.GREEN}${ChatColor.BOLD}fill ${ChatColor.WHITE}${ChatColor.BOLD}커맨드로 설치하려고 시도했습니다"
                    )
                }
            }
        }
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val plugin = PluginManager.getPlugin()

        if (event.blockPlaced.type == Material.TNT) {
            event.isCancelled = true
            plugin.logger.info(
                "${ChatColor.RED}${ChatColor.BOLD}[${event.player.name}]${ChatColor.WHITE}${ChatColor.BOLD}님이 ${ChatColor.RED}${ChatColor.BOLD}T${ChatColor.WHITE}${ChatColor.BOLD}N${ChatColor.RED}${ChatColor.BOLD}T${ChatColor.WHITE}${ChatColor.BOLD}를 ${ChatColor.GREEN}${ChatColor.BOLD}(${LocationUtil.formatLocation(event.block.location, true)}) ${ChatColor.WHITE}${ChatColor.BOLD}에 설치하려고 시도했습니다"
            )
        }
    }

}