package idroid.android.mapskit.utils

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

class CheckServiceAvaiable {
    companion object {
        var distributeType: DistributeType? = null

        fun getAvailableService(context: Context): DistributeType {
            if (distributeType == null) {
                distributeType = when {
                    ConnectionResult.SUCCESS == GoogleApiAvailability.getInstance()
                        .isGooglePlayServicesAvailable(
                            context
                        ) -> DistributeType.GOOGLE_SERVICES
                    com.huawei.hms.api.ConnectionResult.SUCCESS == HuaweiApiAvailability.getInstance()
                        .isHuaweiMobileServicesAvailable(
                            context
                        ) -> DistributeType.HUAWEI_SERVICES
                    else -> DistributeType.GOOGLE_SERVICES
                }
            }
            return distributeType ?: DistributeType.GOOGLE_SERVICES
        }
    }

}