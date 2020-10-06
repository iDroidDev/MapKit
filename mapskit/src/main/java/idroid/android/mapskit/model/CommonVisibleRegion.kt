package idroid.android.mapskit.model

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds

data class CommonVisibleRegion(
    val visibleRegionBounds: LatLngBounds?,
    val farLeft: LatLng,
    val farRight: LatLng,
    val nearLeft: LatLng,
    val nearRight: LatLng
)