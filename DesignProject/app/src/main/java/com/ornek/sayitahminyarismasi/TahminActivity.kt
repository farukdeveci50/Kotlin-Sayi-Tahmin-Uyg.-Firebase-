package com.ornek.sayitahminyarismasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tahmin.*
import kotlin.random.Random

class TahminActivity : AppCompatActivity() {

    private var rastgeleSayi = 0
    private var sayac = 10
    private var puan= 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tahmin)
        var gelenKullaniciAdi = intent.getStringExtra("kullaniciAdi")   //Sayfalar arası kullanıcı adı aktaraımı yapıyoruz.


        rastgeleSayi = Random.nextInt(101)  //0 ile 100 arasında sayı ürettik.
        Log.e("Rastgele Sayı", rastgeleSayi.toString())

        buttonTahmin.setOnClickListener {

            if (editTextGirdi.getText().toString().equals("")) {
                Toast.makeText(this, "Lütfen sayı giriniz.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            sayac--


            val tahmin = editTextGirdi.text.toString().toInt()

            if(tahmin == rastgeleSayi) {
                val intent = Intent(this@TahminActivity,SonucActivity::class.java)

                intent.putExtra("sonuc",true)       //Kazandı bilgisini gönderiyoruz.
                intent.putExtra("kullaniciAdi",gelenKullaniciAdi)
                puan = (sayac + 1) * 10
                intent.putExtra("puan",puan)

                finish()
                startActivity(intent)
                return@setOnClickListener    //Son hakta bilinmesine rağmen kaybettiniz kısmını önlüyoruz.

            }

            if(tahmin > rastgeleSayi) {
                textViewYardim.setText("Tahmininizi Azaltınız")
                textViewKalanHak.setText("Kalan Hak : $sayac")
            }

            if(tahmin < rastgeleSayi){
                textViewYardim.setText("Tahmininizi Arttırınız")
                textViewKalanHak.setText("Kalan Hak : $sayac")
            }

            if(sayac == 0){
                val intent = Intent(this@TahminActivity,SonucActivity::class.java)
                puan=0
                intent.putExtra("puan",puan)
                intent.putExtra("sonuc",false)           //Kaybetti bilgisini gönderiyoruz.
                intent.putExtra("kullaniciAdi",gelenKullaniciAdi)
                intent.putExtra("uretilenSayi",rastgeleSayi)  //Rastgele üretilmiş olan sayıyı gönderiyoruz.
                finish()
                startActivity(intent)
            }

            editTextGirdi.setText("")


        }
    }
}