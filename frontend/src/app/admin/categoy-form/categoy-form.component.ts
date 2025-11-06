import { Component } from '@angular/core';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-categoy-form',
  templateUrl: './categoy-form.component.html',
  styleUrls: ['./categoy-form.component.css']
})
export class CategoyFormComponent {

  category={
    name:'',
    description:''
  };
  constructor(private admservice:AdminService){}
  onSubmit()
  {
    this.admservice.createcategory(this.category).subscribe({
      next: (res) => {
        alert('Category created successfully!');
        this.category = { name: '', description: '' };
      },
      error: (err) => {
        console.error('Error creating category:', err);
        alert('Failed to create category');
      }
    });
  }
}
