# RandGen Users
A simple random user generator for Liferay 7.x. 

Each generated user is assigned to random existing organizations, user groups and roles.

If you want more, you should take a look at this awesome project: **[Dummy Factory](https://github.com/yasuflatland-lf/liferay-dummy-factory)**.

## Modules

- **randgen-users-api**: contains a simple interface for providers
- **randgen-users-service**: kotlin based implementation fetching random users from [randomuser.me](https://randomuser.me)
- **randgen-users-cmd**: provides gogo shell command to generates users

## Gogo Shell Command

For example, generating 10 users:
```
g! randgen:users 10
```

## Compatible Liferay versions

### CE

- 7.0.0-ga1
- 7.1.0-ga1

### DXP

- 7.1.x
