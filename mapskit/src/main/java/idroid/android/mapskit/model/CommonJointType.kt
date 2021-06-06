package idroid.android.mapskit.model

import com.google.android.gms.maps.model.JointType

/**
 * Specifies a common model for setting a polyline joint type.
 * @author Arnau Mora
 * @since 20210602
 */
enum class CommonJointType {
    BEVEL, DEFAULT, ROUND;

    /**
     * Returns the joint type in the Google's format.
     * @author Arnau Mora
     * @since 20210602
     */
    fun google(): Int =
        when {
            this == BEVEL -> JointType.BEVEL
            this == ROUND -> JointType.ROUND
            else -> JointType.DEFAULT
        }

    /**
     * Returns the joint type in the Huawei's format.
     * @author Arnau Mora
     * @since 20210602
     */
    fun hms(): Int =
        when {
            this == BEVEL -> com.huawei.hms.maps.model.JointType.BEVEL
            this == ROUND -> com.huawei.hms.maps.model.JointType.ROUND
            else -> com.huawei.hms.maps.model.JointType.DEFAULT
        }
}
