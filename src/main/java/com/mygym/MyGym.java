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
import java.util.List;

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


    @PostConstruct
    @Transactional
    public void init() {

        if(userRepository.findAll().isEmpty()){
            User u = new User();
            userRepository.save(u);
        }
        if (rutinaRepository.findAll().isEmpty() && exerciciRepository.findAll().isEmpty()) {
            // Crear exercicis comuns per a les rutines
            Exercici pressBanca = new Exercici("Press Banca", "Treballa el pit", "Pit");
            Exercici curlBiceps = new Exercici("Curl Biceps", "Treballa els bíceps", "Braços");
            Exercici pressEspatlles = new Exercici("Press Espatlles", "Treballa les espatlles", "Espatlles");
            Exercici pesMort = new Exercici("Pes Mort", "Treballa l'esquena i les cames", "Esquena/Cames");
            Exercici extensioQuadriceps = new Exercici("Extensió Quadriceps", "Treballa els quàdriceps", "Cames");
            Exercici squat = new Exercici("Sentadeta", "Treballa les cames", "Cames");

            exerciciRepository.saveAll(Arrays.asList(pressBanca, curlBiceps, pressEspatlles, pesMort, extensioQuadriceps, squat));

            List<Integer> series = Arrays.asList(4,4,4,4);
            // Crear la rutina Push-Pull-Legs
            Rutina rutinaPushPullLegs = new Rutina("Push-Pull-Legs", "Rutina clàssica de divisió push-pull-legs");
            rutinaPushPullLegs.setExercicis(Arrays.asList(
                    pesMort.getId(),
                    pressBanca.getId(),
                    pressEspatlles.getId(),
                    squat.getId()
            ));
            rutinaPushPullLegs.setSeries(series);
            rutinaPushPullLegs.setIsDefault(true);
            rutinaRepository.save(rutinaPushPullLegs);

            // Crear la rutina Full Body
            Rutina rutinaFullBody = new Rutina("Full Body", "Rutina que treballa tot el cos en una sessió");
            rutinaFullBody.setExercicis(Arrays.asList(
                    curlBiceps.getId(),
                    pressBanca.getId(),
                    squat.getId(),
                    pressEspatlles.getId()
            ));
            rutinaFullBody.setSeries(series);
            rutinaFullBody.setIsDefault(true);
            rutinaRepository.save(rutinaFullBody);

            // Crear la rutina Torso-Pierna
            Rutina rutinaTorsoPierna = new Rutina("Torso-Pierna", "Rutina dividida en sessions de torso i sessions de cames");
            rutinaTorsoPierna.setExercicis(Arrays.asList(
                    extensioQuadriceps.getId(),
                    pressBanca.getId(),
                    pressEspatlles.getId(),
                    squat.getId()
            ));
            rutinaTorsoPierna.setSeries(series);
            rutinaTorsoPierna.setIsDefault(true);
            rutinaRepository.save(rutinaTorsoPierna);

            // Crear la rutina Push-Pull-Legs
            Rutina rutinaPPL2 = new Rutina("Push-Pull-Legs2", "Rutina clàssica de divisió push-pull-legs");
            rutinaPPL2.setExercicis(Arrays.asList(
                    pressEspatlles.getId(),
                    pressBanca.getId(),
                    pesMort.getId(),
                    squat.getId()
            ));
            rutinaPPL2.setSeries(series);
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
