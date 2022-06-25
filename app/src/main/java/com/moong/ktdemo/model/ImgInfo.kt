package com.moong.ktdemo.model

/**
 * @author DiaoMengq dmq1212@qq.com
 * @date created on 2022/6/25
 */
class ImgInfo {
    var imgURL = ""
    var imgTitle: String = ""

    constructor(imgURL: String, imgTitle: String) {
        this.imgURL = imgURL
        this.imgTitle = imgTitle
    }

}