package gitlabstatics;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Commit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    static Integer[] OBJECT_NUMBERS = {
            287,288,328,451,292,293,296,297,
            299,300,301,302,303,304,305,351,
            307,450,309,310,314,324,318,319,
            320,325,327,332,333,335,336,440,
            526,527,298,359,514,696,697
    };//这写上你要统计的项目id
    public static void main(String[] args) throws InterruptedException, ParseException, GitLabApiException {
        String username="";
        Map<String,User> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String token = "MGgLFEvyxMBkxqHyQ2gs";
        //这写上你家gitlab地址
        GitLabApi gitLabApi = new GitLabApi("http://git.virtueit.net/",token);
        //起止时间
        Date start = sdf.parse("2022-1-1 00:00:00");
        Date end = sdf.parse("2022-12-31 23:59:59");
        for (int number:OBJECT_NUMBERS) {
            List<Commit> commits = gitLabApi.getCommitsApi().getCommits(number, "master", start, end);
            for (Commit commit : commits) {
                User user = null;
                if (!username.contains(commit.getAuthorName())){
                    user = new User();
                    user.setName(commit.getAuthorName());
                    user.setLine(0);
                    user.setCommitTimes(0);
                    map.put(commit.getAuthorName(),user);
                    username+=commit.getAuthorName();
                }else {
                    user = map.get(commit.getAuthorName());
                }
                System.out.print(commit.getAuthorName()+sdf.format(commit.getCommittedDate()));
                commit = gitLabApi.getCommitsApi().getCommit(number, commit.getShortId());
                user.setLine(user.getLine()+commit.getStats().getTotal());
                user.setCommitTimes(user.getCommitTimes()+1);
                System.out.println("变更行数:"+commit.getStats().getTotal()+",累计变更行数："+user.getLine()+",累计提交次数："+user.getCommitTimes());
            }
        }
        System.out.println(map);

    }


}

class User{
    private String name;
    private int line;
    private int commitTimes;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", line=" + line +
                ", commitTimes=" + commitTimes +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCommitTimes() {
        return commitTimes;
    }

    public void setCommitTimes(int commitTimes) {
        this.commitTimes = commitTimes;
    }
}
