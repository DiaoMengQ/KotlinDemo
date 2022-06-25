package com.moong.ktdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.moong.ktdemo.model.ImgInfo

/**
 * @author DiaoMengq dmq1212@qq.com
 * @date created on 2022/6/25
 */
class ImgShowActivity : AppCompatActivity() {
    val TAG: String = ImgShowActivity::class.java.simpleName
    lateinit var recyclerImgView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img_show)

        initView()
        initData()
    }

    private fun initData() {
        Log.d(TAG, "initData: ")
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        val imgList: ArrayList<ImgInfo> = ArrayList()

        for (i in 1..5) {
            // TODO("如何按顺序加载drawable里的图片")
            // var imgInfoItem = ImgInfo(i.toString(), i.toString())
            // https://wx1.sinaimg.cn/large/9f31b0f0ly1h2xkcoxxkrj20fk0fk0ta.jpg
            // https://wx4.sinaimg.cn/large/9f31b0f0ly1h2xkcoeaytj20jp0jpdgv.jpg
            // https://wx4.sinaimg.cn/large/9f31b0f0ly1h2xkco5j0mj20gb0gbq3p.jpg

            val imgInfoItem = ImgInfo(
                "https://wx1.sinaimg.cn/large/9f31b0f0ly1h2xkcoxxkrj20fk0fk0ta.jpg",
                i.toString()
            )
            imgList.add(imgInfoItem)
        }

        val myImgAdapter = ImgRecyclerAdapter(this, imgList)

        recyclerImgView.layoutManager = staggeredGridLayoutManager
        recyclerImgView.adapter = myImgAdapter

        // TODO: 点击无响应
        myImgAdapter.mySetOnclickListener(object : ImgRecyclerAdapter.MyInter {     //自定义接口回调
            override fun testStr(str: String) {
                // test_test.text = str
                Log.d(TAG, "testStr: $str")
            }
        })

    }

    private fun initView() {
        Log.d(TAG, "initView: ")
        recyclerImgView = findViewById(R.id.recycler_img)
    }

}