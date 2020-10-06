package idroid.android.mapskit.model

import android.graphics.Bitmap
import com.google.android.gms.maps.model.LatLng

data class CommonMarkerOptions(val latLng: LatLng, val title: String, val icon: Bitmap) {
}