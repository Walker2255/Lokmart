package com.azimjonc.projects.lookmart.presentation.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.azimjonc.projects.lookmart.R
import com.azimjonc.projects.lookmart.databinding.ItemOnboardingBinding
import com.bumptech.glide.Glide

class OnboardingAdapter : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {
    class ViewHolder(private val binfing: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binfing.root) {
        fun bind(page: Triple<Int, Int, Int>) = with(binfing) {
            Glide.with(root.context).load(page.first).into(image)
            title.text = root.context.getString(page.second)
            description.text = root.context.getText(page.third)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    companion object {
        private val items = listOf(
            Triple(
                R.drawable.onboarding_image_0,
                R.string.onboarding_title_0,
                R.string.onboarding_description_0
            ), Triple(
                R.drawable.onboarding_image_1,
                R.string.onboarding_title_1,
                R.string.onboarding_description_1
            )
        )
    }
}