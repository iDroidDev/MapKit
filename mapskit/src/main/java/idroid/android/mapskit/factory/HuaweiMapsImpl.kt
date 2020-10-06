package idroid.android.mapskit.factory

import android.content.Context
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLngBounds
import com.huawei.hms.maps.CameraUpdateFactory
import com.huawei.hms.maps.HuaweiMap
import com.huawei.hms.maps.MapView
import com.huawei.hms.maps.OnMapReadyCallback
import com.huawei.hms.maps.model.*
import idroid.android.mapskit.model.*
import idroid.android.mapskit.utils.*

class HuaweiMapsImpl(context: Context) : BaseMaps(context), OnMapReadyCallback {

    private var mapView: MapView = MapView(context)
    private lateinit var map: HuaweiMap
    private lateinit var onMapReadyListener: Maps.OnMapReadyListener

    override fun getMapView(): View {
        return mapView
    }

    override fun onCreate(bundle: Bundle) {
        mapView.onCreate(bundle)
    }

    override fun getMapAsync(onMapReadyListener: Maps.OnMapReadyListener) {
        this.onMapReadyListener = onMapReadyListener
        mapView.getMapAsync(this)
    }

    override fun onMapReady(huaweiMap: HuaweiMap) {
        map = huaweiMap
        this.onMapReadyListener.onMapReady(this)
    }

    override fun addMarker(
        title: String,
        snippet: String,
        latitude: Float?,
        longitude: Float?
    ): CommonMarker {
        val nyHuawei = latitude?.toDouble()?.let { lat ->
            longitude?.toDouble()?.let { long ->
                LatLng(lat, long)
            }
        }
        val markerOptionsGoogle = MarkerOptions().position(nyHuawei)
        if (title.isNotEmpty()) markerOptionsGoogle.title(title)
        if (snippet.isNotEmpty()) markerOptionsGoogle.snippet(snippet)
        return map.addMarker(markerOptionsGoogle).toHesMarker()
    }

    override fun addMarker(
        icon: Bitmap,
        latLng: com.google.android.gms.maps.model.LatLng,
        title: String
    ): CommonMarker {
        return map.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(icon))
                .position(latLng.toHuaweiLatLng())
                .title(title)
        ).toHesMarker()
    }

    override fun addMarker(
        icon: Bitmap,
        latLng: com.google.android.gms.maps.model.LatLng,
        zIndex: Float
    ): CommonMarker {
        return map.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(icon))
                .position(latLng.toHuaweiLatLng())
                .zIndex(zIndex)
        ).toHesMarker()
    }

    override fun addMarker(
        icon: Bitmap,
        latLng: com.google.android.gms.maps.model.LatLng
    ): CommonMarker {
        return map.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(icon))
                .position(latLng.toHuaweiLatLng())
        )
            .toHesMarker()
    }

    override fun moveCamera(latitude: Double, longitude: Double, zoomRatio: Float) {
        map.moveCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition(
                    com.google.android.gms.maps.model.LatLng(latitude, longitude).toHuaweiLatLng(),
                    zoomRatio,
                    0f,
                    0f
                )
            )
        )
    }

    override fun moveCamera(
        latLng: com.google.android.gms.maps.model.LatLng,
        zoomRatio: Float,
        v1: Int,
        v2: Int
    ) {
        map.moveCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition(
                    latLng.toHuaweiLatLng(),
                    zoomRatio,
                    v1.toFloat(),
                    v2.toFloat()
                )
            )
        )
    }

    override fun moveCamera(latLng: com.google.android.gms.maps.model.LatLng, zoomRatio: Double) {
        map.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                latLng.toHuaweiLatLng(),
                zoomRatio.toFloat()
            )
        )
    }

    override fun moveCamera(latLng: com.google.android.gms.maps.model.LatLng, zoomRatio: Float) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng.toHuaweiLatLng(), zoomRatio))
    }

    override fun animateCamera(latitude: Float, longitude: Float, zoomRatio: Float) {
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                com.google.android.gms.maps.model.LatLng(
                    latitude.toDouble(),
                    longitude.toDouble()
                ).toHuaweiLatLng(), zoomRatio
            )
        )
    }

    override fun animateCamera(zoomRatio: Float) {
        map.animateCamera(CameraUpdateFactory.zoomTo(zoomRatio))
    }

    override fun animateCamera(latLng: com.google.android.gms.maps.model.LatLng, zoom: Float) {
        val position = CameraPosition.Builder()
            .target(latLng.toHuaweiLatLng())
            .zoom(zoom).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(position))
    }

    override fun animateCamera(latLngBounds: LatLngBounds, padding: Int) {
        map.animateCamera(
            CameraUpdateFactory.newLatLngBounds(
                latLngBounds.toHuaweiLatLngBounds(),
                padding
            )
        )
    }

    override fun animateCamera(
        latLng: com.google.android.gms.maps.model.LatLng,
        zoom: Float,
        duration: Int
    ) {
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(latLng.toHuaweiLatLng(), zoom),
            duration,
            object : HuaweiMap.CancelableCallback {
                override fun onFinish() {}
                override fun onCancel() {}
            })
    }

    override fun animateCamera(location: Location, zoom: Float, bearing: Float, tilt: Float) {
        val position = CameraPosition.Builder()
            .target(
                com.google.android.gms.maps.model.LatLng(location.latitude, location.longitude)
                    .toHuaweiLatLng()
            )
            .zoom(zoom)
            .bearing(getCameraPosition().bearing)
            .tilt(getCameraPosition().tilt)
            .build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(position))
    }

    override fun setInfoWindowAdapter(infoWindowAdapter: Maps.InfoWindowAdapter) {
        map.setInfoWindowAdapter(object : HuaweiMap.InfoWindowAdapter {
            override fun getInfoWindow(marker: Marker): View? {
                infoWindowAdapter.getInfoWindow(marker.toHesMarker())
                return null
            }

            override fun getInfoContents(marker: Marker): View? {
                return null
            }
        })
    }

    override fun addCircle(circleOptions: CircleOptions): CommonCircle {
        return map.addCircle(circleOptions.toHuaweiCircleOptions()).toHesCircle()
    }

    override fun addPolyline(options: CommonPolylineOptions): CommonPolyline {
        val polylineOptions = PolylineOptions()
        polylineOptions.addAll(options.getHuaweiLatLngs())
        polylineOptions.width(options.getWidth())
        polylineOptions.color(options.getColor())
        val polyline = map.addPolyline(polylineOptions)
        return polyline.toHesPolyline()!!
    }

    override fun addTileOverlay(tileOverlayOptions: Any) {
        map.addTileOverlay(tileOverlayOptions as TileOverlayOptions)
    }

    override fun setMaxZoomPreference(zoomRatio: Float) {
        map.setMaxZoomPreference(zoomRatio)
    }

    override fun setMinZoomPreference(zoomRatio: Float) {
        map.setMinZoomPreference(zoomRatio)
    }

    override fun zoomIn() {
        map.animateCamera(CameraUpdateFactory.zoomIn())
    }

    override fun zoomOut() {
        map.animateCamera(CameraUpdateFactory.zoomOut())
    }

    override fun setMyLocationEnabled(myLocationEnabled: Boolean) {
        map.isMyLocationEnabled = myLocationEnabled
        map.uiSettings.isMyLocationButtonEnabled = false
    }

    override fun setMapType(mapType: Maps.Type) {
        if (Maps.Type.SATALLITE === mapType) map.mapType =
            HuaweiMap.MAP_TYPE_SATELLITE else map.mapType = HuaweiMap.MAP_TYPE_NORMAL
    }

    override fun setBuildings(b: Boolean) {
        map.isBuildingsEnabled = b
    }

    override fun getCameraPosition(): com.google.android.gms.maps.model.CameraPosition {
        val cameraPosition = map.cameraPosition
        return com.google.android.gms.maps.model.CameraPosition(
            com.google.android.gms.maps.model.LatLng(
                cameraPosition.target.latitude,
                cameraPosition.target.longitude
            ), cameraPosition.zoom, cameraPosition.tilt, cameraPosition.bearing
        )
    }

    override fun getProjection(): CommonProjection {
        return HesProjectionImpl.getProjection(map.projection)!!
    }

    override fun setOnMarkerClickListener(onMapMarkerClickListener: Maps.OnMapMarkerClickListener?) {
        onMapMarkerClickListener?.let {
            map.setOnMarkerClickListener { marker -> it.onMarkerClick(marker.toHesMarker()) }
        }
    }

    override fun setOnInfoWindowClickListener(onInfoWindowClickListener: Maps.OnMapInfoWindowClickListener) {
        map.setOnInfoWindowClickListener { marker ->
            onInfoWindowClickListener.onInfoWindowClick(
                marker.toHesMarker()
            )
        }
    }

    override fun setOnMapLongClickListener(mapLongClickListener: Maps.MapLongClickListener) {
        map.setOnMapLongClickListener { latLng ->
            mapLongClickListener.onMapLongClick(
                com.google.android.gms.maps.model.LatLng(
                    latLng.latitude,
                    latLng.longitude
                )
            )
        }
    }

    override fun setOnMapClickListener(mapClickListener: Maps.MapClickListener) {
        map.setOnMapClickListener { latLng ->
            mapClickListener.onMapClick(
                com.google.android.gms.maps.model.LatLng(
                    latLng.latitude,
                    latLng.longitude
                )
            )
        }
    }

    override fun setOnMapLoadedCallback(mapLoadedListener: Maps.MapLoadedListener) {
        map.setOnMapLoadedCallback { mapLoadedListener.onMapLoaded() }
    }

    override fun setOnCameraIdleListener(cameraIdleListener: () -> Unit) {
        map.setOnCameraIdleListener { cameraIdleListener.invoke() }
    }

    override fun setOnCameraMoveListener(cameraMoveListener: () -> Unit) {
        map.setOnCameraMoveListener { cameraMoveListener.invoke() }
    }

    override fun snapshot(snapshotReadyListener: Maps.SnapshotReadyListener) {
        map.snapshot { bitmap -> snapshotReadyListener.onSnapshotReady(bitmap) }
    }

    override fun clear() {
        map.clear()
    }

    override fun onSaveInstanceState(bundle: Bundle) {
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
        myLocationButtonEnabled?.let {
            map.uiSettings.isMyLocationButtonEnabled = myLocationButtonEnabled
        }
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

    override fun onEnterAmbient(bundle: Bundle?) {
        mapView.onEnterAmbient(bundle)
    }

}
