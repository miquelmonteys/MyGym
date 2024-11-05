package com.stpg.forms.config;

import com.stpg.forms.security.services.UserDetailsImpl;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //return "Naresh".describeConstable();
        // Can use Spring Security to return currently logged in user
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return Optional.empty();
        }
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return Optional.empty();
        }
        return Optional.ofNullable(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
    }
}
