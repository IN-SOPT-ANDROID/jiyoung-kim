package org.sopt.sample.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.PlaylistData
import org.sopt.sample.databinding.LayoutHeaderBinding
import org.sopt.sample.databinding.LayoutPlaylistBinding

class PlayListAdapter(context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var repoList: List<PlaylistData> = emptyList()

    class PlaylistViewHolder(
        private val binding: LayoutPlaylistBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PlaylistData){
            binding.imgPlay.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.txtSongName.text = data.name
            binding.txtSinger.text = data.singer
        }
    }

    class TitleViewHolder(
        private  val binding: LayoutHeaderBinding,
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            TEXT_HEADER -> {
                val headerBinding = LayoutHeaderBinding.inflate(inflater, parent, false)
                TitleViewHolder((headerBinding))
            }
            TEXT_ITEM -> {
                val itemBinding = LayoutPlaylistBinding.inflate(inflater, parent, false)
                PlaylistViewHolder(itemBinding)
            }
            else -> throw RuntimeException("알 수 없는 뷰 타입 에러")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("MultiViewTypeAdapter", "position: $position")
        val itemOfRepoList = (repoList[position])
        when (holder){
            is PlaylistViewHolder -> {
                holder.onBind(itemOfRepoList)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> TEXT_HEADER
            else -> TEXT_ITEM
        }
    }

    override fun getItemCount(): Int = repoList.size

    fun setRepoList(repoList: List<PlaylistData>) {
        this.repoList = repoList.toList()
        notifyDataSetChanged()
    }

companion object{
    const val TEXT_HEADER = 0
    const val TEXT_ITEM = 1
}

}