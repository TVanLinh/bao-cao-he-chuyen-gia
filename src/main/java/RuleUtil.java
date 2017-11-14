import java.io.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by linhtran on 14/11/17.
 */
public class RuleUtil {

    public Set<Rule> readRulesFromFile(String fileName) {
        Set<Rule> rules = new LinkedHashSet<Rule>();
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());

            FileInputStream input = new FileInputStream(file);
            InputStreamReader isR = new InputStreamReader(input);
            BufferedReader bfR = new BufferedReader(isR);

            String str = "";
            while ((str = bfR.readLine()) != null) {
                rules.add(this.getRuleFromString(str));
            }
            bfR.close();
            isR.close();
            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return rules;
    }

    private Rule getRuleFromString(String string) {
        Rule rule = new Rule();
        Clause right = new Clause();
        String arr[] = string.split("->");
        if (arr.length > 0) {
            rule.setLeft(this.getClausesFromString(arr[0]));
        }
        if (arr.length > 1) {
            rule.setRight(new Clause(arr[1]));
        }
        return rule;
    }

    private Set<Clause> getClausesFromString(String str) {
        Set<Clause> clause = new LinkedHashSet<Clause>();
        String arr[] = str.split(",");
        for (String item : arr) {
            clause.add(new Clause(item.trim()));
        }
        return clause;
    }


}
