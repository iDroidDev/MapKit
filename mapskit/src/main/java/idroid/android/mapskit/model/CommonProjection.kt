package idroid.android.mapskit.model

import android.graphics.Point
import com.google.android.gms.maps.model.LatLng

interface CommonProjection {
    fun fromScreenLocation(point: Point): LatLng
    fun toScreenLocation(latLng: LatLng): Point
    val visibleRegion: CommonVisibleRegion
}