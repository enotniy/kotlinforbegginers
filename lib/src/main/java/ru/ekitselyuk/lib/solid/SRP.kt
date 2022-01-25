package ru.ekitselyuk.lib.solid

class Post1 {

    private val logger = EventLogger

    fun createPost(repository: Repository, post: String) {
        try {
            repository.addPost(post)
        } catch (e: Exception) {
            logger.log(e.message.toString())
        }
    }

    fun showPost() {
    }
}

object FileLogger {
    fun log(message: String) {
        // write to file
    }
}

object EventLogger {

    var repository = Repository

    fun log(message: String) {
        repository.log("Error : ${message}")
    }
}

object Repository {
    fun addPost(message: String) {}
    fun addTag(message: String) {}
    fun addMentionPost(message: String) {}
    fun notifyUser(user: String) {}
    fun log(error: String) {}
}