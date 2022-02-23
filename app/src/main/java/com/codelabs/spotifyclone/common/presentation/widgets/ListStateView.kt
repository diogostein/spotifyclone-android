package com.codelabs.spotifyclone.common.presentation.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.codelabs.spotifyclone.R
import com.codelabs.spotifyclone.databinding.WidgetListStateViewBinding

class ListStateView constructor(
    context: Context,
    attrs: AttributeSet?,
) : FrameLayout(context, attrs) {
    private val binding = WidgetListStateViewBinding.inflate(LayoutInflater.from(context), this)

    private var hasDivider: Boolean = false

    init {
        setLayout(attrs)
    }

    private fun setLayout(attrs: AttributeSet?) {
        attrs?.let { attributeSet ->
            val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListStateView)

            hasDivider = attributes.getBoolean(R.styleable.ListStateView_has_divider, false)

            attributes.recycle()
        }
    }

    fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) {
        binding.recyclerView.layoutManager = layoutManager
    }

    fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        binding.recyclerView.adapter = adapter
    }

    fun addOnScrollListener(onScrollListener: RecyclerView.OnScrollListener) {
        binding.recyclerView.addOnScrollListener(onScrollListener)
    }

    fun setOnRetryClickListener(onRetryClickListener: OnClickListener) {
        binding.includeError.btnReload.setOnClickListener(onRetryClickListener)
    }

    fun showRecyclerView(adapter: RecyclerView.Adapter<*>? = null) {
        binding.recyclerView.visibility = VISIBLE
        binding.includeProgress.progressBar.visibility = GONE
        setErrorVisibility(GONE)
        if (adapter != null) setAdapter(adapter)
    }

    fun showProgressIndicator() {
        binding.recyclerView.visibility = GONE
        binding.includeProgress.progressBar.visibility = VISIBLE
        setErrorVisibility(GONE)
    }

    fun showError(message: String? = null) {
        binding.recyclerView.visibility = GONE
        binding.includeProgress.progressBar.visibility = GONE
        setErrorVisibility(VISIBLE, message)
    }

    private fun setErrorVisibility(visibility: Int, message: String? = null) {
        binding.includeError.apply {
            viewGroupError.visibility = visibility
            tvMessage.text = message
        }
    }
}