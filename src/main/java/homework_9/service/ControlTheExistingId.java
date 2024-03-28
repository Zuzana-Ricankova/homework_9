package homework_9.service;

import homework_9.model.ContributionException;
import homework_9.model.SettingsNameOfFile;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

@Service
public class ControlTheExistingId {
    private final String fileName = SettingsNameOfFile.FILENAME_CONTRIBUTIONS;



    public boolean isContributionIdExists(int contributionId) throws ContributionException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("; ");
                int id = Integer.parseInt(parts[1]);
                if (id == contributionId) {
                    return true;
                }
            }
            return false;
        } catch (FileNotFoundException e) {
            throw new ContributionException("File " + fileName + " was not found!\n" + e.getLocalizedMessage());
        } catch (NumberFormatException e) {
            throw new ContributionException("Error when reading a numerical value from file:\n" + e.getLocalizedMessage());
        }
    }
}
