package com.yufan.library.util;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by root on 16-7-6.
 * <p>
 * 处理七牛图片地址
 */
public class FormatImageUrlUtils {
    /**
     * @param imgUrl 原图片url
     * @param width  指定压缩后图片宽度
     * @param height 指定压缩后图片高度
     * @return 处理后图片url
     */
    public static String formatImageUrl(String imgUrl, int width, int height) {
        if (TextUtils.isEmpty(imgUrl)) {
            return "";
        }
        //http://img.youximao.tv/FnvGfGO03p3leOOl2WJHH1C3tIAI?imageView2/1/w/720/h/720/interlace/1
        //判断图片地址是否来源于七牛 是则做处理，返回处理完后的图片地址  否则不做处理，返回原图片地址
        //?imageView2/1/w/720/h/720/format/webp/interlace/1
        //?imageView2/1/w/720/h/720/interlace/1
        if (imgUrl.contains("?imageView2")) {//已经格式化
            if (!imgUrl.contains("format/webp")) {//未转webp
                imgUrl = imgUrl + "/format/webp";
            }
        } else {
            imgUrl = imgUrl + "?imageView2/1/format/webp/w/" + width + "/h/" + height + "/interlace/1";
        }
        return imgUrl;
    }

    /**
     * @param imgUrl 原图片url
     * @param width  指定压缩后图片宽度
     * @param height 指定压缩后图片高度
     * @return 处理后图片url
     */
    public static String formatImageUrlAndBlur(String imgUrl, int width, int height) {
        if (TextUtils.isEmpty(imgUrl)) {
            return "";
        }
        //http://img.youximao.tv/FnvGfGO03p3leOOl2WJHH1C3tIAI?imageView2/1/w/720/h/720/interlace/1
        //判断图片地址是否来源于七牛 是则做处理，返回处理完后的图片地址  否则不做处理，返回原图片地址
        //?imageView2/1/w/720/h/720/format/webp/interlace/1
        //?imageView2/1/w/720/h/720/interlace/1
        if (imgUrl.contains("?imageView2")) {//已经格式化
            if (!imgUrl.contains("format/webp")) {//未转webp
                imgUrl = imgUrl + "/format/webp";
            }
        } else {
            imgUrl = imgUrl + "?imageView2/1/format/webp/w/" + width + "/h/" + height + "/interlace/1";
        }

        imgUrl = imgUrl + "|" + "imageMogr2/blur/50x50";
        return imgUrl;
    }

    /**
     * @param imgUrl 原图片url
     * @return 处理后图片url
     */
    public static String formatImageUrlBlur(String imgUrl) {
        if (TextUtils.isEmpty(imgUrl)) {
            return "";
        }
        //http://img.youximao.tv/FnvGfGO03p3leOOl2WJHH1C3tIAI?imageView2/1/w/720/h/720/interlace/1
        //判断图片地址是否来源于七牛 是则做处理，返回处理完后的图片地址  否则不做处理，返回原图片地址
        //?imageView2/1/w/720/h/720/format/webp/interlace/1
        //?imageView2/1/w/720/h/720/interlace/1
        if (imgUrl.contains("?imageView2")) {//已经格式化
            if (!imgUrl.contains("format/webp")) {//未转webp
                imgUrl = imgUrl + "/format/webp";
            }
        } else {
            imgUrl = imgUrl + "?imageView2/1/format/webp";
        }

        imgUrl = imgUrl + "|" + "imageMogr2/blur/50x50";
        return imgUrl;
    }


    /**
     * 把图片仅转成webp格式
     *
     * @param imgUrl
     * @return
     */
    public static String formatWebp(String imgUrl){
        if (TextUtils.isEmpty(imgUrl)){
            return "";
        }
        if (!imgUrl.contains("?imageView2")){
            imgUrl = imgUrl + "?imageView2/1/format/webp/interlace/1";
        }
        return imgUrl;
    }

    /**
     * 喵圈处理已经处理尺寸的图片转webp
     *
     * @param imgUrl
     * @return
     */
    public static String formatImageUrl(String imgUrl) {
        if (TextUtils.isEmpty(imgUrl)){
            return "";
        }
        imgUrl = imgUrl + "/format/webp";
        return imgUrl;
    }

    /**
     * 对图片地址含有中文的url进行转码
     *
     * @param oldUrl 含有中文的地址
     * @return 转码后的地址
     */
    public static String encodeUrl(String oldUrl) {
        if (TextUtils.isEmpty(oldUrl)){
            return "";
        }
        String head = oldUrl.substring(0, oldUrl.lastIndexOf("/") + 1);
        String tail = oldUrl.substring(oldUrl.lastIndexOf("/") + 1);
        try {
            String url = head + URLEncoder.encode(tail, "UTF-8");
            return url;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return oldUrl;
        }
    }
}
