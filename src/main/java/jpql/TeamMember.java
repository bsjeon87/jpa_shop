package jpql;

public class TeamMember {

    private Member member;
    private Team team;

    public TeamMember(Member member, Team team) {
        this.member = member;
        this.team = team;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
