package homework_9.controller;

import homework_9.model.Contribution;
import homework_9.model.ContributionException;
import homework_9.model.HttpStatusException;
import homework_9.service.ControlTheExistingId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import homework_9.service.SettingsSaveAndLoad;



@RestController
@RequestMapping("posts")
public class SocialMediaPostController {
    @Autowired
    private SettingsSaveAndLoad saveAndLoad;
    @Autowired
    private ControlTheExistingId control;

    @PostMapping("v1/newContribution")
    public String creatingNewContribution(
            @RequestBody(required = true) Contribution contribution) {
        try {
            saveAndLoad.loadContentFromFile();

            if (control.isContributionIdExists(contribution.getContributionId())) {
                throw new HttpStatusException("Contribution with the same ID already exists.");
            } else {
                saveAndLoad.saveContentToFile(contribution);

            }
        } catch (ContributionException e) {
            return "Error occurred while processing the contribution: " + e.getMessage();
        }
        return "Contribution was saved";
    }
}