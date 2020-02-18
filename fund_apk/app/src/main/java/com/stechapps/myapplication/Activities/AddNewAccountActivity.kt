package com.stechapps.myapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.stechapps.myapplication.Api.APIinterface
import com.stechapps.myapplication.R
import com.stechapps.myapplication.models.APIaddressrandom
import kotlinx.android.synthetic.main.activity_add_new_account.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddNewAccountActivity : AppCompatActivity() {
lateinit var db:FirebaseFirestore
    val apiServe by lazy{
        APIinterface.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_account)
        db= FirebaseFirestore.getInstance()






    }

    fun submitAccFunder(view: View) {
        val m= HashMap<String,String>()
        val call=apiServe.getRandomAddress().enqueue(object : Callback<APIaddressrandom> {
            override fun onFailure(call: Call<APIaddressrandom>, t: Throwable) {
                Toast.makeText(applicationContext," something went wrong",Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(
                call: Call<APIaddressrandom>,
                response: Response<APIaddressrandom>
            ) {
                if(response.body()?.response!!){
                    Toast.makeText(applicationContext,response.body()!!.account.toString(),Toast.LENGTH_SHORT).show()


                    m.put("account", response.body()!!.account)

                    m.put("name",ed_new_acc_name.text.toString())
                    db.collection("Accounts").document(ed_new_acc_name.text.toString()).set(m as Map<String, Any>)
                        .addOnSuccessListener {
                            Toast.makeText(applicationContext," You are ready to except money ",Toast.LENGTH_SHORT).show()

                            finish()
                        }.addOnFailureListener {
                            Toast.makeText(applicationContext," something went wrong",Toast.LENGTH_SHORT).show()
                            Log.d("<<<<<<.>>>>>>>>>>",it.toString())
                        }

                }
                else{
                    Toast.makeText(applicationContext," rand address not fetched ",Toast.LENGTH_SHORT).show()

                }
            }
        }
        )


    }


}
