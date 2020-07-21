package com.imbilalbutt.bilalButtarbisoftv14.service;

import com.imbilalbutt.bilalButtarbisoftv14.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// version 2.0
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@Service("userDetailsSer")
    public class MyUserDetailsService implements UserDetailsService {
    private static List<Person> users = new ArrayList();

    //// new 2 lines
//   /// @Qualifier
//   (repoOfUsers)
    @Autowired
    private ActiveUserStore activeUserStore;

    public MyUserDetailsService() {
        //in a real application, instead of using local data,
        // we will find user details by some other means e.g. from an external system

        //// srf neche wali line /// New
//        this.users = activeUserStore.insertFakePerons();
        users.add(new Person("erin", "123", "user"));
        users.add(new Person("bilal", "123", "user"));
        users.add(new Person("ahmad", "123", "user"));
        users.add(new Person("ali", "123", "user"));
        users.add(new Person("mike", "123", "admin"));
        users.add(new Person("admin", "123", "admin"));
    }

    // Here I am doing the loggedUserPart
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> user = users.stream()
                .filter(u -> u.getName().equals(username))
                .findAny();
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        // Here I am doing the loggedUser Part
        this.activeUserStore.addUniquePerson(user.get());
        return toUserDetails(user.get());
    }

    private UserDetails toUserDetails(Person Person) {
        return User.withUsername(Person.getName())
                .password(Person.getPassword())
                .roles(Person.getRole()).build();
    }
//
//    @Bean
//    public MyUserDetailsService MyUserDetailsService(){
//        return new MyUserDetailsService();
//    }


    //    @Bean
//    public UserDetailsService userDetailsService() {
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

//    @Bean
//    public MyUserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }

}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// version 1.0
//
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    // hard coding the users. All passwords must be encoded.
//    final List<Person> users = Arrays.asList(
//            new Person("omar", "123", "user"),
//            new Person("admin", "123", "admin"),
//            new Person("abc", "abc", "admin"),
//            new Person("def", "def", "user"),
//            new Person("ghi", "ghi", "user"),
//            new Person("jkl", "jkl", "user"),
//            new Person("mno", "mno", "user"),
//            new Person("pqr", "pqr", "user"),
//            new Person("stu", "stu", "user"),
//            new Person("vwx", "uvx", "user"),
//            new Person("xyz", "xyz", "admin"),
//            new Person("bilal", "123", "admin")
//    );
//
////    @Override
////    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
////        return new User("xyz", "xyz", new ArrayList<>());
////    }
//
////    private ActiveUserStore activeUserStore;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        for (Person appUser : this.users) {
//            if (appUser.getName().equals(username)) {
//                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//                        .commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());
//                return new User(appUser.getName(), appUser.getPassword(), grantedAuthorities);
//            }
//        }
//        throw new UsernameNotFoundException("Username: " + username + " not found");
//    }
//
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
//}
