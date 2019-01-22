package pattern3;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UseAnnotation {
    @Scheduled(cron = "*/1 * * * * ?")
    public void run() {
        System.out.println("UseAnnotation run ...");
    }

}
