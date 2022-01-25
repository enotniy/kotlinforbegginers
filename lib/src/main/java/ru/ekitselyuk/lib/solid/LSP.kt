package ru.ekitselyuk.lib.solid

open class Post3 {
    open fun createPost(repository: Repository, message: String) {
        repository.addPost(message)
    }
}

class TagPost : Post3() {
    override fun createPost(repository: Repository, message: String) {
        repository.addTag(message)
    }
}

class MentionPost : Post3() {

    override fun createPost(repository: Repository, message: String) {
        repository.notifyUser(parseUser(message))
        repository.addMentionPost(message)
    }

    private fun parseUser(message: String) =
        message
            .split(" ")
            .first()
            .removePrefix("@")
}

object MessageQueue {
    fun getUnhandledPostsMessages(): List<String> = listOf()
}

class PostHandler() {
    private val repository = Repository
    private val queue = MessageQueue

    fun handleNewPosts() {
        val newPosts = queue.getUnhandledPostsMessages()

        newPosts.forEach { message ->
            val post = when {
                message.startsWith("#") -> TagPost()
                message.startsWith("@") -> MentionPost()
                else -> Post3()
            }

            post.createPost(repository, message)
        }
    }
}