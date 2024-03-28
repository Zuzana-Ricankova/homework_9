package homework_9.model;

public class Contribution {
    private String contribution;
    private int contributionId;
    private boolean isVisible;
    private String user;


    public Contribution(String contribution, int contributionId, boolean isVisible, String user) throws ContributionException{
        this.contribution = contribution;
        this.contributionId = contributionId;
        this.isVisible = isVisible;
        this.user = user;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }

    public int getContributionId() {
        return contributionId;
    }

    public void setContributionId(int contributionId) {
        this.contributionId = contributionId;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "contribution: " + contribution  +
                ", contributionI: " + contributionId +
                ", isVisible: " + isVisible +
                ", user: " + user + "\n";

    }
}
