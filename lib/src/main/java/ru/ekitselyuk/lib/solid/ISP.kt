package ru.ekitselyuk.lib.solid

interface NewPostCreator {
    fun createPost(repository: Repository, message: String)
}

interface PostShower {
    fun showPost(message: String)
}

class PostRender: PostShower {
    override fun showPost(message: String) {
        TODO("Not yet implemented")
    }
}

class CreateAndShowNewPost: PostShower, NewPostCreator {
    override fun createPost(repository: Repository, message: String) {
        TODO("Not yet implemented")
    }

    override fun showPost(message: String) {
        TODO("Not yet implemented")
    }
}