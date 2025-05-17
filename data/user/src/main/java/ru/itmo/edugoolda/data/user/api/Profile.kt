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
            name = "Pavel",
            email = "pavel@mail.ru",
            bio = "I'm pavel, student",
            role = UserRole.Student,
            id = UserId("123")
        )
    }
}