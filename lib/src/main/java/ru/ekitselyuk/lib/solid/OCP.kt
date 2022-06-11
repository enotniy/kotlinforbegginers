package ru.ekitselyuk.lib.solid

open class Post2: Poster {
    override fun createPost(repository: Repository, postMessage: String) {
        repository.addPost(postMessage)
    }
}

class TagPost1: Poster {
    override fun createPost(repository: Repository, postMessage: String) {
        repository.addTag(postMessage)
    }
}

class MentionPost1: Poster {
    override fun createPost(repository: Repository, postMessage: String) {
        repository.addMentionPost(postMessage)
    }
}