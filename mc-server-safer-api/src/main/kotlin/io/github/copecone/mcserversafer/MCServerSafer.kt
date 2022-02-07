package io.github.copecone.mcserversafer

interface MCServerSafer {
    companion object: MCServerSafer by LibraryLoader.loadImplement(MCServerSafer::class.java)

    fun startCore()
}