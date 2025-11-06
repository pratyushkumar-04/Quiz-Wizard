import { Component } from '@angular/core';
import { AdminService } from '../admin.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-question-form',
  templateUrl: './question-form.component.html',
  styleUrls: ['./question-form.component.css']
})
export class QuestionFormComponent {
   question = {
    content: '',
    optionA: '',
    optionB: '',
    optionC: '',
    optionD: '',
    correctAnswer: '',
    categoryId: 0
  };

  categories: any[] = [];

  constructor(private adminService: AdminService,  private route: ActivatedRoute) {}

ngOnInit(): void {
  const passedCategoryId = this.route.snapshot.queryParamMap.get('categoryId');
  if (passedCategoryId && !isNaN(+passedCategoryId)) {
    this.question.categoryId = parseInt(passedCategoryId, 10);
  }

  this.adminService.getAllCategories().subscribe({
    next: (data: any) => {
      this.categories = data;
    },
    error: () => {
      alert('Failed to load categories');
    }
  });
}

  addQuestion(): void {
    this.adminService.createQuestion(this.question).subscribe({
      next: () => {
        alert('Question added successfully!');
        this.question = {
          content: '',
          optionA: '',
          optionB: '',
          optionC: '',
          optionD: '',
          correctAnswer: '',
          categoryId: 0
        };
      },
      error: () => {
        alert('Failed to add question');
      }
    });
  }
}
