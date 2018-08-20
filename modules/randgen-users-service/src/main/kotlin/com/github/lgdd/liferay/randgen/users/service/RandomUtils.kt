package com.github.lgdd.liferay.randgen.users.service

import com.liferay.portal.kernel.dao.orm.QueryUtil
import com.liferay.portal.kernel.service.GroupLocalServiceUtil
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil
import com.liferay.portal.kernel.service.RoleLocalServiceUtil
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil
import com.liferay.portal.kernel.util.PortalUtil
import java.util.*

fun <E> List<E>.getRandomElements(): List<E> {
    if (this.isEmpty())
        return this
    return this.shuffled().take(Random().nextInt(this.size))
}

fun randomRoleIds(): LongArray {
    val allRoleIds =  RoleLocalServiceUtil
            .getRoles(PortalUtil.getDefaultCompanyId())
            .map {
                it.roleId
            }
            .getRandomElements()
            .toLongArray()

    return allRoleIds
}

fun randomOrgIds(): LongArray {
    return OrganizationLocalServiceUtil
            .getOrganizations(QueryUtil.ALL_POS, QueryUtil.ALL_POS)
            .map {
                it.organizationId
            }
            .getRandomElements()
            .toLongArray()
}

fun randomGroupIds(): LongArray {
    return GroupLocalServiceUtil
            .getActiveGroups(PortalUtil.getDefaultCompanyId(), true)
            .map {
                it.groupId
            }
            .getRandomElements()
            .toLongArray()
}

fun randomUserGroupIds(): LongArray {
    return UserGroupLocalServiceUtil
            .getUserGroups(PortalUtil.getDefaultCompanyId())
            .map {
                it.groupId
            }
            .getRandomElements()
            .toLongArray()
}