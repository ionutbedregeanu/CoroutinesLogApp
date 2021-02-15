package com.coroutineslogapp.data.mappers

import java.lang.StringBuilder

/**
Note: GitHub has discontinued password authentication to the API starting on November 13, 2020 for all GitHub.com accounts,
including those on a GitHub Free, GitHub Pro, GitHub Team, or GitHub Enterprise Cloud plan.
You must now authenticate to the GitHub API with an API token, such as an OAuth access token, GitHub App installation access token,
or personal access token, depending on what you need to do with the token.
 */

private const val BASIC = "Basic "

class GetCredentials {
    operator fun invoke(username: String, accessToken: String): String {
        val credentials = StringBuilder().append(username).append(":").append(accessToken)

        return BASIC + android.util.Base64.encodeToString(
            credentials.toString().toByteArray(),
            android.util.Base64.NO_WRAP
        )
    }
}
