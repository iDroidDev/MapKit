package idroid.android.mapskit.model

import com.google.android.gms.maps.model.LatLng
import java.util.*

class CommonPolygonOptions(
    val points: Collection<LatLng>,
    val holes: List<List<LatLng>>? = null,
    var fillColor: Int = 0,
    var strokeColor: Int = 0,
    var strokeWidth: Float = 0.5f,
    var strokeJointType: CommonJointType? = null,
    var visible: Boolean = true,
    var clickable: Boolean = false,
    var geodesic: Boolean = true
) {
    /**
     * Returns [points] as the Huawei's LatLng.
     * @author Arnau Mora
     * @since 20210602
     */
    fun hmsPoints(): List<com.huawei.hms.maps.model.LatLng> {
        val huaweiLatLongs: MutableList<com.huawei.hms.maps.model.LatLng> = ArrayList()
        for (currentLatLng in points) {
            huaweiLatLongs.add(
                com.huawei.hms.maps.model.LatLng(
                    currentLatLng.latitude,
                    currentLatLng.longitude
                )
            )
        }
        return huaweiLatLongs
    }

    /**
     * Returns [holes] as the Huawei's LatLng.
     * @author Arnau Mora
     * @since 20210602
     */
    fun hmsHoles(): List<List<com.huawei.hms.maps.model.LatLng>> {
        val holesPoints: MutableList<MutableList<com.huawei.hms.maps.model.LatLng>> = ArrayList()
        if (holes != null)
            for (hole in holes) {
                val holePoints = arrayListOf<com.huawei.hms.maps.model.LatLng>()
                for (point in hole)
                    holePoints.add(
                        com.huawei.hms.maps.model.LatLng(
                            point.latitude,
                            point.longitude
                        )
                    )
                holesPoints.add(holePoints)
            }

        return holesPoints
    }
}
