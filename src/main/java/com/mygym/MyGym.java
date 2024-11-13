package com.mygym;


import com.mygym.models.User;
import com.mygym.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class MyGym {
//INIT



    private static final Logger logger = LoggerFactory.getLogger(MyGym.class);
    private final UserRepository userRepository;

    public MyGym(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // necessari per executar operacions asincrones
    @Bean(name = "processExecutor")
    public TaskExecutor workExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        threadPoolTaskExecutor.setCorePoolSize(3);
        threadPoolTaskExecutor.setMaxPoolSize(3);
        threadPoolTaskExecutor.setQueueCapacity(600);
        threadPoolTaskExecutor.afterPropertiesSet();
        logger.info("ThreadPoolTaskExecutor set");
        return threadPoolTaskExecutor;
    }

    @PostConstruct
    @Transactional
    public void init() {

        User u = new User();
        userRepository.save(u);
//        Brand b1 = null;
//        Type peelings1 = null, peelings2 = null, peelings3 = null,injectables = null,cosmeceuticals = null;
//        Product p11 = null, p12 = null, p13 = null, p2 = null, p3=null;
//        if (roleRepository.findAll().isEmpty()) {
//            roleRepository.save(new Role(ERole.ROLE_STPG));
//            roleRepository.save(new Role(ERole.ROLE_DOCTOR));
//        }
//        if(brandRepository.findAll().isEmpty()){
//            b1 = brandRepository.save(new Brand("Brand1"));
//        }
//        if(typeRepository.findAll().isEmpty()){
//            peelings1 = typeRepository.save(new Type("peelings"));
//            peelings1.setDepth("superficial");
//            peelings2 = typeRepository.save(new Type("peelings"));
//            peelings2.setDepth("medium");
//            peelings3 = typeRepository.save(new Type("peelings"));
//            peelings3.setDepth("deep");
//            injectables = typeRepository.save(new Type("injectables"));
//            cosmeceuticals = typeRepository.save(new Type("cosmeceuticals"));
//
//        }
//        if (productRepository.findAll().isEmpty()) {
//            productRepository.save(new Product("peeling superficial1", b1, peelings1));
//            productRepository.save(new Product("peeling superficial2", b1, peelings1));
//            productRepository.save(new Product("peeling superficial3", b1, peelings1));
//            productRepository.save(new Product("peeling medium1", b1, peelings2));
//            productRepository.save(new Product("peeling medium2", b1, peelings2));
//            productRepository.save(new Product("peeling medium3", b1, peelings2));
//            productRepository.save(new Product("peeling deep1", b1, peelings3));
//            productRepository.save(new Product("peeling deep2", b1, peelings3));
//            productRepository.save(new Product("peeling deep3", b1, peelings3));
//            productRepository.save(new Product("injectable1", b1, injectables));
//            productRepository.save(new Product("injectable2", b1, injectables));
//            productRepository.save(new Product("injectable3", b1, injectables));
//            productRepository.save(new Product("injectable4", b1, injectables));
//            productRepository.save(new Product("injectable5", b1, injectables));
//            productRepository.save(new Product("injectable6", b1, injectables));
//            productRepository.save(new Product("cosmeceutical1", b1, cosmeceuticals));
//            productRepository.save(new Product("cosmeceutical2", b1, cosmeceuticals));
//            productRepository.save(new Product("cosmeceutical3", b1, cosmeceuticals));
//            productRepository.save(new Product("cosmeceutical4", b1, cosmeceuticals));
//            productRepository.save(new Product("cosmeceutical5", b1, cosmeceuticals));
//            productRepository.save(new Product("cosmeceutical6", b1, cosmeceuticals));
//        }
        // google calendar init
//        try {
//            calendarController.initAPIClientService();
//            String medinet = calendarController.getMedinetCalendar();
//            calendarController.createEvent(medinet);
//        }
//        catch (Exception e){
//            throw new ServiceException("Unable to init Google Calendar API");
//        }

        // posar aqui funcions que volem que s'executin cada cop que arrenquem l'aplicacio
    }

//    @PostConstruct

    // async custom Quim?
//    @Bean
//    public Executor asyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(2);
//        executor.setMaxPoolSize(2);
//        executor.setQueueCapacity(500);
//        executor.setThreadNamePrefix("AppIncAsync-");
//        executor.initialize();
//        return executor;
//    }

    public static void main(String[] args) {
        SpringApplication.run(MyGym.class, args);
    }

}
