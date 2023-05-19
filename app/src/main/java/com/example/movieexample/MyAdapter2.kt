import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.movieexample.Menu
import com.example.movieexample.MenuActivity
import com.example.movieexample.MenuItemFragment
import com.example.movieexample.R
import com.example.movieexample.databinding.FragmentMenuBinding
import com.example.movieexample.databinding.ItemMenuBinding

class MyAdapter2(private val data: List<Menu>, private val activity: FragmentActivity?) :
    RecyclerView.Adapter<MyAdapter2.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemMenuBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            productName.text = data[position].name
            productDescription.text = data[position].description
            productPrice.text = data[position].price.toString()
            menuImage.setImageResource(data[position].picture)

            // Bundle
            var bundle = bundleOf(
                "menuId" to data[position].menuId,
                "restaurantId" to data[position].restaurantId
            )

            holder.itemView.setOnClickListener {
                var bundle = bundleOf(
                    "menuId" to data[position].menuId,
                    "restaurantId" to data[position].restaurantId
                )
                it.findNavController()
                    .navigate(R.id.action_fragmentMenus_to_menuItemFragment, bundle)
            }

        }
    }

    class MyViewHolder(val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root)

}
