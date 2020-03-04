package idroid.android.mapskit.utils

import android.content.Context
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

class CheckServiceAvaiable {
    companion object {
        fun getAvailableService(context: Context): DistributeType {
            if (ConnectionResult.SUCCESS == GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                    context
                )
            ) return DistributeType.GOOGLE_SERVICES
            else if (com.huawei.hms.api.ConnectionResult.SUCCESS == HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(
                    context
                )
            ) return DistributeType.HUAWEI_SERVICES;
            else return DistributeType.GOOGLE_SERVICES;
        }
    }

}