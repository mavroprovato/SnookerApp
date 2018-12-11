package net.mavroprovato.snookerapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.events_list_item.view.*
import net.mavroprovato.snookerapp.R
import net.mavroprovato.snookerapp.adapter.EventsAdapter.EventsAdapterViewHolder

/**
 * Provide binding to the event recycler view.
 */
class EventsAdapter : RecyclerView.Adapter<EventsAdapterViewHolder>() {

    /** The event information */
    var eventData: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * Describe the event view about its place in the recycler view
     */
    inner class EventsAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvEventData = itemView.tv_event_data!!
    }

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.events_list_item, parent, false)

        return EventsAdapterViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method updatea the contents of the
     * itemView to reflect the item at the given position.
     *
     * @param holder ViewHolder which should be updated to represent the contents of the item at the given position in
     * the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: EventsAdapterViewHolder, position: Int) {
        holder.tvEventData.text = eventData[position]
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return eventData.size
    }
}