import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.abdurashidov.bookapp.databinding.BookListRvItemBinding
import uz.abdurashidov.bookapp.domain.model.get_all_books_response.Data

class BookListAdapter(val list: List<Data>, val rvClick: RvClick) :
    RecyclerView.Adapter<BookListAdapter.Vh>() {

    inner class Vh(var itemRv: BookListRvItemBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(book: Data) {
            itemRv.bookListItemName.text = book.name
            Picasso.get().load(Uri.parse("${book.photo}")).into(itemRv.mainImg)
            itemRv.bookListItemAuthor.text = book.author.fullname
            itemRv.likesCount.text = book.likes_count.toString()
            itemRv.storageCount.text = book.storage_count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(BookListRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface RvClick {
        fun itemClick(text: String)
    }

}