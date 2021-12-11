package main.java.gr.aueb.dmst.jabuzzz.entities;

import java.util.Random;

/**
 * Class Question takes an String Array with the question in the first place and
 * the answers to the following positions shuffles it and give back the question
 * and the possible answers.
 * 
 * @author Konstantinos_Terlakis
 * @version 1.0 11/12/2021
 */
public class Question {
	public String question;
	private static String[][] answer = new String[50][5];
	private static String[] correctAnswer = new String[50];
	private static String[] questions = new String[50];
	private static int numberOfQuestions = 0;

	/*
	 * Class constructor taking as parameter an Array of String specifying the
	 * question and the answers.
	 * 
	 * The String parameter has in the first place the question and the remaining
	 * places has the answers.Every time the first of the answers is the correct.
	 * 
	 * @param questionAndAnswers
	 */
	public Question(String[] questionAndAnswers) {
		questions[numberOfQuestions] = (questionAndAnswers[0]);
		numberOfQuestions++;
		/*
		 * initialization of the Array answer
		 */
		for (int i = 1; i < questionAndAnswers.length; i++) {
			Question.answer[numberOfQuestions - 1][i - 1] = questionAndAnswers[i];
		}
		/*
		 * initialization of the correctAnswer
		 */
		Question.correctAnswer[numberOfQuestions] = questionAndAnswers[1];
		shuffleAnswers();
	}

	/*
	 * shuffleArray shuffles the Array of the answers so the correct answer is not
	 * always in the first place. uses the Fisher–Yates shuffle algorithm
	 */
	private static void shuffleAnswers() {
		int index;
		String temp;
		Random random = new Random();
		for (int i = answer[numberOfQuestions - 1].length - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = answer[numberOfQuestions - 1][index];
			answer[numberOfQuestions - 1][index] = answer[numberOfQuestions - 1][i];
			answer[numberOfQuestions - 1][i] = temp;
		}
	}

	/*
	 * findAnswer is called after you shuffle the array so you can find in witch
	 * spot is the correct answer.
	 * 
	 * @return the position of the correct answer.
	 */
	public int findAnswer() {
		for (int i = 0; i < 5; i++) {
			if (Question.answer[numberOfQuestions - 1][i] == correctAnswer[numberOfQuestions]) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * shuffleArray shuffles the static Array in witch the questions is stored and
	 * that affects the array of the answers follows so the analogy doesn't lost.
	 * uses the Fisher–Yates shuffle algorithm
	 */
	public static void shuffleQuestion() {
		int index;
		String temp;
		String[] temp2 = new String[5];
		Random random = new Random();
		for (int i = numberOfQuestions - 1; i > 0; i--) {
			index = random.nextInt(i + 1);
			temp = questions[index];
			questions[index] = questions[i];
			questions[i] = temp;
			temp2 = answer[index];
			answer[index] = answer[i];
			answer[i] = temp2;
			temp = correctAnswer[index];
			correctAnswer[index] = correctAnswer[i];
			correctAnswer[i] = temp;
		}
	}

	/*
	 * getQuestions returns and removes the last question of the list.
	 * 
	 * @return r witch is the last question.
	 */
	public static String getQuestions(int questionIndex) {
		String r = questions[questionIndex];
		return r;
	}

	public static int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	/*
	 * addQuestion is used to add a new question with the potential answers
	 */
	public void addQuestion(String[] questionAndAnswers) {
		questions[numberOfQuestions] = (questionAndAnswers[0]);
		numberOfQuestions++;
		/*
		 * initialization of the Array answer
		 */
		for (int i = 1; i < questionAndAnswers.length; i++) {
			Question.answer[numberOfQuestions - 1][i - 1] = questionAndAnswers[i];
		}
		/*
		 * initialization of the correctAnswer
		 */
		correctAnswer[numberOfQuestions] = questionAndAnswers[1];
	}

	public static String getAnswer(int questionIndex, int answerIndex) {
		return answer[questionIndex][answerIndex];
	}

}
