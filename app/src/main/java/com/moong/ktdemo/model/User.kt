package com.moong.ktdemo.model

/**
 * @author Demi dmq1212@qq.com
 * @date created on 2022/6/10
 */
class User {
    private var userId: String = ""

    private var userName: String = ""
        get() = field
        set(value) {
            field = value
        }

    private var gender: Int? = null

    private var age = 0

    constructor(userId: String, userName: String) {
        this.userId = userId
        this.userName = userName
    }

    constructor(userId: String, userName: String, age: Int) {
        this.userId = userId
        this.userName = userName
        this.age = age
    }
}