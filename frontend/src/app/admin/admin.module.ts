import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MessageComponent } from './message/message.component';
import { CategoyFormComponent } from './categoy-form/categoy-form.component';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { QuestionFormComponent } from './question-form/question-form.component';
import { UserListComponent } from './user-list/user-list.component';
import { ViewQuestionComponent } from './view-question/view-question.component';
import { ViewCategoriesComponent } from './view-categories/view-categories.component';
import { LayoutComponent } from './layout/layout.component';
import { SharedModule } from './shared/shared.module';
import { TopPerformersComponent } from './top-performers/top-performers.component';


@NgModule({
  declarations: [
    DashboardComponent,
    MessageComponent,
    CategoyFormComponent,
    QuestionFormComponent,
    UserListComponent,
    ViewQuestionComponent,
    ViewCategoriesComponent,
    LayoutComponent,
    TopPerformersComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FormsModule,
    SharedModule
  ]
})
export class AdminModule { }
