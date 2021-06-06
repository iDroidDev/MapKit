package idroid.android.mapskit.model

import com.huawei.hms.maps.model.Polygon
import idroid.android.mapskit.utils.DistributeType

data class CommonPolygon(
    var polygon: Any? = null,
    val type: DistributeType
) {

    constructor(
        type: DistributeType,
        polygon: Any
    ) : this(polygon, type)

    fun remove() {
        if (DistributeType.HUAWEI_SERVICES === type) (polygon as Polygon).remove() else (polygon as com.google.android.gms.maps.model.Polygon).remove()
    }
}
