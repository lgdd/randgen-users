package com.github.lgdd.liferay.randgen.users.service

import com.github.lgdd.liferay.randgen.users.api.RandGenUsersApi
import com.liferay.portal.kernel.json.JSONFactoryUtil
import com.liferay.portal.kernel.json.JSONObject
import com.liferay.portal.kernel.log.LogFactoryUtil
import com.liferay.portal.kernel.model.User
import com.liferay.portal.kernel.service.UserLocalServiceUtil
import com.liferay.portal.kernel.util.FileUtil
import com.liferay.portal.kernel.util.PortalUtil
import com.liferay.portal.kernel.util.StringPool
import org.apache.commons.io.IOUtils
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.osgi.service.component.annotations.Component
import java.nio.charset.Charset
import java.util.*

/**
 * Generates random users from an external API (https://randomuser.me/api/).
 *
 * @see [RandGenUsersApi]
 */
@Component(
        immediate = true,
        property = [],
        service = [(RandGenUsersApi::class)]
)
class RandGenUsersImpl : RandGenUsersApi {

    /**
     * Generates between 2 and 10 random users.
     *
     * @return list of Liferay users added.
     */
    override fun generate(): MutableList<User> {
        val size = Random().nextInt(9) + 2
        val httpGet = HttpGet("$BASE_URL?$NAT_PARAMS&results=$size")
        log.debug("Fetching a random user from $BASE_URL")
        val response = _httpClient.execute(httpGet)
        return addUsers(response)
    }

    /**
     * Generates [size] random users.
     *
     * @return list of Liferay users added.
     */
    override fun generate(size: Int): MutableList<User> {
        val httpGet = HttpGet("$BASE_URL?nat=$NAT_PARAMS&results=$size")
        log.debug("Fetching random $size users from $BASE_URL")
        val response = _httpClient.execute(httpGet)
        return addUsers(response)
    }

    /**
     * Adds Liferay users extracted from [response] as json objects.
     *
     * @return list of Liferay users added.
     */
    private fun addUsers(response: CloseableHttpResponse): MutableList<User> {
        val users = listOf<User>().toMutableList()
        response.use {
            val bodyJsonStr = IOUtils.toString(it.entity.content, Charset.defaultCharset())
            log.debug("Body Response : $bodyJsonStr")
            val jsonResults = JSONFactoryUtil.createJSONObject(bodyJsonStr).getJSONArray("results")
            log.debug("Transform body resp to JSONArray")
            val max = jsonResults.length() - 1
            for (i in 0..max) {
                users.add(addUser(jsonResults.getJSONObject(i)))
            }
        }
        return users
    }

    /**
     * Adds a Liferay user from a [userJson].
     *
     * @return Liferay user added.
     */
    private fun addUser(userJson: JSONObject): User {
        val companyId = PortalUtil.getDefaultCompanyId()
        val userId = UserLocalServiceUtil.getDefaultUserId(companyId)
        val dateOfBirth = userJson.getDateOfBirth()
        val user = UserLocalServiceUtil.addUser(
                userId, companyId, false, "test",
                "test", false, userJson.getScreenName(),
                userJson.getEmail(), 0, StringPool.BLANK, userJson.getLocale(),
                userJson.getFirstName(), StringPool.BLANK, userJson.getLastName(), 0, 0,
                userJson.isMale(), dateOfBirth.monthValue - 1,
                dateOfBirth.dayOfMonth, dateOfBirth.year, StringPool.BLANK,
                randomGroupIds(), randomOrgIds(), randomRoleIds(), randomUserGroupIds(),
                false, null
        )
        updateUserPortrait(user, userJson)
        return user
    }

    /**
     * Updates [user] portrait with [userJson]'s picture.
     */
    private fun updateUserPortrait(user: User, userJson: JSONObject) {
        val imageUrl = userJson.getJSONObject("picture").getString("large")
        val httpGet = HttpGet(imageUrl)
        val response = _httpClient.execute(httpGet)
        response.use {
            UserLocalServiceUtil
                    .updatePortrait(
                            user.userId,
                            FileUtil.getBytes(it.entity.content)
                    )
        }
    }

    private val _httpClient = HttpClients.createDefault()

    companion object {

        @JvmStatic
        private val log = LogFactoryUtil.getLog(RandGenUsersImpl::class.java)

        @JvmStatic
        private val BASE_URL = "https://randomuser.me/api/"

        @JvmStatic
        private val NAT_PARAMS = "us,gb,fr,es,de,jp,fi"
    }

}