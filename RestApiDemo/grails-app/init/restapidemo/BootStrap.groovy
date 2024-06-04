package restapidemo

import com.demo.SecurityRole
import com.demo.AppUser
import com.demo.AppUserSecurityRole

class BootStrap {

    // Inject necessary services for persistence
    def init = { servletContext ->
        // Create and save the initial data within a transactional block
		
		   
			def role1 = new SecurityRole(authority:'ROLE_USER')
			if (!role1.save()) {
				role1.errors.allErrors.each {
					println it
				}
			}
			println("Role ID:"+role1.id)			
			def user1 = new AppUser(username:'satish123@gmail.com',password:'kumar1',enabled:true,accountExpired:false,accountLocked:false,passwordExpired:false)
			if (!user1.save()) {
				user1.errors.allErrors.each {
					println it
				}
			}
			println("User ID:"+user1.id)			
			
			AppUserSecurityRole.create(user1,role1)		 
			AppUserSecurityRole.withSession {
				it.flush()
				it.clear()
			 }		 
		
    }
}
