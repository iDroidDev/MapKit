package idroid.android.mapskit.factory

import android.content.Context
import android.os.Bundle


public abstract class BaseMaps(private val context: Context) : Maps,UISettings {

    override fun moveCamera(latitude: Float?, longitude: Float?, zoomRatio: Float?) {
        if (latitude == null || longitude == null || zoomRatio == null) throw NullPointerException("Params can not be Null object reference at moveCamera()")
    }

    override fun animateCamera(latitude: Float?, longitude: Float?, zoomRatio: Float?) {
        if (latitude == null || longitude == null || zoomRatio == null) throw NullPointerException("Params can not be Null object reference at animateCamera()")
    }

    override fun addMarker(title: String, snippet: String, latitude: Float?, longitude: Float?) {
        if (latitude == null || longitude == null) throw NullPointerException("Latitude, Longitude can not be Null object reference at addMarker()")
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        if (bundle == null) throw NullPointerException("Bundle can not be Null object reference at onSaveInstanceState()")
    }
}