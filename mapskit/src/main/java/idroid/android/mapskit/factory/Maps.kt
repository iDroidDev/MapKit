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
    fun onCreate(bundle: Bundle)
    fun getMapAsync(onMapReadyListener: OnMapReadyListener)

    fun addMarker(title: String, snippet: String, latitude: Double?, longitude: Double?): CommonMarker
    fun addMarker(icon: Bitmap, latLng: LatLng, title: String): CommonMarker
    fun addMarker(icon: Bitmap, latLng: LatLng, zIndex: Float): CommonMarker
    fun addMarker(icon: Bitmap, latLng: LatLng): CommonMarker
    fun addMarker(commonMarkerOptions: CommonMarkerOptions): CommonMarker

    fun moveCamera(latitude: Double, longitude: Double, zoomRatio: Float)
    fun moveCamera(latLng: LatLng, zoomRatio: Float, v1: Int, v2: Int)
    fun moveCamera(latLng: LatLng, zoomRatio: Double)
    fun moveCamera(latLng: LatLng, zoomRatio: Float)

    fun animateCamera(latitude: Double, longitude: Double, zoomRatio: Float)
    fun animateCamera(zoomRatio: Float)
    fun animateCamera(latLng: LatLng, zoom: Float)
    fun animateCamera(latLngBounds: LatLngBounds, padding: Int)
    fun animateCamera(latLng: LatLng, zoom: Float, duration: Int)
    fun animateCamera(location: Location, zoom: Float, bearing: Float, tilt: Float)

    fun setInfoWindowAdapter(infoWindowAdapter: InfoWindowAdapter)

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

    fun setOnMarkerClickListener(onMapMarkerClickListener: OnMapMarkerClickListener?)
    fun setOnInfoWindowClickListener(onInfoWindowClickListener: OnMapInfoWindowClickListener)
    fun setOnMapLongClickListener(mapLongClickListener: MapLongClickListener)
    fun setOnMapClickListener(mapClickListener: MapClickListener)
    fun setOnMapLoadedCallback(mapLoadedListener: MapLoadedListener)
    fun setOnCameraIdleListener(cameraIdleListener: () -> Unit)
    fun setOnCameraMoveListener(cameraMoveListener: (position: LatLng) -> Unit)

    fun snapshot(snapshotReadyListener: SnapshotReadyListener)


    fun clear()

    interface OnMapInfoWindowClickListener {
        fun onInfoWindowClick(marker: CommonMarker)
    }

    interface OnMapMarkerClickListener {
        fun onMarkerClick(marker: CommonMarker): Boolean
    }

    interface OnMapReadyListener {
        fun onMapReady(map: Maps)
    }

    interface CameraChangeListener {
        fun onCameraChange(position: CameraPosition)
    }

    interface MapLoadedListener {
        fun onMapLoaded()
    }

    interface MapLongClickListener {
        fun onMapLongClick(point: LatLng)
    }

    interface MapClickListener {
        fun onMapClick(point: LatLng)
    }

    interface InfoWindowAdapter {
        fun getInfoWindow(marker: CommonMarker): View
    }

    interface SnapshotReadyListener {
        fun onSnapshotReady(_bitmap: Bitmap)
    }

    enum class Type {
        SATALLITE, NORMAL
    }
}
