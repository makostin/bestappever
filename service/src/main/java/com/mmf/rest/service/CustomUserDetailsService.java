package com.mmf.rest.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * svetlana.voyteh
 * 30.04.13
 */
public class CustomUserDetailsService implements UserDetailsService {
//    private static final String[] DEFAULT_REMOTE_RIGHTS = {RoleManager.USER_TEAM};
//    @Autowired
//    private AccountDAO accountDAO;
//    @Autowired
//    private RoleDAO roleDAO;
//    @Autowired
//    private RoleManager roleManager;
//    @Autowired
//    private DaoAuthenticationProvider daoAuthenticationProvider;
//
//    @Override
//    public UserDetails loadUserByUsername(String username)
//            throws UsernameNotFoundException, DataAccessException {
//
//        Account account;
//        try {
//            account = accountDAO.getByLogin(username);
//            if (account == null) {
//                throw new UsernameNotFoundException(null);
//            }
//        } catch (com.hiqo.croptracker.core.model.dao.DataAccessException ex) {
//            throw new DataRetrievalFailureException(null, ex);
//        }
//
//        return buildUserFromUserEntity(account);
//    }
//
//    private UserDetails buildUserFromUserEntity(Account account) {
//        return buildUserFromUserEntity(account, false);
//    }
//
//    private UserDetails buildUserFromUserEntity(Account account, boolean remoteAccess) {
//
//        String username = account.getLogin();
//        String password = account.getPassword();
//
//        boolean enabled = AccountStatusDAO.ACTIVE_STATUS_CODE.equals(account.getAccountStatus().getCode())
//                && (account.getTeam() == null || AccountStatusDAO.ACTIVE_STATUS_CODE.equals(account.getTeam().getAccountStatus().getCode()));
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//        if (!remoteAccess) {
//            if ((account.getRoles().isEmpty())
//                    || (account.getRoles().size() == 1 && RoleManager.USER_HANDHELD.equals(account.getRoles().iterator().next().getCode()))) {
//                accountNonLocked = false;
//            }
//        }
//
//        // Add user role access rights
//        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        Set<Role> roles = account.getRoles();
//        for (Role role : roles) {
//            authorities.add(new GrantedAuthorityImpl("ROLE_" + role.getCode()));
//        }
//        if (remoteAccess) {
//            for (String roleCode : DEFAULT_REMOTE_RIGHTS) {
//                authorities.add(new GrantedAuthorityImpl("ROLE_" + roleCode));
//            }
//        }
//
//        // Add workflow access rights
//        if (account.getTeam() == null) { // Superadmin
//            authorities.add(new GrantedAuthorityImpl("ROLE_TEAM_" + WorkflowDAO.WORKFLOW_INPUT));
//            authorities.add(new GrantedAuthorityImpl("ROLE_TEAM_" + WorkflowDAO.WORKFLOW_WATER));
//        } else {
//            Collection<Workflow> teamWorkflows = account.getTeam().getWorkflows();
//            if (teamWorkflows != null) {
//                for (Workflow workflow : teamWorkflows) {
//                    authorities.add(new GrantedAuthorityImpl("ROLE_TEAM_" + workflow.getCode()));
//                }
//            }
//            authorities.add(new GrantedAuthorityImpl("ROLE_TEAM_" + account.getTeam().getMeasureType().getCode()));
//
//        }
//
//        // Return user data
//        return new User(username, password, enabled,
//                accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//    }
}
