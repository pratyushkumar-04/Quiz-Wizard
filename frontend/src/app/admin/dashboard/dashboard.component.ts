import { Component, OnInit } from '@angular/core';
import { AdminService } from '../admin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  categoryCount: number = 0;
  userCount: number = 0;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.loadCounts();
  }

  loadCounts() {
    this.adminService.getAllCategories().subscribe({
      next: (categories) => {
        this.categoryCount = categories.length;
      },
      error: (err) => {
        console.error('Error fetching categories', err);
      }
    });

    this.adminService.getallusers().subscribe({
      next: (users) => {
        this.userCount = users.length;
      },
      error: (err) => {
        console.error('Error fetching users', err);
      }
    });
  }
}
