package idroid.android.mapskit.ui

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import idroid.android.mapskit.R
import idroid.android.mapskit.factory.MapFactory
import idroid.android.mapskit.factory.Maps
import idroid.android.mapskit.factory.MapsLifeCycle
import idroid.android.mapskit.utils.CheckServiceAvaiable
import idroid.android.mapskit.utils.MapType

class HuaweiGoogleSupportMapFragment(context: Context, attrs: AttributeSet?) :
    RelativeLayout(context, attrs) {

    private lateinit var myMaps: Maps
    private val distributeType = CheckServiceAvaiable.getAvailableService(context)

    init {
        inflateLayout()
    }

    private fun inflateLayout() {
        View.inflate(context, R.layout.huawei_google_support_map_fragment, this)
        myMaps =
            MapFactory.createAndGetMap(context, distributeType, MapType.MAP_FRAGMENT)
    }

    fun onCreate(bundle: Bundle) {
        myMaps.onCreate(bundle)
    }

    fun getMapAsync(onMapAsyncListener: (map: Maps) -> Unit) {
        myMaps.getMapAsync(onMapAsyncListener)
    }

    fun onEnterAmbient(bundle: Bundle?) {
        (myMaps as MapsLifeCycle).onEnterAmbient(bundle)
    }

    fun onStart() {
        (myMaps as MapsLifeCycle).onStart()
    }

    fun onResume() {
        (myMaps as MapsLifeCycle).onResume()
    }

    fun onPause() {
        (myMaps as MapsLifeCycle).onPause()
    }

    fun onStop() {
        (myMaps as MapsLifeCycle).onStop()
    }

    fun onDestroy() {
        (myMaps as MapsLifeCycle).onDestroy()
    }

    fun onLowMemory() {
        (myMaps as MapsLifeCycle).onLowMemory()
    }

}
