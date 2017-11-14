import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by linhtran on 14/11/17.
 */
public class Exercise {

    Set<Rule> RULES = new LinkedHashSet<Rule>();

    Set<Rule> SAT = new LinkedHashSet<Rule>();

    Set<Clause> TEMP = new LinkedHashSet<Clause>();


    Set<Clause> GT = new LinkedHashSet<Clause>();

    Set<Rule> conslution = new LinkedHashSet<Rule>();

    private Set<Clause> clauses;

    public void thuatToanSuyDienTien(String fileName) {

        RuleUtil ruleUtil = new RuleUtil();
        if (this.RULES.size() == 0) {
            this.RULES = ruleUtil.readRulesFromFile(fileName);
        }


        Rule r = new Rule();
        this.TEMP.addAll(GT);//gan TG =GT

        filter();//lay tap SAT tuong ung voi buoc SAT=loc(RULE,TG) trong giai thuat
        Iterator<Rule> rulesSat = this.SAT.iterator();

        while (this.RULES.size() > 0 && this.SAT.size() > 0) {
            r = (Rule) this.SAT.toArray()[0];// lay mot  luat trong SAT
            this.TEMP.add(r.getRight());
            this.RULES.remove(r);//tuong ung voi buoc RULE=RULE\{r} trong giai thuat
            filter();
        }
    }


    private boolean checkCaseAnd(Set<Clause> clauses) {
        int dem = 0;
        for (Clause clause : clauses) {
            for (Clause item : this.TEMP) {
                if (clause.equals(item)) {
                    dem++;
                }
            }
        }
        return dem == clauses.size();
    }

    public void filter() {
        SAT.clear();
        for (Rule rule : RULES) {
            //if(TG.get(i).equals(RULE.get(j).vt))
            if (this.checkCaseAnd(rule.getLeft())) {
                SAT.add(rule);
            }
        }
        this.conslution.addAll(SAT);
    }

    private void removeRule(Rule rule) {
        this.RULES.remove(rule);
    }

    public Set<Clause> getClauses(String fileName) {
        ClauseUtil clauseUtil = new ClauseUtil();

        if (this.clauses == null) {
            return this.clauses = clauseUtil.getClausesFromFile(fileName);
        }
        return clauses;
    }

    public void setClauses(Set<Clause> clauses) {
        this.clauses = clauses;
    }

    void showResult(Set<Clause> clauses) {
        ClauseUtil clauseUtil = new ClauseUtil();
        if (this.conslution.size() == 0) {
            System.out.println("Không có kết luận nào !!!!! ");
            return;
        }

        for (Rule rule : this.conslution) {
            String str = null;
            for (Clause clause : rule.getLeft()) {
                clause.setContent(clauseUtil.getClauseContentByClauseName(clauses, clause.getName()));
            }
            str = clauseUtil.getClauseContentByClauseName(clauses, rule.getRight().getName());
            rule.getRight().setContent(str);
        }

        Set<Rule> rules = new LinkedHashSet<Rule>();

        boolean temp;
        for (Rule rule : this.conslution) {
            temp = false;
            for (Clause clause : this.GT) {
                if (rule.getRight().equals(clause)) {
                    temp = true;
                }
            }
            if (!temp) {
                rules.add(rule);
            }
        }

        for (Rule rule : rules) {
            System.out.println("\t" + rule);
            System.out.println();
        }
    }

    public Set<Clause> removeClause(Clause clause) {

        for (Iterator<Clause> iterator = this.clauses.iterator(); iterator.hasNext(); ) {
            Clause claus = iterator.next();
            if (clause.getName().equals(claus.getName())) {
                iterator.remove();
            }
        }
        return this.clauses;
    }

    public void removeRule(Clause clause) {
        for (Iterator<Rule> iterator = this.RULES.iterator(); iterator.hasNext(); ) {
            Rule rule = iterator.next();
            for (Clause cls : rule.getLeft())
                if (clause.getName().equals(cls.getName())) {
                    iterator.remove();
                }
        }
    }

    public int getLengthClauseConslution() {
        Set<Clause> hashSets = new HashSet<Clause>();
        for (Rule rule : this.conslution) {
            hashSets.addAll(rule.getLeft());
            hashSets.add(rule.getRight());
        }
        return hashSets.size();
    }

    public Set<Clause> getClauseFromConslution() {
        Set<Clause> hashSets = new HashSet<Clause>();
        for (Rule rule : this.conslution) {
            hashSets.addAll(rule.getLeft());
            hashSets.add(rule.getRight());
        }
        return hashSets;
    }

    public boolean compare(Set<Clause> cl1, Set<Clause> cls2) {
        if (cl1.size() != cls2.size()) {
            return false;
        }
        int dem = 0;
        for (Clause t1 : cl1) {
            for (Clause t2 : cls2) {
                if (t1.equals(t2)) {
                    dem++;
                }
            }
        }

        return dem == cl1.size();
    }
}

