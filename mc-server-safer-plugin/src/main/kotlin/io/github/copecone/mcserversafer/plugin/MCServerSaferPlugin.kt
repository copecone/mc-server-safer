package io.github.copecone.mcserversafer.plugin

import io.github.copecone.mcserversafer.PluginManager
import io.github.copecone.mcserversafer.MCServerSafer
import io.github.copecone.mcserversafer.event.MCEventListener
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class MCServerSaferPlugin: JavaPlugin() {
    override fun onEnable() {
        server.apply {
            pluginManager.apply {
                registerEvents(MCEventListener(), this@MCServerSaferPlugin)
            }
        }

        PluginManager.setupPlugin(this)
        MCServerSafer.startCore()
    }
}