package idroid.android.mapskit.model

import com.huawei.hms.maps.model.Circle
import idroid.android.mapskit.utils.DistributeType

data class CommonCircle(val type: DistributeType, val circle: Any) {

    fun remove() {
        if (DistributeType.HUAWEI_SERVICES === type) (circle as Circle).remove() else (circle as com.google.android.gms.maps.model.Circle).remove()
    }
}