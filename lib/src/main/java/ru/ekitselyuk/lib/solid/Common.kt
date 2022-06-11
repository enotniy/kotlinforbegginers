package ru.ekitselyuk.lib.solid


interface Logger {
    fun log(message: String)
}

interface Poster {
    fun createPost(repository: Repository, postMessage: String)
}

object Repository {
    fun addPost(message: String) {}
    fun addTag(message: String) {}
    fun addMentionPost(message: String) {}
    fun notifyUser(user: String) {}
    fun log(error: String) {}
}

object MessageQueue {
    fun getUnhandledPostsMessages(): List<String> = listOf()
}