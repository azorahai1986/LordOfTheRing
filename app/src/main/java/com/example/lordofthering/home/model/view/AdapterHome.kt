package com.example.lordofthering.home.model.view

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lordofthering.databinding.ItemHomeBinding
import com.example.lordofthering.dialog.StartDialog
import com.example.lordofthering.home.model.datamodel.Doc
import com.example.lordofthering.home.model.datamodel.Moview

class AdapterHome:RecyclerView.Adapter<AdapterHome.ViewHolder>() {


    var dialogInfo = StartDialog()
    var listForDialog = mutableListOf<Doc>()
    lateinit var context:Context
    lateinit var activity:Activity
    private lateinit var binding:ItemHomeBinding

    fun getContexts(requireContext: Context, requireActivity: FragmentActivity) {
        context = requireContext
        activity = requireActivity
    }
    fun getListDoc(movies: List<Doc>) {
        listForDialog = movies as MutableList<Doc>
    }
    inner class ViewHolder:RecyclerView.ViewHolder(binding.root){
        fun setData(movies: Moview){
            binding.apply {

                    //textNomination.text = movies.academyAwardNominations.toString()
                    //textWins.text = movies.academyAwardWins.toString()
                textName.text = movies.name
                Glide.with(context).load(movies.image).into(imageMoview)





            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val listInPosition = differ.currentList[position]
        holder.setData(listInPosition)
        holder.setIsRecyclable(false)
        holder.absoluteAdapterPosition

        binding.cardHome.setOnClickListener {
            dialogInfo.inflateDialog(context = context, activity = activity, image = listInPosition.image ,list = listForDialog[position])
        }


    }

    override fun getItemCount(): Int = differ.currentList.size

    val differrCallback = object :DiffUtil.ItemCallback<Moview>(){
        override fun areItemsTheSame(oldItem: Moview, newItem: Moview): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Moview, newItem: Moview): Boolean = oldItem==newItem

    }

    val differ = AsyncListDiffer(this, differrCallback)



}