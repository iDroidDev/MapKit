package idroid.android.mapskit.model

import com.google.android.gms.maps.model.LatLng
import com.huawei.hms.maps.model.Polyline
import idroid.android.mapskit.utils.DistributeType

data class CommonPolyline(
    var polyline: Any? = null,
    val type: DistributeType,
    val width: Float?,
    val color: Int?,
    val ZIndex: Float?
) {

    constructor(
        type: DistributeType,
        polyline: Any,
        width: Float,
        color: Int,
        ZIndex: Float,
        points: List<com.huawei.hms.maps.model.LatLng>
    ) : this(polyline, type, width, color, ZIndex)

    constructor(
        type: DistributeType,
        polyline: Any,
        points: List<LatLng>,
        width: Float,
        color: Int,
        ZIndex: Float
    ) : this(polyline, type, width, color, ZIndex)

    fun remove() {
        if (DistributeType.HUAWEI_SERVICES === type) (polyline as Polyline).remove() else (polyline as com.google.android.gms.maps.model.Polyline).remove()
    }
}