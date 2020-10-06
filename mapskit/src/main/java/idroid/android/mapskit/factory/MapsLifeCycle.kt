package idroid.android.mapskit.factory

import android.os.Bundle

interface MapsLifeCycle {
    fun onEnterAmbient(bundle: Bundle?)
    fun onSaveInstanceState(bundle: Bundle)
    fun onStart()
    fun onResume()
    fun onPause()
    fun onStop()
    fun onDestroy()
    fun onLowMemory()
}