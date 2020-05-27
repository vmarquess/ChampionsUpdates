package br.com.victoriasantos.libertadoresupdates.view.activities

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.viewmodel.FirebaseViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.io.IOException

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private val viewModel: FirebaseViewModel by lazy {
        ViewModelProvider(this).get(FirebaseViewModel::class.java)
    }

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        getMarkers()
        val center = LatLng(47.751569,1.675063)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center))

    }

    private fun getMarkers() {
        viewModel.getMarkers { markers ->
            markers?.forEach { m ->
                val markerOptions = MarkerOptions().position(LatLng(m.latitude!!, m.longitude!!))
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                markerOptions.title(getAddress(LatLng(m.latitude!!, m.longitude!!)).toString())
                markerOptions.snippet("Time: ${m.nome}\n Estádio: ${m.estadio}")
                mMap.addMarker(markerOptions)

            }
        }
    }

    private fun getAddress(latLng: LatLng): String? {

        val geocoder = Geocoder(this)
        var addresses: List<Address>? = null
        var address1: String? = null

        try {
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }
        if (addresses != null) {
            address1 = addresses[0].getAddressLine(0)
        }

        return address1
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p0.position, 15F))
        return true
    }
}
