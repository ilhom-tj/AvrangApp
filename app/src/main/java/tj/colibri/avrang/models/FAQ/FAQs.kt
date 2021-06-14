package tj.colibri.avrang.models.FAQ

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "faq_table")
data class FAQs (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id") val id : Int,

    @ColumnInfo(name = "question")
    @SerializedName("question") val question : String,

    @ColumnInfo(name = "answer")
    @SerializedName("answer") val answer : String
        )