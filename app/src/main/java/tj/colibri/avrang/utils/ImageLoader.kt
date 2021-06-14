package tj.colibri.avrang.utils

import android.content.Context
import coil.ImageLoader
import coil.decode.SvgDecoder

class ImageLoader(val context: Context) {
    fun getImageLoader() : ImageLoader{
        return ImageLoader.Builder(context)
            .componentRegistry {
                add(SvgDecoder(context))
            }
            .build()
    }
}