package tj.colibri.avrang.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.colibri.avrang.R
import tj.colibri.avrang.data.ApiData.product.Reviews
import tj.colibri.avrang.utils.Const
import tj.colibri.avrang.utils.Features
import java.text.SimpleDateFormat


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class CommentAdapter(val context: Fragment) : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {

    private var comments = arrayOf<Reviews>()

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.rating_comment_layout, null)
        return CommentHolder(view)
    }

    fun setData(items: Array<Reviews>) {
        this.comments = items
        notifyDataSetChanged()
    }


    fun sortByDateNew() {
        comments.sortByDescending {
            it.created_at
        }
        notifyDataSetChanged()
    }

    fun sortByBest() {
        comments.sortByDescending {
            it.rating
        }
        notifyDataSetChanged()
    }

    fun sortByWorst() {
        comments.sortByDescending {
            it.rating
        }
        comments.reverse()
        notifyDataSetChanged()
    }
//    fun sortByRatingFirstNew() {
//
//
//        Collections.sort(comments, Comparator<Comment> { pendingTrajectPOJO, t1 ->
//
//            val mDate: Date = pendingTrajectPOJO.commentDate
//            val mDate1: Date = t1.commentDate
//
//            mDate.time.compareTo(mDate1.time)
//        })
//        notifyDataSetChanged()
//    }
//
//    fun sortByRatingFirstBest() {
//        Collections.sort(
//            comments,
//            Comparator<Comment> { lhs, rhs -> // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
//                if (lhs.commentRating > rhs.commentRating) -1 else if (lhs.commentRating < rhs.commentRating) 1 else 0
//            })
//        notifyDataSetChanged()
//    }
//
//    fun sortByRatingFirstWorst() {
//        Collections.sort(
//            comments,
//            Comparator<Comment> { lhs, rhs -> // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
//                if (lhs.commentRating < rhs.commentRating) -1 else if (lhs.commentRating > rhs.commentRating) 1 else 0
//            })
//        notifyDataSetChanged()
//    }

    override fun getItemCount() = comments.size

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        val comment = comments[position]
        holder.authorName.text = comment.userName
        Glide.with(context).load(Const.image_url + comment.userImage).circleCrop()
            .into(holder.authorAvatar)

        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val normalDate = date.parse(comment.created_at)

        holder.commentDate.text = Features().getNormalDate(normalDate)
        holder.commentRatingCount.text = comment.rating.toString()
        holder.commentRatingBar.rating = comment.rating.toFloat()
        holder.commentOpinion.text = comment.body[0].comment
        holder.commentAdvantages.text = comment.body[0].advantages
        holder.commentDisadvantages.text = comment.body[0].disadvantages
    }


    inner class CommentHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var authorName: TextView = view.findViewById(R.id.comment_author_name)
        var authorAvatar: ImageView = view.findViewById(R.id.comment_avatar)
        var commentDate: TextView = view.findViewById(R.id.comment_date)
        var commentRatingBar: RatingBar = view.findViewById(R.id.comment_rating)
        var commentRatingCount: TextView = view.findViewById(R.id.comment_rating_count)!!
        var commentOpinion: TextView = view.findViewById(R.id.comment_review)
        var commentAdvantages: TextView = view.findViewById(R.id.comment_advantages)
        var commentDisadvantages: TextView = view.findViewById(R.id.comment_disadvantages)
    }


}