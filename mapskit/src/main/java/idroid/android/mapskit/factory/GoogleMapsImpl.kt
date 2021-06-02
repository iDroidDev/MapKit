package idroid.android.mapskit.factory

import android.content.Context
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import idroid.android.mapskit.model.*
import idroid.android.mapskit.utils.MapType
import idroid.android.mapskit.utils.toHesCircle
import idroid.android.mapskit.utils.toHesMarker
import idroid.android.mapskit.utils.toHesPolyline


class GoogleMapsImpl(context: Context, mapType: MapType = MapType.MAP_VIEW) : BaseMaps(
    context,
    mapType
), OnMapReadyCallback {

    private var mapView: MapView? = null
    private var mapFragment: SupportMapFragment? = null
    private lateinit var map: GoogleMap
    private lateinit var onMapReadyListener: Maps.OnMapReadyListener

    init {
        if (mapType == MapType.MAP_FRAGMENT) {
            mapFragment = SupportMapFragment.newInstance()
            replaceFragment(mapFragment)
        } else if (mapType == MapType.MAP_VIEW) {
            mapView = MapView(context)
        }
    }

    override fun getMapView(): View? {
        return mapView
    }

    override fun onCreate(bundle: Bundle) {
        mapView?.onCreate(bundle)
        mapFragment?.onCreate(bundle)
    }

    override fun getMapAsync(onMapReadyListener: Maps.OnMapReadyListener) {
        this.onMapReadyListener = onMapReadyListener
        if (mapType == MapType.MAP_VIEW) mapView?.getMapAsync(this)
        else if (mapType == MapType.MAP_FRAGMENT) mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        this.onMapReadyListener.onMapReady(this)
    }

    override fun addMarker(
        title: String,
        snippet: String,
        latitude: Double?,
        longitude: Double?
    ): CommonMarker {
        val nyGoogle = latitude?.let { lat ->
            longitude?.let { long ->
                LatLng(lat, long)
            }
        }
        val markerOptionsGoogle = nyGoogle?.let { MarkerOptions().position(it) }
        if (title.isNotEmpty()) markerOptionsGoogle?.title(title)
        if (snippet.isNotEmpty()) markerOptionsGoogle?.snippet(snippet)
        return map.addMarker(markerOptionsGoogle).toHesMarker()
    }

    override fun addMarker(icon: Bitmap, latLng: LatLng, title: String): CommonMarker {
        return map.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(icon))
                .position(latLng)
                .title(title)
        ).toHesMarker()
    }

    override fun addMarker(icon: Bitmap, latLng: LatLng, zIndex: Float): CommonMarker {
        return map.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(icon))
                .position(latLng)
                .zIndex(zIndex)
        ).toHesMarker()
    }

    override fun addMarker(icon: Bitmap, latLng: LatLng): CommonMarker {
        return map.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(icon))
                .position(latLng)
        ).toHesMarker()
    }

    override fun addMarker(commonMarkerOptions: CommonMarkerOptions): CommonMarker {
        return map.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(commonMarkerOptions.icon))
                .position(commonMarkerOptions.latLng)
                .title(commonMarkerOptions.title)
        ).toHesMarker()
    }

    override fun moveCamera(latitude: Double, longitude: Double, zoomRatio: Float) {
        val nyGoogle = LatLng(latitude, longitude)
        map.moveCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition(
                    nyGoogle,
                    zoomRatio,
                    0f,
                    0f
                )
            )
        )
    }

    override fun moveCamera(latLng: LatLng, zoomRatio: Float, v1: Int, v2: Int) {
        map.moveCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition(
                    latLng,
                    zoomRatio,
                    v1.toFloat(),
                    v2.toFloat()
                )
            )
        )
    }

    override fun moveCamera(latLng: LatLng, zoomRatio: Double) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomRatio.toFloat()))
    }

    override fun moveCamera(latLng: LatLng, zoomRatio: Float) {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomRatio))
    }

    override fun animateCamera(latitude: Double, longitude: Double, zoomRatio: Float) {
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    latitude,
                    longitude
                ), zoomRatio
            )
        )
    }

    override fun animateCamera(zoomRatio: Float) {
        map.animateCamera(CameraUpdateFactory.zoomTo(zoomRatio))
    }

    override fun animateCamera(latLng: LatLng, zoom: Float) {
        val position = CameraPosition.Builder()
            .target(latLng)
            .zoom(zoom).build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(position))
    }

    override fun animateCamera(latLngBounds: LatLngBounds, padding: Int) {
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, padding))
    }

    override fun animateCamera(latLng: LatLng, zoom: Float, duration: Int) {
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(latLng, zoom),
            duration,
            object : GoogleMap.CancelableCallback {
                override fun onFinish() {}
                override fun onCancel() {}
            })
    }

    override fun animateCamera(location: Location, zoom: Float, bearing: Float, tilt: Float) {
        val position = CameraPosition.Builder()
            .target(LatLng(location.latitude, location.longitude))
            .zoom(zoom)
            .bearing(getCameraPosition().bearing)
            .tilt(getCameraPosition().tilt)
            .build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(position))
    }

    override fun setInfoWindowAdapter(infoWindowAdapter: Maps.InfoWindowAdapter) {
        map.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
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
        return map.addCircle(circleOptions).toHesCircle()
    }

    override fun addPolyline(options: CommonPolylineOptions): CommonPolyline {
        val polylineOptions = PolylineOptions()
        polylineOptions.addAll(options.getLatLngs())
        polylineOptions.width(options.getWidth())
        polylineOptions.color(options.getColor())

        options.getStartCap()?.googleCap()?.let {
            polylineOptions.startCap(it)
        }
        options.getEndCap()?.googleCap()?.let {
            polylineOptions.endCap(it)
        }

        options.getJointType()?.google()?.let {
            polylineOptions.jointType(it)
        }

        val polyline: Polyline = map.addPolyline(polylineOptions)
        return polyline.toHesPolyline()
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
        map.animateCamera(CameraUpdateFactory.zoomIn())
    }

    override fun setMyLocationEnabled(myLocationEnabled: Boolean) {
        map.isMyLocationEnabled = myLocationEnabled
    }

    override fun setMapType(mapType: Maps.Type) {
        if (Maps.Type.SATALLITE === mapType) map.mapType =
            GoogleMap.MAP_TYPE_SATELLITE else map.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    override fun setBuildings(b: Boolean) {
        map.isBuildingsEnabled = b
    }

    override fun getCameraPosition(): CameraPosition {
        return map.cameraPosition
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
            onInfoWindowClickListener.onInfoWindowClick(marker.toHesMarker())
        }
    }

    override fun setOnMapLongClickListener(mapLongClickListener: Maps.MapLongClickListener) {
        map.setOnMapLongClickListener { latLng -> mapLongClickListener.onMapLongClick(latLng) }
    }

    override fun setOnMapClickListener(mapClickListener: Maps.MapClickListener) {
        map.setOnMapClickListener { latLng -> mapClickListener.onMapClick(latLng) }
    }

    override fun setOnMapLoadedCallback(mapLoadedListener: Maps.MapLoadedListener) {
        map.setOnMapLoadedCallback { mapLoadedListener.onMapLoaded() }
    }

    override fun setOnCameraIdleListener(cameraIdleListener: () -> Unit) {
        map.setOnCameraIdleListener { cameraIdleListener.invoke() }
    }

    override fun setOnCameraMoveListener(cameraMoveListener: (position: LatLng) -> Unit) {
        map.setOnCameraMoveListener { cameraMoveListener.invoke(getCameraPosition().target) }
    }

    override fun snapshot(snapshotReadyListener: Maps.SnapshotReadyListener) {
        map.snapshot { bitmap -> snapshotReadyListener.onSnapshotReady(bitmap) }
    }

    override fun clear() {
        map.clear()
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        mapView?.onSaveInstanceState(bundle)
        mapFragment?.onSaveInstanceState(bundle)
    }

    override fun onStart() {
        mapView?.onStart()
        mapFragment?.onStart()
    }

    override fun onResume() {
        mapView?.onResume()
        mapFragment?.onResume()
    }

    override fun onPause() {
        mapView?.onPause()
        mapFragment?.onPause()
    }

    override fun onStop() {
        mapView?.onStop()
        mapFragment?.onStop()
    }

    override fun onDestroy() {
        mapView?.onDestroy()
        mapFragment?.onDestroy()
    }

    override fun onLowMemory() {
        mapView?.onLowMemory()
        mapFragment?.onLowMemory()
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
            map.uiSettings.isMyLocationButtonEnabled = it
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
        mapView?.onEnterAmbient(bundle)
        mapFragment?.onEnterAmbient(bundle)
    }

}
