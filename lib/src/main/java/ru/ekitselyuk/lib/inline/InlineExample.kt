package ru.ekitselyuk.lib.inline


fun main() {

    // no inline
    "!!!_мПользователь_!!!".firstOrNullByCondition { char ->
        char.isLetter() && char.isUpperCase()
    }

    // inline
    "!!!_мПользователь_!!!".firstOrNullByConditionInline { char ->
        char.isLetter() && char.isUpperCase()
    }

}
