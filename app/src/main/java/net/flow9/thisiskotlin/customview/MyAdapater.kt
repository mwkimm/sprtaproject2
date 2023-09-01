package net.flow9.thisiskotlin.customview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.flow9.thisiskotlin.customview.databinding.ItemBinding
import net.flow9.thisiskotlin.customview.databinding.ItemRecyclerviewBinding
import java.text.DecimalFormat


class MyAdapter(val mItems: MutableList<MyItem>) : RecyclerView.Adapter<MyAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null
    var itemLongClick : ItemLongClick? = null // 길게 클릭 변수선언


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {//
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding) // holder return


    }

    override fun onBindViewHolder(holder: Holder, position: Int) {//

        holder.itemView.setOnClickListener {  //클릭이벤트 추가부분
            itemClick?.onClick(it, position)
        }

        holder.iconImageView.setImageResource(mItems[position].Image)
        holder.product.text = mItems[position].Product
        holder.seller.text = mItems[position].Seller

        val price = mItems[position].Price
        val decimalFormat = DecimalFormat("#,###원") // 원하는 형식의 문자열로 포맷팅
        holder.price.text = decimalFormat.format(price)

        //holder.price.text = mItems[position].Price
        holder.good.text = mItems[position].Good.toString()
        holder.chat.text = mItems[position].Chat


        holder.itemView.setOnLongClickListener() OnLongClickListener@{// 길게 클릭이벤트추가부분
            itemLongClick?.onLongClick(it, position)
            return@OnLongClickListener true
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) { //
        val iconImageView = binding.iconItem
        val product = binding.tvItemTitle
        val seller = binding.tvAddress
        val price = binding.tvPrice
        val good = binding.tvLikeCnt
        val chat = binding.tvChatCnt
        val ivAdapter = binding.ivLike
        val interestcnt = binding.tvLikeCnt

    }

}