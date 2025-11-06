import { Component } from '@angular/core';
import { AdminService } from '../admin.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent {

  users:any[]=[];

  constructor(private admservice:AdminService){}

  ngOnInit() :void
  {
    this.loadusers()
  }

  deleteuser(userId : number)
  {
    if (confirm('Are you sure you want to delete this user?')) {
    this.admservice.deleteuser(userId).subscribe({
      next: () => {
        this.users = this.users.filter(user => user.id !== userId);
        alert('User deleted successfully!');
        this.loadusers();
      },
      error: () => {
        alert('Failed to delete user.');
      }
    });
  }
}
loadusers()
{
  this.admservice.getallusers().subscribe({next: data=> this.users = data,
      error :err=>alert("Failes to load")
    });
}
}
