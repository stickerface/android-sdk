# StickerFace Android SDK

## Using StickerFace

### 1. Add dependency

```groovy
implementation 'io.stickerface:sdk:1.0.1'
```

### 2. Initialize Stickerface

```kotlin
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        StickerFace.init(this)
    }
}
```

*NOTE: Add you application class in `AndroidManifest.xml` and add `android.permission.INTERNET`, `android.permission.CAMERA` in permission*

```xml
<manifest>
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.CAMERA" />
    <application
      android:name=".MyApplication"
      >
    </application>
</manifest>
```

or you can initialize Stickerface in activity

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StickerFace.init(this)
        setContentView(R.layout.activity_main)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        StickerFace.deInitialize()
    }
}
```

### 3. Register activity for result

```kotlin
private val stickerFaceResult = registerForActivityResult(StickerFaceForResult()) { layers ->
    layers
}
```

launch Stickerface

```kotlin
stickerFaceResult.launch(StickerFaceParams.empty())
```

### 4. Display sticker

#### Using StickerFaceView

```xml
<io.stickerface.sdk.widget.StickerFaceView
    android:layout_width="50dp"
    android:layout_height="50dp"
    app:sfv_size="100"
    app:sfv_circle="true"
    app:sfv_layers="0;1;11;13;23;28;68;69;72;83;91;119;125;159;160;187;219;238;253;265;310;337;3000;3220"
/>
```

Programmatically

```kotlin
stickerFaceView.size = 100
stickerFaceView.circle = true
stickerFaceView.setLayers(Layers("0;1;11;13;23;28;68;69;72;83;91;119;125;159;160;187;219;238;253;265;310;337;3000;3220"))
```
