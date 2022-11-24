package org.sopt.sample.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.sample.data.response.ResponseUser
import org.sopt.sample.databinding.LayoutUserlistBinding

class UserDataAdapter(context: Context) : RecyclerView.Adapter<UserDataAdapter.UserViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context) }
    private var userList: List<ResponseUser.UserData> = emptyList()

    class UserViewHolder(
        private val binding: LayoutUserlistBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: ResponseUser.UserData) {
            binding.txtUserName.text = user.first_name
            binding.txtUserEmail.text = user.email
            Glide.with(this.binding.root)
                .load(user.avatar)
                .into(binding.imgProfile)
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setRepoList(repoList: List<ResponseUser.UserData>) {
        this.userList = repoList.toList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = LayoutUserlistBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }
}
