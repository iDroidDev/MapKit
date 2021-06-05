package idroid.android.mapskit.factory

import android.content.Context
import android.graphics.Bitmap
import android.location.Location
import android.os.Bundle
import android.view.View
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLngBounds
import com.huawei.hms.maps.*
import com.huawei.hms.maps.model.*
import idroid.android.mapskit.model.*
import idroid.android.mapskit.utils.*

class HuaweiMapsImpl(
    context: Context,
    mapType: MapType = MapType.MAP_VIEW
) : BaseMaps(context, mapType), OnMapReadyCallback {

    private var mapView: MapView? = null
    private var mapFragment: SupportMapFragment? = null
    private lateinit var map: HuaweiMap
    private lateinit var onMapReadyListener: ((map: Maps) -> Unit)

    init {
        if (mapType == MapType.MAP_FRAGMENT) {
            mapFragment = SupportMapFragment.newInstance()
            replaceFragment(mapFragment)
        } else if (mapType == MapType.MAP_VIEW) {
            mapView = MapView(context)
        }
    }

    override fun getMapView(): View? = mapView

    override fun onCreate(bundle: Bundle?) {
        mapView?.onCreate(bundle)
        mapFragment?.onCreate(bundle)
    }

    override fun getMapAsync(onMapReadyListener: ((map: Maps) -> Unit)) {
        this.onMapReadyListener = onMapReadyListener
        if (mapType == MapType.MAP_VIEW) mapView?.getMapAsync(this)
        else if (mapType == MapType.MAP_FRAGMENT) mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(huaweiMap: HuaweiMap) {
        map = huaweiMap
        if (this::onMapReadyListener.isInitialized)
            this.onMapReadyListener(this)
    }

    override fun addMarker(
        title: String,
        snippet: String,
        latitude: Double?,
        longitude: Double?
    ): CommonMarker {
        val nyHuawei = latitude?.let { lat ->
            longitude?.let { long ->
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
        ).toHesMarker()
    }

    override fun addMarker(commonMarkerOptions: CommonMarkerOptions): CommonMarker {
        return map.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.fromBitmap(commonMarkerOptions.icon))
                .position(commonMarkerOptions.latLng.toHuaweiLatLng())
                .title(commonMarkerOptions.title)
        ).toHesMarker()
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

    override fun moveCamera(zoomRatio: Float) {
        map.moveCamera(CameraUpdateFactory.zoomTo(zoomRatio))
    }

    override fun moveCamera(latLngBounds: LatLngBounds, padding: Int) {
        map.moveCamera(
            CameraUpdateFactory.newLatLngBounds(
                latLngBounds.toHuaweiLatLngBounds(),
                padding
            )
        )
    }

    override fun animateCamera(latitude: Double, longitude: Double, zoomRatio: Float) {
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                com.google.android.gms.maps.model.LatLng(
                    latitude,
                    longitude
                ).toHuaweiLatLng(), zoomRatio
            )
        )
    }

    override fun animateCamera(zoomRatio: Float) {
        map.animateCamera(CameraUpdateFactory.zoomTo(zoomRatio))
    }

    override fun animateCamera(latLng: com.google.android.gms.maps.model.LatLng, zoomRatio: Float) {
        val position = CameraPosition.Builder()
            .target(latLng.toHuaweiLatLng())
            .zoom(zoomRatio).build()
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
        zoomRatio: Float,
        duration: Int
    ) {
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(latLng.toHuaweiLatLng(), zoomRatio),
            duration,
            object : HuaweiMap.CancelableCallback {
                override fun onFinish() {}
                override fun onCancel() {}
            })
    }

    override fun animateCamera(location: Location, zoomRatio: Float, bearing: Float, tilt: Float) {
        val position = CameraPosition.Builder()
            .target(
                com.google.android.gms.maps.model.LatLng(location.latitude, location.longitude)
                    .toHuaweiLatLng()
            )
            .zoom(zoomRatio)
            .bearing(getCameraPosition().bearing)
            .tilt(getCameraPosition().tilt)
            .build()
        map.animateCamera(CameraUpdateFactory.newCameraPosition(position))
    }

    override fun setInfoWindowAdapter(infoWindowAdapter: (marker: CommonMarker) -> View) {
        map.setInfoWindowAdapter(object : HuaweiMap.InfoWindowAdapter {
            override fun getInfoWindow(marker: Marker): View? {
                infoWindowAdapter(marker.toHesMarker())
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

        options.getStartCap()?.hmsCap()?.let {
            polylineOptions.startCap(it)
        }
        options.getEndCap()?.hmsCap()?.let {
            polylineOptions.endCap(it)
        }

        options.getJointType()?.hms()?.let {
            polylineOptions.jointType(it)
        }

        val polyline = map.addPolyline(polylineOptions)
        return polyline.toHesPolyline()!!
    }

    override fun addPolygon(options: CommonPolygonOptions): CommonPolygon {
        val polygonOptions = PolygonOptions()
        polygonOptions.points.addAll(options.hmsPoints())
        polygonOptions.holes.addAll(options.hmsHoles())

        polygonOptions.fillColor(options.fillColor)
        polygonOptions.strokeColor(options.strokeColor)

        polygonOptions.strokeWidth(options.strokeWidth)
        options.strokeJointType?.let {
            polygonOptions.strokeJointType(it.hms())
        }

        polygonOptions.clickable(options.clickable)
        polygonOptions.geodesic(options.geodesic)
        polygonOptions.visible(options.visible)

        val polygon = map.addPolygon(polygonOptions)
        return polygon.toHesPolygon()
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

    override fun setOnMarkerClickListener(onMapMarkerClickListener: (marker: CommonMarker) -> Boolean) {
        map.setOnMarkerClickListener { marker -> onMapMarkerClickListener(marker.toHesMarker()) }
    }

    override fun setOnInfoWindowClickListener(onInfoWindowClickListener: (marker: CommonMarker) -> Unit) {
        map.setOnInfoWindowClickListener { marker ->
            onInfoWindowClickListener(marker.toHesMarker())
        }
    }

    override fun setOnMapLongClickListener(mapLongClickListener: (point: com.google.android.gms.maps.model.LatLng) -> Unit) {
        map.setOnMapLongClickListener { latLng ->
            mapLongClickListener(
                com.google.android.gms.maps.model.LatLng(
                    latLng.latitude,
                    latLng.longitude
                )
            )
        }
    }

    override fun setOnMapClickListener(mapClickListener: (point: com.google.android.gms.maps.model.LatLng) -> Unit) {
        map.setOnMapClickListener { latLng ->
            mapClickListener(
                com.google.android.gms.maps.model.LatLng(
                    latLng.latitude,
                    latLng.longitude
                )
            )
        }
    }

    override fun setOnMapLoadedCallback(mapLoadedListener: () -> Unit) {
        map.setOnMapLoadedCallback { mapLoadedListener() }
    }

    override fun setOnCameraIdleListener(cameraIdleListener: () -> Unit) {
        map.setOnCameraIdleListener { cameraIdleListener.invoke() }
    }

    override fun setOnCameraMoveListener(cameraMoveListener: (position: com.google.android.gms.maps.model.LatLng) -> Unit) {
        map.setOnCameraMoveListener {
            cameraMoveListener.invoke(
                with(map.cameraPosition.target) {
                    com.google.android.gms.maps.model.LatLng(latitude, longitude)
                }
            )
        }
    }

    override fun snapshot(snapshotReadyListener: (_bitmap: Bitmap) -> Unit) {
        map.snapshot { bitmap -> snapshotReadyListener(bitmap) }
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
