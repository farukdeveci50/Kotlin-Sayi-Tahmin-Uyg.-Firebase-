package com.ornek.sayitahminyarismasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sonuc.*

class SonucActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sonuc)


        val sonuc = intent.getBooleanExtra("sonuc",false)                                 //Tahmin sayfasından verilermizi çekiyoruz.
        val puan = intent.getIntExtra("puan",0)
        val uretilenSayi = intent.getIntExtra("uretilenSayi",0)
        var gelenKullaniciAdi = intent.getStringExtra("kullaniciAdi")                              //Kullanıcı adını çektik.

        var database = Firebase.database.reference


        if(sonuc){

            textViewSonuc.text = "SAYIYI BİLDİNİZ"
            imageViewSonuc.setImageResource(R.drawable.mutlu_resim)
            //textViewUretilenSayi.setText(kullaniciAdi.toString())
            textViewUretilenSayi.text = "Puanınız : $puan" +
                    ""

        }else{
            textViewSonuc.text = "SAYIYI BİLEMEDİNİZ"
            imageViewSonuc.setImageResource(R.drawable.uzgun_resim)
            textViewUretilenSayi.text = "$uretilenSayi"

        }


        buttonTekrar.setOnClickListener {
            var kkullaniciadi=gelenKullaniciAdi.toString()          //VERİ EKLEDİK
            var kpuan=puan
            database.child(kkullaniciadi).setValue(Kisiler(kpuan))

            finish()
            startActivity(Intent(this@SonucActivity,TahminActivity::class.java))

        }

        buttonBasaDon.setOnClickListener {

            var kkullaniciadi=gelenKullaniciAdi.toString()          //VERİ EKLEDİK
            var kpuan=puan
            database.child(kkullaniciadi).setValue(Kisiler(kpuan))
            finish()
            startActivity(Intent(this@SonucActivity,MainActivity::class.java))

        }


    }
}