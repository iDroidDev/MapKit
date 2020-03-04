package idroid.android.mapkit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle("MapViewBundleKey")
        }
        maps.onCreate(mapViewBundle!!)

        maps.getMapAsync {
            it.setMyLocationEnabled(true)
            it.moveCamera(41.000000F, 29.00000F, 20f)
        }
    }
}
