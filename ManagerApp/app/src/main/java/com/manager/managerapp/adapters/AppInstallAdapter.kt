package com.manager.managerapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.manager.managerapp.R
import com.manager.managerapp.objects.AppInstallObject

class AppInstallAdapter(context: Context, listener: ListenerAppInstall)
    : RecyclerView.Adapter<AppInstallAdapter.AppInstallHolder>() {
    private var context: Context
    private var listener: ListenerAppInstall
    private var arrApp = ArrayList<AppInstallObject>()

    init {
        this.context = context
        this.listener = listener
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AppInstallHolder {
        return AppInstallHolder(LayoutInflater.from(context).inflate(R.layout.item_app_install, p0, false))
    }

    override fun getItemCount(): Int {
        return arrApp.size
    }

    override fun onBindViewHolder(p0: AppInstallHolder, p1: Int) {
        p0.bindData()
    }

    fun setListApp(arr: ArrayList<AppInstallObject>) {
        this.arrApp = arr
        notifyDataSetChanged()
    }

    fun getApp(pos: Int): AppInstallObject {
        return arrApp.get(pos)
    }


    inner class AppInstallHolder(view: View) : RecyclerView.ViewHolder(view),
            View.OnClickListener {
        private var imgIcon: ImageView
        private var imgUninstall: ImageView
        private var imgUpdate: ImageView
        private var textName: TextView
        private var textSize: TextView
        private var textVersion: TextView
        private var itemApp: RelativeLayout

        init {
            imgIcon = view.findViewById(R.id.img_icon)
            imgUninstall = view.findViewById(R.id.img_uninstall)
            imgUpdate = view.findViewById(R.id.img_update)
            textName = view.findViewById(R.id.text_nameapp)
            textSize = view.findViewById(R.id.text_size)
            textVersion = view.findViewById(R.id.text_version)
            itemApp = view.findViewById(R.id.itemapp)
        }

        fun bindData() {
            var item = getApp(adapterPosition)
            textName.text = item.name
            textVersion.text = "${context.getString(R.string.version)} ${item.version}"
            textSize.text = item.size.toString()
            if (item.isNewVersion) {
                imgUpdate.visibility = View.VISIBLE
            } else {
                imgUpdate.visibility = View.GONE

            }
            imgUpdate.setOnClickListener(this)
            imgUninstall.setOnClickListener(this)
            itemApp.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.itemapp -> {
                    listener.onClickItem(adapterPosition)
                }
                R.id.img_update -> {
                    listener.onClickUpdate(adapterPosition)

                }
                R.id.img_uninstall -> {
                    listener.onClickUninstall(adapterPosition)

                }
            }
        }
    }

    interface ListenerAppInstall {
        fun onClickItem(pos: Int)
        fun onClickUninstall(pos: Int)
        fun onClickUpdate(pos: Int)
    }
}