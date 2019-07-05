package com.u9porn

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import android.support.v7.app.AppCompatDelegate
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper
import com.helper.loadviewhelper.load.LoadViewHelper
import com.liulishuo.filedownloader.FileDownloader
import com.tencent.bugly.crashreport.CrashReport
import com.u9porn.data.DataManager
import com.u9porn.di.component.DaggerAppComponent
//import com.u9porn.di.component.DaggerAppComponent
import com.u9porn.eventbus.LowMemoryEvent
import com.u9porn.utils.AppLogger
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

class V9PornApp : DaggerApplication() {
  companion object {
    lateinit var myApplication: Application

    val TAG = MyApplication::class.java.simpleName
  }

  @Inject
  lateinit var dataManager: DataManager

  override fun onCreate() {
    super.onCreate()
    myApplication = this
    initLightMode()
    AppLogger.initLogger()
    FileDownloader.setup(this)
    initLoadingHelper()
    CrashReport.initCrashReport(this, "e426041d83", BuildConfig.DEBUG)
    BGASwipeBackHelper.init(this, null)
    ObjectBox.init(this)
  }

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }

  private fun initLightMode() {
    val isNightMode = dataManager.isOpenNightMode
    AppCompatDelegate.setDefaultNightMode(if (isNightMode) {
      AppCompatDelegate.MODE_NIGHT_YES
    } else {
      AppCompatDelegate.MODE_NIGHT_NO
    })
  }

  private fun initLoadingHelper() {
    LoadViewHelper.getBuilder()
      .setLoadEmpty(R.layout.empty_view)
      .setLoadError(R.layout.error_view)
      .setLoadIng(R.layout.loading_view)
  }

  override fun onLowMemory() {
    super.onLowMemory()
    if (!dataManager.isForbiddenAutoReleaseMemory) {
      EventBus.getDefault().post(LowMemoryEvent(TAG))
    }
  }

//  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//
//  }
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> = DaggerAppComponent.builder().application(this).build()
}