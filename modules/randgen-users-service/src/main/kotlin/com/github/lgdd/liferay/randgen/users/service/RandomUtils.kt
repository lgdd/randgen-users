package com.github.lgdd.liferay.randgen.users.service

import com.liferay.portal.kernel.dao.orm.QueryUtil
import com.liferay.portal.kernel.service.GroupLocalServiceUtil
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil
import com.liferay.portal.kernel.service.RoleLocalServiceUtil
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil
import com.liferay.portal.kernel.util.PortalUtil
import java.util.*

/**
 * Gets random elements from a list.
 *
 * @return a random list of elements.
 */
fun <E> List<E>.getRandomElements(): List<E> {
    if (this.isEmpty())
        return this
    return this.shuffled().take(Random().nextInt(this.size))
}

/**
 * Generates a random array of role ids from existing roles within the default company.
 *
 * @return a random array of role ids.
 */
fun randomRoleIds(): LongArray {
    return RoleLocalServiceUtil
            .getRoles(PortalUtil.getDefaultCompanyId())
            .map {
                it.roleId
            }
            .getRandomElements()
            .toLongArray()
}

/**
 * Generates a random array of organization ids from existing organizations.
 *
 * @return a random array of organization ids.
 */
fun randomOrgIds(): LongArray {
    return OrganizationLocalServiceUtil
            .getOrganizations(QueryUtil.ALL_POS, QueryUtil.ALL_POS)
            .map {
                it.organizationId
            }
            .getRandomElements()
            .toLongArray()
}

/**
 * Generates a random array of group ids from existing active groups within the default company.
 *
 * @return a random array of group ids.
 */
fun randomGroupIds(): LongArray {
    return GroupLocalServiceUtil
            .getActiveGroups(PortalUtil.getDefaultCompanyId(), true)
            .map {
                it.groupId
            }
            .getRandomElements()
            .toLongArray()
}

/**
 * Generates a random array of user group ids from existing user groups within the default company.
 *
 * @return a random array of user group ids.
 */
fun randomUserGroupIds(): LongArray {
    return UserGroupLocalServiceUtil
            .getUserGroups(PortalUtil.getDefaultCompanyId())
            .map {
                it.groupId
            }
            .getRandomElements()
            .toLongArray()
}