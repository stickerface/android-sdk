package example.sdk.stickerface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import io.stickerface.sdk.result.StickerFaceForResult
import io.stickerface.sdk.result.StickerFaceParams
import io.stickerface.sdk.widget.StickerFaceView

class MainActivity : AppCompatActivity() {

    private val stickerFaceResult = registerForActivityResult(StickerFaceForResult()) { layers ->
        if (layers == null) {
            Toast.makeText(this, R.string.not_selected, Toast.LENGTH_SHORT).show()
        } else {
            stickerView.setLayers(layers)
        }
    }

    private val getStickerButton: Button by lazy { findViewById(R.id.get_sticker) }
    private val stickerView: StickerFaceView by lazy { findViewById(R.id.preview) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getStickerButton.setOnClickListener {
            stickerFaceResult.launch(StickerFaceParams.empty())
        }
    }

}