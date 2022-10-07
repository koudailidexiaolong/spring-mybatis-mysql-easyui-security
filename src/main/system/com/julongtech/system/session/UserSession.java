package com.julongtech.system.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;


/**
 * 用户session类
 * @author julong
 * @date 2017-10-17 下午7:29:05
 */
public class UserSession implements UserDetails{

	private static final Logger logger = LoggerFactory.getLogger(UserSession.class);
	/**
	 * @author julong
	 * @date 2022年9月12日 上午10:03:22
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 * @author julong
	 * @date 2022年9月14日 下午8:05:00
	 */
	private  String userId;
	/**
	 * 用户姓名
	 * @author julong
	 * @date 2022年9月14日 下午8:05:08
	 */
	private  String username;
	/**
	 * 机构编号
	 * @author julong
	 * @date 2022年9月14日 下午8:05:32
	 */
	private  String orgId;
	/**
	 * 密码
	 * @author julong
	 * @date 2022年9月14日 下午8:05:42
	 */
	private  String password;
	/**
	 * 用户皮肤
	 * @author julong
	 * @date 2022年9月14日 下午8:05:49
	 */
	private  String userSkin;
	
	private  Set<GrantedAuthority> authorities;
	private  boolean accountNonExpired;
	private  boolean accountNonLocked;
	private  boolean credentialsNonExpired;
	private  boolean enabled;

	/**
     * Calls the more complex constructor with all boolean arguments set to {@code true}.
     */
    public UserSession(String userId,String username,String orgId, String password,String userSkin, Collection<? extends GrantedAuthority> authorities) {
        this(userId,username,orgId, password,userSkin, true, true, true, true, authorities);
    }

    /**
     * Construct the <code>User</code> with the details required by
     * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}.
     *
     * @param userId 自定义
     *       
     * @param username the username presented to the
     *        <code>DaoAuthenticationProvider</code>
     * @param password the password that should be presented to the
     *        <code>DaoAuthenticationProvider</code>
     * @param enabled set to <code>true</code> if the user is enabled
     * @param accountNonExpired set to <code>true</code> if the account has not
     *        expired
     * @param credentialsNonExpired set to <code>true</code> if the credentials
     *        have not expired
     * @param accountNonLocked set to <code>true</code> if the account is not
     *        locked
     * @param authorities the authorities that should be granted to the caller
     *        if they presented the correct username and password and the user
     *        is enabled. Not null.
     *
     * @throws IllegalArgumentException if a <code>null</code> value was passed
     *         either as a parameter or as an element in the
     *         <code>GrantedAuthority</code> collection
     */
    public UserSession(String userId,String username,String orgId,  String password, String userSkin, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {

        if (((userId == null) || "".equals(userId)) || (password == null)) {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }

        this.userId = userId;
        this.username = username;
        this.orgId = orgId;
        this.password = password;
        this.userSkin = userSkin;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
    }


	public String getUserId() {
		return userId;
	}

	public String getOrgId() {
		return orgId;
	}
	
	public String getUserSkin() {
		return userSkin;
	}
	
	public String getUserName() {
		return username;
	}

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}


    /**
     * Returns {@code true} if the supplied object is a {@code User} instance with the
     * same {@code username} value.
     * <p>
     * In other words, the objects are equal if they have the same username, representing the
     * same principal.
     */
    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof User) {
            return this.userId.equals(((User) rhs).getUsername());
        }
        return false;
    }

    /**
     * Returns the hashcode of the {@code userId}.
     */
    @Override
    public int hashCode() {
        return userId.hashCode();
    }
	
    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities =
            new TreeSet<GrantedAuthority>(new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to the set.
            // If the authority is null, it is a custom authority and should precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
    }
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("userId: ").append(this.userId).append("; ");
		sb.append("username: ").append(this.username).append("; ");
		sb.append("userSkin: ").append(this.userSkin).append("; ");
		sb.append("orgId: ").append(this.orgId).append("; ");
		sb.append("Password: [PROTECTED]; ");
		sb.append("Enabled: ").append(this.enabled).append("; ");
		sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
		sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
		sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");

		if (!authorities.isEmpty()) {
			sb.append("Granted Authorities: ");

			boolean first = true;
			for (GrantedAuthority auth : authorities) {
				if (!first) {
					sb.append(",");
				}
				first = false;

				sb.append(auth);
			}
		} else {
			sb.append("Not granted any authorities");
		}

		return sb.toString();
	}
	
	/**
	 * 获取用户session 信息
	 * @return
	 * @author julong
	 * @date 2022年9月12日 上午11:04:30
	 * @desc
	 */
	public static UserSession getUserSession(){
		logger.debug("【session】-获取当前用户session-principal:{}",SecurityContextHolder.getContext());
		logger.debug("【session】-获取当前用户session-principal:{}",SecurityContextHolder.getContext().getAuthentication());
		if(null == SecurityContextHolder.getContext().getAuthentication()){
			return null; 
		}
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  logger.debug("【session】-获取当前用户session-principal:{}",principal);
		  return (UserSession) principal;
		}
		return null; 
	}
	
}
