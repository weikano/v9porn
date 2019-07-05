package com.u9porn

import android.content.Context
import com.u9porn.data.objectbox.entity.MyObjectBox
import io.objectbox.BoxStore

object ObjectBox {
  lateinit var boxStore:BoxStore
    private set
  fun init(context: Context) {
    boxStore = MyObjectBox.builder().androidContext(context.applicationContext).build()
  }
}