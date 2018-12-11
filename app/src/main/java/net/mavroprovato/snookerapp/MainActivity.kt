package net.mavroprovato.snookerapp

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.mavroprovato.snookerapp.adapter.EventsAdapter
import net.mavroprovato.snookerapp.api.SnookerOrgApi

/**
 * The main application activity.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var eventsAdapter: EventsAdapter

    /**
     * Create the activity.
     *
     * @param savedInstanceState The saved bundle instance.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_events.layoutManager = LinearLayoutManager(this)
        eventsAdapter = EventsAdapter()
        rv_events.adapter = eventsAdapter

        fetchEvents()
    }

    /**
     * Fetch the events
     */
    private fun fetchEvents() {
        showEvents()
        FetchEventsTask().execute(2018)
    }

    /**
     * Make the events view visible and hide the error view.
     */
    private fun showEvents() {
        rv_events.visibility = View.VISIBLE
        tv_error_message_display.visibility = View.INVISIBLE
    }

    /**
     * Hide the events view and make the error view visible.
     */
    private fun showErrors() {
        rv_events.visibility = View.INVISIBLE
        tv_error_message_display.visibility = View.VISIBLE
    }

    /**
     * Create the options menu.
     *
     * @param menu The menu.
     * @return True if the menu is to be displayed.
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.events, menu)

        return true
    }

    /**
     * Called when a menu item is selected.
     *
     * @param item The menu item.
     * @return True if we are processing the event, false otherwise.
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_refresh -> {
                eventsAdapter.eventData = listOf()
                fetchEvents()

                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     * A asynchronous task that fetches all events in a year.
     */
    inner class FetchEventsTask : AsyncTask<Int, Void, List<String>>() {

        override fun onPreExecute() {
            super.onPreExecute()

            pb_loading_indicator.visibility = View.VISIBLE
        }

        /**
         * Fetch the event data.
         *
         * @param params The years requested. Only the first year provided is taken into accout.
         * @return An array with all the events.
         */
        override fun doInBackground(vararg params: Int?): List<String>? {
            val year = params[0]
            return if (year == null) null else SnookerOrgApi.eventsInSeason(year)
        }

        /**
         * Called when the data are fetched and sets the text view data.
         *
         * @param result The result array.
         */
        override fun onPostExecute(result: List<String>?) {
            pb_loading_indicator.visibility = View.INVISIBLE
            if (result == null) {
                showErrors()
            } else {
                eventsAdapter.eventData = result
                showEvents()
            }
        }
    }
}
