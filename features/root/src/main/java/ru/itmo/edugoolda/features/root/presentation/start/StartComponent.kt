package ru.itmo.edugoolda.features.root.presentation.start

interface StartComponent {
    interface Communication {
        fun authRequired()
        fun teacherDetailsRequired()
        fun studentDetailsRequired()
    }
}