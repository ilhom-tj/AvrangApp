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
import tj.colibri.avrang.data.comments.Comment
import tj.colibri.avrang.utils.Const
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Comparator
import kotlin.collections.ArrayList


class CommentAdapter(val context: Fragment) : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {

    private var comments = arrayOf<Reviews>()

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

    fun removeItem(position: Int) {
        notifyDataSetChanged()
    }


    override fun getItemCount() = comments.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        val comment = comments[position]
        holder.authorName.text = comment.user_id.name
        Glide.with(context).load(Const.image_url + comment.user_id.image).circleCrop().into(holder.authorAvatar)
        val date = SimpleDateFormat("dd-MM-yyyy").parse(comment.created_at)
        holder.commentDate.text = comment.created_at
        holder.commentRatingCount.text = comment.rating.toString()
        holder.commentRatingBar.rating = comment.rating.toFloat()
        holder.commentOpinion.text = comment.body
    }

    inner class CommentHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var authorName = view.findViewById<TextView>(R.id.comment_author_name)
        var authorAvatar = view.findViewById<ImageView>(R.id.comment_avatar)
        var commentDate = view.findViewById<TextView>(R.id.comment_date)
        var commentRatingBar = view.findViewById<RatingBar>(R.id.comment_rating)
        var commentRatingCount = view.findViewById<TextView>(R.id.comment_rating_count)
        var commentOpinion = view.findViewById<TextView>(R.id.comment_opinion)
    }


}