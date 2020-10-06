package idroid.android.mapskit.factory

import android.content.Context
import idroid.android.mapskit.utils.DistributeType

class MapFactory {
    companion object {
        fun createAndGetMap(context: Context, type: DistributeType): Maps {
            return if (DistributeType.HUAWEI_SERVICES == type) {
                HuaweiMapsImpl(context)
            } else {
                GoogleMapsImpl(context)
            }
        }
    }
}