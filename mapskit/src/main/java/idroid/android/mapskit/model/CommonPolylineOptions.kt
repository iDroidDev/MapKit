package idroid.android.mapskit.model

import com.google.android.gms.maps.model.LatLng
import java.util.*

class CommonPolylineOptions {
    private val latLngs: MutableList<LatLng> = ArrayList()
    private var width = 0f
    private var color = 0
    private var startCap: CommonCap? = null
    private var endCap: CommonCap? = null
    private var jointType: CommonJointType? = null

    fun width(width: Float): CommonPolylineOptions {
        this.width = width
        return this
    }

    fun color(color: Int): CommonPolylineOptions {
        this.color = color
        return this
    }

    fun add(llngs: Collection<LatLng>): CommonPolylineOptions {
        latLngs.addAll(llngs)
        return this
    }

    fun add(latLng: LatLng): CommonPolylineOptions {
        latLngs.add(latLng)
        return this
    }

    fun startCap(cap: CommonCap): CommonPolylineOptions {
        startCap = cap
        return this
    }

    fun endCap(cap: CommonCap): CommonPolylineOptions {
        endCap = cap
        return this
    }

    fun jointType(type: CommonJointType): CommonPolylineOptions {
        jointType = type
        return this
    }

    fun getLatLngs(): List<LatLng> = latLngs

    fun getHuaweiLatLngs(): List<com.huawei.hms.maps.model.LatLng> {
        val huaweiLatLongs: MutableList<com.huawei.hms.maps.model.LatLng> = ArrayList()
        for (currentLatLng in latLngs) {
            huaweiLatLongs.add(
                com.huawei.hms.maps.model.LatLng(
                    currentLatLng.latitude,
                    currentLatLng.longitude
                )
            )
        }
        return huaweiLatLongs
    }

    fun getWidth(): Float = width

    fun getColor(): Int = color

    fun getStartCap(): CommonCap? = startCap
    fun getEndCap(): CommonCap? = endCap

    fun getJointType(): CommonJointType? = jointType
}
