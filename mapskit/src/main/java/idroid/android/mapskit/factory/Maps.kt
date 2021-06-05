package idroid.android.mapskit.factory

import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresPermission
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import idroid.android.mapskit.model.*


interface Maps : UISettings {
    fun getMapView(): View?
    fun onCreate(bundle: Bundle?)
    fun getMapAsync(onMapReadyListener: (map: Maps) -> Unit)

    fun addMarker(title: String, snippet: String, latitude: Double?, longitude: Double?): CommonMarker
    fun addMarker(icon: Bitmap, latLng: LatLng, title: String): CommonMarker
    fun addMarker(icon: Bitmap, latLng: LatLng, zIndex: Float): CommonMarker
    fun addMarker(icon: Bitmap, latLng: LatLng): CommonMarker
    fun addMarker(commonMarkerOptions: CommonMarkerOptions): CommonMarker

    fun moveCamera(latitude: Double, longitude: Double, zoomRatio: Float)
    fun moveCamera(zoomRatio: Float)
    fun moveCamera(latLng: LatLng, zoomRatio: Double)
    fun moveCamera(latLngBounds: LatLngBounds, padding: Int)
    fun moveCamera(latLng: LatLng, zoomRatio: Float, v1: Int, v2: Int)
    fun moveCamera(latLng: LatLng, zoomRatio: Float)

    fun animateCamera(latitude: Double, longitude: Double, zoomRatio: Float)
    fun animateCamera(zoomRatio: Float)
    fun animateCamera(latLng: LatLng, zoomRatio: Float)
    fun animateCamera(latLngBounds: LatLngBounds, padding: Int)
    fun animateCamera(latLng: LatLng, zoomRatio: Float, duration: Int)
    fun animateCamera(location: Location, zoomRatio: Float, bearing: Float, tilt: Float)

    fun setInfoWindowAdapter(infoWindowAdapter: (marker: CommonMarker) -> View)

    fun addCircle(circleOptions: CircleOptions): CommonCircle
    fun addPolyline(options: CommonPolylineOptions): CommonPolyline
    fun addPolygon(options: CommonPolygonOptions): CommonPolygon
    fun addTileOverlay(tileOverlayOptions: Any)

    fun setMaxZoomPreference(zoomRatio: Float)
    fun setMinZoomPreference(zoomRatio: Float)
    fun zoomIn()
    fun zoomOut()

    @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
    fun setMyLocationEnabled(myLocationEnabled: Boolean)
    fun setMapType(mapType: Type)
    fun setBuildings(b: Boolean)
    fun getCameraPosition(): CameraPosition

    fun getProjection(): CommonProjection

    fun setOnMarkerClickListener(onMapMarkerClickListener: (marker: CommonMarker) -> Boolean)
    fun setOnInfoWindowClickListener(onInfoWindowClickListener: (marker: CommonMarker) -> Unit)
    fun setOnMapLongClickListener(mapLongClickListener: (point: LatLng) -> Unit)
    fun setOnMapClickListener(mapClickListener: (point: LatLng) -> Unit)
    fun setOnMapLoadedCallback(mapLoadedListener: () -> Unit)
    fun setOnCameraIdleListener(cameraIdleListener: () -> Unit)
    fun setOnCameraMoveListener(cameraMoveListener: (position: LatLng) -> Unit)

    fun snapshot(snapshotReadyListener: (_bitmap: Bitmap) -> Unit)

    fun clear()

    enum class Type {
        SATALLITE, NORMAL
    }
}
