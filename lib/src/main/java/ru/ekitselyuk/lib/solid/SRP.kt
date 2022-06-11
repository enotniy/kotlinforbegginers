package ru.ekitselyuk.lib.solid

import java.io.File

class Post1(val logger: Logger = ErrorLogger()): Poster {

    override fun createPost(repository: Repository, postMessage: String) {
        try {
            repository.addPost(postMessage)
        } catch (e: Exception) {
            logger.log(e.localizedMessage)
        }
    }
}

class ErrorLogger(private val repository: Repository = Repository): Logger {
    override fun log(message: String) {
        repository.log(message)
        File("path").writeText(message)
    }
}
