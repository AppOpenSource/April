package com.abt.price.core.bean.zhihu;

import java.io.Serializable;

/**
 * @描述： @SplashImage
 * @作者： @黄卫旗
 * @创建时间： @10/06/2018
 */
public class SplashImage implements Serializable {

    private String text;//图片出处
    private String img;//图片地址

    public String getText() {
        return text;
    }

    public String getImg() {
        return img;
    }

    @Override
    public String toString() {
        return "SplashImage{" +
                "text='" + text + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
