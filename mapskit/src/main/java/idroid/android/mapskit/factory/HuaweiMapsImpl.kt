package idroid.android.mapskit.factory

import android.content.Context
import android.os.Bundle
import android.view.View
import com.huawei.hms.maps.CameraUpdateFactory
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.model.LatLng
import com.huawei.hms.maps.model.Marker
import com.huawei.hms.maps.model.MarkerOptions
import idroid.android.mapskit.listener.OnMapMarkerClickListener
import idroid.android.mapskit.listener.OnMapReadyListener

class HuaweiMapsImpl(context: Context) : BaseMaps(context), OnMapReadyCallback {

    private var mapView: MapView = MapView(context)
    private lateinit var map: HuaweiMap
    private lateinit var onMapReadyListener: OnMapReadyListener

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

    override fun onMapReady(huaweiMap: HuaweiMap) {
        map = huaweiMap
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
        map.setOnInfoWindowClickListener(object : HuaweiMap.OnInfoWindowClickListener {
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

    override fun isCompassEnabled(): Boolean {
        return map.uiSettings.isCompassEnabled
    }

    override fun setCompassEnabled(compassEnabled: Boolean?) {
        map.uiSettings.isCompassEnabled = compassEnabled!!
    }

    override fun isIndoorLevelPickerEnabled(): Boolean {
        return map.uiSettings.isIndoorLevelPickerEnabled

    }

    override fun setIndoorLevelPickerEnabled(indoorLevelPickerEnabled: Boolean?) {
        map.uiSettings.isIndoorLevelPickerEnabled = indoorLevelPickerEnabled!!
    }

    override fun isMapToolbarEnabled(): Boolean {
        return map.uiSettings.isMapToolbarEnabled
    }

    override fun setMapToolbarEnabled(mapToolbarEnabled: Boolean?) {
        map.uiSettings.isMapToolbarEnabled = mapToolbarEnabled!!
    }

    override fun isMyLocationButtonEnabled(): Boolean {
        return map.uiSettings.isMyLocationButtonEnabled
    }

    override fun setMyLocationButtonEnabled(myLocationButtonEnabled: Boolean?) {
        map.uiSettings.isMyLocationButtonEnabled = myLocationButtonEnabled!!
    }

    override fun isRotateGesturesEnabled(): Boolean {
        return map.uiSettings.isRotateGesturesEnabled
    }

    override fun setRotateGesturesEnabled(rotateGesturesEnabled: Boolean?) {
        map.uiSettings.isRotateGesturesEnabled = rotateGesturesEnabled!!
    }

    override fun isScrollGesturesEnabled(): Boolean {
        return map.uiSettings.isScrollGesturesEnabled
    }

    override fun setScrollGesturesEnabled(scrollGesturesEnabled: Boolean?) {
        map.uiSettings.isScrollGesturesEnabledDuringRotateOrZoom = scrollGesturesEnabled!!
    }

    override fun isScrollGesturesEnabledDuringRotateOrZoom(): Boolean {
        return map.uiSettings.isScrollGesturesEnabledDuringRotateOrZoom
    }

    override fun setScrollGesturesEnabledDuringRotateOrZoom(scrollGesturesEnabledDuringRotateOrZoom: Boolean?) {
        map.uiSettings.isScrollGesturesEnabledDuringRotateOrZoom =
            scrollGesturesEnabledDuringRotateOrZoom!!
    }

    override fun isTiltGesturesEnabled(): Boolean {
        return map.uiSettings.isTiltGesturesEnabled
    }

    override fun setTiltGesturesEnabled(tiltGesturesEnabled: Boolean?) {
        map.uiSettings.isTiltGesturesEnabled = tiltGesturesEnabled!!
    }

    override fun isZoomControlsEnabled(): Boolean {
        return map.uiSettings.isZoomControlsEnabled
    }

    override fun setZoomControlsEnabled(zoomControlsEnabled: Boolean?) {
        map.uiSettings.isZoomControlsEnabled = zoomControlsEnabled!!
    }

    override fun isZoomGesturesEnabled(): Boolean {
        return map.uiSettings.isZoomGesturesEnabled
    }

    override fun setZoomGesturesEnabled(zoomGesturesEnabled: Boolean?) {
        map.uiSettings.isZoomGesturesEnabled = true
    }

    override fun setAllGesturesEnabled(allGestureEnable: Boolean?) {
        map.uiSettings.setAllGesturesEnabled(allGestureEnable!!)
    }
}
