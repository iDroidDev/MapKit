package idroid.android.mapskit.factory

import android.content.Context
import android.content.ContextWrapper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import idroid.android.mapskit.utils.MapType


abstract class BaseMaps(private val context: Context, val mapType: MapType) : Maps,
    UISettings, MapsLifeCycle {

    private fun getActivity(): FragmentActivity? {
        var context: Context? = this.context
        while (context is ContextWrapper) {
            if (context is FragmentActivity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

    fun replaceFragment(fragment: Fragment?) {
        getActivity()?.let { fragmentActivity ->
            fragment?.let {
                fragmentActivity.supportFragmentManager.beginTransaction()
                    .add(idroid.android.mapskit.R.id.flRootHuaweiGoogleSupportMapFragment, it)
                    .commit()
            }
        }
    }

}