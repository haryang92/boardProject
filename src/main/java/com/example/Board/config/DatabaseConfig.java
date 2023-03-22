package com.example.Board.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {

    @Autowired
    private ApplicationContext context;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*Mapper.xml"));
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfig() {
        return new org.apache.ibatis.session.Configuration();
    }

    /**
     * @Configuration
    - 스프링은 @Configuration이 선언된 클래스를 자바(Java) 기반의 설정 파일로 인식합니다.
    - 스프링 레가시의 XML 설정 방식을 Java 클래스로 대체한 것으로 생각해 주시면 되겠습니다.

     * @PropertySource
    - 해당 클래스에서 참조할 properties의 경로를 지정합니다.

     * @Autowired
    - 빈(Bean)으로 등록된 인스턴스(이하 객체)를 클래스에 주입하는 데 사용합니다.
    - @Autowired 이외에도 @Resource, @Inject 등이 존재합니다.

     * ApplicationContext
    ApplicationContext는 스프링 컨테이너(Spring Container) 중 하나입니다.
    컨테이너는 사전적 의미로 무언가를 담는 용기 또는 그릇을 의미하는데요,
    스프링 컨테이너는 빈(Bean)의 생성과 사용, 관계, 생명 주기 등을 관리합니다.

    빈(Bean)은 쉽게 이야기해서 Java 객체입니다. 예를 들어, 프로젝트에 100개의 클래스가
    있다고 가정해 보도록 하겠습니다. 이 클래스들이 서로에 대한 의존성이 높다고 했을 때
    "결합도가 높다"라고 표현하는데요, 이러한 문제를 컨테이너에서 빈(Bean)을 주입받는
    방법으로 해결할 수 있습니다.

     * @Bean
    Configuration 클래스의 메서드 레벨에만 선언이 가능하며, @Bean이 선언된 객체는
    스프링 컨테이너에 의해 관리되는 빈(Bean)으로 등록됩니다.

    해당 어노테이션은 인자로 몇 가지 속성(옵션)을 지정할 수 있습니다.

     * @ConfigurationProperties
    해당 어노테이션은 인자에 prefix 속성을 지정할 수 있고, prefix는 접두사, 즉 머리를 의미합니다.
    우리는 prefix에 spring.datasource.hikari를 선언했는데요, 쉽게 말씀드리자면
    @PropertySource에 선언된 파일(application.properties)에서 prefix에 해당하는
    spring.datasource.hikari로 시작하는 설정을 모두 읽어 들여 해당 메서드에 매핑(바인딩)하는
    개념입니다. 추가적으로 해당 어노테이션은 메서드뿐만 아니라 클래스 레벨에도 선언할 수있습니다.

     * hikariConfig
    히카리CP 객체를 생성합니다. 히카리CP는 커넥션 풀(Connection Pool) 라이브러리 중 하나입니다.

     * dataSource
    데이터 소스 객체를 생성합니다. 순수 JDBC는 SQL을 실행할 때마다 커넥션을 맺고 끊는
    I/O 작업을 하는데, 이러한 작업은 상당한 양의 리소스를 잡아먹는다고 합니다.

    이러한 문제의 해결책으로 커넥션 풀이 등장했습니다. 커넥션 풀은 커넥션 객체를 생성해두고,
    데이터베이스에 접근하는 사용자에게 미리 생성해둔 커넥션을 제공했다가 다시 돌려받는
    방법입니다. 데이터 소스는 커넥션 풀을 지원하기 위한 인터페이스입니다.

     * sqlSessionFactory
    SqlSessionFactory 객체를 생성합니다. SqlSessionFactory 는 데이터베이스의 커넥션과,
    SQL 실행에 대한 모든 것을 갖는 객체입니다.

    SqlSessionFactoryBean은 FactoryBean 인터페이스의 구현 클래스로, 마이바티스와
    스프링의 연동 모듈로 사용됩니다. 쉽게 말씀드리자면, factoryBean 객체는 데이터 소스를
    참조하며, XML Mapper(SQL 쿼리 작성 파일)의 경로와 설정 파일 경로 등의 정보를 갖는 객체입니다.

     * sqlSession
    sqlSession 객체를 생성합니다. 마이바티스 공식 문서에는 다음과 같이 정의되어 있습니다.

    1. SqlSessionTemplate은 마이바티스 스프링 연동 모듈의 핵심이다.
    2. SqlSessionTemplate은 SqlSession을 구현하고, 코드에서 SqlSession을 대체한다.
    3. SqlSessionTemplate은 쓰레드에 안전하고, 여러 개의 DAO나 Mapper에서 공유할 수 있다.
    4. 필요한 시점에 세션을 닫고, 커밋 또는 롤백하는 것을 포함한 세션의 생명주기를 관리한다.

    (무슨 말인지 하나도 모르겠습니다...)

    SqlSessionTemplate은 SqlSessionFactory를 통해 생성되고, 공식 문서의 내용과 같이
    데이터베이스의 커밋, 롤백 등 SQL의 실행에 필요한 모든 메서드를 갖는 객체로 생각할 수
    있는데요, SQL 쿼리를 작성하는 과정에서 제대로 알아보도록 하겠습니다.

     * mybatisConfig()
    application.properties에서 mybatis.configuration으로 시작하는 모든 설정을 읽어 스프링 컨테이너에
    빈(Bean)으로 등록합니다.
     */

}
