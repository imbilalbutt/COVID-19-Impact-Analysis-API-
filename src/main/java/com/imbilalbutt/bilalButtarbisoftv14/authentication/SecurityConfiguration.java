package com.imbilalbutt.bilalButtarbisoftv14.authentication;

import com.imbilalbutt.bilalButtarbisoftv14.filter.JwtRequestFilter;
import com.imbilalbutt.bilalButtarbisoftv14.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    fakePersons fakeMan;


    @Qualifier("userDetailsSer") // Qualifier mein value inject hoti hai.
    @Autowired private MyUserDetailsService myUserDetailsService;
//    @Autowired private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        //                      (myUserDetailsService)
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    ////////////////////////
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //////////////////////// authentication part uncomment only agli line
        auth.userDetailsService(myUserDetailsService);
        ///////////////////////////////// Below lines are old
//        auth.inMemoryAuthentication()
//                .withUser("bilal")
//                .password("123")
//                .roles("user")
//                .and()
//                .withUser("admin")
//                .password("admin")
//                .roles("admin");
    }

    //////////////////// NEW

//    @Bean
//    @Override
//    public UserDetailsManager userDetailsService() { //in book userDetailsService
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(
//                User.withUsername("bilal")
//                        .password("123").roles("user").build());
//        manager.createUser(
//                User.withUsername("ahmad")
//                        .password("234").roles("user", "admin").build());
//        return manager;
//    }


    //////////////////////// uncomment to revert
//    @Bean
//    public UserDetailsService userDetailsService() {
////        activeUserStore.insertFakePerons();
////        UserDetails userDetails;
////        List<UserDetails> userDetailsList = new ArrayList<>();
////
////        for (Person singlePerson: activeUserStore.getUsers()) {
//////            userDetails.add(User.withDefaultPasswordEncoder()
//////                    .username((singlePerson.getName()))
//////                    .password(singlePerson.getPassword())
//////                    .roles((singlePerson.getRole()))
//////                    .build());
////            userDetails = new User(singlePerson.getName(), singlePerson.getPassword(), new ArrayList<>());
////            userDetailsList.add((userDetails));
////        }
//
//        /////////////////////////////////////////////////////////
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("bilal")
//                .password("123")
//                .roles("user")
//                .build();
//        UserDetails Admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .roles("user", "admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

     //////Specifying the mapping of path to the role.
//    @Override protected void configure(HttpSecurity http) throws Exception {
//
//        //////////////////////// jwt authentication part
////        http.csrf().disable().authorizeRequests()
////                .antMatchers("/authenticate").permitAll()
////                .anyRequest().authenticated()
////                .and().sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Spring don'e save sessions, or not create any session
////        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//    ///////////////////////////////////
//        http.authorizeRequests()
//                .antMatchers("/admin").hasRole("admin")
//                .antMatchers("/loggedUsers").hasRole("admin")
//                .antMatchers("/user").hasAnyRole("user", "admin")
//                .antMatchers("/csvData").hasAnyRole("user", "admin")
//                .antMatchers("/highest").hasAnyRole("user", "admin")
//                .antMatchers("/corona").hasAnyRole("user", "admin")
//                .antMatchers("/country").hasAnyRole("user", "admin")
//                .antMatchers("/").permitAll()
//                .and().formLogin(formLogin -> formLogin.loginPage("/login").failureUrl("/login-error"));
//    }

    /// Upar wala
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.csrf().disable().authorizeRequests()
                // dont authenticate this particular request
                .antMatchers("/authenticate").permitAll()
                // all other requests need to be authenticated
                .anyRequest().authenticated().and()
                // make sure we use stateless session; session won't be used to
                // store user's state.
                .exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);




///////////////////////////////////////////////////////////////////////////////////////////////////
/*                  Was Using this one                                                           */
///////////////////////////////////////////////////////////////////////////////////////////////////
//        httpSecurity.csrf().disable().authorizeRequests()
//                // dont authenticate this particular request
//                .antMatchers("/authenticate").permitAll()
//                // all other requests need to be authenticated
//                ///////////////////////////////////////////////
//                .antMatchers("/admin").hasRole("admin")
//                .antMatchers("/loggedUsers").hasRole("admin")
//                .antMatchers("/user").hasAnyRole("user", "admin")
//                .antMatchers("/csvData").hasAnyRole("user", "admin")
//                .antMatchers("/highest").hasAnyRole("user", "admin")
//                .antMatchers("/corona").hasAnyRole("user", "admin")
//                .antMatchers("/country").hasAnyRole("user", "admin")
//                .antMatchers("/").permitAll()
//                //////////////////////////////////////////////
//                .anyRequest().authenticated()
//                .and()
//                // make sure we use stateless session; session won't be used to
//                // store user's state.                              //
//                .exceptionHandling().and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().formLogin(formLogin -> formLogin.loginPage("/login").failureUrl("/login-error"));
//
// Don't create sessions.
// Creating filter is not enought, we need to tell "WebSecurtitConfiguruer"
// to use that filter chain.
// I need to inject into the filter chain.
// thus add .sessionCreationPolicy(........)
///////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    //////////////////////// authentication part
    //////////////// ok
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws  Exception{
        return super.authenticationManagerBean();
    }

    // This was causing error
//    @Bean
//    public SecurityConfiguration MySecurityConfiguration(){
//        return new SecurityConfiguration();
//    }

}
