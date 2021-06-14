package tj.colibri.avrang.ui.product.comments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.product_info_fragment_v2.*
import kotlinx.android.synthetic.main.rating_with_comment_fragment.*
import kotlinx.android.synthetic.main.rating_with_comment_fragment.product_rating_five_count
import kotlinx.android.synthetic.main.rating_with_comment_fragment.product_rating_four_count
import kotlinx.android.synthetic.main.rating_with_comment_fragment.product_rating_one_count
import kotlinx.android.synthetic.main.rating_with_comment_fragment.product_rating_three_count
import kotlinx.android.synthetic.main.rating_with_comment_fragment.product_rating_two_count
import tj.colibri.avrang.R
import tj.colibri.avrang.adapters.CommentAdapter
import tj.colibri.avrang.databinding.RatingWithCommentFragmentBinding

class RatingWithCommentFragment : Fragment() {

    private lateinit var commentsAdapter: CommentAdapter
    private val args: RatingWithCommentFragmentArgs by navArgs()
    private lateinit var binding: RatingWithCommentFragmentBinding

    var rOneQ = 0
    var rTwoQ = 0
    var rThreeQ = 0
    var rFourQ = 0
    var rFiveQ = 0

    companion object {
        fun newInstance() = RatingWithCommentFragment()
    }

    private lateinit var viewModel: RatingWithCommentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.rating_with_comment_fragment,
            container,
            false
        )
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(RatingWithCommentViewModel::class.java)

        binding.commentRecyclerview.layoutManager = GridLayoutManager(requireContext(), 1)

        commentsAdapter = CommentAdapter(this)
        commentsAdapter.setData(args.reviews)

        binding.commentRecyclerview.adapter = commentsAdapter

        binding.cardNewComments.setOnClickListener {
            //    CommentsFilterChangeColor(card_new_comments)
            //    commentsAdapter.sortByRatingFirstNew()
            commentsAdapter.sortByDateNew()
            binding.cardNewComments.backgroundTintList = ColorStateList.valueOf(
                requireContext().getColor(
                    R.color.textwarning
                )
            )
            binding.cardBestComments.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            binding.cardWorstComments.backgroundTintList = ColorStateList.valueOf(Color.WHITE)


        }

        binding.cardBestComments.setOnClickListener {
            //       CommentsFilterChangeColor(card_best_comments)
            //     commentsAdapter.sortByRatingFirstBest()
            commentsAdapter.sortByBest()
            binding.cardBestComments.backgroundTintList = ColorStateList.valueOf(
                requireContext().getColor(
                    R.color.textwarning
                )
            )
            binding.cardNewComments.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            binding.cardWorstComments.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        }

        binding.cardWorstComments.setOnClickListener {
            //      CommentsFilterChangeColor(card_worst_comments)
            //       commentsAdapter.sortByRatingFirstWorst()
            commentsAdapter.sortByWorst()
            binding.cardWorstComments.backgroundTintList = ColorStateList.valueOf(
                requireContext().getColor(
                    R.color.textwarning
                )
            )
            binding.cardBestComments.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
            binding.cardNewComments.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
        }

        SetupRating()

    }

    @SuppressLint("SetTextI18n")
    fun SetupRating() {
        lable_review_count.text = "Отзывы ${args.ratings.quantity}"

        args.reviews.forEach { reviews ->
            if (reviews.rating == 1) {
                rOneQ++
            }
            if (reviews.rating == 2) {
                rTwoQ++
            }
            if (reviews.rating == 3) {
                rThreeQ++
            }
            if (reviews.rating == 4) {
                rFourQ++
            }
            if (reviews.rating == 5) {
                rFiveQ++
            }
        }

        //product_rating_five_progress =
        binding.productRatingFiveCount.text = rFiveQ.toString()

        binding.productRatingFourCount.text = rFourQ.toString()

        binding.productRatingThreeCount.text = rThreeQ.toString()

        binding.productRatingTwoCount.text = rTwoQ.toString()

        binding.productRatingOneCount.text = rOneQ.toString()

        binding.ratingBar.rating = args.ratings.rating.toFloat()

        binding.productRating2nd.text = args.ratings.rating.toString()

        binding.productRatingQty.text = args.ratings.quantity.toString() + " отзыва"

        SetUpProgress()
    }

    @SuppressLint("SetTextI18n")
    fun SetUpProgress() {

        binding.ratingBar.rating = args.ratings.rating.toFloat()
        binding.productRating2nd.text = args.ratings.rating.toString()
        binding.productRatingQty.text = args.ratings.quantity.toString() + " Отзывов"

        //Set up value and max value
        val max = args.ratings.rating

        binding.rp5.max = max
        binding.rp5.progress = rFiveQ

        binding.rp4.max = max
        binding.rp4.progress = rFourQ

        binding.rp3.max = max
        binding.rp3.progress = rThreeQ

        binding.rp2.max = max
        binding.rp2.progress = rTwoQ

        binding.rp1.max = max
        binding.rp1.progress = rOneQ

    }


}