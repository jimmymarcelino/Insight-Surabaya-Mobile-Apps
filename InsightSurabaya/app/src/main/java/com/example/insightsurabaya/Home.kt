package com.example.insightsurabaya

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.insightsurabaya.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage


class Home : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        val storageRef =FirebaseStorage.getInstance()

        var imageSlider = findViewById<ImageSlider>(R.id.carosel)
        var slidemodel = ArrayList<SlideModel>()
        var box = findViewById<CardView>(R.id.cardView)
        var test = findViewById<EditText>(R.id.searchmuseum)

        slidemodel.add(SlideModel(R.drawable.kapalselam, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.patung, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.sampoerna, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.wr, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.sepuluhnov, scaleType = ScaleTypes.FIT))
        slidemodel.add(SlideModel(R.drawable.siola, scaleType = ScaleTypes.FIT))

        imageSlider.setImageList(slidemodel, ScaleTypes.FIT)
        print(getUserLocation(this))
        getUserLocation(this);
        test.setOnClickListener{
            val intent = Intent(this@Home,maps_testing::class.java)
            startActivity(intent)
        }



    }
    fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Float {
        val location1 = Location("")
        location1.latitude = lat1
        location1.longitude = lon1

        val location2 = Location("")
        location2.latitude = lat2
        location2.longitude = lon2

        val distance = FloatArray(1)
        Location.distanceBetween(
            location1.latitude, location1.longitude,
            location2.latitude, location2.longitude, distance
        )

        return distance[0]
    }
    private fun getUserLocation(context: Context) {
        val locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager

        // Check if the user has granted permission to access their location. If they haven't, request it:
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1)
            return
        }

        // Define a LocationListener object to receive location updates:
        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                // This method will be called when the user's location changes
                val latitude = location.latitude
                val longitude = location.longitude
                // Do something with the location data

                print(calculateDistance(-7.33628,112.7229,latitude,longitude))
                val distance = calculateDistance(-7.33628,112.7229,latitude,longitude)
                Log.d("Distance", "Distance between the two locations is: $distance meters")
                Log.d("test", "Latitude: " + latitude + " Longitude: " + longitude);
                Log.d("Test", calculateDistance(-7.33628,112.722,latitude,longitude).toString())

            }



            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

//            override fun onProviderEnabled(provider: String?) {}
//
//            override fun onProviderDisabled(provider: String?) {}
        }

        // Register the LocationListener with the LocationManager and request location updates:
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
    }

}