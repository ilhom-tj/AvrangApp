package tj.colibri.avrang.data.comments

import java.util.*

data class Comment(
    val id : Int,
    val commentAuthor : String,
    val commentAuthorImg : String,
    val commentDate : Date,
    val commentRating : Int,
    val commentGoodSide : String,
    val commentBadSide : String,
    val commentOpinion : String
)