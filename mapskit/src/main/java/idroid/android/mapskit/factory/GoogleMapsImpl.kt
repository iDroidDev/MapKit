package idroid.android.mapskit.factory

import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import idroid.android.mapskit.listener.OnMapMarkerClickListener
import idroid.android.mapskit.listener.OnMapReadyListener

class GoogleMapsImpl(context: Context) : BaseMaps(context), OnMapReadyCallback {

    private var mapView: MapView
    private lateinit var map: GoogleMap
    private lateinit var onMapReadyListener: OnMapReadyListener

    init {
        mapView = MapView(context)
    }

    override fun getMapView(): View {
        return mapView
    }

    override fun onCreate(bundle: Bundle) {
        mapView.onCreate(bundle)
    }

    override fun getMapAsync(onMapReadyListener: OnMapReadyListener) {
        this.onMapReadyListener = onMapReadyListener
        mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        this.onMapReadyListener.onMapReady()
    }

    override fun addMarker(title: String, snippet: String, latitude: Float?, longitude: Float?) {
        super.addMarker(title, snippet, latitude, longitude)
        val nyGoogle = latitude?.toDouble()?.let { lat ->
            longitude?.toDouble()?.let { long ->
                LatLng(lat, long)
            }
        }
        val markerOptionsGoogle = nyGoogle?.let { MarkerOptions().position(it) }
        if (title != null && !title.isEmpty()) markerOptionsGoogle?.title(title)
        if (snippet != null && !snippet.isEmpty()) markerOptionsGoogle?.snippet(snippet)
        map.addMarker(markerOptionsGoogle)
    }

    override fun setOnInfoWindowClickListener(onMapMarkerClickListener: OnMapMarkerClickListener) {
        map.setOnInfoWindowClickListener(object : GoogleMap.OnInfoWindowClickListener {
            override fun onInfoWindowClick(marker: Marker) {
                onMapMarkerClickListener.onMarkerClick(marker.getTitle(), marker.getSnippet())
            }
        })
    }

    override fun moveCamera(latitude: Float?, longitude: Float?, zoomRatio: Float?) {
        super.moveCamera(latitude, longitude, zoomRatio)
        val nyGoogle = latitude?.toDouble()?.let { lat ->
            longitude?.toDouble()?.let { long ->
                LatLng(lat, long)
            }
        }
        map.moveCamera(zoomRatio?.let { CameraUpdateFactory.newLatLngZoom(nyGoogle, it) })
    }

    override fun animateCamera(latitude: Float?, longitude: Float?, zoomRatio: Float?) {
        super.animateCamera(latitude, longitude, zoomRatio)
        val nyGoogle = latitude?.toDouble()?.let { lat ->
            longitude?.toDouble()?.let { long ->
                LatLng(lat, long)
            }
        }
        map.animateCamera(zoomRatio?.let { CameraUpdateFactory.newLatLngZoom(nyGoogle, it) })
    }

    override fun setMyLocationEnabled(myLocationEnabled: Boolean?) {
        map.isMyLocationEnabled = myLocationEnabled!!
    }

    override fun clear() {
        map.clear()
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        mapView.onSaveInstanceState(bundle)
    }

    override fun onStart() {
        mapView.onStart()
    }

    override fun onResume() {
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
    }

    override fun onStop() {
        mapView.onStop()
    }

    override fun onDestroy() {
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        mapView.onLowMemory()
    }
}