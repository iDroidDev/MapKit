package idroid.android.mapskit.ui

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import idroid.android.mapskit.R
import idroid.android.mapskit.factory.MapFactory
import idroid.android.mapskit.factory.Maps
import idroid.android.mapskit.listener.OnMapAsyncListener
import idroid.android.mapskit.listener.OnMapMarkerClickListener
import idroid.android.mapskit.listener.OnMapReadyListener
import idroid.android.mapskit.utils.CheckServiceAvaiable
import kotlinx.android.synthetic.main.huawei_google_map_view.view.*

class HuaweiGoogleMapView : RelativeLayout, OnMapReadyListener {
    internal var context: Context
    internal lateinit var myMaps: Maps
    internal lateinit var onMapAsyncListener: OnMapAsyncListener

    constructor(context: Context) : super(context) {
        this.context = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.context = context
        inflateLayout()
    }

    private fun inflateLayout() {
        View.inflate(context, R.layout.huawei_google_map_view, this)
        myMaps =
            MapFactory.createAndGetMap(context, CheckServiceAvaiable.getAvailableService(context))!!

        myMaps.getMapView().layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        rlRootHuaweiGoogleMapView.addView(myMaps.getMapView())
    }

    fun onCreate(bundle: Bundle) {
        myMaps.onCreate(bundle)
    }

    fun getMapAsync(onMapAsyncListener: OnMapAsyncListener) {
        this.onMapAsyncListener = onMapAsyncListener
        myMaps.getMapAsync(this)
    }

    fun addMarker(title: String, snippet: String, latitude: Float, longitude: Float) {
        myMaps.addMarker(title, snippet, latitude, longitude)
    }

    fun setOnInfoWindowClickListener(onMapMarkerClickListener: OnMapMarkerClickListener) {
        myMaps.setOnInfoWindowClickListener(onMapMarkerClickListener)
    }

    fun moveCamera(latitude: Float, longitude: Float, zoomRatio: Float) {
        myMaps.moveCamera(latitude, longitude, zoomRatio)
    }

    fun animateCamera(latitude: Float, longitude: Float, zoomRatio: Float) {
        myMaps.animateCamera(latitude, longitude, zoomRatio)
    }

    fun setMyLocationEnabled(myLocationEnabled: Boolean) {
        myMaps.setMyLocationEnabled(myLocationEnabled)
    }

    fun clear() {
        myMaps.clear()
    }

    fun onSaveInstanceState(bundle: Bundle): Parcelable? {
        myMaps.onSaveInstanceState(bundle)
        return super.onSaveInstanceState()
    }

    fun onStart() {
        myMaps.onStart()
    }

    fun onStop() {
        myMaps.onStop()
    }

    fun onPause() {
        myMaps.onPause()
    }

    fun onResume() {
        myMaps.onResume()
    }

    fun onDestroy() {
        myMaps.onDestroy()
    }

    fun onLowMemory() {
        myMaps.onLowMemory()
    }

    override fun onMapReady() {
        onMapAsyncListener.onMapReady(this)
    }

}