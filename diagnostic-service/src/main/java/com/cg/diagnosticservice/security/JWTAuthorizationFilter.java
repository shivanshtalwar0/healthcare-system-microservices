package com.cg.diagnosticservice.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    JWTAuthorizationFilter(AuthenticationManager authenticationManager){
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        logger.info(header);

        if (header == null || !header.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            logger.info(token.replace("Bearer", ""));
            // parse the token.

            // 4. Validate the token
            Claims claims = Jwts.parser()
                    .setSigningKey("secure")
                    .parseClaimsJws(token.replace("Bearer", ""))
                    .getBody();
            ArrayList<LinkedHashMap<String,String>> authorities=((ArrayList<LinkedHashMap<String,String>>)claims.get("roles"));
            logger.warn(authorities);
            List<String> authortityList=new ArrayList<String>();
            authorities.forEach((k)->{
                authortityList.add(k.get("authority"));
            });
            logger.warn(authortityList);
            Collection<GrantedAuthority> grantedAuthorities=AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",",authortityList));
            Date expiration=claims.getExpiration();
            String user =claims.getSubject();
            if (user != null&&expiration.compareTo(new Date())>0) {
                logger.warn("user authenticated");
                return new UsernamePasswordAuthenticationToken(user, null,grantedAuthorities);
            }
            logger.warn("first inner null");
            return null;
        }
        logger.warn("outer inner null");
        return null;
    }


}
