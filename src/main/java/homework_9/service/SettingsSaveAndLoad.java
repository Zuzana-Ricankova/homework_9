package homework_9.service;

import homework_9.model.Contribution;
import homework_9.model.ContributionException;
import homework_9.model.SettingsNameOfFile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.DateTimeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class SettingsSaveAndLoad {
    private final String fileName = SettingsNameOfFile.FILENAME_CONTRIBUTIONS;
    private List<Contribution> contributionsList = new ArrayList<>();


    public void loadContentFromFile () throws ContributionException {
        int lineCounter = 0;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))){
            while (scanner.hasNextLine()){
                lineCounter ++;
                String line = scanner.nextLine();
                String[] parts = line.split("; ");
                if (parts.length != 4) throw new ContributionException( "Wrong number of items on line: " + lineCounter + " : "+ line + "!");
                String contribution = parts[0];
                int contributionId = Integer.parseInt(parts[1]);
                boolean isVisible = Boolean.parseBoolean(parts[2]);
                String user = parts[3];
                Contribution contribution1 = new Contribution(contribution, contributionId, isVisible, user);
                contributionsList.add(contribution1);
            }
        }
        catch (FileNotFoundException e) {
            throw new ContributionException("File "+fileName+ "was not found!\n"+e.getLocalizedMessage());
        }catch (NumberFormatException e){
            throw new ContributionException("Error when reading a numerical value on line: "+lineCounter+":\n"+e.getLocalizedMessage());
        }catch (IllegalArgumentException e){
            throw new ContributionException("Error when reading the category on the number line: "+lineCounter+":\n"+e.getLocalizedMessage());
        }catch (DateTimeException e){
            throw new ContributionException("Error when reading the date on the number line: "+lineCounter+":\n"+e.getLocalizedMessage());
        }
    }

    public void saveContentToFile(Contribution contribution) throws ContributionException {
        String delimiter = "; ";
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
            writer.println(contribution.getContribution() + delimiter +
                    contribution.getContributionId() + delimiter +
                    contribution.isVisible() + delimiter +
                    contribution.getUser());
        } catch (IOException e) {
            throw new ContributionException("Error saving content to file: " + fileName + ": " + e.getMessage());
        }
    }


    public List<Contribution> getAllContributions() {
        return contributionsList;
    }

    }