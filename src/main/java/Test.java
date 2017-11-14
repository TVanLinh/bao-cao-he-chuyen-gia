import java.util.*;

/**
 * Created by linhtran on 14/11/17.
 */
public class Test {
    public static void main(String[] args) {
        Exercise exercise = new Exercise();

        Exercise temp = new Exercise();

        String fileClause = "mende.txt";
        String fileRule = "luat2.txt";

        Set<Clause> temClause = temp.getClauses(fileClause);

        Set<Clause> clauses = exercise.getClauses(fileClause);

        Set<Clause> listClause = new HashSet<Clause>();


        Scanner scanner = new Scanner(System.in);
        String answer;
        do {
            if (exercise.GT.size() != 0 && (exercise.compare(exercise.getClauseFromConslution(), clauses)
                    || exercise.compare(exercise.getClauseFromConslution(), temClause))) {
                return;
            }
            Clause clause = (Clause) clauses.toArray()[0];

            System.out.print("Có " + clause.getContent() + " ???? : ");
            answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("y")) {
                listClause.add(new Clause(clause.getName()));
                //----------------------------------------------
                exercise.GT = new HashSet<Clause>(listClause);

                exercise.thuatToanSuyDienTien(fileRule);

                exercise.showResult(temClause);
            } else {
                exercise.removeRule(clause);
            }
            clauses = exercise.removeClause(clause);

        } while (clauses.size() > 0);


    }


//    public static void main(String[] args) {
//        Exercise exercise = new Exercise();
//        Set<Clause> clauses = exercise.getClauses(fileClause);
//
//        Set<Clause> listClause = new HashSet<Clause>();
//
//        String answer = "y";
//        Scanner scanner = new Scanner(System.in);
//
//        do {
//
//            for (Clause clause : clauses) {
//                System.out.println(clause.getName() + " : " + clause.getContent());
//            }
//
////            System.out.print("Nhập dấu hiêu: (nhập các chữ cái a,b,c,d ...): ");
//            String clauseEnter;
//            clauseEnter = scanner.nextLine();
//            listClause.add(new Clause(clauseEnter.trim()));
//            //----------------------------------------------
//            exercise.GT = new HashSet<Clause>(listClause);
//
//            exercise.thuatToanSuyDienTien();
//
//            exercise.showResult();
//
//            //--------------------------------------------------
//
//            System.out.print("\nCó thêm dấu hiệu nào không (y/n): ");
//            answer = scanner.nextLine();
//
//        } while (answer.trim().equalsIgnoreCase("y"));
//
//
//    }


}
