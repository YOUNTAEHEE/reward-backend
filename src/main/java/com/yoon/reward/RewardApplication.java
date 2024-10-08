package com.yoon.reward;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(
		basePackages = "com.yoon.reward",
		excludeFilters = @ComponentScan.Filter(
				type = FilterType.ANNOTATION,
				classes = { Mapper.class }
		)
)
@MapperScan(basePackages = "com.yoon.reward.mapper")
@EnableJpaRepositories(basePackages = "com.yoon.reward")
public class RewardApplication {

	public static void main(String[] args) {

		SpringApplication.run(RewardApplication.class, args);
	}

}
