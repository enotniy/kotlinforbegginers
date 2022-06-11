package ru.ekitselyuk.lib.inline

import ru.ekitselyuk.lib.patterns.Car
import java.util.*
import javax.swing.tree.TreeNode

fun <T> TreeNode.findParentOfType(clazz: Class<T>): T? {
    var p = parent
    while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }
    @Suppress("UNCHECKED_CAST")
    return p as T?
}

inline fun <reified T> TreeNode.findParentOfType(): T? {
    var p = parent
    while (p != null && p !is T) {
        p = p.parent
    }
    return p as T?
}


class MyTreeNode : TreeNode {
    override fun getChildAt(childIndex: Int): TreeNode {
        TODO("Not yet implemented")
    }

    override fun getChildCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getParent(): TreeNode {
        TODO("Not yet implemented")
    }

    override fun getIndex(node: TreeNode?): Int {
        TODO("Not yet implemented")
    }

    override fun getAllowsChildren(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isLeaf(): Boolean {
        TODO("Not yet implemented")
    }

    override fun children(): Enumeration<*> {
        TODO("Not yet implemented")
    }

}
