package idroid.android.mapskit.factory

import android.content.Context
import idroid.android.mapskit.utils.DistributeType
import idroid.android.mapskit.utils.MapType

class MapFactory {
    companion object {
        fun createAndGetMap(context: Context, type: DistributeType, mapType: MapType): Maps {
            return if (DistributeType.HUAWEI_SERVICES == type) {
                HuaweiMapsImpl(context, mapType)
            } else {
                GoogleMapsImpl(context, mapType)
            }
        }
    }
}