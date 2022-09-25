package Adapter
import Database.GlobalVar
import Database.GlobalVar.Companion.listDataHewan
import Hewan
import Model.Ayam
import Model.Kambing
import Model.Sapi
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


import com.example.week2_0706012110046.AddHewanActivity
import com.example.week2_0706012110046.R
import com.example.week2_0706012110046.databinding.CardHewanBinding

class ListRVAdapter(private val data: MutableList<Hewan>):
    RecyclerView.Adapter<ListRVAdapter.viewHolder>() {


    class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val binding = CardHewanBinding.bind(itemView)



        fun setData(data:Hewan){
            binding.cardNamahewan.text=data.nama
            binding.cardJenishewan.text=data.jenis
            binding.cardUsiahewan.text=data.usia

            if(!data.imageUri!!.isEmpty()) {
                binding.cardImageview.setImageURI(Uri.parse(data.imageUri))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRVAdapter.viewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_hewan, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: ListRVAdapter.viewHolder, position: Int) {
        holder.setData(data[position])
        with(holder) {
            binding.cardDeletebutton.setOnClickListener(){
                GlobalVar.listDataHewan.removeAt(position)

                notifyItemRemoved(position)
                notifyItemChanged(position,itemCount)
                Toast.makeText(it.context,"Animal succesfully deleted", Toast.LENGTH_SHORT).show()
            }
            binding.cardEditbutton.setOnClickListener(){
                val myIntent = Intent(it.context, AddHewanActivity::class.java)
                myIntent.putExtra("position",position)

                it.context.startActivity(myIntent)

            }
            holder.binding.cardInteractbutton.setOnClickListener {
                if(GlobalVar.filterjenishewan.isEmpty()){
                    if(listDataHewan.get(position) is Ayam || GlobalVar.filterjenishewan.get(position) is Ayam){
                        Toast.makeText(it.context, listDataHewan.get(position).suaraHewan(), Toast.LENGTH_SHORT).show()
                    }else if(listDataHewan.get(position) is Sapi || GlobalVar.filterjenishewan.get(position) is Sapi){
                        Toast.makeText(it.context, listDataHewan.get(position).suaraHewan(), Toast.LENGTH_SHORT).show()
                    }else if(listDataHewan.get(position) is Kambing || GlobalVar.filterjenishewan.get(position) is Kambing){
                        Toast.makeText(it.context, listDataHewan.get(position).suaraHewan(), Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    if(GlobalVar.filterjenishewan.get(position) is Ayam){
                        Toast.makeText(it.context, GlobalVar.filterjenishewan.get(position).suaraHewan(), Toast.LENGTH_SHORT).show()
                    }else if(GlobalVar.filterjenishewan.get(position) is Sapi){
                        Toast.makeText(it.context, GlobalVar.filterjenishewan.get(position).suaraHewan(), Toast.LENGTH_SHORT).show()
                    }else if(GlobalVar.filterjenishewan.get(position) is Kambing){
                        Toast.makeText(it.context, GlobalVar.filterjenishewan.get(position).suaraHewan(), Toast.LENGTH_SHORT).show()
                    }
                }
                holder.binding.cardFeedbutton.setOnClickListener {
                    if(GlobalVar.filterjenishewan.isEmpty()){
                        if(listDataHewan.get(position) is Ayam || GlobalVar.filterjenishewan.get(position) is Ayam){
                            Toast.makeText(it.context, listDataHewan.get(position).feedHewan(jenis = String()), Toast.LENGTH_SHORT).show()
                        }else if(listDataHewan.get(position) is Sapi || GlobalVar.filterjenishewan.get(position) is Sapi){
                            Toast.makeText(it.context, listDataHewan.get(position).feedHewan(jenis = 0), Toast.LENGTH_SHORT).show()
                        }else if(listDataHewan.get(position) is Kambing || GlobalVar.filterjenishewan.get(position) is Kambing){
                            Toast.makeText(it.context, listDataHewan.get(position).feedHewan(jenis = 0), Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        if(GlobalVar.filterjenishewan.get(position) is Ayam){
                            Toast.makeText(it.context, GlobalVar.filterjenishewan.get(position).feedHewan(jenis = String()), Toast.LENGTH_SHORT).show()
                        }else if(GlobalVar.filterjenishewan.get(position) is Sapi){
                            Toast.makeText(it.context, GlobalVar.filterjenishewan.get(position).feedHewan(jenis = 0), Toast.LENGTH_SHORT).show()
                        }else if(GlobalVar.filterjenishewan.get(position) is Kambing){
                            Toast.makeText(it.context, GlobalVar.filterjenishewan.get(position).feedHewan(jenis = 0), Toast.LENGTH_SHORT).show()
                        }
                    }
                }


            }}


    }

    override fun getItemCount(): Int {
        return data.size
    }
}
