import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MessageComponent } from './message/message.component';
import { CategoyFormComponent } from './categoy-form/categoy-form.component';
import { QuestionFormComponent } from './question-form/question-form.component';
import { UserListComponent } from './user-list/user-list.component';
import { ViewQuestionComponent } from './view-question/view-question.component';
import { ViewCategoriesComponent } from './view-categories/view-categories.component';
import { LayoutComponent } from './layout/layout.component';
import { TopPerformersComponent } from './top-performers/top-performers.component';

const routes: Routes = [
  {
    path:'', component:LayoutComponent,
    children:[
  { path: 'dashboard', component:DashboardComponent },
  { path: 'message', component: MessageComponent },
  { path: 'createcategory', component: CategoyFormComponent },
  {path:'createquestion',component:QuestionFormComponent},
  {path : 'questions/:categoryId', component:ViewQuestionComponent},
  {path : 'userlist',component:UserListComponent},
  {path :'categories',component:ViewCategoriesComponent},
  {path :'topperformers',component:TopPerformersComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { } 
