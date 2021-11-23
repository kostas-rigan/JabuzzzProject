package gr.aueb.dmst.jabuzzz.entities;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class Question {
//Random r = new Random();
	private String question;
	private String correctAnswer;
	private String[] answer = new String[5];
	public Question(String question , String[] answer) {
		this.question = question;
		this.answer[0] = answer[0];
		this.answer[1] = answer[1];
		this.answer[2] = answer[2];
		this.answer[3] = answer[3];
		this.answer[4] = answer[4];
		correctAnswer = answer[0];
	}
	public void showAnswer(int numberOfAnswers) {
		String[] duplicateAnswers = new String[numberOfAnswers];
		for(int i=0;i<duplicateAnswers.length;i++) {
			duplicateAnswers[i]=answer[i];
		}
		List<String> intList = Arrays.asList(duplicateAnswers);
		Collections.shuffle(intList);
		System.out.println(Arrays.toString(duplicateAnswers));
	}
}
