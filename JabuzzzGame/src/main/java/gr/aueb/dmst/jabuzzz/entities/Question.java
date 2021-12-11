package main.java.gr.aueb.dmst.jabuzzz.entities;

import java.util.Random;

public class Question {
	public String question;
	private String correctAnswer;
	private String[] answer = new String[5];

	public Question(String question, String[] answer) {
		this.question = question;
		this.answer = answer;
		correctAnswer = answer[0];
	}

	private void shuffleArray() {
		int index;
		String temp;
		Random random = new Random();
		for (int i = answer.length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = answer[index];
			answer[index] = answer[i];
			answer[i] = temp;
		}
		for (int i = 0; i < answer.length; i++) {
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
