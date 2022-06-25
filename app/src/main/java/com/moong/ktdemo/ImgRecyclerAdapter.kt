package com.moong.ktdemo

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moong.ktdemo.model.ImgInfo
import com.squareup.picasso.Picasso

/**
 * @author DiaoMengq dmq1212@qq.com
 * @date created on 2022/6/25
 */
class ImgRecyclerAdapter(context: Context, list: ArrayList<ImgInfo>) :
    RecyclerView.Adapter<ImgRecyclerAdapter.MyViewHolder>() {
    val TAG = ImgRecyclerAdapter::class.java.simpleName

    private var mContext: Context = context
    private var imgList: ArrayList<ImgInfo> = list
    var inter: MyInter? = null

    init {
        notifyDataSetChanged()
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView
        var title: TextView

        init {
            image = view.findViewById(R.id.iv_img_main)
            title = view.findViewById(R.id.tv_img_title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.item_img_info, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Log.d(
        //     TAG,
        //     "onBindViewHolder: imgURL: " + imgList[position].imgURL
        //             + " imgTitle: " + imgList[position].imgTitle
        // )

        holder.title.text = imgList[position].imgTitle
        // holder.image.setImageResource(R.drawable.woooooo)
        Picasso.get().load(imgList[position].imgURL).into(holder.image)
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

    fun mySetOnclickListener(inter: MyInter) {
        this.inter = inter
    }

    interface MyInter {    //自定义的接口（测试接口方式的使用）
        fun testStr(str: String) {
            Log.d("MyInter", "testStr: ")
        }
    }
}