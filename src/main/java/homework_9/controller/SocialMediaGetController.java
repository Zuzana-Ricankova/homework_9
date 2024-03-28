package homework_9.controller;

import homework_9.model.Contribution;
import homework_9.model.ContributionException;
import homework_9.service.SettingsSaveAndLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("posts")
public class SocialMediaGetController {

    @Autowired
    private SettingsSaveAndLoad saveAndLoad;


    @GetMapping("v1/getVisiblePosts")
    public String getVisiblePosts() {
        try {
            saveAndLoad.loadContentFromFile();
            List<Contribution> visibleContributions = new ArrayList<>();
            for (Contribution checking : saveAndLoad.getAllContributions()) {
                if (checking.isVisible()) {
                    visibleContributions.add(checking);
                }
            }
            saveAndLoad.getAllContributions().clear();
            return visibleContributions.toString();
        } catch (ContributionException e) {
            return "Error occurred while loading visible contributions: " + e.getMessage();
        }
    }


    @GetMapping("v2/getAllPosts/{withInvisible}")
    public String getAllPosts(@PathVariable boolean withInvisible) {
        try {
            saveAndLoad.loadContentFromFile();
            List<Contribution> allContributions = new ArrayList<>();
            for (Contribution checking : saveAndLoad.getAllContributions()) {
                if (withInvisible == true) {
                    allContributions.add(checking);
                }
                else {
                    throw new IllegalArgumentException();
                }
            }
            saveAndLoad.getAllContributions().clear();
            return allContributions.toString() + "\nAll contributions are loaded";
        } catch (ContributionException e) {
            return "Error occurred while loading visible contributions: " + e.getMessage();
        }catch(IllegalArgumentException e) {
            return "Error Parametr 'withInvisible' has to be  'true' or 'false'." + e.getMessage();
        }
    }

}
