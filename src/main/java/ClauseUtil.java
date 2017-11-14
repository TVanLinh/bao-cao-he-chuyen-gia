import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by linhtran on 14/11/17.
 */
public class ClauseUtil {

    public Clause getClauseFromString(String string) {
        String arr[] = string.split(",");
        Clause clause = new Clause();
        clause.setName(arr[0].trim());
        clause.setContent(arr[1]);
        clause.setLevel(Integer.parseInt(arr[2].trim()));
        return clause;
    }

    public Set<Clause> getClausesFromFile(String fileName) {
        Set<Clause> clauses = new LinkedHashSet<Clause>();
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());

            FileInputStream input = new FileInputStream(file);
            InputStreamReader isR = new InputStreamReader(input);
            BufferedReader bfR = new BufferedReader(isR);

            String str = "";
            while ((str = bfR.readLine()) != null) {
                if (!str.trim().equals("")) {
                    clauses.add(this.getClauseFromString(str));
                }
            }

            bfR.close();
            isR.close();
            input.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return clauses;
    }

    public String getClauseContentByClauseName(Set<Clause> clauses, String name) {
        for (Clause clause : clauses) {
            if (clause.getName().trim().equals(name.trim())) {
                return clause.getContent();
            }
        }
        return null;
    }
}
