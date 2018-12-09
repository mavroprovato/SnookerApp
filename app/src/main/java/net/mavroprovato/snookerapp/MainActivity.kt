package net.mavroprovato.snookerapp

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.mavroprovato.snookerapp.api.eventsInSeason

/**
 * The main application activity.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Create the activity.
     *
     * @param savedInstanceState The saved bundle instance.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FetchEventsTask().execute(2018)
    }

    /**
     * A asynchronous task that fetches all events in a year.
     */
    inner class FetchEventsTask : AsyncTask<Int, Void, List<String>>() {
        /**
         * Fetch the event data.
         *
         * @param params The years requested. Only the first year provided is taken into accout.
         * @return An array with all the events.
         */
        override fun doInBackground(vararg params: Int?): List<String> {
            return eventsInSeason(params[0])
        }

        /**
         * Called when the data are fetched and sets the text view data.
         *
         * @param result The result array.
         */
        override fun onPostExecute(result: List<String>?) {
            result?.forEach { tv_events.append(it + "\n\n\n") }
        }
    }
}
