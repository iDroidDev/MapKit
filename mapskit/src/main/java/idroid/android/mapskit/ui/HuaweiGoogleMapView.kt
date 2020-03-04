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
import idroid.android.mapskit.listener.OnMapMarkerClickListener
import idroid.android.mapskit.listener.OnMapReadyListener
import idroid.android.mapskit.utils.CheckServiceAvaiable
import kotlinx.android.synthetic.main.huawei_google_map_view.view.*

class HuaweiGoogleMapView(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs),
    OnMapReadyListener {
    private lateinit var myMaps: Maps
    private lateinit var onMapAsyncListener: (HuaweiGoogleMapView) -> Unit

    init {
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

    fun getMapAsync(onMapAsyncListener: (HuaweiGoogleMapView) -> Unit) {
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
        onMapAsyncListener.invoke(this)
    }

}