import { Component } from '@angular/core';
import { UserserviceService } from '../shared/userservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-quizcategories',
  templateUrl: './quizcategories.component.html',
  styleUrls: ['./quizcategories.component.css']
})
export class QuizcategoriesComponent {

  categories: any[] = [];

  ngOnInit() {
    this.loadCategories();
  }

  constructor(private uservice: UserserviceService,private router : Router) { }

  loadCategories() {
    this.uservice.getcategories().subscribe({
      next: (data) => {
        console.log('Fetched categories:', data); // Log here
        this.categories = data;
      },
      error: (err) => console.error('Failed to fetch categories:', err)
    });
  }

  startQuiz(categoryId : Number)
  {
    this.router.navigate(['/user/quiz/start',categoryId]);
  }
}
