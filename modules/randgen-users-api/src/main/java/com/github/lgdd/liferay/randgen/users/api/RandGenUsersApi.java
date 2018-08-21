package com.github.lgdd.liferay.randgen.users.api;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.User;

import java.util.List;

/**
 * Interface describing a random users generator.
 */
@ProviderType
public interface RandGenUsersApi {

    /**
     * Generates random users.
     *
     * @return list of Liferay users added.
     */
    List<User> generate();


    /**
     * Generates a specified number of random users.
     *
     * @return list of Liferay users added.
     */
    List<User> generate(int size);
}