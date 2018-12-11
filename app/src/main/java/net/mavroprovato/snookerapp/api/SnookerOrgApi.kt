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
        /** The logging tag */
        private const val TAG = "SnookerOrgApi"

        /**
         * Return the events in a season.
         *
         * @param year The event year
         * @return The list of events or null if an error has occurred.
         */
        fun eventsInSeason(year: Int): List<String>? {
            try {
                val uri = Uri.parse(API_BASE).buildUpon()
                    .appendQueryParameter("t", EVENTS_IN_SEASON_TYPE)
                    .appendQueryParameter("s", year.toString())
                    .build()
                Log.i(TAG, "Fetching events")
                val result = URL(uri.toString()).readText()
                Log.e(TAG, "Events fetched")
                val json = JSONArray(result)
                val events: MutableList<String> = ArrayList()

                for (i in 0 until json.length()) {
                    val item = json.getJSONObject(i)
                    events.add(item.getString("Name"))
                }

                return events
            } catch (e: Exception) {
                Log.e(TAG, "exception", e)
                return null
            }
        }
    }
}