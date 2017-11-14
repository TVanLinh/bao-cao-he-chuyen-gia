import java.util.Set;

/**
 * Created by linhtran on 14/11/17.
 */
public class Rule {
    private Set<Clause> left;
    private Clause right;

    public Set<Clause> getLeft() {
        return left;
    }

    public void setLeft(Set<Clause> left) {
        this.left = left;
    }

    public Clause getRight() {
        return right;
    }

    public void setRight(Clause right) {
        this.right = right;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Clause clause : this.left) {
            str.append(clause.getContent()).append(" ,");
        }
        str.replace(str.length() - 1, str.length() - 1, "");
        if (this.right != null) {
            str.append("\t====>\t").append(this.right.getContent());
        }
        return str.toString();
    }

    @Override
    public int hashCode() {
        int hasCode = 0;
        for (Clause clause : this.left) {
            hasCode += clause.hashCode();
        }
        hasCode += this.right.hashCode();
        return hasCode;
    }
}
