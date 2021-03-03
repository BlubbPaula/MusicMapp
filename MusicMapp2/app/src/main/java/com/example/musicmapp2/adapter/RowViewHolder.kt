import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.musicmapp2.adapter.RecyclerViewClickListener


class RowViewHolder internal constructor(v: View, private val mListener: RecyclerViewClickListener) : RecyclerView.ViewHolder(v), View.OnClickListener {
    override fun onClick(view: View?) {
        mListener.onClick(view, adapterPosition)
    }

    init {
        v.setOnClickListener(this)
    }
}