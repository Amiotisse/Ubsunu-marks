package com.himnabil.alphau.client.filter;

import com.himnabil.alphau.client.error.InvalidTokenException;
import com.himnabil.alphau.client.filter.utils.TokenDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.function.Function;

/**
 * @author himna
 * @since 4/16/2017.
 */
public class AlphaUClientFilter<C,U> extends GenericFilterBean {

    private Logger log = LoggerFactory.getLogger(AlphaUClientFilter.class);
    private TokenDecoder<C> tokenDecoder;
    private String claimsAttributeName = "claims";
    private Function<C,U> claimsTransformer;

    private AlphaUClientFilter(
            TokenDecoder<C> tokenDecoder,
            String claimsAttributeName,
            Function<C, U> claimsTransformer) {
        this.tokenDecoder = tokenDecoder;
        this.claimsAttributeName = claimsAttributeName;
        this.claimsTransformer = claimsTransformer;
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {
        log.info("filtering (Start): {} ", ((HttpServletRequest)servletRequest).getServletPath() );
        String token = extractToken(servletRequest);
        C claims = tokenDecoder.decodeAndVerify(token);
        U claimsAttributeValue = claimsTransformer.apply(claims);

        servletRequest.setAttribute(claimsAttributeName , claimsAttributeValue);
        log.info("filtering (End): {} ", ((HttpServletRequest)servletRequest).getServletPath() );
        filterChain.doFilter(servletRequest , servletResponse);
    }


   private String extractToken(ServletRequest servletRequest) throws ServletException {
       HttpServletRequest request = (HttpServletRequest) servletRequest;
       String authorizationHeader = request.getHeader("Authorization");

       if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
           throw new InvalidTokenException();
       }
       return authorizationHeader.substring(7) ;

   }

    public static class Builder {
        private TokenDecoder tokenDecoder;
        private String claimsAttributeName = "claims";
        private Function claimsTransformer;

        public Builder setTokenDecoder(TokenDecoder tokenDecoder) {
            this.tokenDecoder = tokenDecoder;
            return this;
        }

        public Builder setClaimsAttributeName(String claimsAttributeName) {
            this.claimsAttributeName = claimsAttributeName;
            return this;
        }

        public Builder setClaimsTransformer(Function claimsTransformer) {
            this.claimsTransformer = claimsTransformer;
            return this;
        }

        public AlphaUClientFilter build() {
            return new AlphaUClientFilter(tokenDecoder, claimsAttributeName, claimsTransformer);
        }
    }
}
