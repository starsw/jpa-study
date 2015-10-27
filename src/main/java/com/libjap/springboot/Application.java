package com.libjap.springboot;

import com.libjap.springboot.domain.Customer;
import com.libjap.springboot.domain.CustomerRepository;
import com.libjap.springboot.domain.Division;
import com.libjap.springboot.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by coupang on 15. 10. 13..
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application implements CommandLineRunner {

	@Autowired
	CustomerRepository repository;

	public static void main(String[] args){
		SpringApplication.run(Application.class,args);
		System.out.print("Spring Boot Application Start !");
	}
	@Override
	public void run(String... strings) throws Exception {
		// save a couple of customers
		repository.save(new Customer("Hong", "Sangwoo"));
		repository.save(new Customer("Hong", "Gildong"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer by ID
		Customer customer = repository.findOne(2L);
		System.out.println("Customer found with findOne(1L):");
		System.out.println("--------------------------------");
		System.out.println(customer);
		System.out.println();

		// fetch customers by last name
		System.out.println("Customer found with findByLastName('Bauer'):");
		System.out.println("--------------------------------------------");
		for (Customer bauer : repository.findByLastName("Bauer")) {
			System.out.println(bauer);
		}
		homework1();
	}

	public void homework1() {
		//[엔티티 매니저 팩토리] - 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

		//
		EntityManager em = emf.createEntityManager();

		//
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			logicMemberCrud(em);
			logicDivisionCrud(em);
			tx.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();

	}

	public static void logicMemberCrud(EntityManager em) {

		String id = "id";
		Member member = new Member();
		member.setId(id);
		member.setUsername("지한");
		member.setAge(2);

		//등록
		em.persist(member);

		//수정
		member.setAge(20);

		//한건 조회
		Member findMember = em.find(Member.class, id);
		System.out.printf("findMember=%s, age=%s\n",findMember.getUsername(),findMember.getAge());

		//목록 조회
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.printf("members.size=%s\n",memberList.size());

		//삭제
		em.remove(member);
	}

	public static void logicDivisionCrud(EntityManager em) {

		Integer id = 1;
		Division division = new Division();
		division.setId(1);
		division.setPartname("발리팀");

		//등록
		em.persist(division);

		//수정
		division.setPartname("발리팀멋져");

		//한건 조회
		Division findDivision = em.find(Division.class, id);
		System.out.printf("findDivision id=%s, partname=%s\n",findDivision.getId(), findDivision.getPartname());

		//목록 조회
		List<Division> divisionList = em.createQuery("select d from Division d", Division.class).getResultList();
		System.out.printf("divisionList.size=%s\n",divisionList.size());

		//삭제
		em.remove(division);
	}
}
