import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.harrypoterinfo.R
import com.ayberk.harrypoterinfo.databinding.ItemSpellsBinding
import com.ayberk.harrypoterinfo.presentation.models.spell.Attributes
import com.bumptech.glide.Glide


class SpellsAdapter(
    private val onDetailsClick: (Attributes) -> Unit,
) : RecyclerView.Adapter<SpellsAdapter.SpellsViewHolder>() {

    private var spellsList: List<Attributes>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpellsViewHolder {
        val binding = ItemSpellsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpellsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpellsViewHolder, position: Int) {
        spellsList?.let {
            if (it.isNotEmpty()) {
                holder.bind(it[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return spellsList?.size ?: 0
    }

    inner class SpellsViewHolder(private val binding: ItemSpellsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(attributes: Attributes) {
            with(binding) {
                linearspells.setOnClickListener {
                    onDetailsClick(attributes)
                }
                txtspellsname.text = attributes.name

                if (attributes.image != null && attributes.image.isNotEmpty()) {
                    Glide.with(imgspells)
                        .load(attributes.image)
                        .centerCrop()
                        .error(R.drawable.wizard)
                        .placeholder(R.drawable.wizard)
                        .into(imgspells)
                } else {
                    Glide.with(imgspells)
                        .load(R.drawable.magic)
                        .centerCrop()
                        .error(R.drawable.wizard)
                        .placeholder(R.drawable.wizard)
                        .into(imgspells)
                }
            }
        }
    }

    fun setSpellsList(newList: List<Attributes>) {
        spellsList = newList
        notifyDataSetChanged()
    }
}