package com.rachel.dzikirapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.rachel.dzikirapp.model.Artikel
import com.rachel.dzikirapp.ui.DoaHarianActivity
import com.rachel.dzikirapp.ui.PagiPetangDzikirActivity
import com.rachel.dzikirapp.ui.QauliyahActivity
import com.rachel.dzikirapp.ui.SetiapSaatDzikirActivity
import com.rachel.dzikirapp.ui.detail.DetailArtikelActivity



class MainActivity : AppCompatActivity() {
    private lateinit var llDzikirDoaHarian : LinearLayout
    private lateinit var llDzikirDoaSholat : LinearLayout
    private lateinit var llDzikirSetiapSaat : LinearLayout
    private lateinit var llDzikirPagiPetang : LinearLayout
    private lateinit var llDotSlider : LinearLayout
    private lateinit var vpArtiker : ViewPager2

    private var dotsCount = 0
    private var artikelData: ArrayList<Artikel> = arrayListOf()
    private lateinit var dotsSlider : Array<ImageView?>

    private val slidingCallBack = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            for (dot in 0 until dotsCount){
                dotsSlider[dot]?.setImageDrawable(ContextCompat.getDrawable(
                    applicationContext, R.drawable.non_active_dot
                ))
            }
            dotsSlider[position]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext, R.drawable.active_dot)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        inittView()
        initData()
        setUpViewPager()
    }

    private fun setUpViewPager() {
        val artikelAdapter = ArtikelAdapter(artikelData)
        artikelAdapter.setOnItemClickCallback(object : OnItemClickCallback{
            override fun onItemCliked(data: Artikel) {

            }

        })
        vpArtiker.apply {
            adapter = artikelAdapter
            registerOnPageChangeCallback(slidingCallBack)

        }
        dotsCount = artikelData.size
        dotsSlider = arrayOfNulls(dotsCount)

        for(i in 0 until dotsCount){
            dotsSlider[i] = ImageView(this)
            dotsSlider[i]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext,R.drawable.non_active_dot)
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8,0,8,0)
            llDotSlider.addView(dotsSlider[i],params)
        }

        dotsSlider[0]?.setImageDrawable(
            ContextCompat.getDrawable(applicationContext,R.drawable.active_dot)
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        vpArtiker.unregisterOnPageChangeCallback(slidingCallBack)
    }

    private fun initData() {
        val image = resources.obtainTypedArray(R.array.img_artikel)
        val title = resources.getStringArray(R.array.title_artikel)
        val desc = resources.getStringArray(R.array.desc_artikel)
        artikelData.clear()
        for (data in title.indices){
            artikelData.add(
                Artikel(
                image.getResourceId(data,0),
                    title[data],
                    desc[data]
            )
            )
        }
        image.recycle()

    }

    private fun inittView() {
        llDzikirDoaSholat = findViewById(R.id.llDzikirDoaSholat)
        llDzikirDoaSholat.setOnClickListener {
            startActivity(Intent(this, QauliyahActivity::class.java))
        }

        llDzikirDoaHarian = findViewById(R.id.llDzikirDoaHarian)
        llDzikirDoaHarian.setOnClickListener {  }

        llDzikirSetiapSaat = findViewById(R.id.llDzikirSetiapSaat)
        llDzikirSetiapSaat.setOnClickListener {  }

        llDzikirPagiPetang = findViewById(R.id.llDzikirPagiPetang)
        llDzikirPagiPetang.setOnClickListener {  }

        llDotSlider = findViewById(R.id.llDotSlider)
        vpArtiker = findViewById(R.id.vpArtiker)
    }
}