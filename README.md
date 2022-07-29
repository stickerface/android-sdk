# StickerFace Android SDK

Official web site [stickerface.io](https://stickerface.io)

[Telegram channel @stickerface](https://t.me/stickerface) | [Русский канал @stickerface_ru](https://t.me/stickerface_ru)

[Telegram chat for developer: @stickerface_android_sdk](https://t.me/stickerface_android_sdk)

## Using StickerFace

### 1. Add dependency

```groovy
implementation 'io.stickerface:sdk:1.1.1'
```

### 2. Add Stickerface initializer to manifest

```xml
 <provider
    android:name="io.stickerface.sdk.StickerFaceInitializer"
    android:authorities="${applicationId}.stickerface.provider"
    android:exported="false" />
```

*NOTE: Add you application in `AndroidManifest.xml` and add `android.permission.INTERNET`, `android.permission.CAMERA` in permission*

### 3. Add Stickerface activity to manifest
```xml
 <activity
    android:name="io.stickerface.sdk.StickerFaceActivity"
    android:launchMode="singleTask"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.VIEW"/>
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
        <data android:host="stickerface.io" android:scheme="http" />
        <data android:host="stickerface.io" android:scheme="https" />
    </intent-filter>
 </activity>
```


### 4. Register activity for result

```kotlin
private val stickerFaceResult = registerForActivityResult(StickerFaceForResult()) { layers ->
    layers
}
```

launch Stickerface:

```kotlin
val params = StickerFaceParams.Builder()
    .setLayers("0;1;11;13;23;28;68;69;72;83;91;119;125;159;160;187;219;238;253;265;310;337;3000;3220") // optional
    .build()

// empty params without builder StickerFaceParams.empty()
stickerFaceResult.launch(params)
```

OR

```kotlin
val params = StickerFaceParams.Builder()
            .setLayers("0;1;11;13;23;28;68;69;72;83;91;119;125;159;160;187;219;238;253;265;310;337;3000;3220") // optional
            .build()

// empty params without builder StickerFaceParams.empty()
val intent = StickerFaceActivity.createIntent(context, params)
startActivityForResult(intent, STICKERFACE_RC)
```

parse result:
```kotlin
val layers = StickerFaceActivity.handleResult(intent)
```


### 5. Display sticker

#### Using StickerFaceView

```xml
<io.stickerface.sdk.widget.StickerFaceView
    android:layout_width="50dp"
    android:layout_height="50dp"
    app:sfv_size="100"
    app:sfv_color="#000000"
    app:sfv_circle="true"
    app:sfv_layers="0;1;11;13;23;28;68;69;72;83;91;119;125;159;160;187;219;238;253;265;310;337;3000;3220"
/>
```

Programmatically:

```kotlin
stickerFaceView.bgColor = Color.BLACK
stickerFaceView.size = 100
stickerFaceView.circle = true
stickerFaceView.setLayers(Layers("0;1;11;13;23;28;68;69;72;83;91;119;125;159;160;187;219;238;253;265;310;337;3000;3220")) {
    // callback after sticker loaded (optional)
}
```

#### Using ImageView

```kotlin
StickerFace.render(Layers("0;1;11;13;23;28;68;69;72;83;91;119;125;159;160;187;219;238;253;265;310;337;3000;3220")).into(imageView)
```

#### Get bitmap

```kotlin
StickerFace.render(Layers("0;1;11;13;23;28;68;69;72;83;91;119;125;159;160;187;219;238;253;265;310;337;3000;3220")).into { bitmap ->  
    // your bitmap
}
```

suspend function for get bitmap:

```kotlin
val bitmap = Layers("0;1;11;13;23;28;68;69;72;83;91;119;125;159;160;187;219;238;253;265;310;337;3000;3220").bitmap()
```

## Layers object
```kotlin
val layers = Layers("0;1;11;13;23;28;68;69;72;83;91;119;125;159;160;187;219;238;253;265;310;337;3000;3220")
```

#### Sticker without background:
```kotlin
layers = layers.withoutBackground()
```
*use `layers.hasBackground()` if you want to know if has a background*

#### Check gender:
```kotlin
if (layers.woman) {
    log("its woman sticker")
} 
```
*use `layers.toggleSex()` if you want change gender*

#### Emotion:
```kotlin
layers = layers.asSticker(StickerType.Hi)
```
*use `layers.stickerType` for get current emotion. `StickerType.None` means no emotion*

#### Get image url:
```kotlin
val uri = layers.getUri(
    size = 256 // optional
)
// uri = https://stickerface.io/api/png/0;1;11;13;23;28;68;69;72;83;91;119;125;159;160;187;219;238;253;265;310;337;3000;3220?size=256
```

## Using with [Fresco](https://frescolib.org)

### 1. Add dependency [Fresco](https://frescolib.org) extension

```groovy
implementation 'io.stickerface:fresco:0.0.1'
```

### 2. Display sticker in SimpleDraweeView
```kotlin
import io.stickerface.fresco.setLayers

... 

simpleDraweeView.setLayers(layers)
```

Use suspend function for create [ImageRequestBuilder](https://frescolib.org/docs/image-requests.html):

```kotlin
import io.stickerface.fresco.createImageBuilder

...

layers.createImageBuilder()
```


