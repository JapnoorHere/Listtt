package com.example.listtt

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Adapter
import android.widget.ArrayAdapter
import com.example.listtt.databinding.FragmentArrayListFragmenttBinding
import com.example.listtt.databinding.NewItemAddLayoutBinding
import com.example.listtt.databinding.UpdateItemLayoutBinding
import java.lang.reflect.Array

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ArrayListFragmentt.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArrayListFragmentt : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var arrayList : ArrayList<String> =ArrayList()
    lateinit var binding :FragmentArrayListFragmenttBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentArrayListFragmenttBinding.inflate(layoutInflater)
        var adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1,arrayList)
            arrayList.add("qwert")
            arrayList.add("yuiop")
            arrayList.add("asdf")
            arrayList.add("ghjkl")
            binding.listView.adapter=adapter

            binding.fabButton.setOnClickListener{
                var dialogBinding = NewItemAddLayoutBinding.inflate(layoutInflater)
                var dialog=Dialog(requireContext())
                dialog.setContentView(dialogBinding.root)
                dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)

                dialogBinding.btnOk.setOnClickListener {
                    if (dialogBinding.etNewItem.text.toString().isNullOrEmpty()) {
                        dialogBinding.etNewItem.setError("Enter New Item")
                    }
                    else{
                        arrayList.add(dialogBinding.etNewItem.text.toString())
                        dialog.dismiss()
                    }

                }
                dialog.show()
            }

        binding.listView.setOnItemClickListener {adapterView,view,i,l ->
            var dialogBinding =UpdateItemLayoutBinding.inflate(layoutInflater)
            var dialog=Dialog(requireContext())
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT)
            dialogBinding.etUpdateItem.setText(arrayList[i])
            dialogBinding.btnUpdate.setOnClickListener{
                if(dialogBinding.etUpdateItem.text.toString().isNullOrEmpty()){
                    dialogBinding.etUpdateItem.setError("Enter Item")
                }
                else{
                    arrayList.set(i,dialogBinding.etUpdateItem.text.toString())
                    dialog.dismiss()
                }
            }
            dialog.show()
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ArrayListFragmentt.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ArrayListFragmentt().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}