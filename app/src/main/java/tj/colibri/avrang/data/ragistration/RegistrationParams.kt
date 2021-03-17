package tj.colibri.avrang.data.ragistration

data class RegistrationParams(
    val name : String,
    val password : String,
    val password_confirmation : String,
    val phone : String
)