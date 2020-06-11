package br.com.victoriasantos.libertadoresupdates.view.activities

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import br.com.victoriasantos.libertadoresupdates.R
import br.com.victoriasantos.libertadoresupdates.viewmodel.FirebaseViewModel
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
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
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationRequest: LocationRequest
    private var locationUpdateState = false
    val LOCATION_PERMISSION_REQUEST_CODE = 1
    val REQUEST_CHECK_SETTINGS = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        manual()
        locationCallback = object : LocationCallback() {
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        createLocationRequest()
    }

        private fun manual(){
            val sharedPref = this.getPreferences(Context.MODE_PRIVATE)

            if(sharedPref.getBoolean(showManual,true )){
                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.title))
                builder.setMessage(getString(R.string.manual))
                builder.apply {
                    setPositiveButton(getString(R.string.ok), object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, which: Int) {
                        }
                    })
                    setNegativeButton(getString(R.string.not_show_again), object : DialogInterface.OnClickListener {
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
        setUpMap()
        btn_center.setOnClickListener {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition(LatLng(47.751569,1.675063), 0.0F, 0.0F, 3F)))
        }
        getMarkers()
        val center = LatLng(53.430983, -2.960809)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 17F))

    }

    fun setUpMap(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        GPSlocation()
    }

    private fun GPSlocation() {
        mMap.isMyLocationEnabled = true

        fusedLocationClient.lastLocation.addOnSuccessListener(this) {
        }

    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 5000
        locationRequest.smallestDisplacement = 5.0F
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())


        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }

        task.addOnFailureListener { e ->
            if (e is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    e.startResolutionForResult(
                        this@MapsActivity,
                        REQUEST_CHECK_SETTINGS
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null /* Looper */)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                locationUpdateState = true
                startLocationUpdates()
            }
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


    public override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }

    public override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }


    override fun onMarkerClick(p0: Marker?): Boolean {
        return true
    }

}
