package com.vcorreia.liteplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*

/**
 * Loads a grid of cards with movies to browse.
 */
class MainFragment : BrowseSupportFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onViewCreated(view, savedInstanceState)

        setupUIElements()

        loadRows()

        setupEventListeners()
    }

    private fun setupUIElements() {
        // over title
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = false
    }

    private fun loadRows() {
        val list = listOf(
                ScheduleEvent("Test1", "http://bofh.nikhef.nl/events/FOSDEM/2021/D.openjdk/modernjava.webm"),
                ScheduleEvent("Test2", "https://ftp.fau.de/fosdem/2021/D.openjdk/jakartaee9beyond.webm"))

        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        val cardPresenter = CardPresenter()

        for (i in 0 until NUM_ROWS) {
            val listRowAdapter = ArrayObjectAdapter(cardPresenter)
            listRowAdapter.add(list[i])
            val header = HeaderItem(i.toLong(), "26-06-2021")
            rowsAdapter.add(ListRow(header, listRowAdapter))
        }

        adapter = rowsAdapter
    }

    private fun setupEventListeners() {
        setOnSearchClickedListener {
            Toast.makeText(context, "Implement your own in-app search", Toast.LENGTH_LONG)
                    .show()
        }

        onItemViewClickedListener = ItemViewClickedListener()
    }

    private inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
                itemViewHolder: Presenter.ViewHolder,
                item: Any,
                rowViewHolder: RowPresenter.ViewHolder,
                row: Row) {

            Log.d(TAG, "Item: " + item.toString())
            val intent = Intent(context, PlaybackActivity::class.java)
            intent.putExtra("EVENT_URL", (item as ScheduleEvent).url)

            activity!!.startActivity(intent)
        }
    }

    companion object {
        private val TAG = "MainFragment"

        private val NUM_ROWS = 2
    }
}