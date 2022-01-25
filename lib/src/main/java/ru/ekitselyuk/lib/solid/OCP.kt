package ru.ekitselyuk.lib.solid

open class Post2 {
    open fun createPost(repository: Repository, message: String) {
        repository.addPost(message)
    }
}


class TagPost2: Post2() {
    override fun createPost(repository: Repository, message: String) {
        repository.addTag(message)
    }
}

class MentionPost2: Post2() {
    override fun createPost(repository: Repository, message: String) {
        repository.addMentionPost(message)
    }
}