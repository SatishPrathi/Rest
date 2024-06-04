// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.password.hash.iterations = 1
grails.plugin.springsecurity.logout.postOnly = false
// Define user and role classes
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.demo.AppUser'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.demo.AppUserSecurityRole'
grails.plugin.springsecurity.authority.className = 'com.demo.SecurityRole'

// REST login configuration
grails.plugin.springsecurity.rest.login.active = true
grails.plugin.springsecurity.rest.login.endpointUrl = '/api/login'
grails.plugin.springsecurity.rest.login.failureStatusCode = 401
grails.plugin.springsecurity.rest.login.useJsonCredentials = true
grails.plugin.springsecurity.rest.login.usernamePropertyName = 'username'
grails.plugin.springsecurity.rest.login.passwordPropertyName = 'password'
grails.plugin.springsecurity.rest.login.useRequestParamsCredentials = false

// REST logout configuration
grails.plugin.springsecurity.rest.logout.endpointUrl = '/api/logout'

// Token validation configuration
grails.plugin.springsecurity.rest.token.validation.useBearerToken = false
grails.plugin.springsecurity.rest.token.validation.headerName = 'X-Auth-Token'
grails.plugin.springsecurity.rest.token.validation.active = true

// Token storage configuration
grails.plugin.springsecurity.rest.token.storage.useGorm = true
grails.plugin.springsecurity.rest.token.storage.gorm.tokenDomainClassName = 'com.demo.AuthenticationToken'
grails.plugin.springsecurity.rest.token.storage.gorm.tokenValuePropertyName = 'tokenValue'
grails.plugin.springsecurity.rest.token.storage.gorm.usernamePropertyName = 'username'

grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.interceptUrlMap=[
[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
    [pattern: '/api/login',      access: ['permitAll']],
	[pattern: '/api/logout', access: ['isFullyAuthenticated()']]
]
grails.plugin.springsecurity.filterChain.chainMap = 
[
	 [pattern: '/assets/**',      filters: 'none'],
	 [pattern: '/**/js/**',       filters: 'none'],
	 [pattern: '/**/css/**',      filters: 'none'],
	 [pattern: '/**/images/**',   filters: 'none'],
	 [pattern: '/**/favicon.ico', filters: 'none'],
	 [pattern: '/**',             filters: 'JOINED_FILTERS']
]


// Additional filter chain configuration
grails.plugin.springsecurity.filterChain.chainMap = [
    // Stateless chain
    [pattern: '/api/**', filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter'],
    [pattern: '/**', filters: 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter']
]