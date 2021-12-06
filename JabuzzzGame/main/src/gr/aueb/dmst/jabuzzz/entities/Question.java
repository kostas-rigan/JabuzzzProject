package gr.aueb.dmst.jabuzzz.entities;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class Question {
	public String question;
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
	private void shuffleArray() {
	    int index;
	    String temp;
	    Random random = new Random();
	    for (int i = answer.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        temp = answer[index];
	        answer[index] = answer[i];
	        answer[i] = temp;
	    }
	    for (int i = 0 ; i < answer.length; i++)
	    {
	    	System.out.println(answer[i]);
	    }
	}
	public int findAnswer() {
		for (int i = 0; i < 5; i++) {
			if (this.answer[i] == correctAnswer) {
				return i;
			}
		}
		return -1;
	}
}
