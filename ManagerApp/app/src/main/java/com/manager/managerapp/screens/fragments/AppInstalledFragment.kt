package com.manager.managerapp.screens.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.manager.managerapp.R
import com.manager.managerapp.adapters.AppInstallAdapter
import com.manager.managerapp.utils.SpaceRecyclerViewVertical
import kotlinx.android.synthetic.main.fragment_app_installed.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class AppInstalledFragment : Fragment() {
    private lateinit var adapterAppInstall: AppInstallAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_installed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    fun initView() {
        initRecyclerView()
    }

    fun initRecyclerView() {
        rc_appinstall.layoutManager = LinearLayoutManager(context!!,
                LinearLayoutManager.VERTICAL,
                false)
        rc_appinstall.addItemDecoration(
                SpaceRecyclerViewVertical(resources.getDimensionPixelSize(R.dimen._10sdp),
                        resources.getDimensionPixelSize(R.dimen._8sdp)))
        adapterAppInstall = AppInstallAdapter(context!!, object : AppInstallAdapter.ListenerAppInstall {
            override fun onClickItem(pos: Int) {

            }

            override fun onClickUninstall(pos: Int) {
            }

            override fun onClickUpdate(pos: Int) {
            }
        })
        rc_appinstall.adapter = adapterAppInstall
    }

    companion object {
        fun newInstance(): AppInstalledFragment {
            var fragment = AppInstalledFragment()
            return fragment
        }
    }

}
