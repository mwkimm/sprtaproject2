package net.flow9.thisiskotlin.customview

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.res.ResourcesCompat
import com.google.android.engage.common.datamodel.Image
import com.google.android.material.snackbar.Snackbar
import net.flow9.thisiskotlin.customview.databinding.ActivityDetailBinding
import java.lang.System.exit
import java.text.DecimalFormat

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private var isLike = false

    private val item: MyItem? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(Constants.ITEM_OBJECT, MyItem::class.java) //tiramisu는 7.0 noti는 8.0 티라미수이상이면 이줄을 사용
        } else {
            intent.getParcelableExtra(Constants.ITEM_OBJECT) // 더이상 이건 안씀 안쓰는데 이걸 티라미수 이하인 버전이면 이줄을 사용
        }
    }

    private val itemPosition: Int by lazy {
        intent.getIntExtra(Constants.ITEM_INDEX, 0)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_detail)

        //Log.d("jblee", "itemPosition = $itemPosition")

        binding.ivItemImage.setImageDrawable(item?.let {
            ResourcesCompat.getDrawable(
                resources,
                it.Image,
                null,
            )
        })

        binding.tvSellerName.text = item?.Seller
        binding.tvProduct.text = item?.Product
        binding.tvAddress.text = item?.Address
        binding.tvProductExplain.text = item?.Explain
        //binding.tvDetailPrice.text = item?.Price
        binding.tvDetailPrice.text = DecimalFormat("#,###").format(item?.Price) + "원"

        isLike = item?.isLike == true

        binding.ivDetailLike.setImageResource(if (isLike) {R.drawable.heart1} else{R.drawable.heart})

        binding.ivBack.setOnClickListener {
            exit()
        }


        binding.llDetaillLike.setOnClickListener {
            if (!isLike) {
                binding.ivDetailLike.setImageResource(R.drawable.heart1)

                Snackbar.make(binding.constLayout, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_SHORT).show()
                isLike = true

            } else {
                binding.ivDetailLike.setImageResource(R.drawable.heart)
                isLike = false
            }
        }
    }

        fun exit() {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("itemIndex", itemPosition)
                putExtra("isLike", isLike)
            }
            setResult(RESULT_OK, intent)
            if (!isFinishing) finish()
        }

        override fun onBackPressed() {
            exit()
        }
    }
