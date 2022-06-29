# StickerFace Android SDK

## Using StickerFace

### 1. Add dependency

```groovy
implementation 'io.stickerface:sdk:1.0.3'
```

### 2. Add Stickerface initializer to manifest

```xml
 <provider
    android:name="io.stickerface.sdk.StickerFaceInitializer"
    android:authorities="${applicationId}.stickerface.provider"
    android:exported="false" />
```

*NOTE: Add you application in `AndroidManifest.xml` and add `android.permission.INTERNET`, `android.permission.CAMERA` in permission*


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
