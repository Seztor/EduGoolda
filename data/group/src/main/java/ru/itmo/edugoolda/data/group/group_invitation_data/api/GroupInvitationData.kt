package ru.itmo.edugoolda.data.group.group_invitation_data.api

data class GroupInvitationData(
    val code: GroupInvitationCode,
    val invitationLink: String
) {
    companion object {
        val MOCK = GroupInvitationData(
            code = GroupInvitationCode("123456"),
            invitationLink = "google.com"
        )
    }
}