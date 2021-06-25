package com.vcorreia.liteplayer

import java.io.Serializable

/**
 * Movie class represents video entity with title, description, image thumbs and video url.
 */
data class ScheduleEvent(
        var title: String? = null
) : Serializable {

    companion object {
        internal const val serialVersionUID = 727566175075960653L
    }

    override fun toString(): String {
        return "ScheduleEvent(title=$title)"
    }
}