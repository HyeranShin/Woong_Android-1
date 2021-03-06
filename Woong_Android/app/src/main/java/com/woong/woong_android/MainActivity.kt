package com.woong.woong_android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import com.woong.woong_android.my.MyPage
import com.woong.woong_android.home.main.HomeMain
import com.woong.woong_android.market.Market
import com.woong.woong_android.myproduct.MyProduct
import com.woong.woong_android.notice.Notice
import com.woong.woong_android.woong_usertoken.user_token
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    val bundle = Bundle()

    override fun onClick(p0: View?) {
        when(p0){
            btn_home_main ->{
                clearSelected()
                btn_home_main.isSelected = true
                replaceFragment(HomeMain())
            }
            btn_market_main ->{
                clearSelected()
                btn_market_main.isSelected = true
                bundle.putString("user_token",user_token)
                replaceFragment(Market())
            }
            btn_myproduct_main ->{
                clearSelected()
                btn_myproduct_main.isSelected = true
                replaceFragment(MyProduct())
            }
            btn_notice_main->{
                clearSelected()
                btn_notice_main.isSelected = true
                replaceFragment(Notice())
            }
            btn_my_main ->{
                clearSelected()
                btn_my_main.isSelected = true
                replaceFragment(MyPage())
            }
        }
    }
   var flag : Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var usertoken = woong_usertoken.user_token

        flag = intent.getIntExtra("address_flag",0)
        if(flag == 1){
            var re_address = intent.getStringExtra("search_address")
//            Log.v("주소넘김",re_address)
         //   bundle.putString("re_address",re_address)
            bundle.putInt("flag",1)
            HomeMain().arguments = bundle
        }
        bundle.putString("user_token",usertoken)

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        HomeMain().arguments = bundle
        transaction.add(R.id.frame_fragment_main,HomeMain())
        transaction.commit()


        btn_home_main.isSelected = true
        btn_myproduct_main.setOnClickListener(this)
        btn_home_main.setOnClickListener(this)
        btn_market_main.setOnClickListener(this)
        btn_my_main.setOnClickListener(this)
        btn_notice_main.setOnClickListener(this)
        if(frgIntent.flag==1){
            replaceFragment(MyProduct())
            clearSelected()
            btn_myproduct_main.isSelected = true
        }
    }

    fun addFragment(fragment: Fragment) {

        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        fragment.arguments = bundle
        transaction.add(R.id.frame_fragment_main,fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        fragment.arguments = bundle
        transaction.replace(R.id.frame_fragment_main,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun clearSelected(){
        btn_myproduct_main.isSelected = false
        btn_home_main.isSelected = false
        btn_market_main.isSelected = false
        btn_my_main.isSelected = false
        btn_notice_main.isSelected = false
    }

}
