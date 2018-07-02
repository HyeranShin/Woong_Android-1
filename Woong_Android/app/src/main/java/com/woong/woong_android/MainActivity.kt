package com.woong.woong_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.SubMenu
import android.view.View
import com.woong.woong_android.R
import com.woong.woong_android.home.main.HomeMain
import com.woong.woong_android.myproduct.MyProduct
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onClick(p0: View?) {
        when(p0){
            btn_home_main ->{
                addFragment(HomeMain())
            }
            btn_market_main ->{
                addFragment(com.woong.woong_android.home.submenu.SubMenu())
            }
            btn_myproduct_main ->{
                addFragment(MyProduct())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(HomeMain())

        btn_myproduct_main.setOnClickListener(this)
        btn_home_main.setOnClickListener(this)
        btn_market_main.setOnClickListener(this)

    }

    fun addFragment(fragment: Fragment) {

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()

        transaction.add(R.id.frame_fragment_main,fragment)
        transaction.commit()

    }

}