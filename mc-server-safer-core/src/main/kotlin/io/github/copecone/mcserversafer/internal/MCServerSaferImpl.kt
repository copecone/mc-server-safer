package io.github.copecone.mcserversafer.internal

import io.github.copecone.mcserversafer.PluginManager
import io.github.copecone.mcserversafer.MCServerSafer
import io.github.monun.heartbeat.coroutines.HeartbeatScope
import kotlinx.coroutines.*
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class MCServerSaferImpl: MCServerSafer {
    private lateinit var plugin: JavaPlugin

    private lateinit var scope: Job
    private var isActive: Boolean = true

    override fun startCore() {
        this.plugin = PluginManager.getPlugin()
        this.scope = HeartbeatScope().launch {

        }
    }
}