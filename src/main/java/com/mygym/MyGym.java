package com.mygym;


import com.mygym.models.User;
import com.mygym.repository.UserRepository;
import com.mygym.models.Exercici;
import com.mygym.models.HistoricRutina;
import com.mygym.models.Rutina;
import com.mygym.models.Serie;
import com.mygym.repository.ExerciciRepository;
import com.mygym.repository.HistoricRutinaRepository;
import com.mygym.repository.RutinaRepository;
import com.mygym.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class MyGym {
//INIT


    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(MyGym.class);
    @Autowired
    private UserService userService;

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
    //NO SE FINS ON ESTA BE
    @Autowired
    ExerciciRepository exerciciRepository;

    @Autowired
    RutinaRepository rutinaRepository;

    @Autowired
    HistoricRutinaRepository historicRutinaRepository;
    //NO SE FINS ON ESTA BE
    @PostConstruct
    @Transactional
    public void init() {

        if(userRepository.findAll().isEmpty()){
            User u = new User();
            userRepository.save(u);
        }
        if (rutinaRepository.findAll().isEmpty()) {
            // Crear exercicis comuns per a les rutines
            Exercici pressBanca = new Exercici(null, "Press Banca", "Treballa el pit", "Pit");
            Exercici curlBiceps = new Exercici(null, "Curl Biceps", "Treballa els bíceps", "Braços");
            Exercici pressEspatlles = new Exercici(null, "Press Espatlles", "Treballa les espatlles", "Espatlles");
            Exercici pesMort = new Exercici(null, "Pes Mort", "Treballa l'esquena i les cames", "Esquena/Cames");
            Exercici extensioQuadriceps = new Exercici(null, "Extensió Quadriceps", "Treballa els quàdriceps", "Cames");
            Exercici squat = new Exercici(null, "Sentadeta", "Treballa les cames", "Cames");

            exerciciRepository.saveAll(Arrays.asList(pressBanca, curlBiceps, pressEspatlles, pesMort, extensioQuadriceps, squat));

            // Crear la rutina Push-Pull-Legs
            Rutina rutinaPushPullLegs = new Rutina("Push-Pull-Legs", "Rutina clàssica de divisió push-pull-legs");
            rutinaPushPullLegs.setExercicis(Arrays.asList(
                    pressBanca.getId(),
                    pressEspatlles.getId(),
                    pesMort.getId(),
                    squat.getId()
            ));
            rutinaPushPullLegs.setDescans(1.5);
            rutinaPushPullLegs.setDuracio(1.0);
            rutinaPushPullLegs.setIsDefault(true);
            rutinaRepository.save(rutinaPushPullLegs);

            // Crear la rutina Full Body
            Rutina rutinaFullBody = new Rutina("Full Body", "Rutina que treballa tot el cos en una sessió");
            rutinaFullBody.setExercicis(Arrays.asList(
                    pressBanca.getId(),
                    curlBiceps.getId(),
                    squat.getId(),
                    pressEspatlles.getId()
            ));
            rutinaFullBody.setDescans(2.0);
            rutinaFullBody.setDuracio(1.5);
            rutinaFullBody.setIsDefault(true);
            rutinaRepository.save(rutinaFullBody);

            // Crear la rutina Torso-Pierna
            Rutina rutinaTorsoPierna = new Rutina("Torso-Pierna", "Rutina dividida en sessions de torso i sessions de cames");
            rutinaTorsoPierna.setExercicis(Arrays.asList(
                    pressBanca.getId(),
                    pressEspatlles.getId(),
                    extensioQuadriceps.getId(),
                    squat.getId()
            ));
            rutinaTorsoPierna.setDescans(1.8);
            rutinaTorsoPierna.setDuracio(1.2);
            rutinaTorsoPierna.setIsDefault(true);
            rutinaRepository.save(rutinaTorsoPierna);

            // Crear la rutina Push-Pull-Legs
            Rutina rutinaPPL2 = new Rutina("Push-Pull-Legs2", "Rutina clàssica de divisió push-pull-legs");
            rutinaPushPullLegs.setExercicis(Arrays.asList(
                    pressBanca.getId(),
                    pressEspatlles.getId(),
                    pesMort.getId(),
                    squat.getId()
            ));
            rutinaPushPullLegs.setDescans(1.5);
            rutinaPushPullLegs.setDuracio(1.0);
            rutinaRepository.save(rutinaPPL2);
            User u = userService.getFirstUser();
            u.addRutinaPersonal(rutinaPPL2.getId());
            userRepository.save(u);

        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MyGym.class, args);
    }

}
