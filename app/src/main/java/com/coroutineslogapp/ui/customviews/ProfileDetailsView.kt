package com.coroutineslogapp.ui.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.coroutineslogapp.R
import com.coroutineslogapp.ui.model.User
import kotlinx.android.synthetic.main.layout_user_details.view.*
import kotlinx.android.synthetic.main.layout_user_details.view.biography_content as biographyContent
import kotlinx.android.synthetic.main.layout_user_details.view.location_content as locationContent
import kotlinx.android.synthetic.main.layout_user_details.view.email_content as emailContent
import kotlinx.android.synthetic.main.layout_user_details.view.created_content as createdContent
import kotlinx.android.synthetic.main.layout_user_details.view.updated_content as updatedContent
import kotlinx.android.synthetic.main.layout_user_details.view.public_repos_content as publicReposContent
import kotlinx.android.synthetic.main.layout_user_details.view.private_repos_content as privateReposContent

class ProfileDetailsView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    ConstraintLayout(context, attrs) {

    var profileModel: User = User()
        set(value) {
            field = value
            render(value)
        }

    init {
        inflate(context, R.layout.layout_user_details, this)
    }

    private fun render(user: User) {
        with(user) {
            Glide.with(context).load(avatarUrl).circleCrop().into(avatar)
            biographyContent.text = biography
            locationContent.text = location
            emailContent.text = email
            createdContent.text = createdAt
            updatedContent.text = updatedAt
            publicReposContent.text = publicRepos.toString()
            privateReposContent.text = privateRepos.toString()
        }
    }
}
