package com.ornek.sayitahminyarismasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.FirebaseDatabaseKtxRegistrar
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ornek.sayitahminyarismasi.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tahmin.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttonBasla.setOnClickListener {
            if (editTextKullanici.getText().toString().equals("")) {
                Toast.makeText(this, "Lütfen kullanıcı adınızı giriniz ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var kullaniciAdi = editTextKullanici.text.toString()

            var yeniIntent = Intent(this@MainActivity,TahminActivity::class.java)
            yeniIntent.putExtra("kullaniciAdi",kullaniciAdi)
            startActivity(yeniIntent)

            //intent.putExtra("kullaniciAdi",kullaniciAdi)
            //startActivity(Intent(this@MainActivity,TahminActivity::class.java))     //Sayfalar arası geçiş

        }
        var database = Firebase.database.reference
        //VERİ OKUMA KISMI
        var getdata = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                for(i in snapshot.children){
                    var kpuan = i.child("kpuan").getValue()
                    sb.append("${i.key} $kpuan \n")
                }
                textViewSkorlar.setText(sb)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        database.addValueEventListener(getdata)
        database.addListenerForSingleValueEvent(getdata)

    }
}