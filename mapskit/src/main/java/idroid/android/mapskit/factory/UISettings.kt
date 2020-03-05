package idroid.android.mapskit.factory

interface UISettings {
    fun isCompassEnabled(): Boolean

    fun setCompassEnabled(compassEnabled: Boolean?)

    fun isIndoorLevelPickerEnabled(): Boolean

    fun setIndoorLevelPickerEnabled(indoorLevelPickerEnabled: Boolean?)

    fun isMapToolbarEnabled(): Boolean

    fun setMapToolbarEnabled(mapToolbarEnabled: Boolean?)

    fun isMyLocationButtonEnabled(): Boolean

    fun setMyLocationButtonEnabled(myLocationButtonEnabled: Boolean?)

    fun isRotateGesturesEnabled(): Boolean

    fun setRotateGesturesEnabled(rotateGesturesEnabled: Boolean?)

    fun isScrollGesturesEnabled(): Boolean

    fun setScrollGesturesEnabled(scrollGesturesEnabled: Boolean?)

    fun isScrollGesturesEnabledDuringRotateOrZoom(): Boolean

    fun setScrollGesturesEnabledDuringRotateOrZoom(scrollGesturesEnabledDuringRotateOrZoom: Boolean?)

    fun isTiltGesturesEnabled(): Boolean

    fun setTiltGesturesEnabled(tiltGesturesEnabled: Boolean?)

    fun isZoomControlsEnabled(): Boolean

    fun setZoomControlsEnabled(zoomControlsEnabled: Boolean?)

    fun isZoomGesturesEnabled(): Boolean

    fun setZoomGesturesEnabled(zoomGesturesEnabled: Boolean?)

    fun setAllGesturesEnabled(allGestureEnable: Boolean?)
}