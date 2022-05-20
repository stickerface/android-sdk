package example.sdk.stickerface

import android.app.Application
import io.stickerface.sdk.StickerFace

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        StickerFace.init(this)
    }
}