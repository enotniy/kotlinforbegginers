package ru.ekitselyuk.lib.solid

class Post5(private val logger: Logger = EventLogger()) : Poster {
    override fun createPost(repository: Repository, postMessage: String) {
        try {
            repository.addPost(postMessage)
        } catch (e: Exception) {
            logger.log(e.localizedMessage)
        }
    }
}

class EventLogger(private val repository: Repository = Repository) : Logger {
    override fun log(message: String) {
        repository.log(message)
    }
}

object DebugLogger : Logger {
    override fun log(message: String) {
        println(message)
    }
}

object StubLogger : Logger {
    override fun log(message: String) {
        // do nothing
    }
}
