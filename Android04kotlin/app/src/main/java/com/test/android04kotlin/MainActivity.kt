package com.test.android04kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //type >> Int,Byte,Short,Char,Long,Float,Boolean
        val su:Int = 10000
        //print(su)
        val str:String = "kim"
        val d:Double = 3.14
        val c:Char = 'A'
        val l:Long = 222222222222222222L
        val names:StringBuffer = StringBuffer()
        names.append("aaa")

        val sus:IntArray = intArrayOf(1,2,3)
        for (x in sus)
            Log.i("testLog","x:"+x)

        val strs = arrayOf("aaa","bbb")

        if (str==="kim")
            Log.i("testLog","hello")

        if (c in 'A'..'Z')
            Log.i("testLog","big eng")

        tv.text = "aaaaaaaaaaaa"
        tv.setOnClickListener(View.OnClickListener {
            Log.i("testLog","tv click")
        })

        button.setOnClickListener(View.OnClickListener {
            Log.i("testLog","button click")
            startActivity(
                    Intent(
                            applicationContext,
                            Main2Activity::class.java))
            test(3333)
        })

    }

    fun test(c:Int){
        Log.i("testLog","c:"+c)
    }

}

