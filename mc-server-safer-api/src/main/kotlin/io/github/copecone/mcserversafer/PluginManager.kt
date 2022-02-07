package io.github.copecone.mcserversafer

import org.bukkit.plugin.java.JavaPlugin

object PluginManager {

    private lateinit var plugin: JavaPlugin

    fun  setupPlugin(plugin: JavaPlugin) {
        this.plugin = plugin
    }

    fun getPlugin(): JavaPlugin {
        return this.plugin
    }

}