import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { QuizcategoriesComponent } from './quizcategories/quizcategories.component';
import { QuizstartComponent } from './quizstart/quizstart.component';
import { LayoutComponent } from './layout/layout.component';
import { ResultComponent } from './result/result.component';
import { AnalysisComponent } from './analysis/analysis.component';
import { leadingComment } from '@angular/compiler';
import { LeaderboardComponent } from './leaderboard/leaderboard.component';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { ChangePasswordComponent } from './change-password/change-password.component';

const routes: Routes = [
  {
    path: '', component: LayoutComponent, children: [

      { path: 'dashboard', component: DashboardComponent },
      { path: 'categories', component: QuizcategoriesComponent },
      { path: 'quiz/start/:categoryId', component: QuizstartComponent },
      { path: 'results', component: ResultComponent },
      {path :'analysis/:resultId',component:AnalysisComponent},
      {path : 'leaderboard',component:LeaderboardComponent},
      {path:'update',component:UpdateProfileComponent},
      {path:'changepassword',component:ChangePasswordComponent}
      
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
