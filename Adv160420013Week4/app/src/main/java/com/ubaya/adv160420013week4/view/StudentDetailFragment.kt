package com.ubaya.adv160420013week4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.adv160420013week4.R
import com.ubaya.adv160420013week4.util.loadImage
import com.ubaya.adv160420013week4.viewmodel.DetailViewModel
import androidx.navigation.Navigation
import com.ubaya.adv160420013week4.databinding.FragmentStudentDetailBinding
import com.ubaya.adv160420013week4.databinding.StudentListItemBinding
import com.ubaya.adv160420013week4.model.Student
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonNotifListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding
    private lateinit var student:Student

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        var student_id:String = "0"
        arguments?.let {
            student_id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId.toString()
        }
        viewModel.fetch(student_id)
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            dataBinding.student = it
            student = it
//            val txtID = view?.findViewById<TextView>(R.id.txtID)
//            val txtName = view?.findViewById<TextView>(R.id.txtName)
//            val txtBod = view?.findViewById<TextView>(R.id.txtBod)
//            val txtPhone = view?.findViewById<TextView>(R.id.txtPhone)
//            val imgStudent = view?.findViewById<ImageView>(R.id.imgStudent)
//            val progressBarStudent = view?.findViewById<ProgressBar>(R.id.progressBarStudent)
//
//            txtID?.text = viewModel.studentLD.value?.id
//            txtName?.text = viewModel.studentLD.value?.name
//            txtBod?.text = viewModel.studentLD.value?.dob
//            txtPhone?.text = viewModel.studentLD.value?.phone
//            if (progressBarStudent != null) {
//                imgStudent?.loadImage(viewModel.studentLD.value?.photoUrl, progressBarStudent)
//            }
//
            val btnNotif = view?.findViewById<Button>(R.id.btnNotif)
            var student = it
            btnNotif?.setOnClickListener {
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(student.name.toString(),
                            "Hai, this is a notif for you",
                            R.drawable.ic_baseline_circle_24)
                    }
            }
        })
    }

    override fun onButtonNotifClick() {
//        Observable.timer(5, TimeUnit.SECONDS)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Log.d("Messages", "five seconds")
//                MainActivity.showNotification(
//                    student.name.toString(),
//                    "Hai, this is a notif for you",
//                    R.drawable.ic_baseline_circle_24
//                )
//            }
    }
}