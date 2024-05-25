package kr.co.dbinc.back_work;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
@MapperScan(basePackages = "kr.co.dbinc.back_work.mapper")
public class BackWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackWorkApplication.class, args);
	}

}
