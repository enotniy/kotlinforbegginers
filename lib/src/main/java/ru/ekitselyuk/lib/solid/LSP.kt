package ru.ekitselyuk.lib.solid

open class Post3: Poster {
    override fun createPost(repository: Repository, postMessage: String) {
        repository.addPost(postMessage)
    }
}

class TagPost : Post3() {
    override fun createPost(repository: Repository, postMessage: String) {
        repository.addTag(postMessage)
    }
}

class MentionPost : Post3() {

    override fun createPost(repository: Repository, postMessage: String) {
        val user = parseUser(postMessage)
        repository.notifyUser(user)
        repository.addPost(postMessage)
        super.createPost(repository, postMessage)
    }

    private fun parseUser(message: String) =
        message
            .split(" ")
            .first()
            .removePrefix("@")
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
                else -> Post1()
            }

            post.createPost(repository, message)
        }
    }
}