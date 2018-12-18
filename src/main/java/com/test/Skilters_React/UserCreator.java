package com.test.Skilters_React;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.test.Skilters_React.model.User;
import com.test.Skilters_React.model.User.t_status;
import com.test.Skilters_React.model.UserRepository;

@Component
class UserCreator {

    @Autowired
    private UserRepository repository;

    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
    	Calendar cal1 = Calendar.getInstance();
		cal1.set(2019, 02, 01);
		Date d1 = cal1.getTime();
		repository.save(new User("Mehdi","Benazzou","mehdi@mehdi.com","0123456789","Informatique",d1,bCryptPasswordEncoder.encode("123"), t_status.valide));
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(2019, 02, 01);
		Date d2 = cal2.getTime();
		repository.save(new User("Younes","Ziani","younes@younes.com","0123456789","Informatique",d2,bCryptPasswordEncoder.encode("123"),t_status.actif));
		
		Calendar cal3 = Calendar.getInstance();
		cal3.set(2019, 02, 01);
		Date d3 = cal3.getTime();
		repository.save(new User("Marwa","Bourhanzour","marwa@marwa.com","0123456789","Informatique",d3,bCryptPasswordEncoder.encode("123"),t_status.non_valide));
		
		Calendar cal4 = Calendar.getInstance();
		cal4.set(2019, 02, 01);
		Date d4 = cal4.getTime();
		repository.save(new User("Famo","Conde","famo@famo.com","0123456789","Informatique",d4,bCryptPasswordEncoder.encode("123"),t_status.valide));
		
		Calendar cal5 = Calendar.getInstance();
		cal5.set(2019, 02, 01);
		Date d5 = cal5.getTime();
		repository.save(new User("Fouad","Benazzou","fouad@fouad.com","0123456789","Informatique",d5,bCryptPasswordEncoder.encode("123"),t_status.valide));
    }
}
