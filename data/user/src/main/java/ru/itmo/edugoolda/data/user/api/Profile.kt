package ru.itmo.edugoolda.data.user.api

data class Profile(
    val name: String,
    val email: String,
    val bio: String,
    val role: UserRole,
    val id: UserId,
) {
    companion object {
        val MOCK = Profile(
            name = "Name Surname1",
            email = "email@mail.com",
            bio = "Sample text in bio",
            role = UserRole.Teacher,
            id = UserId("sampleId")
        )
    }
}