package com.rachel.dzikirapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rachel.dzikirapp.R
import com.rachel.dzikirapp.adapter.DzikirDoaAdapter
import com.rachel.dzikirapp.model.DataDzikirDoa
import com.rachel.dzikirapp.model.DzikirDoa

class QauliyahActivity : AppCompatActivity() {
    private lateinit var rvQauliyah : RecyclerView
    private var listsQauliyah: ArrayList<DzikirDoa> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qauliyah)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        rvQauliyah = findViewById(R.id.rvQauliyah)
        listsQauliyah.clear()
        listsQauliyah.addAll(DataDzikirDoa.listQauliyah)

        rvQauliyah.layoutManager = LinearLayoutManager(this)
        rvQauliyah.adapter = DzikirDoaAdapter(listsQauliyah)
        rvQauliyah.setHasFixedSize(true)
    }
}