package idroid.android.mapskit.factory

import android.os.Bundle
import android.view.View
import idroid.android.mapskit.listener.OnMapMarkerClickListener
import idroid.android.mapskit.listener.OnMapReadyListener

interface Maps {
    fun getMapView(): View
    fun onCreate(bundle: Bundle)
    fun getMapAsync(onMapReadyListener: OnMapReadyListener)
    fun addMarker(title: String, snippet: String, latitude: Float?, longitude: Float?)
    fun setOnInfoWindowClickListener(onMapMarkerClickListener: OnMapMarkerClickListener)
    fun moveCamera(latitude: Float?, longitude: Float?, zoomRatio: Float?)
    fun animateCamera(latitude: Float?, longitude: Float?, zoomRatio: Float?)
    fun setMyLocationEnabled(myLocationEnabled: Boolean?)
    fun clear()
    fun onSaveInstanceState(bundle: Bundle)
    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroy()
    fun onLowMemory()
}