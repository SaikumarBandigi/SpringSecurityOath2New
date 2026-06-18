package com.spring.security.oath.config.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// the server
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


// the default html page login
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;

//  username & password from login page + username & password from withUser, password check happens here
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// equals logic really happens here
import org.springframework.security.crypto.password.NoOpPasswordEncoder; // (doubt)

//
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


//


import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
/*
here additionalAuthenticationChecks method is for password checking
here retrieveUser is for username checking

passwordEncoder.isPasswordValid(
    userDetails.getPassword(),   // stored password
    presentedPassword,           // UI password
    salt
)

-----------------------------
👉 credentials = ONLY password
👉 principal = username / UserDetails

Note:
here UserDetailsService is interface there are several classes whicl impl this interafce how can i know which real rumtime class is implementing



 */


@SpringBootApplication
public class SpringSecurityOath2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOath2Application.class, args);
    }

}


/*

the flow is
http://localhost:8082/ui/

then after clicking it Login to OAuth here it takes to
http://localhost:8081/auth/login

after verifying

http://localhost:8082/ui/secure opens with succes


 */

/*
Complete Flow

1) Open
http://localhost:8082/ui

3) Click "Login to OAuth here"
<a href="secure">Login to OAuth here</a>

4) /secure requires authentication because:
.anyRequest().authenticated();

5) @EnableOAuth2Sso starts OAuth flow and redirects to:
http://localhost:8081/auth/oauth/authorize

6) User is not logged into Authorization Server.

7) Spring Security intercepts /oauth/authorize and redirects to its default login page:
http://localhost:8081/auth/login

8) You enter:
Username: ramu
Password: ramu
which comes from:
.inMemoryAuthentication()
    .withUser("ramu")
    .password("ramu")
    .roles("USER");

9) After successful login, Spring Security sends you back to:
/auth/oauth/authorize

10) Authorization code is generated and sent to the client.

11) Client exchanges the code for an access token using:
accessTokenUri:
  http://localhost:8081/auth/oauth/token

12) Client fetches user details from:
userInfoUri:
  http://localhost:8081/auth/rest/hello/principal

Finally you reach:
http://localhost:8082/ui/secure
 */

/// ////////////////////////////////////////////////////////////////////////
/*

| Case                                | What happens                      |
| ----------------------------------- | --------------------------------- |
| No `.loginPage()` configured        | Spring generates default login UI |
| `.loginPage("/myLogin")` configured | YOU must provide that page        |
| `.loginPage(...)` + controller/view | You serve your own HTML           |

 */