package systems.miso.systemsdomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestSystemsdomainApplication {

	public static void main(String[] args) {
		SpringApplication.from(SystemsdomainApplication::main).with(TestSystemsdomainApplication.class).run(args);
	}

}
