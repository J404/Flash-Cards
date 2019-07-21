import java.util.*;

public class Cards {
    private String a, category;
    private List<String> set;
    private ArrayList<QuestionSet> questions =  new ArrayList<>();
    private int minimumDate;

    public Cards(ArrayList<String> set, int mD, String category) {
        this.set = set;
        this.minimumDate = mD;

        if (!category.equals("all"))
            this.category = category.toLowerCase();
        else
            this.category = "";

        formatSet();
    }

    public boolean generateQuestion() {
        Random r = new Random();
        QuestionSet q;
        q = this.questions.get(r.nextInt(this.set.size()));
        if (Main.mode == Main.MODE.DATES) {
            if (Integer.parseInt(q.answer) >= this.minimumDate && q.category.contains(this.category)) {
                this.a = q.answer;
            } else {
                while (true) {
                    q = this.questions.get(r.nextInt(this.set.size()));
                    if (Integer.parseInt(q.answer) >= this.minimumDate && q.category.contains(this.category)) {
                        this.a = q.answer;
                        break;
                    }
                }
            }
        } else {
            if (q.category.contains(this.category)) {
                this.a = q.answer;
            } else {
                while (true) {
                    q = this.questions.get(r.nextInt(this.set.size()));
                    if (q.category.contains(this.category)) {
                        this.a = q.answer;
                        break;
                    }
                }
            }
        }
        System.out.println(q.clue);
        String uA = Main.INPT.next();
        if (this.a.toLowerCase().equals(uA.toLowerCase())) {
            return true;
        } else if (uA.toLowerCase().equals("exit")) {
            Main.state = Main.STATE.STOP;
            return false;
        } else {
            return false;

        }
    }

    private void formatSet() {
        for (String aSet : this.set) {
            String[] parts = aSet.split(",");
            String clue = parts[0];
            String ans = parts[1];
            String cat = parts[2];
            this.questions.add(new QuestionSet(ans, clue, cat));
        }
    }

    public String getA() {
        return this.a;
    }
}
