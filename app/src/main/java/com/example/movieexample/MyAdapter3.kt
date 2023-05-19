import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieexample.Util
import com.example.movieexample.*
import com.example.movieexample.databinding.ItemOrderBinding

class MyAdapter3(val data: MutableList<Menu>, cartFragment: CartFragment) :
    RecyclerView.Adapter<MyAdapter3.MyViewHolder>() {
    val util = Util()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {

            val instance = AppDatabase.buildDatabase(holder.itemView.context)
            val menu = instance?.getMenuDao()?.getMenuById(data[position].menuId)

            productName.text = menu?.name
            productDescription.text = menu?.description
            productImage.setImageResource(menu?.picture!!)
            quantity.text = data[position].quantity.toString()
            productPrice.text = (data[position].quantity * data[position].price).toString() + " DA"

            btnDelete.setOnClickListener {
                util.deleteItemFromCart(holder.itemView.context, data[position].menuId)
                data.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    class MyViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root)

}