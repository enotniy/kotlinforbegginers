package ru.ekitselyuk.lib.solid

class Post5(private val logger: Logger) {

    fun createPost(repository: Repository, post: String) {
        try {
            repository.addPost(post)
        } catch (e: Exception) {
            logger.log(e.message.toString())
        }
    }
}

object DebugLogger : Logger {
    override fun log(message: String) {
        println(message)
    }
}

interface Logger {
    fun log(message: String)
}