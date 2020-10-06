package idroid.android.mapskit.model

import android.graphics.Point
import com.google.android.gms.maps.model.LatLng
import com.huawei.hms.maps.Projection
import idroid.android.mapskit.utils.toGoogleLatLng
import idroid.android.mapskit.utils.toGoogleLatLngBounds

class HesProjectionImpl {

    companion object {

        fun getProjection(projection: Any?): CommonProjection? {
            if (projection != null) {
                if (projection is Projection) {
                    return HuaweiProjection(projection)
                } else if (projection is com.google.android.gms.maps.Projection) {
                    return GoogleProjection(projection)
                }
            }
            return null
        }
    }
}

internal class HuaweiProjection(private val huaweiProjection: Projection) : CommonProjection {

    override val visibleRegion: CommonVisibleRegion
        get() {
            val visibleRegion = huaweiProjection.visibleRegion
            val latLngBounds = visibleRegion.latLngBounds.toGoogleLatLngBounds()

            return CommonVisibleRegion(
                latLngBounds,
                visibleRegion.farLeft.toGoogleLatLng(),
                visibleRegion.farRight.toGoogleLatLng(),
                visibleRegion.nearLeft.toGoogleLatLng(),
                visibleRegion.nearRight.toGoogleLatLng()
            )
        }

    override fun fromScreenLocation(point: Point): LatLng {
        val latLng = huaweiProjection.fromScreenLocation(point)
        return LatLng(latLng.latitude, latLng.longitude)
    }

    override fun toScreenLocation(latLng: LatLng): Point {
        return huaweiProjection.toScreenLocation(
            com.huawei.hms.maps.model.LatLng(
                latLng.latitude,
                latLng.longitude
            )
        )
    }
}

internal class GoogleProjection(private val googleProjection: com.google.android.gms.maps.Projection) :
    CommonProjection {

    override val visibleRegion: CommonVisibleRegion
        get() {
            val visibleRegion = googleProjection.visibleRegion
            return CommonVisibleRegion(
                visibleRegion.latLngBounds,
                visibleRegion.farLeft,
                visibleRegion.farRight,
                visibleRegion.nearLeft,
                visibleRegion.nearRight
            )
        }

    override fun fromScreenLocation(point: Point): LatLng {
        return googleProjection.fromScreenLocation(point)
    }

    override fun toScreenLocation(latLng: LatLng): Point {
        return googleProjection.toScreenLocation(latLng)
    }
}