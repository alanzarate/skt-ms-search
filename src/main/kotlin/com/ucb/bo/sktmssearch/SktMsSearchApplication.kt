package com.ucb.bo.sktmssearch

import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean
import javax.sql.DataSource


@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.ucb.bo.sktmssearch.repository")
class SktMsSearchApplication{
	@Bean
	@Throws(Exception::class)
	fun sqlSessionFactory(dataSource: DataSource?): SqlSessionFactory? {
		val factoryBean = SqlSessionFactoryBean()
		factoryBean.setDataSource(dataSource)
		val sqlSessionFactory = factoryBean.getObject()
		sqlSessionFactory!!.configuration.isMapUnderscoreToCamelCase = true
		return sqlSessionFactory
	}

}
fun main(args: Array<String>) {
	runApplication<SktMsSearchApplication>(*args)
}


