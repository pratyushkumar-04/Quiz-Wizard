import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-view-categories',
  templateUrl: './view-categories.component.html',
  styleUrls: ['./view-categories.component.css']
})
export class ViewCategoriesComponent {
categories:any[]=[];
ngOnInit()
{
  this.categoryset()
}

constructor(private admservice:AdminService,private router :Router){}

  categoryset():any
  {
    this.admservice.getAllCategories().subscribe({
      next : (res)=> {
        this.categories=res;
      }
    });
  }
  navigateToAddQuestion(categoryId: number) {
    this.router.navigate(['/admin/createquestion'], {
      queryParams: { categoryId }
    });
  }
  viewQuestions(categoryId: number) {
  this.router.navigate(['/admin/questions', categoryId]);
}
}
