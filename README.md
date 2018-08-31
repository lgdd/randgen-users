# RandGen Users

A simple random user generator for Liferay 7.x.

![gif](doc/randgen-users.gif)
> Gogo Shell portlet is native starting 7.1. A community plugin exists for 7.0 ([GitHub](https://github.com/mario83/WebGogoShell) & [Liferay Marketplace](https://web.liferay.com/fr/marketplace/-/mp/application/95475771))
 
Each generated user is randomly assigned to existing organizations, user groups and roles.

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
> Official documentation about _[Using Felix Gogo Shell](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)_

## Compatible Liferay versions

### CE

- 7.0.0-ga1
- 7.0.1-ga2
- 7.0.2-ga3
- 7.0.3-ga4
- 7.0.4-ga5
- 7.0.5-ga6
- 7.0.6-ga7
- 7.1.0-ga1

### DXP

- 7.1.x
