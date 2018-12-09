package net.mavroprovato.snookerapp.api

import android.net.Uri
import android.util.Log
import org.json.JSONArray
import java.lang.Exception
import java.net.URL

const val API_BASE = "http://api.snooker.org/"
const val EVENTS_IN_SEASON_TYPE = "5"

class SnookerOrgApi {

    companion object {
        /**
         * Return the events in a season.
         *
         * @param year The event year
         * @return The list of events.
         */
        fun eventsInSeason(year: Int): List<String>? {
            try {
                val uri = Uri.parse(API_BASE).buildUpon()
                    .appendQueryParameter("t", EVENTS_IN_SEASON_TYPE)
                    .appendQueryParameter("s", year.toString())
                    .build()
                val result = URL(uri.toString()).readText()
                val json = JSONArray(result)
                val events: MutableList<String> = ArrayList()

                for (i in 0 until json.length()) {
                    val item = json.getJSONObject(i)
                    events.add(item.getString("Name"))
                }

                return events
            } catch (e: Exception) {
                Log.e("SnookerOrgAPI", "exception", e)
                return null
            }
        }
    }
}