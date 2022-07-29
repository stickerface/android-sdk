package example.sdk.stickerface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import io.stickerface.fresco.setLayers
import io.stickerface.sdk.api.Layers
import io.stickerface.sdk.result.StickerFaceForResult
import io.stickerface.sdk.result.StickerFaceParams
import io.stickerface.sdk.widget.StickerFaceView

class MainActivity : AppCompatActivity() {

    private val stickerFaceResult = registerForActivityResult(StickerFaceForResult()) { layers ->
        if (layers == null) {
            Toast.makeText(this, R.string.not_selected, Toast.LENGTH_SHORT).show()
        } else {
            stickerView.setLayers(layers)
            frescoView.setLayers(layers.withoutBackground())
        }
    }

    private val exampleLayers: Layers by lazy { Layers(getString(R.string.stickerface_example_layers)) }
    private val getStickerButton: Button by lazy { findViewById(R.id.get_sticker) }
    private val stickerView: StickerFaceView by lazy { findViewById(R.id.preview) }
    private val frescoView: SimpleDraweeView by lazy { findViewById(R.id.fresco) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_main)

        getStickerButton.setOnClickListener {
            val params = StickerFaceParams.Builder()
                .setLayers(exampleLayers)
                .build()
            stickerFaceResult.launch(params)
        }

        frescoView.setLayers(exampleLayers.withoutBackground())
    }

}