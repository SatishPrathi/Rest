package com.demo

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.codehaus.groovy.util.HashCodeHelper
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@ToString(cache=true, includeNames=true, includePackage=false)
class AppUserSecurityRole implements Serializable {

	private static final long serialVersionUID = 1

	AppUser appUser
	SecurityRole securityRole

	@Override
	boolean equals(other) {
		if (other instanceof AppUserSecurityRole) {
			other.appUserId == appUser?.id && other.securityRoleId == securityRole?.id
		}
	}

    @Override
	int hashCode() {
	    int hashCode = HashCodeHelper.initHash()
        if (appUser) {
            hashCode = HashCodeHelper.updateHash(hashCode, appUser.id)
		}
		if (securityRole) {
		    hashCode = HashCodeHelper.updateHash(hashCode, securityRole.id)
		}
		hashCode
	}

	static AppUserSecurityRole get(long appUserId, long securityRoleId) {
		criteriaFor(appUserId, securityRoleId).get()
	}

	static boolean exists(long appUserId, long securityRoleId) {
		criteriaFor(appUserId, securityRoleId).count()
	}

	private static DetachedCriteria criteriaFor(long appUserId, long securityRoleId) {
		AppUserSecurityRole.where {
			appUser == AppUser.load(appUserId) &&
			securityRole == SecurityRole.load(securityRoleId)
		}
	}

	static AppUserSecurityRole create(AppUser appUser, SecurityRole securityRole, boolean flush = false) {
		def instance = new AppUserSecurityRole(appUser: appUser, securityRole: securityRole)
		instance.save(flush: flush)
		instance
	}

	static boolean remove(AppUser u, SecurityRole r) {
		if (u != null && r != null) {
			AppUserSecurityRole.where { appUser == u && securityRole == r }.deleteAll()
		}
	}

	static int removeAll(AppUser u) {
		u == null ? 0 : AppUserSecurityRole.where { appUser == u }.deleteAll() as int
	}

	static int removeAll(SecurityRole r) {
		r == null ? 0 : AppUserSecurityRole.where { securityRole == r }.deleteAll() as int
	}

	static constraints = {
	    appUser nullable: false
		securityRole nullable: false, validator: { SecurityRole r, AppUserSecurityRole ur ->
			if (ur.appUser?.id) {
				if (AppUserSecurityRole.exists(ur.appUser.id, r.id)) {
				    return ['userRole.exists']
				}
			}
		}
	}

	static mapping = {
		id composite: ['appUser', 'securityRole']
		version false
	}
}
