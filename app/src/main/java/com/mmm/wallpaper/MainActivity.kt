package com.mmm.wallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.mmm.wallpaper.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: WallpaperAdapter
    var auth = "tJCyTiaz7mjtevZkfdemL7rGVEkUnNpcumf60Stl3KTX4gL32sW6r77G"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = WallpaperAdapter()
        binding.btnSubmit.setOnClickListener {
            callApi()
        }
    }

    private fun callApi() {
        var txt = binding.edtSearch.text.toString()

        var api: APIinterface = ApiClient.getApiClient().create(APIinterface::class.java)
        api.getPhotos(auth,txt).enqueue(object : Callback<PhotoModel> {
            override fun onResponse(call: Call<PhotoModel>, response: Response<PhotoModel>) {
                if (response.isSuccessful) {
                    var photos = response.body()?.photos
                    adapter.setPhotos(photos as List<PhotosItem>?)
                    binding.rcvView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                    binding.rcvView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<PhotoModel>, t: Throwable) {

            }
        })
    }
}