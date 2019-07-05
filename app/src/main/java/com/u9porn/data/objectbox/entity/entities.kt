package com.u9porn.data.objectbox.entity

import android.os.Parcelable
import android.text.TextUtils
import com.u9porn.utils.SDCardUtils
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Index
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
@Parcelize
data class AutoCompleteEntity(
  @Id var id: Long,
  @Index var name: String,
  var useTime: Int,
  var type: Int,
  var addDate: Date,
  var updateDate: Date) : Parcelable

@Entity
@Parcelize
data class Category(
  @Id var id: Long,
  var categoryType: Int,
  var categoryName: String,
  var categoryValue: String,
  var categoryUrl: String,
  var sortId: Int,
  var isShow: Boolean
) : Parcelable {
  companion object {
    @JvmStatic
    val CATEGORY_DEFAULT_91PORN_VALUE = arrayOf("index", "watch", "hot", "rp", "long", "md", "tf", "mf", "rf", "top", "top1", "hd")
    @JvmStatic
    val CATEGORY_DEFAULT_91PORN_NAME = arrayOf("主页", "最近更新", "当前最热", "最近得分", "10分钟以上", "本月讨论", "本月收藏", "收藏最多", "最近加精", "本月最热", "上月最热", "高清(会员)")
    @JvmStatic
    val CATEGORY_DEFAULT_91PORN_FORUM_VALUE = arrayOf("index", "17", "19", "4", "21", "33", "34")
    @JvmStatic
    val CATEGORY_DEFAULT_91PORN_FORUM_NAME = arrayOf("主页", "自拍达人原创区", "自拍达人原创申请", "原创自拍区", "我爱我妻", "X趣分享", "两X健康")
    @JvmStatic
    val CATEGORY_DEFAULT_MEI_ZI_TU_VALUE = arrayOf("index", "hot", "best", "xinggan", "japan", "taiwan", "mm")
    @JvmStatic
    val CATEGORY_DEFAULT_MEI_ZI_TU_NAME = arrayOf("主页", "最热", "推荐", "性感妹子", "日本妹子", "台湾妹子", "清纯妹子")
    val CATEGORY_DEFAULT_PXG_AV_VALUE = arrayOf("index", "熱門", "長片", "每日", "最新", "日韓", "精選")
    val CATEGORY_DEFAULT_PXG_AV_NAME = arrayOf("主页", "热门", "长片", "每日", "最新", "日韩", "精选")
    val CATEGORY_DEFAULT_99_MM_VALUE = arrayOf("index", "meitui", "xinggan", "qingchun", "hot")
    val CATEGORY_DEFAULT_99_MM_NAME = arrayOf("主页", "靓丽腿模", "性感美女", "清纯美女", "美女推荐")
    val CATEGORY_DEFAULT_HUA_BAN_NAME = arrayOf("造型美妆", "美食", "旅行", "手工布艺", "健身舞蹈", "儿童", "宠物", "美图", "明星", "美女", "礼物", "极客", "动漫", "建筑设计", "人文艺术", "数据图", "游戏", "汽车摩托", "电影图书", "生活百科", "教育", "运动", "搞笑")

    val CATEGORY_DEFAULT_AXGLE_VALUE = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24")
    val CATEGORY_DEFAULT_AXGLE_NAME = arrayOf("AV女優", "日本AV", "無修正", "少女", "素人", "肛X", "巨乳", "Cosplay", "女子校生", "人妻", "熟女", "SM", "中國", "香港", "日本", "韓国", "台湾", "亞洲", "金髪洋物", "3D", "VR", "偶像", "映画・電影", "Anime")

    const val TYPE_91PORN = 1
    const val TYPE_91PORN_FORUM = 2
    const val TYPE_MEI_ZI_TU = 3
    const val TYPE_PXG_AV = 4
    const val TYPE_99_MM = 5
    const val TYPE_HUA_BAN = 6
    const val TYPE_AXGLE = 7
  }
}

@Entity
@Parcelize
data class VideoResult(
  @Id(assignable = true)
  var id: Long = 0,
  var videoUrl: String = "",
  var videoId: String = "",
  var ownerId: String = "",
  var authorId: String = "",
  var thumbImgUrl: String = "",
  var videoName: String = "",
  var ownerName: String = "",
  var addDate: String = "",
  var userOtherInfo: String = ""
) : Parcelable {
  companion object {
    const val OUT_OF_WATCH_TIMES = -1L
  }
}

@Entity
@Parcelize
data class V9PornItem(
  @Id
  var id: Long = 0,
  @Index
  var viewKey: String = "",
  var title: String = "",
  var imgUrl: String = "",
  var duration: String = "",
  var info: String = "",
  var videoResultId: Long = 0,
//  @ToOne(joinProperty = "videoResultId")
  var videoResult: io.objectbox.relation.ToOne<VideoResult>? = null,
  var downloadId: Int = 0,
  var progress: Int = 0,
  var speed: Long = 0,
  var soFarBytes: Int = 0,
  var totalFarBytes: Int = 0,
  var status: Int = 0,
  var addDownloadDate: Date = Date(),
  var finishedDownloadDate: Date = Date(),
  var viewHistoryDate: Date = Date()
) : Parcelable {
  fun getTitleWithDuration(): String = "title ($duration)"
  fun getDownLoadPath(customDownloadVideoDirPath: String): String {
    return if (!TextUtils.isEmpty(customDownloadVideoDirPath)) {
      customDownloadVideoDirPath + title + viewKey + ".mp4"
    } else SDCardUtils.DOWNLOAD_VIDEO_PATH + title + viewKey + ".mp4"

  }
}