package ru.itmo.edugoolda.data.invitations.api


import ru.itmo.edugoolda.data.user.api.UserInfo


data class Invitation (
    val id: String,
    val sender: UserInfo

)