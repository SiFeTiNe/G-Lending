package com.gable.glending;

import com.gable.glending.dto.SignupDto;
import com.gable.glending.model.Item;
import com.gable.glending.model.Member;
import com.gable.glending.repository.MemberRepository;
import com.gable.glending.service.SignupService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GlendingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(GlendingApplication.class, args);
		SignupService signupService = configurableApplicationContext.getBean(SignupService.class);

		SignupDto signupDto = new SignupDto();
		signupDto.setFirstName("test");
		signupDto.setLastName("test");
		signupDto.setEmail("test@gmail.com");
		signupDto.setRole("ROLE_ADMIN");
		signupDto.setPassword("test");
		signupDto.setUsername("test");

		signupService.createMember(signupDto);
	}

}
