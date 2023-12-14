import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.abdurashidov.bookapp.databinding.BookListRvItemBinding
import uz.abdurashidov.bookapp.domain.model.book_list_response.BookListResponse
import uz.abdurashidov.bookapp.domain.model.book_list_response.Data

class BookListAdapter(val list: List<Data>, val rvClick: RvClick) :
    RecyclerView.Adapter<BookListAdapter.Vh>() {

    inner class Vh(var itemRv: BookListRvItemBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(book: Data) {
            itemRv.bookListItemName.text = book.name
            Picasso.get().load(Uri.parse("${book.photo}")).into(itemRv.bookImg)
            itemRv.bookListItemAuthor.text = book.author.fullname
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