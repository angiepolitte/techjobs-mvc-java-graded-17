package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

//If the user enters “all” in the search box, or if they leave the box empty, call the findAll() method from JobData.
// Otherwise, send the search information to findByColumnAndValue. In either case, store the results in a jobs ArrayList.

    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", columnChoices);
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        ArrayList<Job> jobs;
            if (searchTerm.equals("all")) {
                jobs = JobData.findAll();
            } else {
                jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            }
        model.addAttribute("jobs", jobs);

        model.addAttribute("title", "Jobs with " + searchType + ": " + searchTerm);
            return "search";
    }


}

