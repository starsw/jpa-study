package com.libjap.springboot;

import com.libjap.springboot.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

	public static void main(String[] args){
		SpringApplication.run(Application.class,args);
		System.out.print("Spring Boot Application Start !");
	}
	@Override
	public void run(String... strings) throws Exception {
//      example1();
		homework1();
	}

    public void example1(){
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
//			logicMemberCrud(em);
//			logicDivisionCrud(em);
            logicMemberSingleCreate(em);
			tx.commit();

		} catch (Exception e) {
			System.out.print(e.getMessage());
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();

	}

	public static void logicMemberCrud(EntityManager em) {
        int id = 1;
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

		int id = 1;
		Team team = new Team();
		team.setId(id);
		team.setName("발리팀");

		//등록
		em.persist(team);

		//수정
		team.setName("발리팀멋져");

		//한건 조회
		Team findTeam = em.find(Team.class, id);
		System.out.printf("findTeam id=%s, partname=%s\n", findTeam.getId(), findTeam.getName());

		//목록 조회
		List<Team> teamList = em.createQuery("select t from Team t", Team.class).getResultList();
		System.out.printf("teamList.size=%s\n", teamList.size());

		//삭제
		em.remove(team);
	}

    public void logicMemberSingleCreate(EntityManager em) {


        Team team = new Team();
        team.setId(2);
        team.setName("bali");

        Member member = new Member();
        member.setId(2);
        member.setUsername("지한");
        member.setAge(2);
        member.setTeam(team);

        //등록

        em.persist(member);
    }
}
