package idroid.android.mapskit.listener

import idroid.android.mapskit.ui.HuaweiGoogleMapView


interface OnMapAsyncListener {
    fun onMapReady(mapView: HuaweiGoogleMapView)
}