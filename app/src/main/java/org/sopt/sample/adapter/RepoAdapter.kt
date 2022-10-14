package org.sopt.sample.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.RepoData
import org.sopt.sample.databinding.LayoutGithubRepoBinding
import org.sopt.sample.databinding.LayoutHeaderBinding

class RepoAdapter(context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var repoList: List<RepoData> = emptyList()

    class RepoViewHolder(
        private val binding: LayoutGithubRepoBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepoData){
            binding.imgGithub.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.txtRepoName.text = data.name
            binding.txtRepoAuthor.text = data.author
        }
    }

    class TextViewHolder(
        private  val binding: LayoutHeaderBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepoData){
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            TEXT_HEADER -> {
                val headerBinding = LayoutHeaderBinding.inflate(inflater, parent, false)
                TextViewHolder((headerBinding))
            }
            TEXT_ITEM -> {
                val repoBinding = LayoutGithubRepoBinding.inflate(inflater, parent, false)
                RepoViewHolder(repoBinding)
            }
            else -> throw RuntimeException("알 수 없는 뷰 타입 에러")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("MultiViewTypeAdapter", "Hi, onBindViewHolder")
        val obj = (repoList[position])
        when (holder){
            is TextViewHolder -> {
                holder.onBind(obj)
            }
            is RepoViewHolder -> {
                holder.onBind(obj)
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

    fun setRepoList(repoList: List<RepoData>) {
        this.repoList = repoList.toList()
        notifyDataSetChanged()
    }

companion object{
    const val TEXT_HEADER = 0
    const val TEXT_ITEM = 1
}

}