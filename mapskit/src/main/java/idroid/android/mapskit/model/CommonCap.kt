package idroid.android.mapskit.model

import com.google.android.gms.maps.model.ButtCap
import com.google.android.gms.maps.model.Cap
import com.google.android.gms.maps.model.RoundCap
import com.google.android.gms.maps.model.SquareCap

/**
 * Represents a polyline starting and ending line cap.
 * TODO: Custom Caps
 * @author Arnau Mora
 * @since 20210602
 */
enum class CommonCap {
    BUTT, ROUND, SQUARE;

    /**
     * Returns the [CommonCap] with the Google class implementation.
     * @author Arnau Mora
     * @since 20210602
     */
    fun googleCap(): Cap? =
        when {
            this == BUTT -> ButtCap()
            this == ROUND -> RoundCap()
            this == SQUARE -> SquareCap()
            else -> null
        }

    /**
     * Returns the [CommonCap] with the Huawei class implementation.
     * @author Arnau Mora
     * @since 20210602
     */
    fun hmsCap(): com.huawei.hms.maps.model.Cap? =
        when {
            this == BUTT -> com.huawei.hms.maps.model.ButtCap()
            this == ROUND -> com.huawei.hms.maps.model.RoundCap()
            this == SQUARE -> com.huawei.hms.maps.model.SquareCap()
            else -> null
        }
}
