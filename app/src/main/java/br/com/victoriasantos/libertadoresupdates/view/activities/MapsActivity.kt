package br.com.victoriasantos.libertadoresupdates.view.activities

import android.content.Context
import android.content.DialogInterface
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.viewmodel.FirebaseViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_maps.*
import java.io.IOException


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private val viewModel: FirebaseViewModel by lazy {
        ViewModelProvider(this).get(FirebaseViewModel::class.java)
    }
    private var showManual: String = "ShowManual"
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        manual()


    }

        private fun manual(){
            val sharedPref = this.getPreferences(Context.MODE_PRIVATE)

            if(sharedPref.getBoolean(showManual,true )){
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Mapa ChampionsUpdates")
                builder.setMessage("Você está vendo o estádio do atual campeão! Navege pelo mapa para descobrir onde estão localizados os estádios de todos os times que participaram/participam do torneio dessa temporada! Clique no botão de centralizar mapa para ver os outros marcadores, eles indicam os estadios!")
                builder.apply {
                    setPositiveButton("OK, ENTENDI", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, which: Int) {
                        }
                    })
                    setNegativeButton("NÃO MOSTRAR NOVAMENTE", object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            sharedPref.edit().putBoolean(showManual, false).apply()

                        }
                    })
                    builder.show()
                }
            }
        }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        getMarkers()
        val center = LatLng(53.430983, -2.960809)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 17F))

        btn_center.setOnClickListener {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(LatLng(47.751569,1.675063), 0.0F, 0.0F, 3F)))
        }

    }

    private fun getMarkers() {
        viewModel.getMarkers { markers ->
            markers?.forEach { m ->
                val markerOptions = MarkerOptions().position(LatLng(m.latitude!!, m.longitude!!))
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                markerOptions.title(getAddress(LatLng(m.latitude!!, m.longitude!!)).toString())
                markerOptions.snippet("Time: ${m.nome}\n")

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
            Log.e("MapsActivity", e.localizedMessage!!)
        }
        if (addresses != null) {
            address1 = addresses[0].getAddressLine(0)
        }

        return address1
    }


    override fun onMarkerClick(p0: Marker?): Boolean {
        return true
    }

}
