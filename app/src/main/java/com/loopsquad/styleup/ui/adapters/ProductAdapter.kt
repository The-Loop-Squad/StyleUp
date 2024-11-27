
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.loopsquad.styleup.R
import com.loopsquad.styleup.ui.activities.ClothingDetailActivity

class ProductAdapter(
    productList: List<Product>,
    private val context: Context
) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private val productList: List<Product> = productList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.list_item_clothes_catalog, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: Product = productList[position]
        holder.imageView.setImageResource(product.imageResourceId)
        holder.textView.setText(product.name)

        holder.itemView.setOnClickListener { v: View? ->
            val intent = Intent(
                context,
                ClothingDetailActivity::class.java
            )
            intent.putExtra("productName", product.name)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView =
            itemView.findViewById<ImageView>(R.id.itemImage) // Update with IDs in list_item_clothes_catalog
        var textView: TextView =
            itemView.findViewById<TextView>(R.id.itemName) // Update with IDs in list_item_clothes_catalog
    }
}