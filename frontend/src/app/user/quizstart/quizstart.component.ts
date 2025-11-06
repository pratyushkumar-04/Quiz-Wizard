import { Component } from '@angular/core';
import { UserserviceService } from '../shared/userservice.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-quizstart',
  templateUrl: './quizstart.component.html',
  styleUrls: ['./quizstart.component.css']
})
export class QuizstartComponent {
  categoryId!: number;
  questions: any[] = [];
  currQuestionIndx = 0;
  selectedanswers: string[] = [];

  constructor(private uservice: UserserviceService, private route: ActivatedRoute) {}

  ngOnInit() {
    this.categoryId = +this.route.snapshot.paramMap.get('categoryId')!;
    this.loadquestion(this.categoryId);
  }

  loadquestion(catId: number) {
    this.uservice.getquestions(catId).subscribe({
      next: (data) => {
        console.log("Fetched questions:", data);
        this.questions = data;
        this.selectedanswers = new Array(this.questions.length).fill(null);
      },
      error: (err) => console.error('Failed to load questions', err)
    });
  }
selectanswer(option: string) {
    this.selectedanswers[this.currQuestionIndx] = option;
}
  nextQuestion() {
    if (this.currQuestionIndx < this.questions.length - 1) {
      this.currQuestionIndx++;
    }
  }

  prevQuestion() {
    if (this.currQuestionIndx > 0) {
      this.currQuestionIndx--;
    }
  }

  allQuestionsAnswered(): boolean {
    return this.selectedanswers.length === this.questions.length &&
      this.selectedanswers.every(ans => ans !== null);
  }

  submitQuiz() {
    console.log("Submitting quiz with answers:", this.selectedanswers);

    let score = 0;
    let correct = 0;

    const questionResults = this.questions.map((q, idx) => {
      const selected = this.selectedanswers[idx];
      const isCorrect = selected === q.correctAnswer;

      if (isCorrect) {
        score++;
        correct++;
      }

      return {
        questionId: q.id,
        selectedAnswer: selected || '', // Handle null answers
        correctAnswer: q.correctAnswer,
        isCorrect: isCorrect
      };
    });

    const result = {
      categoryId: this.categoryId, // Use categoryId instead of categoryName
      score: score,
      questions: this.questions.length,
      correctAnswers: correct, // Match DTO field name
      questionResults: questionResults
    };

    this.uservice.submitResult(result).subscribe({
      next: (res) => {
        console.log("Result saved:", res);
        alert(`Result saved! You scored ${score} out of ${this.questions.length}`);
      },
      error: (err) => {
        console.error("Failed to save result:", err);
        alert(`Error saving result: ${err.status} - ${err.message}`);
      }
    });
  }
}