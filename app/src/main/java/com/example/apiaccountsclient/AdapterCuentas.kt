package com.example.apiaccountsclient

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.apiaccountsclient.databinding.PatronlistaBinding

class AdapterCuentas : RecyclerView.Adapter<AdapterCuentas.MyViewHolder>()  {
    var Cuentas : MutableList<listadoPrin> = ArrayList()
    lateinit var context : Context

    class MyViewHolder(val binding: PatronlistaBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotConstructor")

    fun AdapterCuentas(Cuenta: MutableList<listadoPrin>, context: Context){
        this.Cuentas = Cuenta
        this.context = context
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = Cuentas.get(position)
        holder.binding.textCuenta.text = item.cuentas
        holder.binding.nrocuenta.setOnClickListener{
            Toast.makeText(context, "cuenta-> " + item.cuentas.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(PatronlistaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return Cuentas.size
    }

}