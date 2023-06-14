package com.ubaya.adv160420013week4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.adv160420013week4.model.Student

//class DetailViewModel: ViewModel() {
class DetailViewModel(application: Application): AndroidViewModel(application) {
    val studentLD = MutableLiveData<Student>()

    val TAG = "volleyTagDetail"
    private var queue: RequestQueue? = null

    fun fetch(student_id:String) {
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id="+student_id

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
//                val sType = object : TypeToken<Student>() { }.type
                val result = Gson().fromJson<Student>(it,
                    Student::class.java)
                studentLD.value = result

                Log.d("showvoley2", result.toString())
            },
            {
                Log.d("showvoley2", it.toString())
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}