package idroid.android.mapskit.utils

import com.google.android.gms.maps.model.*
import idroid.android.mapskit.model.CommonCircle
import idroid.android.mapskit.model.CommonMarker
import idroid.android.mapskit.model.CommonPolygon
import idroid.android.mapskit.model.CommonPolyline

// Huawei Map Objects
fun com.huawei.hms.maps.model.LatLng.toGoogleLatLng(): LatLng =
    LatLng(this.latitude, this.longitude)

fun com.huawei.hms.maps.model.Marker.toHesMarker(): CommonMarker = CommonMarker(
        DistributeType.HUAWEI_SERVICES,
        this,
        this.id,
        this.alpha,
        this.rotation,
        LatLng(this.position.latitude, this.position.longitude),
        this.title,
        this.snippet,
        this.tag
)


fun com.huawei.hms.maps.model.Polyline.toHesPolyline(): CommonPolyline? = CommonPolyline(
    DistributeType.HUAWEI_SERVICES,
    this,
    this.width,
    this.color,
    this.zIndex,
    this.points
)

fun com.huawei.hms.maps.model.Polygon.toHesPolygon(): CommonPolygon = CommonPolygon(
    DistributeType.HUAWEI_SERVICES,
    this
)

fun com.huawei.hms.maps.model.LatLngBounds.toGoogleLatLngBounds(): LatLngBounds = LatLngBounds(
    LatLng(this.southwest.latitude, this.southwest.longitude),
    LatLng(this.northeast.latitude, this.northeast.longitude)
)

fun com.huawei.hms.maps.model.Circle.toHesCircle(): CommonCircle =
    CommonCircle(DistributeType.HUAWEI_SERVICES, this)


// Google Map Objects
fun LatLng.toHuaweiLatLng(): com.huawei.hms.maps.model.LatLng =
    com.huawei.hms.maps.model.LatLng(this.latitude, this.longitude)

fun Marker.toHesMarker(): CommonMarker = CommonMarker(
        DistributeType.GOOGLE_SERVICES,
        this,
        this.id,
        this.alpha,
        this.rotation,
        LatLng(this.position.latitude, this.position.longitude),
        this.title,
        this.snippet,
        this.tag
)

fun Polyline.toHesPolyline(): CommonPolyline = CommonPolyline(
    DistributeType.GOOGLE_SERVICES,
    this,
    this.points,
    this.width,
    this.color,
    this.zIndex
)

fun Polygon.toHesPolygon(): CommonPolygon = CommonPolygon(
    DistributeType.GOOGLE_SERVICES,
    this
)

fun LatLngBounds.toHuaweiLatLngBounds(): com.huawei.hms.maps.model.LatLngBounds =
    com.huawei.hms.maps.model.LatLngBounds(
        LatLng(this.southwest.latitude, this.southwest.longitude).toHuaweiLatLng(),
        LatLng(this.northeast.latitude, this.northeast.longitude).toHuaweiLatLng()
    )

fun Circle.toHesCircle(): CommonCircle = CommonCircle(DistributeType.GOOGLE_SERVICES, this)

fun CircleOptions.toHuaweiCircleOptions(): com.huawei.hms.maps.model.CircleOptions =
    com.huawei.hms.maps.model.CircleOptions().radius(this.radius)
        .center(this.center.toHuaweiLatLng())
        .fillColor(this.fillColor)
        .strokeColor(this.strokeColor)
        .strokeWidth(this.strokeWidth)
