package idroid.android.mapskit.model

import android.graphics.Bitmap
import com.google.android.gms.maps.model.LatLng
import com.huawei.hms.maps.model.BitmapDescriptorFactory
import com.huawei.hms.maps.model.Marker
import idroid.android.mapskit.utils.DistributeType
import idroid.android.mapskit.utils.toHuaweiLatLng

data class CommonMarker(
    var type: DistributeType,
    val marker: Any,
    val id: String?,
    val alpha: Float?,
    val rotation: Float?,
    val position: LatLng,
    val title: String?,
    val snippet: String?,
    var tag: Any?
) {

    fun setIcon(icon: Bitmap) {
        if (DistributeType.HUAWEI_SERVICES === type) (marker as Marker).setIcon(
            BitmapDescriptorFactory.fromBitmap(icon)
        ) else (marker as com.google.android.gms.maps.model.Marker).setIcon(
            com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(
                icon
            )
        )
    }

    fun remove() {
        if (DistributeType.HUAWEI_SERVICES === type) (marker as Marker).remove() else (marker as com.google.android.gms.maps.model.Marker).remove()
    }

    fun setPosition(latLng: LatLng) {
        if (DistributeType.HUAWEI_SERVICES === type) (marker as Marker).position =
            latLng.toHuaweiLatLng() else (marker as com.google.android.gms.maps.model.Marker).position =
            latLng
    }

    fun setVisibilty(isVisible: Boolean) {
        if (DistributeType.HUAWEI_SERVICES === type) (marker as Marker).isVisible =
            isVisible else (marker as com.google.android.gms.maps.model.Marker).isVisible =
            isVisible
    }

    fun setMarkerTag(tag: Any) {
        this.tag = tag
        if (DistributeType.HUAWEI_SERVICES === type) (marker as Marker).tag =
            tag else (marker as com.google.android.gms.maps.model.Marker).tag = tag
    }

    fun showInfoWindow() {
        if (DistributeType.HUAWEI_SERVICES === type) (marker as Marker).showInfoWindow() else (marker as com.google.android.gms.maps.model.Marker).showInfoWindow()
    }
}