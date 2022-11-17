package org.sopt.sample.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.sopt.sample.adapter.UserDataAdapter
import org.sopt.sample.data.UserFactory
import org.sopt.sample.data.response.ResponseUser
import org.sopt.sample.databinding.FragmentHome2Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHome2Binding? = null
    private val binding get() = requireNotNull(_binding) { }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHome2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = UserDataAdapter(requireContext())
        binding.rcvProfile.adapter = adapter

        val call: Call<ResponseUser> = UserFactory.userService.getUser()

        call.enqueue(object : Callback<ResponseUser> {
            override fun onResponse(
                call: Call<ResponseUser>,
                response: Response<ResponseUser>
            ) {
                Log.d("Home", "42")
                Log.d("Home", response.body().toString())
                if (response.isSuccessful) { // 통신 성공
                    response.body()?.let {
                        adapter.setRepoList(it.data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseUser>, t: Throwable) { // 통신 실패
                Log.e("ProfileActivity", t.message.toString(), t)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
