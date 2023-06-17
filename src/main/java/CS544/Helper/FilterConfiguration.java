package CS544.Helper;

import CS544.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;
import java.util.Base64;

import io.jsonwebtoken.*;
@Configuration
public class FilterConfiguration extends GenericFilterBean {
    @Autowired
    private UserService userService;
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain
            filterChain)throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req, res);
        } else if ("/user/add".equals(request.getRequestURI())) {
            // Allow access to the "/public" path without authentication
            filterChain.doFilter(req, res);
        }
        else if ("/user/login".equals(request.getRequestURI())) {
            // Allow access to the "/public" path without authentication
            filterChain.doFilter(req, res);
        } else {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("Missing or invalid Authorization header");
            }
            final String token = authHeader.substring(7);
            try {
                // Verify and parse the JWT token
                final Claims claims = Jwts.parser()
                        .setSigningKey(Base64.getDecoder().decode("jOfj0dc1JGPpTE/1O4JQDBdonFDeAFiYMFh+P1z6FuI="))
                        .parseClaimsJws(token)
                        .getBody();
                // Extract the username from the claims
                String username = claims.getSubject();

                // Check if the user is registered
                if (!userService.isExistsUserName(username)) {
                    throw new ServletException("User is not registered");
                }

                // Proceed with the authenticated request
                request.setAttribute("claims", claims);
                filterChain.doFilter(req, res);
            } catch (SignatureException e) {
                throw new ServletException("Invalid token");
            }
        }
    }
}
