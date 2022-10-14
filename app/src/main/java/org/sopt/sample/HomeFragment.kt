package org.sopt.sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.adapter.RepoAdapter
import org.sopt.sample.data.RepoData
import org.sopt.sample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding) { }

    private val mockRepoList = listOf<RepoData>(
        RepoData(R.drawable.github_logo,"A","jiyoung"),
        RepoData(R.drawable.github_logo,"B","jiyoung"),
        RepoData(R.drawable.github_logo,"C","jiyoung"),
        RepoData(R.drawable.github_logo,"D","jiyoung"),
        RepoData(R.drawable.github_logo,"E","jiyoung"),
        RepoData(R.drawable.github_logo,"F","jiyoung"),
        RepoData(R.drawable.github_logo,"G","jiyoung")
    )

    // onCreateView: UI의 초기화가 일어나는 부분
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RepoAdapter(requireContext())
        binding.rvRepos.adapter = adapter
        adapter.setRepoList(mockRepoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}