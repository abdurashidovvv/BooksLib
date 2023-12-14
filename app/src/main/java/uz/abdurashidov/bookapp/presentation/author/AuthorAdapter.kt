package uz.abdurashidov.bookapp.presentation.author

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.abdurashidov.bookapp.databinding.AuthorItemRvBinding
import uz.abdurashidov.bookapp.domain.model.author_response.AuthorResponseItem

class AuthorAdapter(
    val list: ArrayList<AuthorResponseItem>,
    val authorOnClickInterface: AuthorOnClickInterface
) :
    RecyclerView.Adapter<AuthorAdapter.Vh>() {

    inner class Vh(val itemRv: AuthorItemRvBinding) : ViewHolder(itemRv.root) {
        fun onBind(authorResponseItem: AuthorResponseItem) {
            itemRv.authorItemName.text = authorResponseItem.fullName
            if (authorResponseItem.death_date != null) {
                "${
                    authorResponseItem.birth_date.substring(
                        0,
                        5
                    )
                }${
                    authorResponseItem.death_date.toString().substring(0, 5)
                }".also { itemRv.authorDate.text = it }
            } else {
                "${
                    authorResponseItem.birth_date.substring(
                        0,
                        5
                    )
                }Now".also { itemRv.authorDate.text = it }
            }

            itemRv.root.setOnClickListener {
                authorOnClickInterface.AuthorOnClick(authorResponseItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(AuthorItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    interface AuthorOnClickInterface {
        fun AuthorOnClick(authorResponseItem: AuthorResponseItem)
    }
}