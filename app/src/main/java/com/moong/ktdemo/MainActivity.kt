package com.moong.ktdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.moong.ktdemo.model.User
import com.moong.ktdemo.utils.AppPermissionManager
import com.moong.ktdemo.utils.requestPermission
import com.moong.ktdemo.utils.showToast
import com.squareup.picasso.Picasso


/* Top-level (顶级声明) */
// 静态：在类以外定义的变量和方法，不属于任何一个类，而是直接属于package，可直接使用
/* const 编译期常量 */
// 编译器在编译的时候就知道 这个变量在实际运行时的每个调用出的实际值
private const val TAG: String = "KTDemo"


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var context: Context? = null

    private fun printDebug(str: String) {
        // FILE 当作静态变量直接用，放在伴随对象中
        Log.d(TAG, "$FILE $str")
    }

    // companion object 伴随对象内的变量可以当作静态变量
    // 此处的object 不是一个类，而是修饰词，意思是创建一个类，并且创建一个这个类的对象
    // 其他类可以使用 MainActivity.TAG 来调用这个静态属性
    companion object {

        /* 获取当前类名 */
        // val 等价于 final
        // private val FILE: String = MainActivity::class.java.simpleName
        private const val FILE: String = "MainActivity"

        var staticStr = "hello world"
    }

    // kt要求定义变量时一定不可为空
    // 在确定后面一定会初始化的场景，可加关键字 lateinit 表示稍后初始化，跳过检查
    lateinit var etUserName: EditText
    lateinit var btnLogin: Button
    lateinit var ivTempImg: ImageView

    // 确实不知道该值是否为空的情况，可以加一个问号表示可空
    var unKnownStr: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this

        initUI()
        unKnownStr = "hello world!"
        unKnownStr = getStr()

        // 使用非空变量的时候也需要加 ？调用内部方法，相当于调用时先进行判断
        Log.d(MainActivity.staticStr, "unKnownVar: " + (unKnownStr?.length ?: 0))
        // !!是告诉编译器，变量在使用时绝对不会为null，如果为null则抛出异常
        Log.d(TAG, "unKnownVar: " + (unKnownStr!!.length))

        initData()

        /*使用单例模式*/
        var singleStr = SingleTest.str
        SingleTest.printStr()

        // 动态申请权限
        requestPermission(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        AppPermissionManager(this).onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )
    }


    private fun initUI() {
        etUserName = findViewById(R.id.et_userName)
        btnLogin = findViewById(R.id.btn_login)
        ivTempImg = findViewById(R.id.iv_temp)

        btnLogin.setOnClickListener(this)
    }

    /* 带返回值的方法 */
    private fun getStr(): String {
        return "hahaha"
    }

    /* 不带返回值的方法 */
    private fun initData() {
        Log.d(TAG, "initData: 加载图片")
        Picasso.get().load("https://wx4.sinaimg.cn/large/9f31b0f0ly1h2xkcoeaytj20jp0jpdgv.jpg")
            .into(ivTempImg)

        var user1: User = User("0001", "张三")
        var user2 = User("0002", "张三2")

        /* 创建链表 */
        // 方法一：
        // 注意 这里的 List 类是不可变的，无法使用add，ArrayList 和 MutableList 等可变
        var userList: List<User> = ArrayList()
        // 方法二：
        var userList2: ArrayList<User> = arrayListOf(user1, user2)

        /* 多态 调用子类的方法 */
        // 方法一：
        if (userList is ArrayList) {
            // 类型判断后，不需要强转
            userList.add(user1)
        }
        // 方法二：
        (userList as ArrayList).add(user1)
        // 如果强转出错会抛出异常，所以可以使用 ？ 来调用
        (userList as? ArrayList)?.add(user1)

        /* 创建数组 */
        var strArr: Array<String> = arrayOf("a", "b", "c")
        printDebug(strArr[0] + " or " + strArr.get(1))
        // 数组失去了 协变 特性，不能把子类的数组对象，赋值给父类的数组
        // java 中 String[] 的对象可以赋值给 Object[] 的对象
        // var anyArr: Array<Any> = strArr

        /* 创建Map */
        var strMap: Map<String, Int> = mapOf("a" to 0, "b" to 0)
        var strMap2: HashMap<String, Int> = HashMap()
    }

    // 单例模式（懒汉式）
    object SingleTest {
        val str = "hello single test"

        // 里面的所有方法都默认是静态方法
        fun printStr() = println(str)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // 不可以写成 keyCode+“”
        printDebug("" + keyCode)
        if (keyCode == KeyEvent.KEYCODE_3) {
            context?.let { showToast(it, "press Number 3") }
            // toast(this, "press Button 3")
        }

        return super.onKeyDown(keyCode, event)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_login -> {
                val intent2img = Intent(this, ImgShowActivity::class.java)
                startActivity(intent2img)
            }
        }
    }

}
