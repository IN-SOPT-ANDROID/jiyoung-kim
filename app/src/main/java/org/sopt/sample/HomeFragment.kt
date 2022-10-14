package org.sopt.sample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.sample.adapter.PlayListAdapter
import org.sopt.sample.data.PlaylistData
import org.sopt.sample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = requireNotNull(_binding) { }

    private val AlbumList = listOf<PlaylistData>(
        PlaylistData(0,"",""),
        PlaylistData(R.drawable.play_button,"Forever 1","소녀시대"),
        PlaylistData(R.drawable.play_button,"마지막 인사","NCT DREAM"),
        PlaylistData(R.drawable.play_button,"ZOOM","제시 (Jessi)"),
        PlaylistData(R.drawable.play_button,"Hype boy","NewJeans"),
        PlaylistData(R.drawable.play_button,"Girls","aespa"),
        PlaylistData(R.drawable.play_button,"LOVE DIVE","IVE (아이브)"),
        PlaylistData(R.drawable.play_button,"도깨비불","aespa"),
        PlaylistData(R.drawable.play_button,"HOT","세븐틴"),
        PlaylistData(R.drawable.play_button,"Hurt","NewJeans")
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
        val adapter = PlayListAdapter(requireContext())
        binding.rvRepos.adapter = adapter
        adapter.setRepoList(AlbumList)
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