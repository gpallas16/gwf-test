package com.exampl.gwftest.util.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@BindingAdapter("listItems")
fun setListItems(recyclerView: RecyclerView, items: List<ListItem>?) {
    var adapter = (recyclerView.adapter as? BindableRecyclerAdapter)
    if (adapter == null) {
        adapter = BindableRecyclerAdapter()
        recyclerView.adapter = adapter
    }
    adapter.submitList(items ?: listOf())
}

@BindingAdapter("itemClickListener")
fun setItemClickListener(
    recyclerView: RecyclerView,
    itemClickListener: BindableRecyclerAdapter.ItemClickListener?
) {
    var adapter = (recyclerView.adapter as? BindableRecyclerAdapter)
    if (adapter == null) {
        adapter = BindableRecyclerAdapter()
        recyclerView.adapter = adapter
    }
    adapter.itemClickListener = itemClickListener
}

@BindingAdapter("geolocation")
fun setItemClickListener(map: MapView, point: GeoPoint?) {
    point?.let {
        val mapController = map.controller
        val startMarker = Marker(map)
        startMarker.position = it
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_TOP);
        map.overlays.add(startMarker)
        mapController.animateTo(it)
    }
}