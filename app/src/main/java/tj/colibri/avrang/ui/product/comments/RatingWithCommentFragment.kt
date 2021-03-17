package tj.colibri.avrang.ui.product.comments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_delivery_adres.*
import kotlinx.android.synthetic.main.product_info_fragment.*
import kotlinx.android.synthetic.main.product_info_fragment_v2.*
import kotlinx.android.synthetic.main.rating_with_comment_fragment.*
import kotlinx.android.synthetic.main.rating_with_comment_fragment.product_rating_five_count
import kotlinx.android.synthetic.main.rating_with_comment_fragment.product_rating_four_count
import kotlinx.android.synthetic.main.rating_with_comment_fragment.product_rating_one_count
import kotlinx.android.synthetic.main.rating_with_comment_fragment.product_rating_three_count
import kotlinx.android.synthetic.main.rating_with_comment_fragment.product_rating_two_count
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.CommentAdapter
import tj.colibri.avrang.data.mock.MockData
import kotlinx.android.synthetic.main.product_info_fragment.product_rating_2nd as product_rating_2nd1
import kotlinx.android.synthetic.main.product_info_fragment.product_rating_qty as product_rating_qty1
import kotlinx.android.synthetic.main.product_info_fragment.product_rating_three_progress as product_rating_three_progress1
import kotlinx.android.synthetic.main.product_info_fragment.rating_bar as rating_bar1
import kotlinx.android.synthetic.main.rating_with_comment_fragment.product_rating_2nd as product_rating_2nd1
import kotlinx.android.synthetic.main.rating_with_comment_fragment.product_rating_qty as product_rating_qty1
import kotlinx.android.synthetic.main.rating_with_comment_fragment.rating_bar as rating_bar1

class RatingWithCommentFragment : Fragment() {

    private lateinit var commentsAdapter: CommentAdapter
    private val args : RatingWithCommentFragmentArgs by navArgs()

    var rOneQ = 0;
    var rTwoQ = 0;
    var rThreeQ = 0;
    var rFourQ = 0;
    var rFiveQ = 0;

    companion object {
        fun newInstance() = RatingWithCommentFragment()
    }

    private lateinit var viewModel: RatingWithCommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.rating_with_comment_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(RatingWithCommentViewModel::class.java)

        comment_recyclerview.layoutManager = GridLayoutManager(requireContext(), 1)

        commentsAdapter = CommentAdapter(this)
        commentsAdapter.setData(args.reviews)

        comment_recyclerview.adapter = commentsAdapter

        card_new_comments.setOnClickListener {
        //    CommentsFilterChangeColor(card_new_comments)
        //    commentsAdapter.sortByRatingFirstNew()
            card_new_comments.backgroundTintList = ColorStateList.valueOf(
                requireContext().getColor(
                    R.color.textwarning
                )
            )
            card_best_comments.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            card_worst_comments.backgroundTintList = ColorStateList.valueOf(Color.WHITE)



        }
        card_best_comments.setOnClickListener {
     //       CommentsFilterChangeColor(card_best_comments)
      //     commentsAdapter.sortByRatingFirstBest()
            card_best_comments.backgroundTintList = ColorStateList.valueOf(
                requireContext().getColor(
                    R.color.textwarning
                )
            )
            card_new_comments.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            card_worst_comments.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        }
        card_worst_comments.setOnClickListener {
      //      CommentsFilterChangeColor(card_worst_comments)
     //       commentsAdapter.sortByRatingFirstWorst()
            card_worst_comments.backgroundTintList = ColorStateList.valueOf(
                requireContext().getColor(
                    R.color.textwarning
                )
            )
            card_best_comments.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            card_new_comments.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        }

       SetupRating()

    }

    fun SetupRating(){
         lable_review_count.setText("Отзывы ${args.ratings.quantity}")

        args.reviews.forEach { reviews ->
            if (reviews.rating == 1) rOneQ++;
            if (reviews.rating == 2) rTwoQ++;
            if (reviews.rating == 3) rThreeQ++;
            if (reviews.rating == 4) rFourQ++;
            if (reviews.rating == 5) rFiveQ++;
        }
        //product_rating_five_progress =
        product_rating_five_count.text = rFiveQ.toString()

        product_rating_four_count.text = rFourQ.toString()

        product_rating_three_count.text = rThreeQ.toString()

        product_rating_two_count.text = rTwoQ.toString()

        product_rating_one_count.text = rOneQ.toString()

        rating_bar.rating = args.ratings.rating.toFloat()

        product_rating_2nd.setText(args.ratings.rating.toString())

        product_rating_qty.setText(args.ratings.quantity.toString() + " отзыва")

        SetUpProgress()
    }
    fun SetUpProgress(){

        rating_bar.rating = args.ratings.rating.toFloat()
        product_rating_2nd.setText(args.ratings.rating.toString())

        product_rating_qty.setText(args.ratings.quantity.toString() + " Отзывов" )
        //Set up value and max value
        val max = args.ratings.rating

        rp5.max = max
        rp5.setProgress(rFiveQ)

        rp4.max = max
        rp4.setProgress(rFourQ)

        rp3.max = max
        rp3.setProgress(rThreeQ)

        rp2.max = max
        rp2.setProgress(rTwoQ)

        rp1.max = max
        rp1.setProgress(rOneQ)

    }



}