/**
 * Created by linhtran on 14/11/17.
 */
public class Clause {
    private int level;
    private String content;
    private String name;

    public Clause() {
    }

    public Clause(String name) {
        this.name = name;
    }

    public Clause(String name, String content) {
        this.content = content;
        this.name = name;
    }

    public Clause(int level, String content, String name) {
        this.level = level;
        this.content = content;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return name.trim().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Clause clause = (Clause) obj;
        return this.name.trim().equals(clause.getName().trim());
    }

    @Override
    public String toString() {
        return "Clause{" +
                "level=" + level +
                ", content='" + content + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
