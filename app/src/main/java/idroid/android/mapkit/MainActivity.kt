package idroid.android.mapkit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mapViewBundle = savedInstanceState?.getBundle("MapViewBundleKey")
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            savedInstanceState?.putBundle("MapViewBundleKey", mapViewBundle)
        }

        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync {
            it.addMarker("Marker", "Snippet", 41.000000F, 29.00000F)
            it.moveCamera(41.000000F, 29.00000F, 20f)
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }
}
