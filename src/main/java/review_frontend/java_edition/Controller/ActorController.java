package review_frontend.java_edition.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import review_frontend.java_edition.Service.ActorService;

@Controller
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @DeleteMapping()
    public ResponseEntity<?> deleteActor(@PathVariable int id){
        try {
            return new ResponseEntity<>("Delete Actor", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Delete Actor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
