package ru.ekitselyuk.lib.solid

interface PostCreator : Poster {

    override fun createPost(repository: Repository, message: String)
}

interface Shower {
    fun showPost(message: String)
}

class PostCreatorAndPoster: Poster, Shower {
    override fun createPost(repository: Repository, postMessage: String) {
        TODO("Not yet implemented")
    }

    override fun showPost(message: String) {
        TODO("Not yet implemented")
    }

}