package com.github.lgdd.liferay.randgen.users.api;

import aQute.bnd.annotation.ProviderType;
import com.liferay.portal.kernel.model.User;

import java.util.List;

@ProviderType
public interface RandGenUsersApi {

    List<User> generate();

    List<User> generate(int size);
}